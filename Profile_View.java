import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile_View extends JPanel implements Dimensions
{
    int size = 225;
    Image operatorImage = new ImageIcon("src/Icons/Profile Original.png").getImage();
    JLabel operatorName;
    JLabel userId, mobile, email, age;
    JLabel [] semicolon;
    JLabel userIdData, emailData, mobileData, ageData;
    JButton logout;
    int labelWidth = 200, labelHeight = 30, x = (labelWidth*2)+30, gap = 15;
    int x1, x2;
    Profile_View()
    {
        setBounds(0, 0, mainPanelWidth, mainPanelHeight);
        setBackground(null);
        setBorder(null);
        setVisible(false);
        setLayout(null);

        x1 = getX(x);
        x2 = x1+labelWidth+30;

        initComponents();

        logout = new JButton("Log Out");
        logout.setBounds(getX(labelWidth-25), getHeight()-130, labelWidth-25, 30);
        logout.setFont(new Font("Times New Roman", Font.BOLD, 21));
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RadarReadings.deleteRows();
                SQL_Connection.closeSQLConnection();
                Main.f.dispose();
                System.exit(0);
            }
        });
        add(logout);
    }
    private void initComponents()
    {
        operatorName = new JLabel("Operator Name");
        operatorName.setBounds(getX(200),35+size, 200, 30);
        operatorName.setBorder(null);
        operatorName.setForeground(Color.BLACK);
        operatorName.setBackground(null);
        operatorName.setFont(new Font("Times New Roman", Font.BOLD, 22));
        operatorName.setHorizontalAlignment(0);
        add(operatorName);

        userId = getLabels("User ID", x1,operatorName.getY()+operatorName.getHeight()+gap+5);
        mobile = getLabels("Mobile Number", x1, userId.getY()+userId.getHeight()+gap);
        email = getLabels("E-Mail", x1, mobile.getY()+mobile.getHeight()+gap);
        age = getLabels("Age", x1, email.getY()+email.getHeight()+gap);

        semicolon = new JLabel[4];
        for (int i=0; i<semicolon.length; i++)
        {
            int y = userId.getY() + i * labelHeight + i * gap;
            semicolon[i] = getLabels(":",0,0);
            semicolon[i].setBounds(x1+labelWidth, y,30, labelHeight);
            semicolon[i].setHorizontalAlignment(0);
        }

        userIdData = getLabels("x0x0x0x0x0x0x0x0", x2, userId.getY());
        mobileData = getLabels("0123456789", x2, mobile.getY());
        emailData = getLabels("operator@gmail.com", x2, email.getY());
        ageData = getLabels("00", x2, age.getY());

    }
    private JLabel getLabels(String text, int x, int y)
    {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, labelWidth, labelHeight);
        label.setForeground(Color.BLACK);
        label.setBackground(null);
        label.setBorder(null);
        label.setFont(font);
        add(label);

        return label;
    }
    private int getX(int w)
    {
        return (getWidth()-w)/2;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(operatorImage,(getWidth()-size)/2, 25, size, size, null);
    }
}
