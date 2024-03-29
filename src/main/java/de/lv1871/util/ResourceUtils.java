package de.lv1871.util;

import java.net.URL;

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
