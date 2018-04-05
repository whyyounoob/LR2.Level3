package by.company.View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ProgressBarRenderer extends JProgressBar implements TableCellRenderer {

    public ProgressBarRenderer(int min, int max) {
        super(min, max);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        //System.out.println("pisos");
        //System.out.println(value);
        setValue((int) ((Float) value).floatValue());
        return this;
    }
}
