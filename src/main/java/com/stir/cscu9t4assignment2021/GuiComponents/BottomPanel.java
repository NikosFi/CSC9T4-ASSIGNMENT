package com.stir.cscu9t4assignment2021.GuiComponents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
/**
 * @author 2836012
 */
public class BottomPanel extends JPanel {

    public BottomPanel() {

        Border blackline = BorderFactory.createLineBorder(Color.lightGray);
        setBackground(Color.lightGray);
        setBorder(blackline);
        setPreferredSize(new Dimension(360, 90));
    }
}
