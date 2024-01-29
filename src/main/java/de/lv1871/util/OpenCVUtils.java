package de.lv1871.util;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public final class OpenCVUtils {

    private OpenCVUtils() {
        // hide Constructor
    }

    /**
     * Initialize OpenCV and load the OpenCV Native Library.
     * <p>
     * Source:
     * https://opencv-java-tutorials.readthedocs.io/en/latest/02-first-java-application-with-opencv.html#create-a-simple-application
     */
    public static void initOpenCV() {
        OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * Load the given image as a Mat object.
     *
     * @param sourceImagePath the source image to load
     * @param filter          Imgcodecs load image filter (e.g. Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE)
     * @return image as matrix representation
     */
    public static Mat loadImage(final String sourceImagePath, final int filter) {
        final Imgcodecs imageCodecs = new Imgcodecs();
        return imageCodecs.imread(sourceImagePath, filter);
    }

    /**
     * Load the given image as a Mat object.
     *
     * @param sourceImage the source image to load
     * @param filter      Imgcodecs load image filter (e.g. Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE)
     * @return image as matrix representation
     */
    public static Mat loadImage(final byte[] sourceImage, final int filter) {
        return Imgcodecs.imdecode(new MatOfByte(sourceImage), filter);
    }

    public static Mat loadImageFromResources(final String sourceResourcesImagePath, final Class<?> clazz, final int filter) {
        String sourceImagePath = ResourceUtils.getAbsoluteResourcePath(sourceResourcesImagePath, clazz);
        return loadImage(sourceImagePath, filter);
    }

    /**
     * Save the given Mat object as image file.
     *
     * @param imageMatrix     image as matrix representation
     * @param targetImagePath the target image to save
     */
    public static void saveImage(final Mat imageMatrix, final String targetImagePath) {
        final Imgcodecs imageCodecs = new Imgcodecs();
        imageCodecs.imwrite(targetImagePath, imageMatrix);
    }
}
