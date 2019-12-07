/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 4:30 PM
Lau ji Ghauri aya fir
*/

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private ToolbarPanel toolbarPanel;
    private FormPanel formPanel;

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
            }
        });

        this.add(this.textPanel, BorderLayout.CENTER);
        this.add(this.toolbarPanel, BorderLayout.NORTH);
        this.add(this.formPanel, BorderLayout.WEST);
    }
}
