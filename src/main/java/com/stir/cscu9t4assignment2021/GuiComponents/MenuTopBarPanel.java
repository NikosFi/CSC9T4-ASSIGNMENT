package com.stir.cscu9t4assignment2021.GuiComponents;

import javax.swing.*;
import java.awt.*;

public class MenuTopBarPanel extends JMenuBar{

    public MenuTopBarPanel() {

        JMenu menu1 = new JMenu("FILE");
        JMenu menu2 = new JMenu("Help");
        add(menu1);
        add(menu2);
        JMenuItem item1 = new JMenuItem("Open");
        JMenuItem item2 = new JMenuItem("Save as");
        menu1.add(item1);
        menu1.add(item2);

    }
}
