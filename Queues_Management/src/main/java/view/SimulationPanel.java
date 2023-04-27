package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationPanel extends JPanel {
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JPanel bottomPanel;
    private JTextArea textArea;
    private JButton homeButton;

    public SimulationPanel() {
        setLayout(new BorderLayout());

        createHeaderPanel();
        createContentPanel();
        createBottomPanel();
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JLabel simulatorSettingLabel = new JLabel("Simulating...");
        simulatorSettingLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        headerPanel.add(simulatorSettingLabel);
    }

    private void createContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        textArea = new JTextArea(20, 20);
        textArea.setEditable(false);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(textArea);
        contentPanel.add(scrollPane);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        homeButton = new JButton("HOME");

        bottomPanel.add(homeButton);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public void addHomeButtonListener(ActionListener start) {
        homeButton.addActionListener(start);
    }
}
