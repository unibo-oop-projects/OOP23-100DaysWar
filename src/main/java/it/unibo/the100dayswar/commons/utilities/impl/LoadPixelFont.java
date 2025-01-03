package it.unibo.the100dayswar.commons.utilities.impl;

import java.awt.Font;
import java.awt.FontFormatException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class to load a pixel-style font.
 * Falls back to Arial if the custom font cannot be loaded.
 * 
 * {@implNote} the pixel-style font is 'art2P-Regular' located
 * in the 'art2P-Regular.ttf' file.
 */
public final class LoadPixelFont {
    private static final float PIXEL_FONT_SIZE = 24f;
    private static final int ARIAL_SIZE = 24;
    private static Font pixelFont;

    /**
     * Private constructor to hide the implicit public one.
     */
    private LoadPixelFont() {
    }

    /**
     * Gets the pixel-style font from 'art2P-Regular.ttf'.
     * 
     * @return the pixel-style font, in case of an exception
     *         it fallbacks on 'Arial'
     */
    public static Font getFont() {
        if (pixelFont == null) {
            synchronized (LoadPixelFont.class) {
                try (InputStream fontStream = LoadPixelFont.class.getResourceAsStream("/fonts/PressStart2P-Regular.ttf")) {
                    if (fontStream == null) {
                        pixelFont = new Font("Arial", Font.BOLD, ARIAL_SIZE);
                    } else {
                        pixelFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(PIXEL_FONT_SIZE);
                    }
                } catch (FontFormatException | IOException e) {
                    pixelFont = new Font("Arial", Font.BOLD, ARIAL_SIZE); // Fallback
                }
            }
        }
        return pixelFont;
    }
}
