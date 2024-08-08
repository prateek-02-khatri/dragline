import javax.swing.*;
import java.awt.*;

// Camera Ports 1 & 2
public class Main extends JFrame implements Dimensions {
    static JFrame f;
    static Menu menu;
    static MainPanel mainPanel;

    Main() {
        f = new JFrame();
        f.setSize(frameWidth, frameHeight);
        f.setLocationRelativeTo(null);
        f.setUndecorated(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);
        int code = 255;
        f.getContentPane().setBackground(new Color(code, code, code));
        initComponents();

        f.setVisible(true);
    }

    private void initComponents() {
        mainPanel = new MainPanel();
        f.add(mainPanel);

        menu = new Menu();
        f.add(menu);
    }

    public static void main(String[] args) {
//        connect();
        run();
//        getRadarData();
    }

    public static void run() {
        try {
            SwingUtilities.invokeLater(Main::new);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void connect() {
        SQL_Connection.createSQLConnection();
    }

    private static void getRadarData() {
        Runnable dataCollection = () -> {
            Sensor_Data.radarData();
            RadarReadings.getData();
        };
        new Thread(dataCollection).start();
    }
}
