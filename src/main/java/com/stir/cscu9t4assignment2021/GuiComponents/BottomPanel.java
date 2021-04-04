package com.stir.cscu9t4assignment2021.GuiComponents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BottomPanel extends JPanel {

    public BottomPanel() {

        Border blackline = BorderFactory.createLineBorder(Color.lightGray);
        setBackground(Color.lightGray);
        setBorder(blackline);
        setPreferredSize(new Dimension(360, 90));
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        add(label); // Components Added using Flow Layout
        add(tf);
        add(send);
        add(reset);
    }
}
