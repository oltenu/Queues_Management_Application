package view;

import logic.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private final View view;
    private Thread thread;

    public Controller(View view) {
        this.view = view;

        view.setupPanel.addStartButtonListener(new StartButtonListener());
        view.simulationPanel.addHomeButtonListener(new HomeButtonListener());
    }

    class StartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SimulationManager simulationManager = new SimulationManager(view);
            simulationManager.setNumberOfClients(Integer.parseInt(view.setupPanel.getNumberOfClients()));
            simulationManager.setNumberOfQueues(Integer.parseInt(view.setupPanel.getNumberOfQueues()));
            simulationManager.setSimulationInterval(Integer.parseInt(view.setupPanel.getSimulationTime()));
            simulationManager.setMinimumArrivalTime(Integer.parseInt(view.setupPanel.getMinimumArrivalTime()));
            simulationManager.setMaximumArrivalTime(Integer.parseInt(view.setupPanel.getMaximumArrivalTime()));
            simulationManager.setMinimumServiceTime(Integer.parseInt(view.setupPanel.getMinimumServiceTime()));
            simulationManager.setMaximumServiceTIme(Integer.parseInt(view.setupPanel.getMaximumServiceTime()));
            simulationManager.generateRandomTasks();

            thread = new Thread(simulationManager);
            thread.start();
            view.showSimulationPanel();
        }
    }

    class HomeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            thread.stop();
            view.showSetupPanel();
        }
    }
}
