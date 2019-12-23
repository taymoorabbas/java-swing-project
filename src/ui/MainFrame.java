package ui;/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 4:30 PM
Lau ji Ghauri aya fir
*/

import controller.Controller;
import model.Prefs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.sql.SQLException;

public class MainFrame extends JFrame{

    private TextPanel textPanel;
    private ToolbarPanel toolbarPanel;
    private FormPanel formPanel;
    private FormMenuBar formMenuBar;
    private TablePanel tablePanel;
    private JFileChooser fileChooser;
    private Controller controller;
    private PrefsDialog prefsDialog;
    private JSplitPane splitPane;
    private JTabbedPane tabbedPane;
    private ModalDialog modalDialog;

    public MainFrame(String title){

        super(title);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.disconnect();
                dispose(); //close window
                System.gc();
                System.out.println("window closing");
            }
        });
        this.setMinimumSize(new Dimension(600,500));
        this.setSize(600, 500);

        //Controller
        this.controller = new Controller();

        //file chooser
        this.fileChooser = new JFileChooser();
        //this.fileChooser.addChoosableFileFilter(new PersonFileFilter());
        this.fileChooser.setFileFilter(new PersonFileFilter());
        //make downloads the default directory
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") +
                System.getProperty("file.separator") + "Downloads"));

        this.tablePanel = new TablePanel();
        this.tablePanel.setData(controller.getPeople());
        this.tablePanel.setPersonTableListener(new PersonTableListener(){

            public void deleteRow(int row){

                System.out.println(row);
                controller.removePerson(row);
                //tablePanel.refresh();
            }
        });

        //prefs dialog
        this.prefsDialog = new PrefsDialog(this);
        this.prefsDialog.setPrefsListener(new PrefsListener() {
            @Override
            public void prefsSet(int port, String username, String password) {

                Prefs.savePort(port);
                Prefs.saveUsername(username);
                Prefs.savePassword(password);

                try {
                    controller.configure(port, username, password);
                }
                catch (SQLException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Unable to configure database connection",
                            "database", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.prefsDialog
                .setDefaults(
                        Prefs.getPort(),
                        Prefs.getUsername(),
                        Prefs.getPassword());

        try {
            controller.configure(Prefs.getPort(), Prefs.getUsername(), Prefs.getPassword());
        }
        catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Unable to configure database connection",
                    "database", JOptionPane.ERROR_MESSAGE);
        }
        
        //text panel and toolbar panel
        this.textPanel = new TextPanel();
        this.toolbarPanel = new ToolbarPanel();
        this.toolbarPanel.setToolbarListener(new ToolbarListener() {
            @Override
            public void saveEventOccurred() {
                try {
                    controller.connect();
                } catch (SQLException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Cannot connect to database",
                            "Database", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    controller.save();
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Data saved to database",
                            "Database", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Cannot save to database",
                            "Database", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void refreshEventOccurred() {
                try {
                    controller.connect();
                } catch (SQLException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Cannot connect to database",
                            "Database", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    controller.load();
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Data loaded from database",
                            "Database", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Unable to load from database",
                            "Database", JOptionPane.ERROR_MESSAGE);
                }
                tablePanel.refresh();
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
        this.formMenuBar.setColor(Color.darkGray);
        this.formMenuBar.setMenuItemListener(new MenuItemListener() {
            @Override
            public void itemClicked(JMenuItem item) {

                if(item.getActionCommand().equals("export")){

                    if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){

                        if(fileChooser.getSelectedFile() != null){

                            controller.saveToFile(fileChooser.getSelectedFile());
                            JOptionPane.showMessageDialog(MainFrame.this, "Data exported successfully",
                                    "Export", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println(fileChooser.getSelectedFile());
                        }
                        else{
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Cannot open the file for export",
                                    "File error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                if(item.getActionCommand().equals("import")){

                    if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                        if(fileChooser.getSelectedFile() != null){

                            JOptionPane.showMessageDialog(MainFrame.this, "Data imported successfully",
                                    "Export", JOptionPane.INFORMATION_MESSAGE);
                            controller.loadFromFile(fileChooser.getSelectedFile());
                            tablePanel.refresh();
                            System.out.println(fileChooser.getSelectedFile());
                        }
                        else{
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "Cannot open the file for import",
                                    "File error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
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

                        //contains all window listeners ie. closing event
                        WindowListener[] windowListeners = getWindowListeners();
                        for(WindowListener windowListener : windowListeners){

                            windowListener.windowClosing(new WindowEvent(MainFrame.this, 0));
                        }
                    }
                }

                if(item.getActionCommand().equals("showForm")){

                    JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) item;
                    if(checkBox.isSelected()){

                        splitPane.setDividerLocation((int) formPanel.getMinimumSize().getWidth());
                    }
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
                if(item.getActionCommand().equals("prefs")){

                    prefsDialog.setVisible(true);
                }
            }
        });

        this.setJMenuBar(this.formMenuBar);

        //tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 18));
        tabbedPane.addTab("Person database", tablePanel);
        tabbedPane.addTab("Messages", textPanel);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if(tabbedPane.getSelectedIndex() == 0){

                    JDialog dialog = new JDialog(MainFrame.this, "Switched to tab 1");
                    dialog.setLocationRelativeTo(MainFrame.this);
                    dialog.setSize(500,200);
                    dialog.setModal(false);
                    dialog.setVisible(true);

                    //timer to show dialog for 2 sec
                    new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            dialog.setVisible(false);
                        }
                    }).start();
                }
                else{

                    JDialog dialog = new JDialog(MainFrame.this, "Switched to tab 2");
                    dialog.setLocationRelativeTo(MainFrame.this);
                    dialog.setSize(500,200);
                    dialog.setModal(false);
                    dialog.setVisible(true);

                    //timer to show dialog for 2 sec
                    new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            dialog.setVisible(false);
                        }
                    }).start();
                }
            }
        });

        //split pane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tabbedPane);
        splitPane.setOneTouchExpandable(true);

        //to limit the drag of split pane
        splitPane.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                JSplitPane splitPane = (JSplitPane)evt.getSource();

                int screenSize = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
                if(splitPane.getDividerLocation() > screenSize){

                    splitPane.setDividerLocation(screenSize);
                }
            }
        });

        this.add(this.toolbarPanel, BorderLayout.PAGE_START); //to setup toolbar as dock able
        this.add(this.splitPane, BorderLayout.CENTER);
    }
}
