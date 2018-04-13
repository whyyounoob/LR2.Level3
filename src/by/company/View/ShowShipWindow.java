package by.company.View;

import javax.swing.*;
import java.awt.*;

/**
 * This class for showing window with information of ship`s queue.
 *
 * @author Maxim Borodin
 */

public class ShowShipWindow extends JDialog {

    private JTable table = new JTable();
    private JScrollPane tableScroll = new JScrollPane(table);

    /**
     * Constructor which create window.
     *
     * @param portName name of port
     */

    public ShowShipWindow(final String portName) {
        super((Dialog) null, new String("List of ships ("
                + portName + ")"), true);
        this.setBounds(400, 150, 600, 450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        Container container = this.getContentPane();
        container.setLayout(null);

        tableScroll.setBounds(0, 0, 595, 450);
        table.setModel(new ShipTableModel(portName));
        table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
        container.add(tableScroll);

        this.setVisible(true);
    }
}
