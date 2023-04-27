package model;

public class Task {
    private final int ID;
    private final int arrivalTime;
    private final int serviceTime;
    private int servedTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getServedTime() {
        return servedTime;
    }

    public void setServedTime(int servedTime) {
        this.servedTime = servedTime;
    }
}
