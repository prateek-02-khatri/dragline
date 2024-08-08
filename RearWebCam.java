import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;

public class RearWebCam implements Dimensions
{
    protected VideoCapture rearFrontView;
    private Mat rearImage;
    static float currentRatio = ratio;
    static Thread thread;
    RearWebCam() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public void stopRearCamera() {
        rearFrontView.release();
        rearImage.release();
    }
    private Mat resizeImage(Mat inputImage, double scaleX, double scaleY) {
        Mat resized = new Mat();
        Size newSize = new Size(inputImage.width() * scaleX, inputImage.height() * scaleY);
        Imgproc.resize(inputImage, resized, newSize, 0, 0, Imgproc.INTER_AREA);
        return resized;
    }
    public void startRearCamera() {
        System.out.println("* Rear Camera *");
        rearFrontView = new VideoCapture(defaultCameraIndex);

        rearImage = new Mat();
        final byte[][] imageData = new byte[1][1];
        final ImageIcon[] icon = new ImageIcon[1];

        Runnable cameraUpdater = () -> {
            while (true) {
                rearFrontView.read(rearImage);

                Mat resizedImage = resizeImage(rearImage, currentRatio, currentRatio);

                MatOfByte buf = new MatOfByte();
                Imgcodecs.imencode(".jpg", resizedImage, buf);

                imageData[0] = buf.toArray();

                icon[0] = new ImageIcon(imageData[0]);
                SwingUtilities.invokeLater(() -> MainPanel.rearCameraView.getImage(icon[0]));

                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };

        thread = new Thread(cameraUpdater);
        thread.start();
    }
}
