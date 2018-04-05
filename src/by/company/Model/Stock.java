package by.company.Model;

import java.io.Serializable;

public class Stock implements Serializable{

    private long maxCapacity;
    private long leftCapacity;
    private String typeOfCargo;

    public Stock(String typeOfCargo, long capacity){
        this.typeOfCargo = typeOfCargo;
        this.maxCapacity = capacity;
        leftCapacity = capacity;
    }

    public long getCapacity() {
        return maxCapacity;
    }

    public void setCapacity(long weight) {
        this.leftCapacity -= weight;
    }

    public String getTypeOfCargo() {
        return typeOfCargo;
    }

    public long getLeftCapacity() {
        return leftCapacity;
    }
}
