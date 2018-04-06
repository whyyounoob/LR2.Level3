package by.company.Model;

import java.io.Serializable;
import java.util.HashMap;

public class Stock implements Serializable {

    private long maxCapacity;
    private long leftCapacity;
    private String typeOfCargo;
    private HashMap<String, Long> mapOfGoods;

    public Stock(String typeOfCargo, long capacity) {
        this.typeOfCargo = typeOfCargo;
        this.maxCapacity = capacity;
        leftCapacity = capacity;
        createMap();
    }

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

    public void setValueMap(String target, String goods, long value) {

        long pastValue = mapOfGoods.get(goods);

        switch (target) {
            case "Loading":
                mapOfGoods.put(goods, pastValue - value);
                break;
            case "Unloading":
                mapOfGoods.put(goods, pastValue + value);
                break;
        }
    }

    public boolean checkGood(String target, String goods, long weight) {
        switch (target) {
            case "Loading":
                long value = mapOfGoods.get(goods);
                if (value < weight) {
                    return false;
                } else {
                    return true;
                }
            case "Unloading":
                return true;
        }
        return false;
    }

    public long getCapacity() {
        return maxCapacity;
    }

    public void setCapacity(long weight) {
        this.leftCapacity -= weight;
    }

    public String getTypeOfCargo() {
        return typeOfCargo;
    }

    public long getLeftCapacity() {
        return leftCapacity;
    }
}
