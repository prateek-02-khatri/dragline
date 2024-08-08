import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Radar_View extends JPanel implements Dimensions {
    private int angle = 30;
    private int direction = 1;
    private final int detectedAngle = 95;
    int currentPosition = 30;
    int index = 0;
    float currentDistance;
    List<Integer> positions = new ArrayList<>();
    Radar_View() {
        setBounds(mainPanelWidth - radarWidth - (mainPanelGap / 2), mainPanelHeight - radarHeight - (mainPanelGap / 2), radarWidth, radarHeight);
        setVisible(false);
        enlargeRadar();

        Timer timer = new Timer(100, e -> {
            angle += 5 * direction; // Increment or decrement the angle based on direction
            if (angle >= 150 || angle <= 30) {
                direction *= -1; // Change direction at boundaries (0 and 180 degrees)
            }
//            int pos = RadarReadings.getPosition();
//            float dist = RadarReadings.getDistance();
//            positions.add(pos);
//            currentPosition = positions.get(index);
//            currentDistance = dist;
//            index++;
            repaint();
        });
        timer.start();
    }

    private void view1Components() {
        setBounds(mainPanelGap / 2, mainPanelGap / 2, extendRadarWidth, extendRadarHeight);
        width = extendRadarWidth;
        height = extendRadarHeight;
        MainPanel.rearCameraView.setBounds(mainPanelWidth - decreasedRearCameraWidth - (mainPanelGap / 2), mainPanelHeight - decreasedRearCameraHeight - (mainPanelGap / 2), decreasedRearCameraWidth, decreasedRearCameraHeight);
        Rear_Camera_View.imageWidth = decreasedRearCameraWidth;
        Rear_Camera_View.imageHeight = decreasedRearCameraHeight;
    }

    private void enlargeRadar() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1 && Rear_Camera_View.view == 0) {
                    view1Components();
                    Rear_Camera_View.view = 1;
                }
            }
        });
    }

    static int width = radarWidth;
    static int height = radarHeight;
    int centerX, centerY;
    int maxRadius;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(2));

        centerX = width / 2;
        centerY = height;

        maxRadius = (int) (0.9 * Math.min(width, height)) - 20; // Adjust the radius for the radar display within the frame

        // Draw the outermost arc
        g2d.drawArc(centerX - maxRadius, centerY - maxRadius, 2 * maxRadius, 2 * maxRadius, 0, 180);

        // Draw additional interior arcs
        for (int i = 1; i <= 5; i++) {
            int radius = (int) (maxRadius * i / 5.0);
            g2d.drawArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, 0, 180);
        }

        // Draw lines at specific angles and mark their endpoints
        drawAngleLine(g2d, centerX, centerY, maxRadius + 10, 120);
        drawAngleLine(g2d, centerX, centerY, maxRadius + 10, 150);
        drawAngleLine(g2d, centerX, centerY, maxRadius + 10, 90);
        drawAngleLine(g2d, centerX, centerY, maxRadius + 10, 60);
        drawAngleLine(g2d, centerX, centerY, maxRadius + 10, 30);

        // Draw line from 180 to 0 degrees
        int endX180 = (int) (centerX + maxRadius * Math.cos(Math.toRadians(180)));
        int endY180 = centerY - (int) (maxRadius * Math.sin(Math.toRadians(180)));
        int startX0 = (int) (centerX + maxRadius * Math.cos(Math.toRadians(0)));
        int startY0 = centerY - (int) (maxRadius * Math.sin(Math.toRadians(0)));
        g2d.drawLine(endX180 - 10, endY180, startX0 + 10, startY0);

        // Draw the scanning line
//        drawScanningLines(g2d);

        if (angle == detectedAngle) {
            g2d.setColor(Color.RED); // Set color to red for the detected angle
        } else {
            g2d.setColor(Color.GREEN); // Set default color to green
        }

        // Draw the scanning line
        int endX = (int) (centerX + maxRadius * Math.cos(Math.toRadians(angle)));
        int endY = centerY - (int) (maxRadius * Math.sin(Math.toRadians(angle)));
        g2d.drawLine(centerX, centerY, endX, endY);

    }
    private void drawScanningLines(Graphics2D g2d)
    {
        System.out.println(currentDistance);
        if (currentDistance < 20) {
            g2d.setColor(Color.RED);
        } else {
            g2d.setColor(Color.GREEN);
        }
        int endX = (int) (centerX + maxRadius * Math.cos(Math.toRadians(currentPosition)));
        int endY = centerY - (int) (maxRadius * Math.sin(Math.toRadians(currentPosition)));
        g2d.drawLine(centerX, centerY, endX, endY);
    }
    private void drawAngleLine(Graphics2D g2d, int centerX, int centerY, int radius, int angle) {
        int x = (int) (centerX + radius * Math.cos(Math.toRadians(angle)));
        int y = centerY - (int) (radius * Math.sin(Math.toRadians(angle)));
        g2d.drawLine(centerX, centerY, x, y);
    }
}
