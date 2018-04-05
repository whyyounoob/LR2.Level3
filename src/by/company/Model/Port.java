package by.company.Model;

import by.company.View.MainWindow;
import by.company.View.PierTableModel;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.util.*;


public class Port implements Serializable {

    private String namePort;
    private Stock stock;
    private List<Pier> pierList = new ArrayList<Pier>();
    private List<Ship> shipList = Collections.synchronizedList(new ArrayList<Ship>());
    private PierTableModel tableModel = new PierTableModel();

    public Port(String namePort, String typeOfCargo, long capacity) {
        this.namePort = namePort;
        stock = new Stock(typeOfCargo, capacity);
        /*for(int i = 0; i<pierList.size();i++){
            new Thread(pierList.get(i)).start();
        }*/
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

    public void addPier(String namePier, int speed) {
        Pier pier = new Pier(namePier, speed);
        pierList.add(pier);
        tableModel.addRow(pier);
        new Thread(pierList.get(pierList.size() - 1)).start();
    }

    public class Pier extends Observable implements Runnable, Serializable {


        private String namePier;
        private String status;
        private int speed;
        private float progress;
        private Ship ship = null;

        private long startWeight;

        public Pier(String namePier, int speed) {
            this.namePier = namePier;
            this.speed = speed;
            status = "Waiting.";
            progress = 0.0f;
            ship = null;
        }

        public String getNamePier() {
            return namePier;
        }

        public String getStatus() {
            return status;
        }

        public int getSpeed() {
            return speed;
        }

        public Ship getShip() {
            return ship;
        }

        public float getProgress() {
            return progress;
        }

        public long getStartWeight(){
            return startWeight;
        }

        @Override
        public void run() {
            while (true) {
                if (ship != null) {
                    status = "Ship here.";
                    setChanged();
                    notifyObservers();
                    while (ship.getWeight() > 0) {

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        ship.setWeight(ship.getWeight() - speed);
                        progress = 100 - ((float) ship.getWeight() / startWeight) * 100;
                       // System.out.println(progress);
                        setChanged();
                        notifyObservers();
                    }
                    stock.setCapacity(startWeight);
                    MainWindow.resetPanel();

                    status = "Ship is ready to be shipped.";
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ship = null;
                    status = "Waiting for ship";
                    progress = 0.0f;
                    setChanged();
                    notifyObservers();
                }
                if (shipList.isEmpty()) {
                    synchronized (shipList) {
                        status = "Waiting for ship.";
                        setChanged();
                        notifyObservers();
                        try {
                            shipList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.ship = shipList.get(0);
                        shipList.remove(0);
                        status = "Ship here.";
                        startWeight = ship.getWeight();
                        setChanged();
                        notifyObservers();
                    }
                } else {
                    this.ship = shipList.get(0);
                    shipList.remove(0);
                    status = "Ship here.";
                    startWeight = ship.getWeight();
                    setChanged();
                    notifyObservers();
                }

            }

        }

    }

    public void addShip(Ship ship) {
        new AddShip(ship);
    }

    public PierTableModel getTableModel() {
        tableModel.removeAll();
        for (Pier pier : pierList) {
            tableModel.addRow(pier);
        }
        return tableModel;
    }


    public List<Pier> getPierList() {
        return pierList;
    }

    class AddShip extends Thread {

        private Ship ship;


        public AddShip(Ship ship) {
            this.ship = ship;
            this.start();
        }


        @Override
        public void run() {
            synchronized (shipList) {
                shipList.add(ship);
                shipList.notify();
            }
        }
    }
}
