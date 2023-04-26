package logic;

import model.Server;
import model.Task;
import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<Server> servers;
    private final Strategy strategy;
    private int peekHour;
    private int maxLoad;

    public Scheduler(){
        servers = new ArrayList<>();
        strategy = new TimeStrategy();
        peekHour = 0;
        maxLoad = 0;
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
            server.computeWaitingPeriod();
        }
    }

    public boolean checkLoad(){
        int currentLoad = 0;
        for (Server server : servers) {
            currentLoad += server.getQueueSize();
        }

        if(currentLoad > maxLoad){
            maxLoad = currentLoad;
            peekHour = SimulationManager.getCurrentTime();
        }

        return currentLoad != 0;
    }

    public void dispatchTask(Task task){
        strategy.addTask(servers, task);
    }

    public int getPeekHour() {
        return peekHour;
    }

    public int getServiceTime(){
        int serviceTime = 0;
        for (Server server : servers) {
            serviceTime += server.getServiceTime();
        }

        return serviceTime;
    }
    
    public int getRemainingTasks(){
        int remainingTasks = 0;
        for (Server server : servers) {
            remainingTasks += server.getQueueSize();
        }

        return remainingTasks;
    }
}
