package by.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowShipActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog window = new JDialog((Frame) null, "", true);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }
}
