package model;

import logic.SimulationManager;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server implements Runnable {
    private final int serverID;
    private final BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int finishTime;
    private boolean status;

    public Server(int serverID) {
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
        status = false;
        finishTime = 0;
        this.serverID = serverID;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!tasks.isEmpty()) {
                    Task task = tasks.element();
                    task.setServedTime(SimulationManager.getCurrentTime());
                    finishTime = task.getServiceTime() + SimulationManager.getCurrentTime();
                    computeWaitingPeriod();
                    sleep(task.getServiceTime() * 1000L);
                    tasks.remove();
                    if (tasks.isEmpty()) {
                        status = false;
                    }
                    finishTime = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void computeWaitingPeriod() {
        int tasksServicePeriod = 0;
        for (Task task : tasks) {
            tasksServicePeriod += task.getServiceTime();
        }
        waitingPeriod = new AtomicInteger((finishTime - SimulationManager.getCurrentTime()) + tasksServicePeriod);
    }

    public void addTask(Task task) {
        tasks.add(task);
        if (!status)
            status = true;
        computeWaitingPeriod();
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public boolean isStatus() {
        return status;
    }

    public int getQueueSize() {
        return tasks.size();
    }

    public int getServiceTime() {
        int serviceTime = 0;
        for (Task task : tasks) {
            serviceTime += task.getServiceTime();
        }

        return serviceTime;
    }

    public String toString() {
        StringBuilder returnValue = new StringBuilder();
        returnValue.append("Queue ").append(serverID).append(": ");
        if (isStatus()) {
            Iterator<Task> iterator = tasks.iterator();
            Task t = iterator.next();
            returnValue.append("(").append(t.getID()).append(", ").append(t.getArrivalTime())
                    .append(", ").append(t.getServiceTime() - (SimulationManager.getCurrentTime() - t.getServedTime()) + 1).append("); ");

            while(iterator.hasNext()) {
                Task task = iterator.next();
                returnValue.append("(").append(task.getID()).append(", ").append(task.getArrivalTime())
                        .append(", ").append(task.getServiceTime()).append("); ");
            }
        } else {
            returnValue.append("closed");
        }

        return returnValue.toString();
    }
}
