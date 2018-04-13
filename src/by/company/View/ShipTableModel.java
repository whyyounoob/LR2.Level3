package by.company.View;

import by.company.Model.Ship;
import by.company.Controller.Town;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * This class create table model for table which contains
 * information about ship queue.
 */

public class ShipTableModel extends AbstractTableModel implements Observer {

    /**
     * Data for table.
     */

    private List<Ship> data = new ArrayList<Ship>();

    /**
     * Constructor getting ship list.
     *
     * @param portName name of port
     */

    public ShipTableModel(final String portName) {

        setData(Town.getInstance().getShipList(portName));
    }

    /**
     * Method creating data.
     *
     * @param data list of ship
     */

    public void setData(final List<Ship> data) {
        this.data.addAll(data);
    }

    @Override
    public void update(final java.util.Observable o, final Object arg) {
        int index = data.indexOf(o);
        if (index != -1) {
            fireTableRowsUpdated(index, index);
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return GUIConstants.SHIP_TABLE_HEADERS.length;
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        Ship ship = (Ship) data.get(rowIndex);
        if (columnIndex == 0) return ship.getNameShip();
        else if (columnIndex == 1) return ship.getTarget();
        else if (columnIndex == 2) return ship.getCargo();
        else if (columnIndex == 3) return ship.getWeight();
        else return null;
    }

    @Override
    public Class getColumnClass(final int c) {
        return GUIConstants.SHIP_COLUMN_CLASSES[c];
    }

    @Override
    public String getColumnName(final int col) {
        return GUIConstants.SHIP_TABLE_HEADERS[col];
    }
}
