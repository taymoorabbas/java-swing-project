package ui;/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 08-Dec-19
Time: 6:59 PM
Lau ji Ghauri aya fir
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class FormMenuBar extends JMenuBar implements ActionListener {

    private Color color = Color.WHITE;
    private JMenu fileMenu, windowMenu, editMenu;
    private JMenuItem importItem, exportItem, exitItem;
    private MenuItemListener menuItemListener;
    private JRadioButtonMenuItem redItem, greenItem, blueItem, orangeItem;

    public FormMenuBar() {

        this.setOpaque(false);
        createFileMenu();
        createWindowMenu();
        createEditMenu();

    }
    public void setMenuItemListener(MenuItemListener menuItemListener){

        this.menuItemListener = menuItemListener;
    }
    private void createFileMenu(){

        this.fileMenu = new JMenu("File");
        this.fileMenu.setForeground(Color.WHITE);

        exportItem = new JMenuItem("Export data...");
        exportItem.setActionCommand("export");
        exportItem.addActionListener(this);

        importItem = new JMenuItem("Import data...");
        importItem.setActionCommand("import");
        importItem.addActionListener(this);

        exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand("exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK)); //accelerator for exit
        exitItem.addActionListener(this);

        fileMenu.add(exportItem);
        fileMenu.add(importItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        this.fileMenu.setMnemonic(KeyEvent.VK_F);
        this.add(fileMenu);
    }

    private void createWindowMenu(){

        this.windowMenu = new JMenu("Window");
        this.windowMenu.setForeground(Color.WHITE);

        JMenu showMenu = new JMenu("Show");
        JCheckBoxMenuItem showFormCheckBox = new JCheckBoxMenuItem("Show form");
        showFormCheckBox.setSelected(true);
        showFormCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(menuItemListener != null){

                    showFormCheckBox.setActionCommand("showForm");
                    menuItemListener.itemClicked(showFormCheckBox);
                }
            }
        });
        showMenu.add(showFormCheckBox);
        windowMenu.add(showMenu);

        this.add(windowMenu);
    }

    private void createEditMenu(){

        this.editMenu = new JMenu("Edit");
        this.editMenu.setForeground(Color.WHITE);
        JMenu borderColorMenu = new JMenu("Change border color");

        redItem = new JRadioButtonMenuItem("Red");
        redItem.setActionCommand("redColor");
        redItem.addActionListener(this);

        greenItem = new JRadioButtonMenuItem("Green");
        greenItem.setActionCommand("greenColor");
        greenItem.addActionListener(this);

        blueItem = new JRadioButtonMenuItem("Blue");
        blueItem.setActionCommand("blueColor");
        blueItem.addActionListener(this);

        orangeItem = new JRadioButtonMenuItem("Orange");
        orangeItem.setActionCommand("orangeColor");
        orangeItem.addActionListener(this);
        orangeItem.setSelected(true);

        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(redItem);
        colorGroup.add(greenItem);
        colorGroup.add(blueItem);
        colorGroup.add(orangeItem);

        borderColorMenu.add(redItem);
        borderColorMenu.add(greenItem);
        borderColorMenu.add(blueItem);
        borderColorMenu.add(orangeItem);
        editMenu.add(borderColorMenu);

        this.add(this.editMenu);
    }

    public void setColor(Color color){

        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(color);
        graphics2D.fillRect(0,0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == exportItem){

            if (menuItemListener != null){

                menuItemListener.itemClicked(exportItem);
            }
        }
        if(e.getSource() == importItem){

            if(menuItemListener != null){

                menuItemListener.itemClicked(importItem);
            }
        }

        if(e.getSource() == exitItem){

            if(menuItemListener != null){

                menuItemListener.itemClicked(exitItem);
            }
        }

        if(e.getSource() == redItem){

            if(menuItemListener != null){

                menuItemListener.itemClicked(redItem);
                System.out.println(redItem.getActionCommand());
            }
        }
        if(e.getSource() == greenItem){

            if(menuItemListener != null){

                menuItemListener.itemClicked(greenItem);
                System.out.println(greenItem.getActionCommand());
            }
        }
        if(e.getSource() == blueItem){

            if(menuItemListener != null){

                menuItemListener.itemClicked(blueItem);
            }
        }
        if(e.getSource() == orangeItem){

            if(menuItemListener != null){

                menuItemListener.itemClicked(orangeItem);
            }
        }
    }
}
