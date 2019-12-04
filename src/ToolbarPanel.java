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
    private TextListener textListener;

    public ToolbarPanel(){

        this.setLayout(new FlowLayout());

        this.helloButton = new JButton("Hello");
        this.goodbyeButton = new JButton("Goodbye");

        this.helloButton.addActionListener(this);
        this.goodbyeButton.addActionListener(this);


        this.add(helloButton);
        this.add(goodbyeButton);
    }

    public void setTextListener(TextListener textListener){

        this.textListener = textListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        if(clickedButton == this.helloButton){

            if(this.textListener != null){

                textListener.textListened("Hello\n");
            }
        }
        if(clickedButton == this.goodbyeButton){

            if(this.textListener != null){

                textListener.textListened("Goodbye\n");
            }
        }
    }
}
