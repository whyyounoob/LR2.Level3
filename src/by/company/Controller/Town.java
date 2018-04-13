package by.company.Controller;

import by.company.Model.Constants;
import by.company.Model.Port;
import by.company.Model.Ship;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for our program.
 *
 * @author Maxim Baradzin
 */

public final class Town {

    private static volatile Town instance;
    /**
     * Port`s list.
     */
    private static ArrayList<Port> portList = new ArrayList<Port>();

    private Town() {

    }

    public static Town getInstance() {
        if (instance == null)
            synchronized (Town.class) {
                if (instance == null) instance = new Town();
            }
        return instance;
    }

    /**
     * Add port from file and start every thread.
     *
     * @param list - list of ports
     */

    public void setPortList(ArrayList<Port> list) {
        this.portList.addAll(list);
        for (Port port : portList) {
            for (Port.Pier pier : port.getPierList()) {
                new Thread(pier).start();
            }
        }
    }

    /**
     * Getter for port`s list.
     */

    public static ArrayList<Port> getPortList() {
        return portList;
    }

    /**
     * Add port for our town.
     *
     * @param namePort    - new port`s name
     * @param typeOfCargo - new port`s type of cargo
     * @param capacity    - new port`s capacity of stock
     */

    public static int addPort(final String namePort, final String typeOfCargo, final long capacity) {
        int ret = 1;
        for (Port port : portList) {
            if (port.getNamePort().equals(namePort)) {
                ret = 0;
                break;
            }
        }
        if (ret == 1) {
            portList.add(new Port(namePort, typeOfCargo, capacity));
        }
        return ret;
    }

    /**
     * This method add ship to queue in some port.
     *
     * @param ship added ship
     * @return 0 - successful
     */

    public static int addShip(Ship ship) {

        int ret = 0;
        String typeOfCargo = "";
        switch (ship.getCargo()) {
            case "Gold":
            case "Rock":
            case "Coal":
                typeOfCargo = Constants.TYPEOFCARGO[0];
                break;
            case "Coca Cola":
            case "Pepsi":
            case "Sugar":
            case "Salt":
            case "Sereals":
                typeOfCargo = Constants.TYPEOFCARGO[1];
                break;
            case "People":
            case "Drugs":
                typeOfCargo = Constants.TYPEOFCARGO[2];
                break;
            case "Adidas":
            case "Nike":
            case "Puma":
            case "Dislabel":
            case "Stone Island":
                typeOfCargo = Constants.TYPEOFCARGO[3];
                break;
        }

        String portName = "";

        float check = Float.MAX_VALUE;

        for (Port port : portList) {
            if (port.getStock().getTypeOfCargo().equals(typeOfCargo)) {
                if (port.getPierList().size() == 0) {

                } else {
                    if (port.getStock().checkGood(ship.getTarget(), ship.getCargo(), ship.getWeight())) {
                        float temp = port.getShipList().size() / port.getPierList().size();
                        if (temp < check) {
                            check = temp;
                            portName = port.getNamePort();
                        }
                    }

                }
            }
        }
        if (portName.equals("")) {
            ret = 1;
        } else {
            for (Port port : portList) {
                if (port.getNamePort().equals(portName)) {
                    port.addShip(ship);
                }
            }
        }
        return ret;
    }

    /**
     * This method return queue of ship it some port
     *
     * @param portName port`s name
     * @return list of ship
     */

    public static List<Ship> getShipList(String portName) {
        for (Port port : portList) {
            if (port.getNamePort().equals(portName)) {
                return port.getShipList();
            }
        }
        return null;
    }

    /**
     * This method create a new pier in some port.
     *
     * @param portName port in which we create a pier
     * @param pierName pier`s name
     * @param speed    pier`s speed of loading/unloading
     */

    public static int addPier(String portName, String pierName, int speed) {

        for (Port port : portList) {
            if (port.getNamePort().equals(portName)) {
                port.addPier(pierName, speed);
                return 1;
            }
        }
        return 0;
    }

}
