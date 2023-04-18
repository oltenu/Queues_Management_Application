package model;

import logic.SimulationManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private final BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int currentServiceTime;
    private boolean status;

    public Server(){
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
        status = false;
        currentServiceTime = 0;
    }


    @Override
    public void run() {
        if(tasks.isEmpty()){
            status = false;
        }else{
            status = true;
            try{
                Task task = tasks.take();
                task.setServedTime(SimulationManager.getCurrentTime());
                currentServiceTime = task.getServiceTime() + SimulationManager.getCurrentTime();
                wait(task.getServiceTime());
                currentServiceTime = 0;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public int getTasksServicePeriod(){
        int tasksServicePeriod = 0;
        for (Task task : tasks) {
            tasksServicePeriod += task.getServiceTime();
        }

        return tasksServicePeriod;
    }

    public void addTask(Task task){
        tasks.add(task);
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
}
