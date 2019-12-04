/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 7:55 PM
Lau ji Ghauri aya fir
*/

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {

    public FormPanel(){

        Border innerBorder1 = BorderFactory.createTitledBorder("Add person");
        Border outerBorder1 = BorderFactory.createEmptyBorder(5,5,5,5);

        //this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        Border outerBorder = BorderFactory.createCompoundBorder(outerBorder1, innerBorder1);
        Border innerBorder = BorderFactory.
                createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createLineBorder(Color.orange));

        Border theBorder = BorderFactory.
                createCompoundBorder(BorderFactory.createTitledBorder("Add person"),
                BorderFactory.createLineBorder(Color.orange, 2, true));

        this.setBorder(BorderFactory.
                createCompoundBorder(BorderFactory.
                        createEmptyBorder(2,5,5,5),
                        theBorder));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250,10);
    }
}
