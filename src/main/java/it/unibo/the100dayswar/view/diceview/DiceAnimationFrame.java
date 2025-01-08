package it.unibo.the100dayswar.view.diceview;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;

/**
 * A utility class that shows a dice animation in a JFrame.
 */
public final class DiceAnimationFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    private static final String RESOURCES = "dice/";
    private static final String DICE_GIF = RESOURCES + "dice.gif";

    private static final int DURATION_MS = 3000;

    /**
     * Costruttore privato per impedire l'istanziamento.
     */
    private DiceAnimationFrame() {
    }

    /**
     * Crea e configura un JFrame con la GIF del dado.
     * Il frame non è ridimensionabile, non chiudibile manualmente
     * e si chiude automaticamente dopo {@link #DURATION_MS} millisecondi.
     *
     * @return il JFrame creato (ancora non visibile).
     */
    public static JFrame createDiceFrame() {
        // Crea il frame
        final JFrame frame = new JFrame("Rolling your dice...");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        final ImageIcon diceIcon = (ImageIcon) IconLoader.loadIcon(DICE_GIF);
        final JLabel diceLabel = new JLabel(diceIcon);

        frame.add(diceLabel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);

        final Timer closeTimer = new Timer(DURATION_MS, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                frame.dispose();
            }
        });
        closeTimer.setRepeats(false);
        closeTimer.start();

        return frame;
    }

    /**
     * Crea, configura e mostra subito il frame con la GIF del dado.
     */
    public static void showDiceFrame() {
        final JFrame frame = createDiceFrame();
        frame.setVisible(true);
    }
}