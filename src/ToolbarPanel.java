/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 5:06 PM
Lau ji Ghauri aya fir
*/

import javax.swing.*;
import java.awt.*;

public class ToolbarPanel extends JPanel {

    private JButton helloButton, goodbyeButton;

    public ToolbarPanel(){

        this.setLayout(new FlowLayout());

        this.helloButton = new JButton("Hello");
        this.goodbyeButton = new JButton("Goodbye");

        this.add(helloButton);
        this.add(goodbyeButton);
    }
}
