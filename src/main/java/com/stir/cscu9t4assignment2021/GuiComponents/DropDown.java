package com.stir.cscu9t4assignment2021.GuiComponents;

import javax.swing.*;
import java.awt.*;


public class DropDown {



    public static class MyComboBoxRenderer extends DefaultListCellRenderer
    {

        private final String _title;
        private int r;
        private int g;
        private int b;

        public MyComboBoxRenderer(String title,int r,int g,int b)
        {
            _title = title;
            this.r=r;
            this.g=g;
            this.b=b;

        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean hasFocus)
        {

            JLabel label = (JLabel) super.getListCellRendererComponent(list,value,index,isSelected,hasFocus);
            label.setOpaque(isSelected); // Highlight only when selected
            if (index != -1)
            label.setOpaque(true); // Highlight always

            if (index == -1 && value == null) {
                setText(_title); label.setOpaque(false);
            }

            if (isSelected) {
                setBackground(new Color(r,g,b));
            }
            return this;
        }
    }
}