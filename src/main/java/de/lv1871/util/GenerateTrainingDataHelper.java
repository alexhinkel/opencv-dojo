package de.lv1871.util;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class GenerateTrainingDataHelper {


    /// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///
    /// Set your own model name and URL             ///
    /// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///
    private static final String MODEL_NAME = "moorhuhn";
    private static final boolean BROWSER_MODE = false;
    private static final String URL = "https://www.1001spiele.de/aktion/moorhuhn-remake";


    private static final String POSITIVE_TRAINING_DATA_PATH = "opencv_trainer/" + MODEL_NAME + "/pos/";
    private static final String NEGATIVE_TRAINING_DATA_PATH = "opencv_trainer/" + MODEL_NAME + "/neg/";

    /**
     * Take screenshots of selenium browser window or desktop.
     * <p>
     * Press 'p' to create a positive screenshot with existing objects
     * Press 'n' to create a negative screenshot without objects
     * Press 'e' to exit
     */
    public static void main(String[] args) {
        OpenCVUtils.initOpenCV();

        WebDriver driver = null;
        if (BROWSER_MODE) {
            // Navigate to url
            driver = SeleniumUtils.initChromeDriver();
            driver.get(URL);
        }

        final AtomicReference<Mat> screenshot = new AtomicReference<>(new Mat());

        OpenCVWindow window = new OpenCVWindow("TrainingData");
        window.onWindowClosed((WindowLEvent) -> {
            if (BROWSER_MODE) {
                driver.close();
            }
        });
        window.onKeyPressed((KeyEvent e) -> {
            if ("e".equalsIgnoreCase(KeyEvent.getKeyText(e.getKeyCode()))) {
                if (BROWSER_MODE) {
                    driver.close();
                }
                System.exit(0);
            } else if ("p".equalsIgnoreCase(KeyEvent.getKeyText(e.getKeyCode()))) {
                OpenCVUtils.saveImage(screenshot.get(), new File(POSITIVE_TRAINING_DATA_PATH + UUID.randomUUID() + ".jpg").getAbsolutePath());
            } else if ("n".equalsIgnoreCase(KeyEvent.getKeyText(e.getKeyCode()))) {
                OpenCVUtils.saveImage(screenshot.get(), new File(NEGATIVE_TRAINING_DATA_PATH + UUID.randomUUID() + ".jpg").getAbsolutePath());
            }
        });


        while (true) {
            if (BROWSER_MODE) {
                byte[] scrFile = SeleniumUtils.getScreenshot(driver, OutputType.BYTES);
                screenshot.set(OpenCVUtils.loadImage(scrFile, Imgcodecs.IMREAD_UNCHANGED));
            } else {
                screenshot.set(ScreenshotUtils.getScreenshotAsMat());
            }

            window.update(screenshot.get());
        }
    }
}
