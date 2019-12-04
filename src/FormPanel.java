/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 7:55 PM
Lau ji Ghauri aya fir
*/

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {

    private JLabel nameLabel, occupationLabel;
    private JTextField nameTextField, occupationTextField;
    private JButton saveButton;

    public FormPanel(){

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        //uses magic constants ie. pre defined constants (options)
        this.nameLabel = new JLabel("name", SwingConstants.CENTER);
        this.occupationLabel = new JLabel("occupation", SwingConstants.CENTER);

        this.nameTextField = new JTextField(10);
        this.occupationTextField = new JTextField(10);

        this.saveButton = new JButton("Save");


        Border theBorder = BorderFactory.
                createCompoundBorder(BorderFactory.createTitledBorder("Add person"),
                BorderFactory.createLineBorder(Color.orange, 2, true));

        this.setBorder(BorderFactory.
                createCompoundBorder(BorderFactory.
                        createEmptyBorder(2,5,5,5),
                        theBorder));

        this.add(nameLabel);
        this.add(nameTextField);

        this.add(occupationLabel);
        this.add(occupationTextField);
        this.add(saveButton);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250,10);
    }
}
