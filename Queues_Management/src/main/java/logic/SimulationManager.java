package logic;

import model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable{
    private final Scheduler scheduler;
    private final List<Task> tasks;
    private int numberOfClients;
    private int simulationInterval;
    private int minimumArrivalTime;
    private int maximumArrivalTime;
    private int minimumServiceTime;
    private int maximumServiceTIme;
    private static int currentTime;


    public SimulationManager(){
        scheduler = new Scheduler();
        tasks = new ArrayList<>();
    }

    @Override
    public void run() {
        for(int i = 0; i < simulationInterval; i++){
            currentTime = i;
            scheduler.computeWaitingPeriod();
            for (Task task : tasks) {
                if(task.getArrivalTime() >= i)
                    scheduler.dispatchTask(task);
            }

            try{
                wait(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void generateRandomTasks(){
        Random random = new Random();
        for(int i = 0; i < numberOfClients; i++){
            int arrivalTime = random.nextInt((maximumArrivalTime + 1) - minimumArrivalTime) + minimumArrivalTime;
            int serviceTime = random.nextInt((maximumServiceTIme + 1) - minimumServiceTime) + minimumServiceTime;
            tasks.add(new Task(i + 1, arrivalTime, serviceTime));
        }
    }

    public double computeAverageWaitingTime(){
        double totalWaitingTime = 0;
        for (Task task : tasks) {
            totalWaitingTime += (task.getServedTime() - task.getArrivalTime());
        }

        return totalWaitingTime / tasks.size();
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public void setNumberOfQueues(int numberOfQueues) {
        scheduler.generateServers(numberOfQueues);
    }

    public void setSimulationInterval(int simulationInterval) {
        this.simulationInterval = simulationInterval;
    }

    public void setMinimumArrivalTime(int minimumArrivalTime) {
        this.minimumArrivalTime = minimumArrivalTime;
    }

    public void setMaximumArrivalTime(int maximumArrivalTime) {
        this.maximumArrivalTime = maximumArrivalTime;
    }

    public void setMinimumServiceTime(int minimumServiceTime) {
        this.minimumServiceTime = minimumServiceTime;
    }

    public void setMaximumServiceTIme(int maximumServiceTIme) {
        this.maximumServiceTIme = maximumServiceTIme;
    }
    public static int getCurrentTime() {
        return currentTime;
    }
}
