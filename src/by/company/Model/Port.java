package by.company.Model;

import java.util.*;


public class Port {

    private String namePort;
    private Stock stock;
    public List<Pier> pierList = new ArrayList<Pier>();
    public List<Ship> shipList = Collections.synchronizedList(new ArrayList<Ship>());

    public Port(String namePort, String typeOfCargo) {
        this.namePort = namePort;
        stock = new Stock(typeOfCargo);
        for(int i = 0; i<pierList.size();i++){
            new Thread(pierList.get(i)).start();
        }
    }

    public String getNamePort() {
        return namePort;
    }

    public Stock getStock() {
        return stock;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public void addPier(String namePier, int speed){
        pierList.add(new Pier(namePier, speed));
        new Thread(pierList.get(pierList.size()-1)).start();
    }


    public class Pier extends Observable implements Runnable{


        private String namePier;
        private String status;
        private int speed;
        private float progress;
        private Ship ship;

        public Pier(String namePier, int speed){
            this.namePier = namePier;
            this.speed = speed;
            status = "Waiting.";
            progress = 10.0f;
            ship = null;
        }

        public String getNamePier() {
            return namePier;
        }

        public String getStatus() {
            return status;
        }

        public int getSpeed(){
            return speed;
        }

        public Ship getShip() {
            return ship;
        }

        public float getProgress() {
            return progress;
        }

        @Override
        public void run() {

        }

    }

}
