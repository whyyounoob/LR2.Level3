package by.company.View;

import by.company.Model.Port;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

public class MainWindow extends JFrame {

    private JLabel nameLabel = new JLabel("Tortuga");
    private JPanel portPanel = new JPanel();
    private JScrollPane portScroll = new JScrollPane(portPanel);
    private JPanel infoPanel = new JPanel();
    private JLabel infoLabel = new JLabel();
    private JScrollPane scrollTable = new JScrollPane();
    private JTable infoTable = new JTable();
    private JButton btnAddPort = new JButton("Add Port");
    private JButton btnAddPier = new JButton("Add Pier");
    private JButton btnAddShip = new JButton("Add Ship");
    private JButton btnShowShip = new JButton("Show ships");
    private JPanel btnPanel = new JPanel();

    private ArrayList<Port> portList = new ArrayList<Port>();

    public MainWindow() {

        super("Tortuga");
        this.setBounds(215, 100, 901, 550);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLists();
        infoLabel.setText("Name of port: " + portList.get(0).getNamePort() + ",  Numbers of piers: " + portList.get(0).pierList.size() +
                ", What contains: " + portList.get(0).getStock().getTypeOfCargo() + ", Weight of stock: " + portList.get(0).getStock().getWeight() +
                ", Ship at queue: " + portList.get(0).getShipList().size());
        infoPanel.add(infoLabel);

        Container container = this.getContentPane();
        container.setLayout(null);

        container.add(nameLabel);
        nameLabel.setBounds(0, 0, 207, 62);
        nameLabel.setFont(new Font("Chiller", 3, 60));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(new LineBorder(new Color(0, 0, 0), 1));


        container.add(portScroll);
        portScroll.setBounds(0, 70, 208, 330);
        portPanel.setSize(208, 330);

        portPanel.setLayout(new BoxLayout(portPanel, BoxLayout.Y_AXIS));
        setPortPanel();

        container.add(infoPanel);
        infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        infoPanel.setBounds(215, 0, 680, 62);

        PierTableModel tableModel = new PierTableModel();
        for (int i =0; i<portList.get(0).pierList.size();i++) {
            tableModel.addRow(portList.get(0).pierList.get(i));
        }
        infoTable.setModel(tableModel);
        scrollTable.setViewportView(infoTable);
        scrollTable.setBounds(215, 70, 680, 330);
        container.add(scrollTable);
        GridLayout layout = new GridLayout(1, 4);
        layout.setHgap(8);

        ProgressBarRenderer progressBarRenderer = new ProgressBarRenderer(0,100);
        progressBarRenderer.setStringPainted(true);
        infoTable.setDefaultRenderer(JProgressBar.class, progressBarRenderer);

        btnPanel.setLayout(layout);
        btnPanel.add(btnAddPort);
        btnPanel.add(btnAddPier);
        btnPanel.add(btnAddShip);
        btnPanel.add(btnShowShip);
        btnAddPier.addActionListener(new AddPierActionListener());
        btnShowShip.addActionListener(new ShowShipActionListener());
        btnAddShip.addActionListener(new AddShipActionListener());
        btnAddPort.addActionListener(new AddPortActionListener());

        container.add(btnPanel);
        btnPanel.setBounds(0, 408, 901, 60);

        setVisible(true);

    }

    public void setPortPanel() {


        for (int i = 0; i < portList.size(); i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(190, 70));
            JLabel portLabel = new JLabel(portList.get(i).getNamePort());
            panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), BorderFactory.createEmptyBorder(0, 0, 0, 0)));
            panel.add(portLabel);
            panel.addMouseListener(new PortLabelAction(portList.get(i)));
            panel.setToolTipText(portList.get(i).getNamePort());

            this.portPanel.add(panel);
        }
    }

    public void setLists() {
        portList.add(new Port("Port 1", "Food"));
        portList.add(new Port("Port 2", "Minerals"));
        portList.add(new Port("Port 3", "Drugs"));
        portList.add(new Port("Port 4", "Alcohol"));
        portList.add(new Port("Port 5", "People"));
        portList.add(new Port("Port 6", "Mechanism"));
        portList.add(new Port("Port 7", "Other"));
        for (int i = 0; i < 5; i++) {
            portList.get(0).addPier(new String("Pier " + i), 100);
        }

        for (int i = 0; i < 6; i++) {
            portList.get(1).addPier(new String("Pier " + i), 100);
        }

        for (int i = 0; i < 4; i++) {
            portList.get(5).addPier(new String("Pier " + i), 100);
        }
    }

    class PortLabelAction implements MouseListener {

        Port port;

        public PortLabelAction(Port port) {
            this.port = port;

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            infoLabel.setText("Name of port: " + port.getNamePort() + ",  Numbers of piers: " + port.pierList.size() +
                    ", What contains: " + port.getStock().getTypeOfCargo() + ", Weight of stock: " + port.getStock().getWeight() +
                    ", Ship at queue: " + port.getShipList().size());
            infoPanel.add(infoLabel);
            infoPanel.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class AddPierActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog window = new JDialog((Frame) null, "", true);
            window.setBounds(500, 150, 300, 300);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setVisible(true);
        }
    }

    class AddShipActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog window = new JDialog((Frame) null, "", true);
            window.setBounds(500, 150, 300, 300);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setVisible(true);
        }
    }

    class AddPortActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
