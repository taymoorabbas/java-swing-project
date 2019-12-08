/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 4:30 PM
Lau ji Ghauri aya fir
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{

    private TextPanel textPanel;
    private ToolbarPanel toolbarPanel;
    private FormPanel formPanel;
    private FormMenuBar formMenuBar;

    public MainFrame(String title){

        super(title);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);

        this.textPanel = new TextPanel();
        this.toolbarPanel = new ToolbarPanel();
        this.toolbarPanel.setTextListener(new TextListener() {
            @Override
            public void textListened(String text) {

                textPanel.appendText(text);
            }
        });

        this.formPanel = new FormPanel();
        this.formPanel.setFormListener(new FormListener() {
            @Override
            public void formFilled(FormEvent e) {

                textPanel.appendText("name: " + e.getName() + "\n");
                textPanel.appendText("occupation: " + e.getOccupation() + "\n");
                textPanel.appendText("age: " + e.getAgeCategory() + "\n");
                textPanel.appendText("employment: " + e.getEmploymentCategory() + "\n");
                textPanel.appendText("US citizenship: " + e.isUSCitizen() + "\n");
                if(e.getTaxID() != null){

                    textPanel.appendText("Tax ID: " + e.getTaxID() + "\n");
                }
                textPanel.appendText("Gender: " + e.getGender() + "\n");
            }
        });

        this.formMenuBar = new FormMenuBar();
        this.formMenuBar.setMenuItemListener(new MenuItemListener() {
            @Override
            public void itemClicked(JMenuItem item) {

                if(item.getActionCommand().equals("showForm")){

                    JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                    formPanel.setVisible(checkBox.isSelected());
                }
                if(item.getActionCommand().equals("redColor")){

                    formPanel.addBorder(Color.RED);
                }
                if(item.getActionCommand().equals("greenColor")){

                    formPanel.addBorder(Color.GREEN);
                }
                if(item.getActionCommand().equals("blueColor")){

                    formPanel.addBorder(Color.BLUE);
                }
                if(item.getActionCommand().equals("orangeColor")){

                    formPanel.addBorder(Color.ORANGE);
                }
            }
        });

        this.setJMenuBar(this.formMenuBar);

        this.add(this.textPanel, BorderLayout.CENTER);
        this.add(this.toolbarPanel, BorderLayout.NORTH);
        this.add(this.formPanel, BorderLayout.WEST);
    }

    private JMenuBar createMenuBar(){

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exportItem = new JMenuItem("Export data...");
        JMenuItem importItem = new JMenuItem("Import data...");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exportItem);
        fileMenu.add(importItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");
        JCheckBoxMenuItem showFormCheckBox = new JCheckBoxMenuItem("Show form");
        showFormCheckBox.setSelected(true);
        showFormCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                formPanel.setVisible(showFormCheckBox.isSelected());
                formPanel.setBorder(null);
            }
        });
        showMenu.add(showFormCheckBox);
        windowMenu.add(showMenu);

        JMenu editMenu = new JMenu("Edit");
        JMenu borderColorMenu = new JMenu("Change border color");

        JRadioButtonMenuItem redItem = new JRadioButtonMenuItem("Red");
        JRadioButtonMenuItem greenItem = new JRadioButtonMenuItem("Green");
        JRadioButtonMenuItem blueItem = new JRadioButtonMenuItem("Blue");
        JRadioButtonMenuItem yellowItem = new JRadioButtonMenuItem("Yellow");
        yellowItem.setSelected(true);

        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(redItem);
        colorGroup.add(greenItem);
        colorGroup.add(blueItem);
        colorGroup.add(yellowItem);

        borderColorMenu.add(redItem);
        borderColorMenu.add(greenItem);
        borderColorMenu.add(blueItem);
        editMenu.add(borderColorMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        menuBar.add(editMenu);

        return menuBar;
    }
}
