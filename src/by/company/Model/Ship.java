package by.company.Model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Ship extends Observable implements Serializable {

    private String nameShip;
    private String target;
    private long weight;
    private String cargo;

    public Ship(String nameShip, String target, long weight, String cargo){
        this.nameShip = nameShip;
        this.target = target;
        this.weight = weight;
        this.cargo = cargo;
    }

    public String getNameShip() {
        return nameShip;
    }

    public String getCargo() {
        return cargo;
    }

    public String getTarget() {
        return target;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}

