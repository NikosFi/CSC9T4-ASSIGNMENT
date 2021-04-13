package com.stir.cscu9t4assignment2021.GuiComponents;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
/**
 * @author 2836012
 */
public class TextAreaPanel extends JPanel {

    JScrollPane outputScrollPane = new JScrollPane();
    JTextArea output = new JTextArea(20,98);

    public TextAreaPanel() {
        setLayout(new CardLayout());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBackground(Color.lightGray);
        setBorder(blackline);
        JButton testBtn = new JButton("Copy Text");
        testBtn.setBounds(1450,15,100,100);
        outputScrollPane = new JScrollPane(output);
        outputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        output.setEditable(false);
        output.setLineWrap(true);
        add(outputScrollPane);

    }

    public void setText (String text){
       output.setText(text);
    }

    public String getText () {
        return output.getText();
    }
}
