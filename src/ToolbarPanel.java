/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 5:06 PM
Lau ji Ghauri aya fir
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolbarPanel extends JPanel implements ActionListener {

    private JButton helloButton, goodbyeButton;
    private TextPanel textPanel;

    public ToolbarPanel(){

        this.setLayout(new FlowLayout());

        this.helloButton = new JButton("Hello");
        this.goodbyeButton = new JButton("Goodbye");

        this.helloButton.addActionListener(this);
        this.goodbyeButton.addActionListener(this);


        this.add(helloButton);
        this.add(goodbyeButton);
    }

    public void setTextPanel(TextPanel textPanel) {

        this.textPanel = textPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        if(clickedButton == this.helloButton){

            this.textPanel.appendText("Hello there\n");
        }
        if(clickedButton == this.goodbyeButton){

            this.textPanel.appendText("Goodbye\n");
        }
    }
}
