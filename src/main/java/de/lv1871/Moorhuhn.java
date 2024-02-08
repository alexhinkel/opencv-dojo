package de.lv1871;

import de.lv1871.util.OpenCVUtils;
import de.lv1871.util.OpenCVWindow;
import de.lv1871.util.ResourceUtils;
import de.lv1871.util.SeleniumUtils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;

public class Moorhuhn {

    /**
     * !! Funktioniert nur außerhalb der Citrix-Umgebung !!
     */
    public static void main(String[] args) {
        OpenCVUtils.initOpenCV();
        WebDriver driver = SeleniumUtils.initChromeDriver();

        // Navigate to the Moorhuhn
        driver.get("https://html5.gamedistribution.com/8198902ff8ae4fd7a29208013eb9d072/");

        final String model = "model/moorhuhn.xml";
        final String modelPath = ResourceUtils.getAbsoluteResourcePath(model, Moorhuhn.class);
        CascadeClassifier cc = new CascadeClassifier(modelPath);

        AtomicBoolean autoClick = new AtomicBoolean(false);
        Actions actions = new Actions(driver);
        int ammo = 10;

        OpenCVWindow window = new OpenCVWindow("EyeDetection");
        window.onWindowClosed((WindowLEvent) -> driver.close());
        window.onKeyPressed((KeyEvent e) -> {
            if ("e".equalsIgnoreCase(KeyEvent.getKeyText(e.getKeyCode()))) {
                driver.close();
                System.exit(0);
            } else if ("s".equalsIgnoreCase(KeyEvent.getKeyText(e.getKeyCode()))) {
                autoClick.set(!autoClick.get());
            }
        });

        while (true) {
            byte[] scrFile = SeleniumUtils.getScreenshot(driver, OutputType.BYTES);
            Mat screen = OpenCVUtils.loadImage(scrFile, Imgcodecs.IMREAD_UNCHANGED);

            MatOfRect detectedObjects = new MatOfRect();
            cc.detectMultiScale(screen, detectedObjects,
                    1.10,
                    55,
                    0,
                    new Size(25, 25),
                    new Size(100, 100));

            for (Rect rect : detectedObjects.toArray()) {
                if (autoClick.get()) {
                    actions.moveToLocation(rect.x + rect.width / 2, rect.y + rect.height / 2);
                    actions.click().perform();
                    ammo = ammo - 1;
                    if (ammo == 0) {
                        actions.sendKeys(" ");
                        ammo = 10;
                    }
                }
                Imgproc.rectangle(
                        screen,
                        new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 0, 255),
                        3
                );
            }

            window.update(screen);
        }
    }
}
