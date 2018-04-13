package by.company.View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Class creating renderer for table.
 *
 * @author Maxim Borodin
 */

public class ProgressBarRenderer extends JProgressBar
        implements TableCellRenderer {

    /**
     * Constructor for progress bar.
     *
     * @param max max value of progress bar
     * @param min min value of progress bar
     */

    public ProgressBarRenderer(final int min, final int max) {
        super(min, max);
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table,
                                                   final Object value,
                                                   final boolean isSelected,
                                                   final boolean hasFocus,
                                                   final int row,
                                                   final int column) {

        setValue((int) ((Float) value).floatValue());
        return this;
    }
}
