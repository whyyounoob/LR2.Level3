package by.company.Model;

import java.util.ArrayList;
import java.util.List;

public final class Town {

    private static volatile Town instance;
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

    public void setPortList(ArrayList<Port> list) {
        this.portList.addAll(list);
        for (Port port : portList) {
            for (Port.Pier pier : port.getPierList()) {
                new Thread(pier).start();
            }
        }
    }

    public static ArrayList<Port> getPortList() {
        return portList;
    }

    public static void addPort(Port port) {
        portList.add(port);
    }

    public static void addShip(String portName, Ship ship) {
        String typeOfCargo;
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
        float size = 0.0f;
        for (Port port : portList) {
            if (port.getNamePort().equals(portName)) {
                port.addShip(ship);
            }
        }
    }

    public static List<Ship> getShipList(String portName) {
        for (Port port : portList) {
            if (port.getNamePort().equals(portName)) {
                return port.getShipList();
            }
        }
        return null;
    }

    public static void addPier(String portName, String pierName, int speed) {
        for (Port port : portList) {
            if (port.getNamePort().equals(portName)) {
                port.addPier(pierName, speed);
            }
        }
    }

}
