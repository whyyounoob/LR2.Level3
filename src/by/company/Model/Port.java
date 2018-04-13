package by.company.Model;

import by.company.View.MainWindow;
import by.company.View.PierTableModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * This is model for port.
 *
 * @author Maxim Borodin
 */

public class Port implements Serializable {

    /**
     * Port`s name.
     */

    private String namePort;

    /**
     * Port`s stock.
     */

    private Stock stock;

    /**
     * List of pier.
     */

    private List<Pier> pierList = new ArrayList<Pier>();

    /**
     * Queue of ships.
     */

    private List<Ship> shipList = Collections
            .synchronizedList(new ArrayList<Ship>());

    /**
     * Port`s table model.
     */

    private PierTableModel tableModel = new PierTableModel();

    /**
     * Constructor for port.
     *
     * @param namePort    port`s name
     * @param capacity    stock`s capacity
     * @param typeOfCargo stock`s type of cargo
     */

    public Port(final String namePort, final String typeOfCargo,
                final long capacity) {
        this.namePort = namePort;
        stock = new Stock(typeOfCargo, capacity);

    }

    /**
     * Getter for port`s name.
     *
     * @return port`s name
     */

    public String getNamePort() {
        return namePort;
    }

    /**
     * Getter for stock.
     *
     * @return object of stock
     */

    public Stock getStock() {
        return stock;
    }

    /**
     * Getter for queue of ships.
     *
     * @return ships in queue
     */

    public List<Ship> getShipList() {
        return shipList;
    }

    /**
     * This method add pier in port.
     *
     * @param namePier pier`s name
     * @param speed    unloading/loading speed
     */

    public void addPier(final String namePier, final int speed) {
        Pier pier = new Pier(namePier, speed);
        pierList.add(pier);
        tableModel.addRow(pier);
        new Thread(pierList.get(pierList.size() - 1)).start();
    }

    /**
     * This is model for pier.
     */

    public class Pier extends Observable implements Runnable, Serializable {

        /**
         * Pier`s name.
         */

        private String namePier;

        /**
         * Pier`s status.
         */

        private String status;

        /**
         * Pier`s unloading/loading speed.
         */

        private int speed;

        /**
         * Unloading/loading progress.
         */

        private float progress;

        /**
         * Ship in pier.
         */

        private Ship ship = null;

        /**
         * Start weight of ship.
         */

        private long startWeight;

        /**
         * Constructor for pier.
         *
         * @param speed    unloading/loading speed
         * @param namePier pier`s name
         */

        public Pier(final String namePier, final int speed) {
            this.namePier = namePier;
            this.speed = speed;
            status = "Waiting.";
            progress = 0.0f;
            ship = null;
        }

        /**
         * Getter for pier`s name.
         *
         * @return pier`s name
         */

        public String getNamePier() {
            return namePier;
        }

        /**
         * Getter for pier`s status.
         *
         * @return pier`s status
         */

        public String getStatus() {
            return status;
        }

        /**
         * Getter for pier`s speed.
         *
         * @return pier`s speed
         */

        public int getSpeed() {
            return speed;
        }

        /**
         * Getter for pier`s ship.
         *
         * @return pier`s ship
         */

        public Ship getShip() {
            return ship;
        }

        /**
         * Getter for work progress.
         *
         * @return work progress(in percent)
         */

        public float getProgress() {
            return progress;
        }

        /**
         * Getter for start weight of ship.
         *
         * @return start weight of ship
         */

        public long getStartWeight() {
            return startWeight;
        }

        /**
         * This method start thread in which work pier.
         */

        @Override
        public void run() {
            while (true) {
                if (ship != null) {
                    status = ship.getTarget();
                    setChanged();
                    notifyObservers();
                    while (ship.getWeight() > 0) {

                        try {
                            Thread.sleep(Constants.WORK_DELAY);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        ship.setWeight(ship.getWeight() - speed);
                        progress = Constants.FULL_PROGRESS
                                - ((float) ship.getWeight()
                                / startWeight) * Constants.FULL_PROGRESS;
                        // System.out.println(progress);
                        setChanged();
                        notifyObservers();
                    }
                    stock.setValueMap(ship.getTarget(),
                            ship.getCargo(), startWeight);
                    MainWindow.setInfo(namePort);

                    status = "Ship is ready to be shipped.";
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(Constants.UNSHIP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ship = null;
                    status = "Waiting for ship.";
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

    /**
     * Method for adding ship.
     *
     * @param ship added ship
     */

    public void addShip(final Ship ship) {
        new AddShip(ship);
    }

    /**
     * Getter for table model of port.
     *
     * @return table model of this port
     */

    public PierTableModel getTableModel() {
        tableModel.removeAll();
        for (Pier pier : pierList) {
            tableModel.addRow(pier);
        }
        return tableModel;
    }

    /**
     * Getter for list of piers.
     *
     * @return list of piers
     */

    public List<Pier> getPierList() {
        return pierList;
    }

    /**
     * Class for adding ship in some port.
     */

    class AddShip extends Thread {

        /**
         * Added ship.
         */

        private Ship ship;

        /**
         * Standart constructor.
         *
         * @param ship added ship
         */

         AddShip(final Ship ship) {
            this.ship = ship;
            MainWindow.setInfo(namePort);
            this.start();
        }

        /**
         * Method for adding ship in queue and notify about it.
         */

        @Override
        public void run() {
            synchronized (shipList) {
                shipList.add(ship);
                shipList.notify();
            }
        }
    }
}
