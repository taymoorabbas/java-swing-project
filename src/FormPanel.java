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

    private JLabel nameLabel, occupationLabel, taxLabel;
    private JTextField nameTextField, occupationTextField;
    private JButton saveButton;
    private JList ageList;
    private JComboBox employmentComboBox;
    private JCheckBox citizenCheckBox;
    private JTextField taxIDTextField;
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
                AgeCategory age = (AgeCategory) ageList.getSelectedValue();
                String employmentCategory = (String) employmentComboBox.getSelectedItem();
                boolean isUSCitizen = citizenCheckBox.isSelected();
                String taxID = null;

                if(taxIDTextField.isEnabled()){

                    taxID = taxIDTextField.getText().trim();
                }

                System.out.println(age.getId());
                System.out.println(employmentCategory);

                FormEvent formEvent = new FormEvent(e, name, occupation, age.getId(), employmentCategory, isUSCitizen, taxID);

                if (formListener != null) {

                    formListener.formFilled(formEvent);
                }
            }
        });

        this.ageList = new JList<>();

        //SETUP AGELIST
        //acts as M in MVC for the JList. for info on MVC, google it
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2,"65 and above"));
        ageList.setModel(ageModel);
        ageList.setPreferredSize(new Dimension(110, 70));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        this.employmentComboBox = new JComboBox();

        //SETUP ComboBox
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.addElement("employed");
        comboModel.addElement("self-employed");
        comboModel.addElement("unemployed");
        this.employmentComboBox.setModel(comboModel);
        this.employmentComboBox.setSelectedIndex(0);
        this.employmentComboBox.setEditable(true);

        this.citizenCheckBox = new JCheckBox();

        this.taxLabel = new JLabel("Tax ID: ");
        this.taxLabel.setEnabled(false);

        this.taxIDTextField = new JTextField(10);
        taxIDTextField.setEnabled(false);

        this.citizenCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(citizenCheckBox.isSelected()){

                    taxLabel.setEnabled(true);
                    taxIDTextField.setEnabled(true);
                }
                else{

                    taxLabel.setEnabled(false);
                    taxIDTextField.setEnabled(false);
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

        layoutComponents();

    }

    private void layoutComponents() {
        //the most flexible layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridy = 0;

        ////////////////////////ROW///////////////////////////////////////////

        gridBagConstraints.gridx = 0;
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

        ////////////////////////NEXT ROW///////////////////////////////////////////

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;

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

        ////////////////////////NEXT ROW///////////////////////////////////////////

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy++;
        gridBagConstraints.weightx = 1; //like in linear layout android sdk
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START; //stick component to the left
        gridBagConstraints.insets = new Insets(0, 0, 0, 0); //reset padding (already done above)
        this.add(ageList, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END; //stick component to the right
        gridBagConstraints.insets = new Insets(0, 0, 0, 5); //padding of component
        this.add(new JLabel("Age: "), gridBagConstraints);

        ////////////////////////NEXT ROW///////////////////////////////////////////

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy++;
        gridBagConstraints.weightx = 1; //like in linear layout android sdk
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START; //stick component to the left
        gridBagConstraints.insets = new Insets(0, 0, 0, 0); //reset padding (already done above)
        this.add(employmentComboBox, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END; //stick component to the right
        gridBagConstraints.insets = new Insets(0, 0, 0, 5); //padding of component
        this.add(new JLabel("Employment: "), gridBagConstraints);

        ////////////////////////NEXT ROW///////////////////////////////////////////

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy++;
        gridBagConstraints.weightx = 1; //like in linear layout android sdk
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START; //stick component to the left
        gridBagConstraints.insets = new Insets(0, 0, 0, 0); //reset padding (already done above)
        this.add(citizenCheckBox, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END; //stick component to the right
        gridBagConstraints.insets = new Insets(0, 0, 0, 5); //padding of component
        this.add(new JLabel("US citizen: "), gridBagConstraints);

        ////////////////////////NEXT ROW///////////////////////////////////////////

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy++;
        gridBagConstraints.weightx = 1; //like in linear layout android sdk
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START; //stick component to the left
        gridBagConstraints.insets = new Insets(0, 0, 0, 0); //reset padding (already done above)
        this.add(taxIDTextField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_END; //stick component to the right
        gridBagConstraints.insets = new Insets(0, 0, 0, 5); //padding of component
        this.add(taxLabel, gridBagConstraints);

        ////////////////////////NEXT ROW///////////////////////////////////////////

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy++;
        gridBagConstraints.weightx = 1; //like in linear layout android sdk
        gridBagConstraints.weighty = 1; //we need big vertical space for button
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

    class AgeCategory{

        private int id;
        private String text;

        public AgeCategory(int id, String text) {
            this.id = id;
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }
}
