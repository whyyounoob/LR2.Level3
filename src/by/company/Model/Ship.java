package by.company.Model;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Ship implements Observer {

    private String nameShip;
    private String target;
    private int weight;
    private String cargo;


    @Override
    public void update(Observable o, Object arg) {

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

    public int getWeight() {
        return weight;
    }
}

