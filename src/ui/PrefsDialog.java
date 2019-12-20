/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 21-Dec-19
Time: 1:40 AM
Lau ji Ghauri aya fir
*/

package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog implements ActionListener {

    private JButton saveButton, cancelButton;
    private JSpinner databasePortSpinner;
    private SpinnerNumberModel spinnerNumberModel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent){

        super(parent, "preferences", false);
        this.setSize(340,250);
        this.setLocationRelativeTo(parent);

        this.saveButton = new JButton("Save");
        this.saveButton.addActionListener(this);

        //making buttons the same size
        this.cancelButton = new JButton("Cancel");
        this.cancelButton.addActionListener(this);

        Dimension buttonSize = this.cancelButton.getPreferredSize();
        this.saveButton.setPreferredSize(buttonSize);

        this.spinnerNumberModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        this.databasePortSpinner = new JSpinner(this.spinnerNumberModel);

        this.usernameField = new JTextField(10);
        this.passwordField = new JPasswordField(10);
        //this.passwordField.setEchoChar('X');

        layoutComponents();
    }

    private void layoutComponents() {

        JPanel controlsPanel = new JPanel(new GridBagLayout());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        controlsPanel.setBorder(BorderFactory
                .createCompoundBorder(BorderFactory
                        .createEmptyBorder(15,15,15,15),
                        BorderFactory.createTitledBorder("Database preferences")));

        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.setLayout(new BorderLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        Insets rightPadding = new Insets(0,0,0,15);
        Insets noPadding = new Insets(0,0,0,0);

        //toDo: use gridbagContraints.width to span component to multiple cells
        // ie. gbc.with = 3;

        //////first row////////////////////////////////
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = rightPadding;
        controlsPanel.add(new JLabel("Username: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = noPadding;
        controlsPanel.add(this.usernameField, gridBagConstraints);

        //////next row////////////////////////////////

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = rightPadding;
        controlsPanel.add(new JLabel("Password: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = noPadding;
        controlsPanel.add(this.passwordField, gridBagConstraints);

        //////next row////////////////////////////////

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = rightPadding;
        controlsPanel.add(new JLabel("Port: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = noPadding;
        controlsPanel.add(this.databasePortSpinner, gridBagConstraints);

        //////Buttons panel//////////////////////

        buttonsPanel.add(this.saveButton);
        buttonsPanel.add(this.cancelButton);

        this.add(controlsPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setPrefsListener(PrefsListener prefsListener){

        this.prefsListener = prefsListener;
    }

    public void setDefaults(int port, String username, String password){

        this.databasePortSpinner.setValue(port);
        this.usernameField.setText(username);
        this.passwordField.setText(password);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(this.saveButton)){

            int port = (int)this.databasePortSpinner.getValue();
            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword());
            System.out.println(port);
            System.out.println(username);
            System.out.println(password);
            this.setVisible(false);

            if(prefsListener != null){

                prefsListener.prefsSet(port, username, password);
            }

        }
        if(e.getSource().equals(this.cancelButton)){
            this.setVisible(false);
        }
    }
}
