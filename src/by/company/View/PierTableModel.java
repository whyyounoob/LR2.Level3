package by.company.View;

import by.company.Model.Port;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This class create table model for main table.
 *
 * @author Maxim Borodin
 */

public class PierTableModel extends AbstractTableModel
        implements Observer, Serializable {

    /**
     * Data for table.
     */

    private ArrayList<Port.Pier> data = new ArrayList<Port.Pier>();

    /**
     * This method add row in table.
     *
     * @param pier added pier
     */

    public void addRow(final Port.Pier pier) {
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
    public Object getValueAt(final int rowIndex, final int columnIndex) {
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
        } else if (columnIndex == 6) return new Float(pier.getProgress());
        else return null;
    }

    @Override
    public Class getColumnClass(final int c) {
        return GUIConstants.PIER_COLUMN_CLASSES[c];
    }

    @Override
    public String getColumnName(final int col) {
        return GUIConstants.PIER_TABLE_HEADERS[col];
    }

    @Override
    public void update(final Observable o, final Object arg) {
        int index = data.indexOf(o);

        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(final int row, final int col) {
        return false;
    }

    /**
     * This method remove all rows from table.
     */

    public void removeAll() {
        data.clear();
        fireTableRowsDeleted(0, data.size());
    }
}
