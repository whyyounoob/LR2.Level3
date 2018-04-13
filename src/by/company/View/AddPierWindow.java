package by.company.View;

import by.company.Controller.Town;
import by.company.Model.RegEx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class create dialog window for adding pier.
 *
 * @author Maxim Borodin
 */

public class AddPierWindow extends JDialog {

    /**
     * The name of the port to which the pier is added.
     */

    private String portName;
    private JLabel nameLabel = new JLabel("Name of pier: ");
    private JTextField nameInput = new JTextField();
    private JLabel speedLabel = new JLabel("Loading speed: ");
    private JTextField speedInput = new JTextField();
    private JButton addBtn = new JButton("Add pier");

    /**
     * Constructor which create window.
     *
     * @param portName name of port
     */

    public AddPierWindow(final String portName) {
        super((Dialog) null, "Add Pier", true);
        this.setBounds(500, 150, 300, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.portName = portName;
        Container container = this.getContentPane();
        container.setLayout(null);

        nameLabel.setBounds(20, 15, 250, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        container.add(nameLabel);

        nameInput.setBounds(20, 50, 200, 30);
        nameInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
        container.add(nameInput);

        speedLabel.setBounds(20, 95, 250, 30);
        speedLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        container.add(speedLabel);

        speedInput.setBounds(20, 130, 200, 30);
        speedInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
        container.add(speedInput);

        addBtn.setBounds(50, 180, 200, 50);
        addBtn.setFont(new Font("Tahoma", Font.BOLD, 25));
        addBtn.addActionListener(new AddButton());
        container.add(addBtn);

        this.setVisible(true);
    }

    /**
     * Action listener for add button.
     */

    class AddButton implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            int i;
            if (nameInput.getText().isEmpty() || speedInput.getText()
                    .isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Fill in all the fields.", "Ooops...", 2);
            } else if (RegEx.checkPierSpeed(speedInput.getText())) {
                i = Town.getInstance().addPier(portName, nameInput.getText(),
                        Integer.parseInt(speedInput.getText()));
                if (i == 1) {
                    JOptionPane.showMessageDialog(null,
                            "Pier add.", "Congratulations.",
                            1);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Choose or add port.", "Ooops...", 2);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Enter the correct speed of pier.(3-4 signs)",
                        "Ooops...", 2);
            }
        }
    }
}
