package it.unibo.the100dayswar.view.rules;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * class that rappresent the Rules Viewer that show the rules for the game.
 */
public class RulesViewer extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final String PATH_STRING = "rules.txt";

    /**
     * constructor for the RulesViewer.
     */
    public RulesViewer() {
        super("Rules Viewer");
    }
    /**
     * initialize the class.
     */
    public final void intitialize() {
        setUI();
        setPostInitialize();
    }
    /**
     * Final initialization step for frame configuration.
     */
    private void setPostInitialize() {
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * This method create the ui for the ruelsViewer frame.
     */
    private void setUI() {
        final JPanel panel;
        final JTextArea textArea;
        final JScrollPane scrollPane;
        this.setSize(WIDTH, HEIGHT);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        this.add(panel);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
            ClassLoader.getSystemResourceAsStream(PATH_STRING), StandardCharsets.UTF_8))) {
            final StringBuilder content = new StringBuilder();
            reader.lines().forEach(line -> content.append(line).append('\n'));
            textArea.setText(content.toString());
        } catch (IOException e) {
            textArea.setText("Error loading the file:\n" + e.getMessage());
        }
    }
}
