package model;

import logic.SimulationManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server implements Runnable {
    private int serverID;
    private final BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int currentServiceTime;
    private boolean status;

    public Server(int serverID) {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
        status = false;
        currentServiceTime = 0;
        this.serverID = serverID;
    }


    @Override
    public void run() {
        while (true) {
            try {
                if(!tasks.isEmpty()){
                    Task task = tasks.element();
                    task.setServedTime(SimulationManager.getCurrentTime());
                    currentServiceTime = task.getServiceTime() + SimulationManager.getCurrentTime();
                    sleep(task.getServiceTime() * 1000L);
                    tasks.remove();
                    if(tasks.isEmpty()){
                        status = false;
                    }
                    currentServiceTime = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getTasksServicePeriod() {
        int tasksServicePeriod = 0;
        for (Task task : tasks) {
            tasksServicePeriod += task.getServiceTime();
        }

        return tasksServicePeriod;
    }

    public void addTask(Task task) {
        tasks.add(task);
        if (!status)
            status = true;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getCurrentServiceTime() {
        return currentServiceTime;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public boolean isStatus() {
        return status;
    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public String toString() {
        StringBuilder returnValue = new StringBuilder();
        returnValue.append("Queue ").append(serverID).append(": ");
        if (isStatus()) {
            for (Task task : tasks) {
                returnValue.append("(").append(task.getID()).append(", ").append(task.getArrivalTime())
                        .append(", ").append(task.getServiceTime()).append("); ");
            }
        } else {
            returnValue.append("closed");
        }

        return returnValue.toString();
    }
}
