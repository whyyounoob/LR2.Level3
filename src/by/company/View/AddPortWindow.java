package by.company.View;

import by.company.Controller.Town;
import by.company.Model.Constants;
import by.company.Model.RegEx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class create dialog window for adding port.
 *
 * @author Maxim Borodin
 */

public class AddPortWindow extends JDialog {

    private JTextField inputName = new JTextField();
    private JLabel nameLabel = new JLabel("Name of port: ");
    private JComboBox listOfCargo = new JComboBox(Constants.TYPEOFCARGO);
    private JLabel cargoLabel = new JLabel("Choose type of goods: ");
    private JTextField capacityField = new JTextField();
    private JLabel capacityLabel = new JLabel("Enter storage capacity: ");
    private JButton addBtn = new JButton("ADD PORT");

    /**
     * Constructor which create window.
     */

    public AddPortWindow() {
        super((Dialog) null, "Add Port", true);
        this.setBounds(500, 150, 300, 350);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(null);

        nameLabel.setBounds(20, 15, 250, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        container.add(nameLabel);

        inputName.setBounds(20, 50, 200, 30);
        inputName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        container.add(inputName);

        cargoLabel.setBounds(20, 95, 250, 30);
        cargoLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        container.add(cargoLabel);

        listOfCargo.setBounds(20, 130, 200, 30);
        listOfCargo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        container.add(listOfCargo);

        capacityLabel.setBounds(20, 175, 250, 30);
        capacityLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        container.add(capacityLabel);

        capacityField.setBounds(20, 210, 200, 30);
        capacityField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        container.add(capacityField);

        addBtn.setBounds(50, 250, 200, 50);
        addBtn.setFont(new Font("Tahoma", Font.BOLD, 25));

        addBtn.addActionListener(new AddPort());


        container.add(addBtn);

        this.setVisible(true);
    }

    /**
     * Action listener for add button.
     */

    class AddPort implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            String namePort;
            String typeOfCargo;
            long capacity;
            int i;
            if (inputName.getText().isEmpty() || capacityField.getText()
                    .isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Fill in all the fields.", "Ooops...", 2);
            } else if (RegEx.checkCapacity(capacityField.getText())) {
                namePort = inputName.getText();
                typeOfCargo = (String) listOfCargo.getSelectedItem();
                capacity = Long.parseLong(capacityField.getText());
                i = Town.getInstance().addPort(namePort, typeOfCargo, capacity);
                if (i == 1) {
                    MainWindow.addElement(namePort);
                    JOptionPane.showMessageDialog(null, "Port add.",
                            "Congratulations.",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "This port`s name is already taken.",
                            "Ooops...", 2);

                }

            } else {
                JOptionPane.showMessageDialog(null,
                        "Enter the correct capacity of the stock.(6-20 signs)",
                        "Ooops...", 2);

            }
        }
    }

}
