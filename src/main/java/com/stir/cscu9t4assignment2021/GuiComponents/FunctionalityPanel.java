package com.stir.cscu9t4assignment2021.GuiComponents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FunctionalityPanel extends JPanel {

    public FunctionalityPanel() {

        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setPreferredSize(new Dimension(350,20));
    }


    /**
     * method to remove the Journal Fields
     */

    public void removeFields(JLabel lab, JLabel lab2, JTextField text, JTextField text2) {
        remove(lab);
        remove(lab2);
        remove(text);
        remove(text2);
    }




}
