package de.lv1871;

import de.lv1871.util.OpenCVUtils;
import de.lv1871.util.ResourceUtils;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import java.awt.event.KeyEvent;

public class EyeDetection {

    public static void main(String[] args) {
        OpenCVUtils.initOpenCV();

        // Initialize the eye detection
        // More models to try -> resources/model
        final String model = "model/haarcascade_eye.xml";
        final String modelPath = ResourceUtils.getAbsoluteResourcePath(model, EyeDetection.class);
        CascadeClassifier cc = new CascadeClassifier(modelPath);

        // Connect with camera
        VideoCapture vc = new VideoCapture(0);

        while (true) {
            // Get image from camera
            Mat videoSource = new Mat();
            vc.read(videoSource);

            // Detect all eyes in camera image
            MatOfRect detectedEyes = new MatOfRect();

            /**
             * Parameter:
             *
             * scaleFactor - The model has a fixed size that is defined during training.
             * Through the resizing of the input image, it is possible to scale up/down an object,
             * make it detectable by the algorithm.
             *
             * minNeighbors - Good explanation -> https://stackoverflow.com/questions/22249579/opencv-detectmultiscale-minneighbors-parameter
             *
             * flags - Objdetect.CASCADE_DO_CANNY_PRUNING, Objdetect.CASCADE_FIND_BIGGEST_OBJECT, Objdetect.CASCADE_SCALE_IMAGE, Objdetect.CASCADE_DO_ROUGH_SEARCH
             *
             * minSize/maxSize - Objects smaller/taller than that are ignored.
             */
            cc.detectMultiScale(videoSource, detectedEyes,
                    1.02,
                    6,
                    0,
                    new Size(10, 10),
                    new Size(35, 35));

            // Draw rectangles around eyes
            for (Rect detectedEye : detectedEyes.toArray()) {
                Imgproc.rectangle(
                        videoSource,
                        new Point(detectedEye.x, detectedEye.y),
                        new Point(detectedEye.x + detectedEye.width, detectedEye.y + detectedEye.height),
                        new Scalar(0, 0, 255),
                        2
                );
            }

            // Show resulte
            HighGui.imshow("EyeDetection", videoSource);

            // Exit on 'e' pressed
            int key = HighGui.waitKey(1);
            if ("e".equalsIgnoreCase(KeyEvent.getKeyText(key))) {
                HighGui.destroyAllWindows();
                System.exit(0);
            }
        }
    }
}
