package it.unibo.the100dayswar.commons.utilities.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Utility class to load images using ImageIO.
 */
public final class ImageLoader {

    private static final Logger LOGGER = Logger.getLogger(ImageLoader.class.getName());

    /**
     * Private constructor to hide the implicit public one.
     */
    private ImageLoader() {
    }

    /**
     * Loads an image as a BufferedImage from the given path in the classpath.
     *
     * @param path the path to the image resource (e.g. "/startmenu/background.png")
     * @return a BufferedImage if successful, or null if not found or not readable
     */
    public static BufferedImage loadImage(final String path) {
        final URL url = ImageLoader.class.getResource(path);
        if (url == null) {
            LOGGER.log(Level.WARNING, "Resource not found: {0}", path);
            return null;
        }

        try {
            final BufferedImage img = ImageIO.read(url);
            if (img == null) {
                LOGGER.log(Level.WARNING, "Failed to read image data: {0}", path);
            } else {
                LOGGER.log(Level.INFO, "Image loaded: {0} -> {1}x{2}",
                        new Object[] { path, img.getWidth(), img.getHeight() });
            }
            return img;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException reading image: " + path, e);
            return null;
        }
    }
}

