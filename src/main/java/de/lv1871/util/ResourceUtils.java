package de.lv1871.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ResourceUtils {

    private ResourceUtils() {
        // hide constructor
    }

    public static String getAbsoluteResourcePath(final String relativeResourcePath, final Class<?> clazz) {
        final URL url = clazz.getResource(relativeResourcePath);
        if (null == url) {
            throw new IllegalArgumentException("Resource " + relativeResourcePath + " not found");
        }

        String absoluteResourcePath = url.getPath();
        String urlString = url.toString();
        if (absoluteResourcePath.startsWith("/") && urlString.matches("file:/[a-zA-Z]:/.*")) {
            absoluteResourcePath = absoluteResourcePath.substring(1);
        }

        return absoluteResourcePath;
    }
}
