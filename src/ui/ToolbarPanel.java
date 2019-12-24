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
import java.net.URL;

public class ToolbarPanel extends JToolBar implements ActionListener {

    private JButton saveButton, refreshButton, addButton;
    private ToolbarListener toolbarListener;

    public ToolbarPanel(){

        //to make border float able
        setFloatable(false);

        this.saveButton = new JButton(createIcon("/images/save.png"));
        this.saveButton.setToolTipText("Save");

        this.refreshButton = new JButton(createIcon("/images/refresh.png"));
        this.refreshButton.setToolTipText("Refresh");

        this.addButton = new JButton("Add new");

        this.saveButton.addActionListener(this);
        this.refreshButton.addActionListener(this);
        this.addButton.addActionListener(this);
//
        this.setBorder(BorderFactory.
                createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),
                        BorderFactory.createLineBorder(Color.orange, 2, true)));


        this.add(saveButton);
        this.addSeparator(new Dimension(10,10));
        this.add(refreshButton);
        this.addSeparator(new Dimension(10,10));
        this.add(addButton);
    }

    private ImageIcon createIcon(String path){
        URL url = getClass().getResource(path);

        if(url == null){

            System.err.println("Unable to load image from path: " + path);
        }
        else{

//            Image img = icon.getImage() ;
//            Image newimg = img.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;
//            icon = new ImageIcon( newimg );

            //get a resized icon to 16x16
            ImageIcon imageIcon = new ImageIcon(url, "meri jooti ko parwa nai");
            Image image = imageIcon.getImage();
            Image resized = image.getScaledInstance(32,32, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(resized);
        }

        return null;
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
        if(clickedButton == this.addButton){

            toolbarListener.addEventOccurred();
        }
    }
}
