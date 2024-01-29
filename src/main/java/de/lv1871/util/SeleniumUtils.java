package de.lv1871.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public final class SeleniumUtils {

    private final static String UBLOCK_EXTENSION = "selenium/extensions/ublock_1.55.0_0.crx";

    private SeleniumUtils() {
        // hide
    }

    /**
     * Initialize ChromeDriver with AdBlocker.
     *
     * @return ChromeDriver
     */
    public static ChromeDriver initChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        String extensionFile = ResourceUtils.getAbsoluteResourcePath(UBLOCK_EXTENSION, SeleniumUtils.class);
        options.addExtensions(new File(extensionFile));
        options.addArguments("--mute-audio");
        return new ChromeDriver(options);
    }

    /**
     * Returns a screenshot of the browser window.
     *
     * @param driver
     * @param outputType output type (OutputType.BYTES, OutputType.FILE,...)
     * @param <T>
     * @return
     */
    public static <T> T getScreenshot(final WebDriver driver, OutputType<T> outputType) {
        return ((TakesScreenshot) driver).getScreenshotAs(outputType);
    }
}
