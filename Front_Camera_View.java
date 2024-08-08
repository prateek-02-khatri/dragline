import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Front_Camera_View extends JPanel implements Dimensions
{
    ImageIcon defaultImage = new ImageIcon("src/Icons/Default White Screen.jpg");
    private ImageIcon imageIcon = defaultImage;
    JButton launchButton;
    Front_Camera_View()
    {
        setBounds(0, 0, mainPanelWidth, mainPanelHeight);
        setBackground(null);
        setOpaque(true);
        setVisible(false);
        setBorder(blackBorder);

        int buttonWidth = 200, buttonHeight = 45;
        launchButton = new JButton("Launch Camera");
        launchButton.setBounds((getWidth() - buttonWidth) / 2, SwingConstants.NORTH, buttonWidth, buttonHeight);
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Menu.frontView.isSelected())
                {
                    launchButton.setVisible(false);
                    MainPanel.frontWebCam.startFrontCamera();
                    repaint();
                }
            }
        });
        add(launchButton);
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
        g.drawString("Front Camera View", (getWidth()-205)/2, (getHeight()-15)/2);
//        FontMetrics fontMetrics = g.getFontMetrics(new Font("Times New Roman", Font.BOLD, 24));
//        int w = fontMetrics.stringWidth("Front Camera View");
//        System.out.println(w + " " + fontMetrics.getHeight());
        repaint();
//        g.drawImage(setImage().getImage(),(getWidth()- frontCameraWidth)/2,(getHeight()- frontCameraHeight)/2, frontCameraWidth, frontCameraHeight,null);

    }
}
