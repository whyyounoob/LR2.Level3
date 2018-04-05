package by.company.View;

import by.company.Model.Port;
import by.company.Model.Ship;
import javafx.beans.InvalidationListener;

import java.io.Serializable;
import java.util.Observable;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Observer;

public class PierTableModel extends AbstractTableModel implements Observer, Serializable {

    private ArrayList<Port.Pier> data = new ArrayList<Port.Pier>();

    public void addRow(Port.Pier pier) {
        data.add(pier);

        pier.addObserver(this);
        //fireTableRowsInserted(data.size() - 1, data.size() - 1);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return GUIConstants.PIER_TABLE_HEADERS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Port.Pier pier = (Port.Pier) data.get(rowIndex);
        if (columnIndex == 0) return pier.getNamePier();
        else if (columnIndex == 1) return (Integer) pier.getSpeed();
        else if (columnIndex == 2) return pier.getStatus();
        else if (columnIndex == 3) {
            if (pier.getShip() == null) {
                return "----";
            } else {
                return pier.getShip().getNameShip();
            }
        } else if (columnIndex == 4) {
            if (pier.getShip() == null) {
                return "----";
            } else {
                return pier.getShip().getCargo();
            }
        } else if (columnIndex == 5) {
            if (pier.getShip() == null) {
                return null;
            } else {
                return (Long) pier.getStartWeight();
            }
        } else if (columnIndex == 6) return new Float (pier.getProgress());
        else return null;
    }

    @Override
    public Class getColumnClass(int c) {
        return GUIConstants.PIER_COLUMN_CLASSES[c];
    }

    @Override
    public String getColumnName(int col) {
        return GUIConstants.PIER_TABLE_HEADERS[col];
    }

    @Override
    public void update(Observable o, Object arg) {
        int index = data.indexOf(o);
        /*if (index != -1)
            fireTableRowsUpdated(index, index);*/
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void removeAll(){
        data.clear();
        fireTableRowsDeleted(0,data.size());
    }
}
