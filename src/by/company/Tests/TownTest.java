package by.company.Tests;

import by.company.Controller.Town;
import by.company.Model.Ship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TownTest {

    @Before
    public void setUp() {
        Town.getInstance().addPort("Port 1", "Minerals", 1000000000);
        Town.getInstance().addPort("Port 2", "Food", 234500000);
        Town.getInstance().addPier("Port 1", "Pier", 1000);

    }

    @Test
    public void addPort() {
        String namePort = "Port 3";
        String typeOfCargo = "Minerals";
        long capacity = 100000000;

        int expected = 1;
        int actual = Town.getInstance().addPort(namePort, typeOfCargo, capacity);
        Assert.assertEquals("This name contains yet", expected, actual);
    }

    @Test
    public void addShip() {
        Ship ship = new Ship("Ship", "Unloading", 100000, "Coal");

        int expected = 0;
        int actual = Town.getInstance().addShip(ship);
        Assert.assertEquals("No suitable port", expected, actual);
    }
}