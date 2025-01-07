package it.unibo.the100dayswar.view.diceview;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.the100dayswar.commons.utilities.impl.IconLoader;

public class DiceAnimationFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public DiceAnimationFrame() {
        super("Rolling your dice...");

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        final ImageIcon diceIcon = (ImageIcon) IconLoader.loadIcon("dice/dice.gif");
        final JLabel diceLabel = new JLabel(diceIcon);

        this.add(diceLabel);

        this.setSize(300, 300);
        this.setLocationRelativeTo(null);

        final Timer closeTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        closeTimer.setRepeats(false);
        closeTimer.start();
    }
}
