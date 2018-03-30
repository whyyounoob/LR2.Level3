package by.company.Model;

public class Stock {

    private int weight;
    private String typeOfCargo;

    public Stock(String typeOfCargo){
        this.typeOfCargo = typeOfCargo;
        weight = 0;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight += weight;
    }

    public String getTypeOfCargo() {
        return typeOfCargo;
    }
}
