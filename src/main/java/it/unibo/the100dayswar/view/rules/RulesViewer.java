package it.unibo.the100dayswar.view.rules;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class RulesViewer extends JFrame{
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final String PATH_STRING = "rules.txt";
    private final JPanel panel;
    private final JTextArea textArea;
    private final JScrollPane scrollPane;

    public RulesViewer(){
        super("Rules Viewer");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        this.add(panel);
    }

    public void setString(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(PATH_STRING), StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            textArea.setText(content.toString());
        } catch (IOException e) {
            textArea.setText("Error loading the file:\n" + e.getMessage());
        }
    }
}
