package logic;

import model.Server;
import model.Task;

import java.util.List;

public class TimeStrategy implements Strategy {
    public void addTask(List<Server> servers, Task task) {
        Server serverToUse = servers.get(0);
        for (Server server : servers) {
            if (server.getWaitingPeriod().intValue() < serverToUse.getWaitingPeriod().intValue())
                serverToUse = server;
        }

        serverToUse.addTask(task);
    }
}
