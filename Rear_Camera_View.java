import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rear_Camera_View extends JPanel implements Dimensions {
    ImageIcon defaultImage = new ImageIcon();
    private ImageIcon imageIcon = defaultImage;
    JButton launchButton;
    static int view = 0;

    Rear_Camera_View() {
        setBounds(mainPanelGap / 2, mainPanelGap / 2, rearCameraWidth, rearCameraHeight);
        setBackground(null);
        setBorder(null);
        setVisible(false);
        enlargeRearCamera();

        int buttonWidth = 200, buttonHeight = 45;
        launchButton = new JButton("Launch Camera");
        launchButton.setBounds((getWidth() - buttonWidth) / 2, SwingConstants.NORTH, buttonWidth, buttonHeight);
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Menu.rearView.isSelected()) {
                    launchButton.setVisible(false);
                    MainPanel.rearWebCam.startRearCamera();
                    repaint();
                }
            }
        });
        add(launchButton);
    }

    private void view0Components() {
        setBounds(mainPanelGap / 2, mainPanelGap / 2, rearCameraWidth, rearCameraHeight);
        Radar_View.width = radarWidth;
        Radar_View.height = radarHeight;

        MainPanel.radarView.setBounds(mainPanelWidth - radarWidth - (mainPanelGap / 2), mainPanelHeight - radarHeight - (mainPanelGap / 2), radarWidth, radarHeight);
        Rear_Camera_View.imageWidth = rearCameraWidth;
        Rear_Camera_View.imageHeight = rearCameraHeight;
    }

    private void enlargeRearCamera() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1 && Rear_Camera_View.view == 1) {
                    view0Components();
                    view = 0;
                } else if (e.getButton() == MouseEvent.BUTTON3 && MainPanel.isRearCameraOpened()) {
                    MainPanel.rearWebCam.stopRearCamera();
                    try {
                        RearWebCam.thread.join();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    launchButton.setVisible(true);
                    repaint();
                }
            }
        });
    }

    public void getImage(ImageIcon image) {
        imageIcon = image;
    }

    private ImageIcon setImage() {
        return imageIcon;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 24));
        g.drawString("Rear Camera View", (getWidth()-205)/2, (getHeight()-15)/2);
//        drawRearCameraImage(g);
        repaint();
    }

    static int imageWidth = rearCameraWidth;
    static int imageHeight = rearCameraHeight;

    private void drawRearCameraImage(Graphics g) {
        g.drawImage(setImage().getImage(), 0, 0, imageWidth, imageHeight, null);
    }
}
