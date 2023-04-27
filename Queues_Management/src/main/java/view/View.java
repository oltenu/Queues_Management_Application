package view;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    public final SetupPanel setupPanel;
    public final SimulationPanel simulationPanel;
    private final JPanel contentPanel;

    private final CardLayout cardLayout;

    public View() {
        setSize(1000, 700);
        setTitle("Queues Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();

        contentPanel = new JPanel();
        contentPanel.setLayout(cardLayout);
        setupPanel = new SetupPanel();
        simulationPanel = new SimulationPanel();

        contentPanel.add(setupPanel, "1");
        contentPanel.add(simulationPanel, "2");

        add(contentPanel);
    }

    public void setText(String text) {
        simulationPanel.setText(text);
    }

    public void showSetupPanel() {
        cardLayout.show(contentPanel, "1");
    }

    public void showSimulationPanel() {
        cardLayout.show(contentPanel, "2");
    }
}
