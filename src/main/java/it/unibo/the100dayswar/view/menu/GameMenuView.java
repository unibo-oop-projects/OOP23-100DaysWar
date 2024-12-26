package it.unibo.the100dayswar.view.menu;
import javax.swing.*;
import java.awt.*;

public class GameMenuView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JButton btnStart = new JButton(new );
    private final JButton btnResume = new JButton("RESUME");
    private final JButton btnRules = new JButton("RULES");
    private final JButton btnExit = new JButton("EXIT");

    public GameMenuView() {
        super("The 100 Days War - Start Screen");

        // GridLayout con 4 righe, 1 colonna, 
        // e spaziatura orizzontale/verticale di 10 pixel
        this.setLayout(new GridLayout(4, 3, 10, 10));

        // Imposta dimensioni iniziali della finestra
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Aggiungi i pulsanti: ognuno occuperà una "riga"
        this.add(createButtonPanel(btnStart));
        this.add(createButtonPanel(btnResume));
        this.add(createButtonPanel(btnRules));
        this.add(createButtonPanel(btnExit));

        // (Facoltativo) Se vuoi forzare la finestra a tutto schermo in automatico:
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Inizializza azioni dei pulsanti
        initActions();
    }

    /**
     * Crea un pannello con un pulsante centrato e dimensioni limitate.
     * 
     * @param button il pulsante da aggiungere al pannello
     * @return un pannello con il pulsante centrato
     */
    private JPanel createButtonPanel(JButton button) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centra il pulsante
        button.setPreferredSize(new Dimension(200, 50)); // Dimensione preferita del pulsante
        panel.add(button);
        return panel;
    }

    /**
     * Actions of the GUI.
     */
    private void initActions() {
        btnStart.addActionListener(e -> {
            // TODO
            // Avvia nuova partita...
        });

        btnResume.addActionListener(e -> {
            // TODO
            // Carica partita salvata...
        });

        btnRules.addActionListener(e -> {
            // TODO
            // Mostra popup con regole...
            JOptionPane.showMessageDialog(
                this,
                "Inserisci qui le regole del gioco.",
                "Game Rules",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        btnExit.addActionListener(e -> {
            // TODO
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameMenuView screen = new GameMenuView();
            screen.setVisible(true);
        });
    }
}
