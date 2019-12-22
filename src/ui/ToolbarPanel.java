package ui;/*
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

    private JButton saveButton, refreshButton;
    private ToolbarListener toolbarListener;

    public ToolbarPanel(){

        this.setLayout(new FlowLayout(FlowLayout.LEADING));

        this.saveButton = new JButton("Save");
        this.refreshButton = new JButton("Refresh");

        this.saveButton.addActionListener(this);
        this.refreshButton.addActionListener(this);

        this.setBorder(BorderFactory.
                createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),
                        BorderFactory.createLineBorder(Color.orange, 2, true)));


        this.add(saveButton);
        this.add(refreshButton);
    }

    public void setToolbarListener(ToolbarListener toolbarListener){

        this.toolbarListener = toolbarListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clickedButton = (JButton) e.getSource();

        if(clickedButton == this.saveButton){

            if(this.toolbarListener != null){

                toolbarListener.saveEventOccurred();
            }
        }
        if(clickedButton == this.refreshButton){

            if(this.toolbarListener != null){

                toolbarListener.refreshEventOccurred();
            }
        }
    }
}
