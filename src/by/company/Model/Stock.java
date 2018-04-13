package by.company.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This is model for stock.
 *
 * @author Maxim Borodin
 */

public class Stock implements Serializable {

    /**
     * Max capacity of the stock.
     */

    private long maxCapacity;

    /**
     * Free capacity of the stock.
     */

    private long leftCapacity;

    /**
     * Type of cargo.
     */

    private String typeOfCargo;

    /**
     * Condition of every goods.
     */

    private HashMap<String, Long> mapOfGoods;

    /**
     * Standart constructor for class.
     *
     * @param typeOfCargo type of cargo in new stock
     * @param capacity    capacity of the new stock
     */

    public Stock(final String typeOfCargo, final long capacity) {
        this.typeOfCargo = typeOfCargo;
        this.maxCapacity = capacity;
        leftCapacity = capacity;
        createMap();
    }

    /**
     * This method creating start condition of stock.
     */

    public void createMap() {
        mapOfGoods = new HashMap<String, Long>();
        switch (typeOfCargo) {
            case "Minerals":
                for (String str : Constants.MINERALS_GOODS) {
                    mapOfGoods.put(str, 0L);
                }
                break;
            case "Food":
                for (String str : Constants.FOOD_GOODS) {
                    mapOfGoods.put(str, 0L);
                }
                break;
            case "Clothes":
                for (String str : Constants.CLOTHES_GOODS) {
                    mapOfGoods.put(str, 0L);
                }
                break;
            case "Illegal goods":
                for (String str : Constants.ILLEGAL_GOODS) {
                    mapOfGoods.put(str, 0L);
                }
                break;
        }
    }

    /**
     * Method for changing condition of goods.
     *
     * @param target target of ship
     * @param goods  goods in ship
     * @param value  weight of goods
     */

    public void setValueMap(final String target, final String goods,
                            final long value) {

        long pastValue = mapOfGoods.get(goods);

        System.out.println("Before: ");
        Set<Map.Entry<String, Long>> set = mapOfGoods.entrySet();
        for (Map.Entry<String, Long> me : set) {
            System.out.println(me.getKey() + ": " + me.getValue());
        }
        System.out.println("Capacity: " + maxCapacity);
        System.out.println("Left capacity: " + leftCapacity);


        switch (target) {
            case "Loading":
                mapOfGoods.put(goods, pastValue - value);
                leftCapacity += value;
                break;
            case "Unloading":
                mapOfGoods.put(goods, pastValue + value);
                leftCapacity -= value;
                break;
        }


        System.out.println("After: ");
        Set<Map.Entry<String, Long>> set1 = mapOfGoods.entrySet();
        for (Map.Entry<String, Long> me : set1) {
            System.out.println(me.getKey() + ": " + me.getValue());
        }

        System.out.println("Capacity: " + maxCapacity);
        System.out.println("Left capacity: " + leftCapacity);
    }

    /**
     * Check for goods in stock.
     *
     * @param goods  goods of ship
     * @param target target of ship
     * @param weight weight of goods
     * @return true if the ship can moor
     */

    public boolean checkGood(final String target, final String goods,
                             final long weight) {
        switch (target) {
            case "Loading":
                long value = mapOfGoods.get(goods);
                if (value < weight) {
                    return false;
                } else {
                    return true;
                }
            case "Unloading":
                if (weight > leftCapacity) {
                    return false;
                } else {
                    return true;
                }
        }
        return false;
    }

    /**
     * Getter for condition of goods in the stock.
     *
     * @return condition of goods in the stock
     */

    public String getStockStatus() {
        String str = "";
        Set<Map.Entry<String, Long>> set = mapOfGoods.entrySet();
        for (Map.Entry<String, Long> me : set) {
            str += me.getKey() + ": " + me.getValue() + ",  ";
        }
        str += "Left capacity: " + leftCapacity;
        return str;
    }

    /**
     * Getter for type of cargo in the stock.
     *
     * @return type of cargo in the stock
     */

    public String getTypeOfCargo() {
        return typeOfCargo;
    }
}
