package com.stir.cscu9t4assignment2021.GuiComponents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FunctionalityPanel extends JPanel {



    public FunctionalityPanel() {
        setLayout(null);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setPreferredSize(new Dimension(350,800));
        setBackground(Color.lightGray);

    }

}
