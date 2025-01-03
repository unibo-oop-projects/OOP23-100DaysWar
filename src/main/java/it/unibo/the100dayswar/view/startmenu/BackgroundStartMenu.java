package it.unibo.the100dayswar.view.startmenu;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;

/**
 * Class that implements the background of the starting menu.
 */
public class BackgroundStartMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private final String imagePath;

    /**
     * Constructor of the class.
     * It loads the Image using the IconLoader utility class.
     * 
     * @param imagePath
     */
    public BackgroundStartMenu(final String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(((ImageIcon) IconLoader.loadIcon(imagePath)).getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
