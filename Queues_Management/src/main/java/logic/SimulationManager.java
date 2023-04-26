package logic;

import model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.Thread.sleep;

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
        Logger logger = Logger.getLogger("Logger");
        FileHandler fh;

        try{
            fh = new FileHandler("D:\\Darius\\Facultate\\Anul_II\\Semestrul_II\\" +
                    "Tehnici_de_Programare_Fundamentale\\Laborator\\Assingment_02\\Logger");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            for(int i = 0; i < simulationInterval; i++){
                currentTime = i;
                Iterator<Task> iterator = tasks.iterator();
                while(iterator.hasNext()){
                    Task task = iterator.next();
                    if(task.getArrivalTime() <= i){
                        scheduler.dispatchTask(task);
                        scheduler.computeWaitingPeriod();
                        iterator.remove();
                    }
                }
                logger.info("\nTime: " + i + "\n" + scheduler.generateLog());
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            System.exit(0);
        }
    }

    public void generateRandomTasks(){
        Random random = new Random();
        for(int i = 0; i < numberOfClients; i++){
            int arrivalTime = random.nextInt((maximumArrivalTime + 1) - minimumArrivalTime) + minimumArrivalTime;
            int serviceTime = random.nextInt((maximumServiceTIme + 1) - minimumServiceTime) + minimumServiceTime;
            tasks.add(new Task(i + 1, arrivalTime, serviceTime));
        }
        for (Task task : tasks) {
            System.out.println("Task: " + task.getID() + "; AT: " + task.getArrivalTime() + "; ST: " + task.getServiceTime());
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
