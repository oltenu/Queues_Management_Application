package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SetupPanel extends JPanel {
    private JPanel headerPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;

    private JButton startButton;
    private JTextField numberOfClientsTextField;
    private JTextField numberOfQueuesTextFields;
    private JTextField simulationTimeTextField;
    private JTextField minimumArrivalTimeTextField;
    private JTextField maximumArrivalTimeTextField;
    private JTextField minimumServiceTimeTextField;
    private JTextField maximumServiceTimeTextField;

    public SetupPanel() {
        setLayout(new BorderLayout());

        createHeaderPanel();
        createRightPanel();
        createLeftPanel();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.add(leftPanel);
        contentPanel.add(rightPanel);

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel);
    }

    private void createHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JLabel simulatorSettingLabel = new JLabel("Simulator Setting");
        simulatorSettingLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

        headerPanel.add(simulatorSettingLabel);
    }

    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout());

        startButton = new JButton("START");
        startButton.setFocusable(false);

        rightPanel.add(startButton);
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(7, 2, 0, 65));

        numberOfClientsTextField = new JTextField();
        numberOfQueuesTextFields = new JTextField();
        simulationTimeTextField = new JTextField();
        minimumArrivalTimeTextField = new JTextField();
        maximumArrivalTimeTextField = new JTextField();
        minimumServiceTimeTextField = new JTextField();
        maximumServiceTimeTextField = new JTextField();

        JLabel numberOfClientsLabel = new JLabel("Number of Clients:");
        JLabel numberOfQueuesLabel = new JLabel("Number of Queues:");
        JLabel simulationTimeLabel = new JLabel("Simulation TIme:");
        JLabel minimumArrivalTimeLabel = new JLabel("Minimum Arrival Time:");
        JLabel maximumArrivalTimeLabel = new JLabel("Maximum Arrival Time:");
        JLabel minimumServiceTimeLabel = new JLabel("Minimum Service Time:");
        JLabel maximumServiceTimeLabel = new JLabel("Maximum Service Time:");

        leftPanel.add(numberOfClientsLabel);
        leftPanel.add(numberOfClientsTextField);
        leftPanel.add(numberOfQueuesLabel);
        leftPanel.add(numberOfQueuesTextFields);
        leftPanel.add(simulationTimeLabel);
        leftPanel.add(simulationTimeTextField);
        leftPanel.add(minimumArrivalTimeLabel);
        leftPanel.add(minimumArrivalTimeTextField);
        leftPanel.add(maximumArrivalTimeLabel);
        leftPanel.add(maximumArrivalTimeTextField);
        leftPanel.add(minimumServiceTimeLabel);
        leftPanel.add(minimumServiceTimeTextField);
        leftPanel.add(maximumServiceTimeLabel);
        leftPanel.add(maximumServiceTimeTextField);
    }

    //getters
    public String getNumberOfClients() {
        return numberOfClientsTextField.getText();
    }

    public String getNumberOfQueues() {
        return numberOfQueuesTextFields.getText();
    }

    public String getSimulationTime() {
        return simulationTimeTextField.getText();
    }

    public String getMinimumArrivalTime() {
        return minimumArrivalTimeTextField.getText();
    }

    public String getMaximumArrivalTime() {
        return maximumArrivalTimeTextField.getText();
    }

    public String getMinimumServiceTime() {
        return minimumServiceTimeTextField.getText();
    }

    public String getMaximumServiceTime() {
        return maximumServiceTimeTextField.getText();
    }

    //listeners
    public void addStartButtonListener(ActionListener start) {
        startButton.addActionListener(start);
    }
}
