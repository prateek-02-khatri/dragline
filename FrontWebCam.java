import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;

public class FrontWebCam implements Dimensions
{
    protected VideoCapture captureFrontView;
    protected Mat frontImage;
    FrontWebCam() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public void stopFrontCamera() {
        captureFrontView.release();
        frontImage.release();
    }
    private Mat resizeImage(Mat inputImage, double scaleX, double scaleY) {
        Mat resized = new Mat();
        Size newSize = new Size(inputImage.width() * scaleX, inputImage.height() * scaleY);
        Imgproc.resize(inputImage, resized, newSize, 0, 0, Imgproc.INTER_AREA);
        return resized;
    }
    public void startFrontCamera() {
        System.out.println("* Front Camera *");
        captureFrontView = new VideoCapture(frontWebCamIndex);

        frontImage = new Mat();
        final byte[][] imageData = new byte[1][1];
        final ImageIcon[] icon = new ImageIcon[1];

        Runnable cameraUpdater = () -> {
            while (true) {
                captureFrontView.read(frontImage);

                Mat resizedImage = resizeImage(frontImage, 1.25, 1.25);

                MatOfByte buf = new MatOfByte();
                Imgcodecs.imencode(".jpg", resizedImage, buf);

                imageData[0] = buf.toArray();

                icon[0] = new ImageIcon(imageData[0]);
                SwingUtilities.invokeLater(() -> MainPanel.frontCameraView.getImage(icon[0])); // Update the label in EDT

                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };

        new Thread(cameraUpdater).start(); // Start the camera feed updating thread
    }
}
