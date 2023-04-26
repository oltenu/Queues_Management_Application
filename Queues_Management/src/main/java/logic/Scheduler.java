package logic;

import model.Server;
import model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler {
    private final List<Server> servers;
    private final Strategy strategy;

    public Scheduler(){
        servers = new ArrayList<>();
        strategy = new TimeStrategy();
    }

    public void generateServers(int numberOfQueues){
        for(int i = 0; i < numberOfQueues; i++) {
            servers.add(new Server(i + 1));
        }
        for (Server server : servers) {
            (new Thread(server)).start();
        }
    }

    public String generateLog(){
        StringBuilder logString = new StringBuilder();
        for (Server server : servers) {
            logString.append(server).append("\n");
        }

        return logString.toString();
    }

    public void computeWaitingPeriod(){
        for (Server server : servers) {
            server.setWaitingPeriod(new AtomicInteger((server.getCurrentServiceTime() - SimulationManager.getCurrentTime()) + server.getTasksServicePeriod()));
        }
    }

    public void dispatchTask(Task task){
        strategy.addTask(servers, task);
    }
}
