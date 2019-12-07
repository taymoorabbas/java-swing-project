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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {

    private JLabel nameLabel, occupationLabel;
    private JTextField nameTextField, occupationTextField;
    private JButton saveButton;
    private FormListener formListener;

    public FormPanel() {

        //uses magic constants ie. pre defined constants (options)
        this.nameLabel = new JLabel("name:", SwingConstants.CENTER);
        this.occupationLabel = new JLabel("occupation:", SwingConstants.CENTER);

        this.nameTextField = new JTextField(10);
        this.occupationTextField = new JTextField(10);

        this.saveButton = new JButton("Save");
        this.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameTextField.getText().trim();
                String occupation = occupationTextField.getText().trim();

                FormEvent formEvent = new FormEvent(e, name, occupation);

                if (formListener != null) {

                    formListener.formFilled(formEvent);
                }
            }
        });

        Border theBorder = BorderFactory.
                createCompoundBorder(BorderFactory.createTitledBorder("Add person"),
                        BorderFactory.createLineBorder(Color.orange, 2, true));

        this.setBorder(BorderFactory.
                createCompoundBorder(BorderFactory.
                                createEmptyBorder(2, 5, 5, 5),
                        theBorder));

        //the most flexible layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        ////////////////////////ROW 1///////////////////////////////////////////

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1; //like in linear layout android sdk
        gridBagConstraints.weighty = 0.1; //we need small vertical spaces
        gridBagConstraints.fill = GridBagConstraints.NONE; //how much space the component will fill in cell
        gridBagConstraints.anchor = GridBagConstraints.LINE_END; //alignment of the cell ie. stick component to the right
        gridBagConstraints.insets = new Insets(0, 0, 0, 5); //padding of component
        this.add(nameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START; //stick component to the left
        gridBagConstraints.insets = new Insets(0, 0, 0, 0); //reset padding
        this.add(nameTextField, gridBagConstraints);

        ////////////////////////ROW 2///////////////////////////////////////////

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1; //like in linear layout android sdk
        gridBagConstraints.weighty = 0.1; //we need small vertical spaces
        gridBagConstraints.anchor = GridBagConstraints.LINE_END; //stick component to the right
        gridBagConstraints.insets = new Insets(0, 0, 0, 5); //padding of component
        this.add(occupationLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START; //stick component to the left
        gridBagConstraints.insets = new Insets(0, 0, 0, 0); //reset padding
        this.add(occupationTextField, gridBagConstraints);

        ////////////////////////ROW 3///////////////////////////////////////////

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1; //like in linear layout android sdk
        gridBagConstraints.weighty = 2; //we need big vertical space for button
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START; //stick component to the left
        gridBagConstraints.insets = new Insets(0, 0, 0, 0); //reset padding (already done above)
        this.add(saveButton, gridBagConstraints);
    }

    public void setFormListener(FormListener formListener){

        this.formListener = formListener;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250,10);
    }
}
