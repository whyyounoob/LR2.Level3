package by.company.View;

import by.company.Controller.Town;
import by.company.Model.FileInput;
import by.company.Model.Port;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This method for creating main window.
 *
 * @author Maxim Borodin
 */

public class MainWindow extends JFrame {

    private JLabel nameLabel = new JLabel("Tortuga");
    private static JPanel infoPanel = new JPanel();
    private JScrollPane scrollTable = new JScrollPane();
    private static JTable infoTable = new JTable();
    private JButton btnAddPort = new JButton("Add Port");
    private JButton btnAddPier = new JButton("Add Pier");
    private JButton btnAddShip = new JButton("Add Ship");
    private JButton btnShowShip = new JButton("Show ships");
    private JPanel btnPanel = new JPanel();
    private static String portName = "";
    private static DefaultListModel<String> listModel =
            new DefaultListModel<String>();
    private static JList<String> portList = new JList<String>(listModel);
    private static JScrollPane portPane = new JScrollPane(portList);

    private static JLabel namePortLabel = new JLabel();
    private static JLabel leftShipLabel = new JLabel();
    private static JLabel stockStatus = new JLabel();

    /**
     * This constructor describes the window.
     */

    public MainWindow() {

        super("Tortuga");
        this.setBounds(215, 100, 901, 550);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(null);

        container.add(nameLabel);
        nameLabel.setBounds(0, 0, 207, 62);
        nameLabel.setFont(new Font("Chiller", 3, 60));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1));

        container.add(portPane);
        portPane.setBounds(0, 70, 208, 330);
        setListModel();

        portList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        portList.addListSelectionListener(new ChoosePort());

        container.add(infoPanel);
        infoPanel.setLayout(null);
        infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        infoPanel.setBounds(215, 0, 680, 62);
        infoPanel.add(namePortLabel);
        namePortLabel.setBounds(5, 0, 300, 40);
        namePortLabel.setFont(new Font("Times New Roman", 3, 30));
        infoPanel.add(leftShipLabel);
        leftShipLabel.setBounds(300, 8, 200, 30);
        leftShipLabel.setFont(new Font("Times New Roman", 3, 20));
        infoPanel.add(stockStatus);
        stockStatus.setBounds(5, 40, 670, 20);
        stockStatus.setFont(new Font("Times New Roman", 3, 14));

        PierTableModel tableModel = new PierTableModel();

        infoTable.setModel(tableModel);
        scrollTable.setViewportView(infoTable);
        scrollTable.setBounds(215, 70, 680, 330);
        container.add(scrollTable);
        GridLayout layout = new GridLayout(1, 4);
        layout.setHgap(8);

        ProgressBarRenderer progressBarRenderer =
                new ProgressBarRenderer(0, 100);
        progressBarRenderer.setStringPainted(true);
        infoTable.setDefaultRenderer(JProgressBar.class, progressBarRenderer);

        infoTable.setRowHeight((int) progressBarRenderer
                .getPreferredSize().getHeight());


        btnPanel.setLayout(layout);
        btnPanel.add(btnAddPort);
        btnPanel.add(btnAddPier);
        btnPanel.add(btnAddShip);
        btnPanel.add(btnShowShip);
        btnAddPier.addMouseListener(new AddPierActionListener());
        btnShowShip.addMouseListener(new ShowShipActionListener());
        btnAddShip.addMouseListener(new AddShipMouseListener());
        btnAddPort.addMouseListener(new AddPortActionListener());

        container.add(btnPanel);
        btnPanel.setBounds(0, 408, 901, 60);

        this.addWindowListener(new WindowClose());

        setVisible(true);

    }

    /**
     * This method create list model in the begining.
     */

    public void setListModel() {
        listModel.removeAllElements();
        for (int i = 0; i < Town.getInstance().getPortList().size(); i++) {
            listModel.addElement(Town.getInstance().getPortList()
                    .get(i).getNamePort());
        }
    }

    /**
     * This class add mouse listener for Add Pier Button.
     */

    class AddPierActionListener implements MouseListener {

        @Override
        public void mouseClicked(final MouseEvent e) {
            new AddPierWindow(portName);
        }

        @Override
        public void mousePressed(final MouseEvent e) {

        }

        @Override
        public void mouseReleased(final MouseEvent e) {

        }

        @Override
        public void mouseEntered(final MouseEvent e) {

        }

        @Override
        public void mouseExited(final MouseEvent e) {

        }
    }

    /**
     * This class add mouse listener for Add Ship Button.
     */

    class AddShipMouseListener implements MouseListener {

        @Override
        public void mouseClicked(final MouseEvent e) {
            new AddShipWindow();
        }

        @Override
        public void mousePressed(final MouseEvent e) {

        }

        @Override
        public void mouseReleased(final MouseEvent e) {

        }

        @Override
        public void mouseEntered(final MouseEvent e) {

        }

        @Override
        public void mouseExited(final MouseEvent e) {

        }
    }

    /**
     * This class add mouse listener for Add Port Button.
     */

    class AddPortActionListener implements MouseListener {

        @Override
        public void mouseClicked(final MouseEvent e) {
            new AddPortWindow();

        }

        @Override
        public void mousePressed(final MouseEvent e) {

        }

        @Override
        public void mouseReleased(final MouseEvent e) {

        }

        @Override
        public void mouseEntered(final MouseEvent e) {

        }

        @Override
        public void mouseExited(final MouseEvent e) {

        }
    }

    /**
     * This class add mouse listener for Show ship Button.
     */

    class ShowShipActionListener implements MouseListener {

        @Override
        public void mouseClicked(final MouseEvent e) {
            if (portName.equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Choose or add port.", "Ooooops...", 1);
            } else {
                new ShowShipWindow(portName);
            }

        }

        @Override
        public void mousePressed(final MouseEvent e) {

        }

        @Override
        public void mouseReleased(final MouseEvent e) {

        }

        @Override
        public void mouseEntered(final MouseEvent e) {

        }

        @Override
        public void mouseExited(final MouseEvent e) {

        }
    }

    /**
     * Properties of window close.
     */

    class WindowClose implements WindowListener {

        @Override
        public void windowOpened(final WindowEvent e) {

        }

        @Override
        public void windowClosing(final WindowEvent e) {

            FileInput.inputFile();

            System.exit(0);
        }

        @Override
        public void windowClosed(final WindowEvent e) {

        }

        @Override
        public void windowIconified(final WindowEvent e) {

        }

        @Override
        public void windowDeiconified(final WindowEvent e) {

        }

        @Override
        public void windowActivated(final WindowEvent e) {

        }

        @Override
        public void windowDeactivated(final WindowEvent e) {

        }
    }

    /**
     * This class crate selection listener for every port in list.
     */

    class ChoosePort implements ListSelectionListener {

        @Override
        public void valueChanged(final ListSelectionEvent e) {

            String name = (String) portList.getSelectedValue();
            setInfo(name);

        }
    }

    /**
     * This method set info about class at info panel.
     *
     * @param name name of port
     */

    public static void setInfo(final String name) {

        Port port = null;
        for (int i = 0; i < Town.getInstance().getPortList().size(); i++) {
            if (Town.getInstance().getPortList().get(i)
                    .getNamePort().equals(name)) {
                port = Town.getInstance().getPortList().get(i);
            }
        }
        infoTable.setModel(port.getTableModel());
        portName = port.getNamePort();
        stockStatus.setText(port.getStock().getStockStatus());
        namePortLabel.setText(port.getNamePort());
        leftShipLabel.setText("Ship in queue: " + port.getShipList().size());

    }

    /**
     * This method add 1 port at list.
     *
     * @param name name of new port
     */

    public static void addElement(final String name) {
        listModel.addElement(name);
    }
}
