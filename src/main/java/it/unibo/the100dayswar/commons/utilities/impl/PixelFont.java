package it.unibo.the100dayswar.commons.utilities.impl;

import java.awt.Font;
import java.awt.FontFormatException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class to load a pixel-style font.
 * Falls back to Arial if the custom font cannot be loaded.
 */
public final class PixelFont {
    /**
     * Static Font field to load the font only one time.
     */
    private static Font pixelFont;

    /**
     * Private constructor to hide the implicit public one.
     */
    private PixelFont() {
    }

    public static Font getFont() {
        try (InputStream fontStream = PixelFont.class.getResourceAsStream("/fonts/PressStart2P-Regular.ttf")) {
            if (fontStream == null) {
                throw new IOException("Font file not found");
            }
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(24f);
        } catch (FontFormatException | IOException e) {
            System.out.println(e);
            pixelFont = new Font("Arial", Font.BOLD, 24);
            System.out.println("font non caricato");
        }

        return pixelFont;
    }
}
