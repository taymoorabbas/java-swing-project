/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 10-Dec-19
Time: 1:04 AM
Lau ji Ghauri aya fir
*/

package ui;

import model.EmploymentCategory;
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        Person person = people.get(rowIndex);
        if (columnIndex == 6) {
            return person.isUSCitizen();
        }
        return true;
    }

    //for updating edited values
    //todo: implement all columns
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (people == null){

            return;
        }

        Person person = people.get(rowIndex);

         switch (columnIndex){
             case 1:
                 person.setName((String) aValue );
                 break;
             case 4:
                 person.setEmployment((EmploymentCategory) aValue);
                 break;
             case 5:
                 person.setUSCitizen(((Boolean) aValue));
                 fireTableCellUpdated(rowIndex, columnIndex);

                 if(!person.isUSCitizen()){
                     person.setTaxID(null);
                     fireTableCellUpdated(rowIndex, 6);
                 }
                 break;
             case 6:
                 person.setTaxID((String)aValue);
                 break;
             default:
                 return;
         }
    }

    //custom made columns
    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return EmploymentCategory.class;
            case 5:
                return Boolean.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            default:
                return null;
        }
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
