package by.company.View;

import by.company.Model.RegEx;
import by.company.Model.Town;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPierWindow extends JDialog {

    private String portName;
    private JLabel nameLabel = new JLabel("Name of pier: ");
    private JTextField nameInput = new JTextField();
    private JLabel speedLabel = new JLabel("Loading speed: ");
    private JTextField speedInput = new JTextField();
    private JButton addBtn = new JButton("Add pier");


    public AddPierWindow(String portName) {
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

    class AddButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (nameInput.getText().isEmpty() || speedInput.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill in all the fields.", "Ooops...", 2);
            } else if (RegEx.checkpierSpeed(speedInput.getText())) {
                Town.getInstance().addPier(portName, nameInput.getText(), Integer.parseInt(speedInput.getText()));
            } else {
                JOptionPane.showMessageDialog(null,
                        "Enter the correct capacity of the stock.\n(10e+5-10e+20)", "Ooops...", 2);
            }
        }
    }
}
