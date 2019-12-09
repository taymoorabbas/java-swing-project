package ui;/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 4:30 PM
Lau ji Ghauri aya fir
*/

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private TextPanel textPanel;
    private ToolbarPanel toolbarPanel;
    private FormPanel formPanel;
    private FormMenuBar formMenuBar;
    private TablePanel tablePanel;
    private JFileChooser fileChooser;
    private Controller controller;

    public MainFrame(String title){

        super(title);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension(600,500));
        this.setSize(600, 500);

        //Controller
        this.controller = new Controller();

        //file chooser
        this.fileChooser = new JFileChooser();
        this.fileChooser.addChoosableFileFilter(new PersonFileFilter());

        this.textPanel = new TextPanel();
        this.tablePanel = new TablePanel();
        this.tablePanel.setData(controller.getPeople());
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
                controller.addPerson(e);
                tablePanel.refresh();
            }
        });

        this.formMenuBar = new FormMenuBar();
        this.formMenuBar.setMenuItemListener(new MenuItemListener() {
            @Override
            public void itemClicked(JMenuItem item) {

                if(item.getActionCommand().equals("export")){

                    if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                        System.out.println(fileChooser.getSelectedFile());
                    }
                }

                if(item.getActionCommand().equals("import")){

                    if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){

                        System.out.println(fileChooser.getSelectedFile());
                    }
                }

                if(item.getActionCommand().equals("exit")){

                    String input = JOptionPane.showInputDialog(MainFrame.this,
                            "Enter username",
                            "Login",
                            JOptionPane.OK_OPTION|JOptionPane.QUESTION_MESSAGE);
                    System.out.println(input);

                    int choice = JOptionPane.showConfirmDialog(MainFrame.this,
                            "Exit for sure ?",
                            "Exit",
                            JOptionPane.YES_NO_OPTION);

                    if(choice == JOptionPane.YES_OPTION){

                        System.exit(0);
                    }
                }

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

        this.add(this.tablePanel, BorderLayout.CENTER);
        this.add(this.toolbarPanel, BorderLayout.NORTH);
        this.add(this.formPanel, BorderLayout.WEST);
    }
}
