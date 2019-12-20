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
        this.setSize(400,300);
        this.setLocationRelativeTo(parent);

        this.saveButton = new JButton("Save");
        this.saveButton.addActionListener(this);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.addActionListener(this);

        this.spinnerNumberModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        this.databasePortSpinner = new JSpinner(this.spinnerNumberModel);

        this.usernameField = new JTextField(10);
        this.passwordField = new JPasswordField(10);
        //this.passwordField.setEchoChar('X');

        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        //////first row////////////////////////////////
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        this.add(new JLabel("Username: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        this.add(this.usernameField, gridBagConstraints);

        //////next row////////////////////////////////

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;

        this.add(new JLabel("Password: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        this.add(this.passwordField, gridBagConstraints);

        //////next row////////////////////////////////

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;

        this.add(new JLabel("Port: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        this.add(this.databasePortSpinner, gridBagConstraints);

        //////next row//////////////////////

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(saveButton, gridBagConstraints);

        gridBagConstraints.gridx++;
        add(this.cancelButton, gridBagConstraints);
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
