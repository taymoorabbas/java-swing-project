/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 10-Dec-19
Time: 1:04 AM
Lau ji Ghauri aya fir
*/

package ui;

import model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {

    private List<Person> people;
    private String[] columnNames = {
            "ID",
            "Name",
            "Occupation",
            "Age",
            "Employment",
            "US citizenship",
            "Tax ID",
            "Gender"};

    public PersonTableModel(){ }

    public void setData(List<Person> people){

        this.people = people;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = people.get(rowIndex);

        switch (columnIndex){

            case 0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                return person.getOccupation();
            case 3:
                return person.getAge();
            case 4:
                return person.getEmployment();
            case 5:
                return person.isUSCitizen();
            case 6:
                return person.getTaxID();
            case 7:
                return person.getGender();
            default:
                return null;
        }
    }
}
