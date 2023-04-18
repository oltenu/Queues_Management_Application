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
        for(int i = 0; i < numberOfQueues; i++)
            servers.add(new Server());
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
