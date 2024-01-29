package de.lv1871.util;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.UUID;

public class GenerateTrainingDataHelper {

    private static final String MODEL_NAME = "moorhuhn";
    private static final String POSITIVE_TRAINING_DATA_PATH = "opencv_trainer/" + MODEL_NAME + "/pos/";
    private static final String NEGATIVE_TRAINING_DATA_PATH = "opencv_trainer/" + MODEL_NAME + "/neg/";

    private static final boolean browserMode = true;

    public static void main(String[] args) {
        OpenCVUtils.initOpenCV();
        System.out.println("Press 'p' to create a positive screenshot with existing objects");
        System.out.println("Press 'n' to create a negative screenshot without objects");
        System.out.println("Press 'e' to exit");

        WebDriver driver = SeleniumUtils.initChromeDriver();
        if (browserMode) {
            // Navigate to url
            driver.get("https://www.1001spiele.de/aktion/moorhuhn-remake");
        }

        while (true) {
            Mat screenshot;
            if (browserMode) {
                byte[] scrFile = SeleniumUtils.getScreenshot(driver, OutputType.BYTES);
                screenshot = OpenCVUtils.loadImage(scrFile, Imgcodecs.IMREAD_UNCHANGED);
            } else {
                screenshot = ScreenshotUtils.getScreenshotAsMat();
            }

            Mat downSizedScreen = new Mat();
            Imgproc.cvtColor(screenshot, downSizedScreen, Imgproc.COLOR_BGRA2BGR);
            HighGui.imshow("EyeDetection", downSizedScreen);

            // Exit if 'e' pressed
            int key = HighGui.waitKey(10);
            if ("e".equalsIgnoreCase(KeyEvent.getKeyText(key))) {
                HighGui.destroyAllWindows();
                break;
            } else if ("p".equalsIgnoreCase(KeyEvent.getKeyText(key))) {
                OpenCVUtils.saveImage(screenshot, new File(POSITIVE_TRAINING_DATA_PATH + UUID.randomUUID() + ".jpg").getAbsolutePath());
            } else if ("n".equalsIgnoreCase(KeyEvent.getKeyText(key))) {
                OpenCVUtils.saveImage(screenshot, new File(NEGATIVE_TRAINING_DATA_PATH + UUID.randomUUID() + ".jpg").getAbsolutePath());
            }
        }
    }
}
