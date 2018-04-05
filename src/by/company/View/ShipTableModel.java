package by.company.View;

import by.company.Model.Ship;
import by.company.Model.Town;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class ShipTableModel extends AbstractTableModel implements Observer {

    private List<Ship> data = new ArrayList<Ship>();

    public ShipTableModel(String portName){

        setData(Town.getInstance().getShipList(portName));
    }



    public void setData(List<Ship> data) {
        this.data.addAll(data);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        int index = data.indexOf(o);
        if (index != -1)
            fireTableRowsUpdated(index, index);
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ship ship = (Ship)data.get(rowIndex);
        if(columnIndex == 0) return ship.getNameShip();
        else if(columnIndex == 1) return ship.getTarget();
        else if(columnIndex == 2) return ship.getCargo();
        else if (columnIndex == 3) return ship.getWeight();
        else return null;
    }

    @Override
    public Class getColumnClass(int c) {
        return GUIConstants.SHIP_COLUMN_CLASSES[c];
    }

    @Override
    public String getColumnName(int col) {
        return GUIConstants.SHIP_TABLE_HEADERS[col];
    }
}
