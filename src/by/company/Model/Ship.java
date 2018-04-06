package by.company.Model;

import java.io.Serializable;
import java.util.Observable;

/**
 * This is model for ship.
 * Contains all information about ship.
 *
 * @author Maxim Baradzin
 */
public class Ship extends Observable implements Serializable {
    /**
     * Name of ship.
     */
    private String nameShip;
    /**
     * Target of ship.
     */
    private String target;
    /**
     * Weight of goods.
     */
    private long weight;
    /**
     * Name of goods.
     */
    private String cargo;

    /**
     * Standart constructor fo class.
     *
     * @param nameShip ship`s name
     * @param cargo    ship`s cargo
     * @param target   ship`s target
     * @param weight   weight o fgoods
     */

    public Ship(final String nameShip, final String target,
                final long weight, final String cargo) {
        this.nameShip = nameShip;
        this.target = target;
        this.weight = weight;
        this.cargo = cargo;
    }

    /**
     * Getter for ship`s name.
     *
     * @return string
     */

    public String getNameShip() {
        return nameShip;
    }

    /**
     * Getter for Ship`s cargo.
     *
     * @return string
     */

    public String getCargo() {
        return cargo;
    }

    /**
     * Getter for ship`s target.
     *
     * @return string
     */

    public String getTarget() {
        return target;
    }

    /**
     * Getter for weight of goods.
     *
     * @return long
     */

    public long getWeight() {
        return weight;
    }

    /**
     * Setter for weight of goods.
     *
     * @param weight weight of goods
     */

    public void setWeight(final long weight) {
        this.weight = weight;
    }
}

