package it.unibo.the100dayswar.commons.utilities.impl;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 * Utility class to load a pixel-style font.
 * Falls back to Arial if the custom font cannot be loaded.
 */
public final class PixelFont {
    /**
     * Static Font field to load the font only the first time.
     */
    private static Font pixelFont;

    /**
     * Private constructor to hide the implicit public one.
     */
    private PixelFont() {
    }

    /**
     * Loads and returns the pixel-style font.
     * 
     * @return the pixel-style font if successfully loaded, or Arial as a fallback
     */
    public static Font getFont() {
        try {
            if(pixelFont == null) {
                pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P-Regular.ttf"))
                                            .deriveFont(24f);
            }
        } catch (FontFormatException | IOException e) {
            pixelFont = new Font("Arial", Font.BOLD, 24);
        }

        return pixelFont;
    }
}
