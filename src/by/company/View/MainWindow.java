package by.company.View;

import by.company.Model.Constants;
import by.company.Model.Port;
import by.company.Model.RegEx;
import by.company.Model.Town;


import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

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
    private String portName;

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

        /*portList.add(new Port("Tortuga", "Minerals", 10000000));
        portList.get(0).addPier("Pier 1", 1000);
        portList.get(0).addPier("Pier 2", 500);
        portList.get(0).addPier("Pier 3", 400);
        portList.add(new Port("Port 1", "Minerals", 2353454));
        setPortPanel();
*/
        container.add(portScroll);
        portScroll.setBounds(0, 70, 208, 330);
        portPanel.setSize(208, 330);

        portPanel.setLayout(new BoxLayout(portPanel, BoxLayout.Y_AXIS));
        setPortPanel();

        container.add(infoPanel);
        infoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        infoPanel.setBounds(215, 0, 680, 62);

        PierTableModel tableModel = new PierTableModel();

        //tableModel.addRow(portList.get(0).pierList.get(0));

        //tableModel.addRow(portList.get(0).pierList.get(1));

        //tableModel.addRow(portList.get(0).pierList.get(2));

        infoTable.setModel(tableModel);
        scrollTable.setViewportView(infoTable);
        scrollTable.setBounds(215, 70, 680, 330);
        container.add(scrollTable);
        GridLayout layout = new GridLayout(1, 4);
        layout.setHgap(8);

        ProgressBarRenderer progressBarRenderer = new ProgressBarRenderer(0, 100);
        progressBarRenderer.setStringPainted(true);
        infoTable.setDefaultRenderer(JProgressBar.class, progressBarRenderer);

        infoTable.setRowHeight((int) progressBarRenderer.getPreferredSize().getHeight());

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


    public void setPortPanel() {
        this.portPanel.removeAll();

        for (int i = 0; i < Town.getInstance().getPortList().size(); i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(190, 70));
            JLabel portLabel = new JLabel(Town.getInstance().getPortList().get(i).getNamePort());
            panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), BorderFactory.createEmptyBorder(0, 0, 0, 0)));
            panel.add(portLabel);
            panel.addMouseListener(new PortLabelAction(Town.getInstance().getPortList().get(i)));
            panel.setToolTipText(Town.getInstance().getPortList().get(i).getNamePort());

            this.portPanel.add(panel);
        }

        this.portPanel.repaint();
        this.portScroll.revalidate();
    }

    class PortLabelAction implements MouseListener {

        Port port;

        public PortLabelAction(Port port) {
            this.port = port;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            portName = port.getNamePort();
            infoLabel.setText("Name of port: " + port.getNamePort() + ",\n  Numbers of piers: " + port.getPierList().size()  + ", Capacity of stock: " + port.getStock().getCapacity() +
                    ", Ship at queue: " + port.getShipList().size() + ", Left capacity: " + port.getStock().getLeftCapacity());
            infoPanel.add(infoLabel);
            infoPanel.repaint();
            infoTable.setModel(port.getTableModel());
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

    class AddPierActionListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            new AddPierWindow(portName);
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

    class AddShipMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            new AddShipWindow(portName);
            setPortPanel();
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

    class AddPortActionListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            new AddPortWindow();
            setPortPanel();
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

    class ShowShipActionListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            new ShowShipWindow(portName);
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

    class WindowClose implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
            //Town.getInstance().setPortList();
        }

        @Override
        public void windowClosing(WindowEvent e) {

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("test.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(Town.getInstance().getPortList());
                oos.flush();
                oos.close();

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }

}
