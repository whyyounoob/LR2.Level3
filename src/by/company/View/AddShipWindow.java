package by.company.View;

import by.company.Controller.Town;
import by.company.Model.Constants;
import by.company.Model.RegEx;
import by.company.Model.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class create dialog window for adding ship.
 *
 * @author Maxim Borodin
 */

public class AddShipWindow extends JDialog {


    private JLabel nameLabel = new JLabel("Name of ship: ");
    private JTextField nameInput = new JTextField();
    private JLabel weightLabel = new JLabel("Weight of cargo: ");
    private JTextField weightInput = new JTextField();
    private JLabel cargoLabel = new JLabel("Choose your cargo: ");
    private JComboBox cargoList = new JComboBox(Constants.MINERALS_GOODS);
    private JLabel targetLabel = new JLabel("Choose ship`s target: ");
    private JComboBox targetList = new JComboBox(Constants.SHIP_TARGET);
    private JButton addBtn = new JButton("Add Ship");

    /**
     * Constructor which create window.
     */

    public AddShipWindow() {

        super((Dialog) null, "Add Ship", true);
        this.setBounds(500, 150, 300, 450);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(null);

        for (String str : Constants.CLOTHES_GOODS) {
            cargoList.addItem(str);
        }
        for (String str : Constants.FOOD_GOODS) {
            cargoList.addItem(str);
        }
        for (String str : Constants.ILLEGAL_GOODS) {
            cargoList.addItem(str);
        }

        nameLabel.setBounds(20, 15, 250, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        container.add(nameLabel);

        nameInput.setBounds(20, 50, 200, 30);
        nameInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
        container.add(nameInput);

        cargoLabel.setBounds(20, 95, 250, 30);
        cargoLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        container.add(cargoLabel);

        cargoList.setBounds(20, 130, 200, 30);
        cargoList.setFont(new Font("Tahoma", Font.PLAIN, 16));
        container.add(cargoList);

        targetLabel.setBounds(20, 175, 250, 30);
        targetLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        container.add(targetLabel);

        targetList.setBounds(20, 210, 200, 30);
        targetList.setFont(new Font("Tahoma", Font.PLAIN, 16));
        container.add(targetList);

        weightLabel.setBounds(20, 255, 250, 30);
        weightLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        container.add(weightLabel);

        weightInput.setBounds(20, 290, 200, 30);
        weightInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
        container.add(weightInput);

        addBtn.setBounds(50, 340, 200, 50);
        addBtn.setFont(new Font("Tahoma", Font.BOLD, 25));
        addBtn.addActionListener(new AddShipListener());
        container.add(addBtn);

        this.setVisible(true);
    }

    /**
     * Action listener for add button.
     */

    private class AddShipListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            int i = 0;
            if (nameInput.getText().isEmpty()
                    || weightInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Fill in all the fields.", "Ooops...", 2);
            } else if (RegEx.checkShipWeight(weightInput.getText())) {
                i = Town.getInstance().addShip(new Ship(nameInput.getText(),
                        (String) targetList.getSelectedItem(),
                        Integer.parseInt(weightInput.getText()),
                        (String) cargoList.getSelectedItem()));
                if (i == 1) {
                    JOptionPane.showMessageDialog(null,
                            "Sorry, but we can not accept a "
                                    + "ship with this load...",
                            "Ooops", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "The ship will be "
                                    + "successfully taken by our port town",
                            "Congratulations!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Enter the correct weight of goods.(4-10 signs)",
                        "Ooops...", 2);
            }

        }
    }
}
