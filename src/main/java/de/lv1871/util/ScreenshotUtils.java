package de.lv1871.util;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public final class ScreenshotUtils {

    private ScreenshotUtils() {
        // hide constructor
    }

    public static Mat getScreenshotAsMat() {
        try {
            BufferedImage image = getScreenshotAsBufferedImage();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            byteArrayOutputStream.flush();
            return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage getScreenshotAsBufferedImage() {
        try {
            Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            return new Robot().createScreenCapture(screenSize);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getScreenshotAsJPG(final String filePath) {
        try {
            File outputfile = new File(filePath);
            ImageIO.write(getScreenshotAsBufferedImage(), "jpg", outputfile);
            return outputfile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}