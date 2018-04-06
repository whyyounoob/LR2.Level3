package by.company.Model;

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
     * @param port - adde port
     */

    public static void addPort(Port port) {
        portList.add(port);
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
                    float temp = port.getShipList().size() / port.getPierList().size();
                    if (temp < check) {
                        check = temp;
                        portName = port.getNamePort();
                    }
                }
            }
        }
        if (portName.equals("")) {
            ret = 1;
        } else {
            for (Port port : portList) {
                if (port.getNamePort().equals(portName)) {
                    if (port.getStock().checkGood(ship.getTarget(), ship.getCargo(), ship.getWeight())) {
                        port.addShip(ship);
                    } else ret = 1;
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

    public static void addPier(String portName, String pierName, int speed) {
        for (Port port : portList) {
            if (port.getNamePort().equals(portName)) {
                port.addPier(pierName, speed);
            }
        }
    }

}
