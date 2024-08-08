import org.opencv.videoio.Videoio;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface Dimensions
{
    Font font =  new Font("Times New Roman", Font.PLAIN, 20);
    // Frame
    int frameWidth = 1200, frameHeight = 700;
    // Menu
    int menuWidth = 250; int menuHeight = frameHeight;
    // Main Panel 950 X 700
    // After Gap = 920 X 670
    int mainPanelGap = 15;
    int mainPanelWidth = frameWidth-menuWidth-(mainPanelGap*2), mainPanelHeight = frameHeight - (mainPanelGap*2);

    // Image Dimensions
    int frontCameraWidth = 800, frontCameraHeight = 600;


    // 320 X 240
    float ratio = 1.0f;
    int rearCameraWidth = (int) (640*ratio), rearCameraHeight = (int) (480*ratio);

    int decreasedRearCameraWidth = (int) (640 * 0.78), decreasedRearCameraHeight = (int) (480 * 0.68);


    // Radar
    int radarWidth = 320, radarHeight = 175;
    int extendRadarWidth = 640, extendRadarHeight = 320;

    // 920 X 670
    int mainChartWidth = 512, mainChartHeight = 300;
    int subChartWidth = 400, subChartHeight = 300;
    int horizontalGap = 40, verticalGap = 23;
    int mainChartX = (mainPanelWidth-mainChartWidth)/2, mainChartY = verticalGap;
    int subChartX1 = horizontalGap, subChartX2 = horizontalGap+subChartWidth+horizontalGap;
    int subChartY = verticalGap+mainChartHeight+verticalGap;


    // Camera Index
    public static final int defaultCameraIndex = Videoio.CAP_DSHOW;
    public int rearWebCamIndex = 0;
    public int frontWebCamIndex = 1;
     /*
        (If external Web Cam is connected)
        0 for External Web Cam
        1 for Integrated Web Cam
         */
    // Borders
    Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE,1);
    Border blackBorder = BorderFactory.createLineBorder(Color.BLACK,1);
    Color backgroundColor = Color.WHITE;
}
