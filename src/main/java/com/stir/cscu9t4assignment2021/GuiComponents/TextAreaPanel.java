package com.stir.cscu9t4assignment2021.GuiComponents;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextAreaPanel extends JPanel {

    JScrollPane outputScrollPane = new JScrollPane();
    JTextArea output = new JTextArea();

    public TextAreaPanel() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBackground(Color.lightGray);
        setBorder(blackline);
        setLayout(null); //null for manual positioning
        JButton testBtn = new JButton("Copy Text");
        testBtn.setBounds(1450,15,100,100);
        outputScrollPane = new JScrollPane(output);
        outputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        output.setEditable(false);
        output.setLineWrap(true);
        outputScrollPane.setBounds(15,15,1420,580);
        add(outputScrollPane);
        add(testBtn);
    }

    public void setText (String text){
       output.setText(text);
    }
}