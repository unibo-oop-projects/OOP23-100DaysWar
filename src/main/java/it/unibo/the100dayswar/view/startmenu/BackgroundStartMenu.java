package it.unibo.the100dayswar.view.startmenu;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;

public class BackgroundStartMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image backgroundImage;

    public BackgroundStartMenu(final String imagePath) {
        this.backgroundImage = ((ImageIcon) IconLoader.loadIcon(imagePath)).getImage();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
