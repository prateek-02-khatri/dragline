import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements Dimensions
{
    static Front_Camera_View frontCameraView;
    static Rear_Camera_View rearCameraView;
    static Graphical_Analysis_View graphicalAnalysisView;
    static Performance_Analysis_View performanceAnalysisView;
    static Profile_View profileView;
    static Radar_View radarView;
    static FrontWebCam frontWebCam = new FrontWebCam();
    static RearWebCam rearWebCam = new RearWebCam();
    MainPanel() {
        setBounds(menuWidth + mainPanelGap, mainPanelGap, mainPanelWidth, mainPanelHeight);
        setBorder(null);
        setBackground(null);
        setLayout(null);

        initComponent();
    }

    private void initComponent() {
        frontCameraView = new Front_Camera_View();
        add(frontCameraView);

        rearCameraView = new Rear_Camera_View();
        add(rearCameraView);

        radarView = new Radar_View();
        add(radarView);

        graphicalAnalysisView = new Graphical_Analysis_View();
        add(graphicalAnalysisView);

        performanceAnalysisView = new Performance_Analysis_View();
        add(performanceAnalysisView);

        profileView = new Profile_View();
        add(profileView);
    }
    protected static boolean isFrontCameraOpened() {
        return frontWebCam.captureFrontView.isOpened();
    }
    protected static boolean isRearCameraOpened() {
        return rearWebCam.rearFrontView.isOpened();
    }
}
