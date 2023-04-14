package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private boolean status;

    public Server(){
        tasks = new LinkedBlockingQueue<Task>();
        waitingPeriod = new AtomicInteger(0);
        status = false;
    }

    @Override
    public void run() {
        if(tasks.isEmpty()){
            status = false;
        }else{
            status = true;
            try{
                wait(tasks.take().getServiceTime());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
