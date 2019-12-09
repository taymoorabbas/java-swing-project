/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 10-Dec-19
Time: 12:21 AM
Lau ji Ghauri aya fir
*/

package controller;

import model.*;
import ui.FormEvent;

public class Controller {
    private Database database = new Database();
    public void addPerson(FormEvent event){

        String name = event.getName();
        int ageID = event.getAgeCategory();
        String employment = event.getEmploymentCategory();
        boolean isUSCitizen = event.isUSCitizen();
        String taxID = event.getTaxID();
        String genderType = event.getGender();

        AgeCategory ageCategory = null;

        switch (ageID){
            case 0:
                ageCategory = AgeCategory.child;
                break;

            case 1:
                ageCategory = AgeCategory.adult;
                break;

            case 2:
                ageCategory = AgeCategory.senior;
                break;
        }

        EmploymentCategory employmentCategory = null;

        switch (employment){

            case "employed":
                employmentCategory = EmploymentCategory.employed;
                break;

            case "self-employed":
                employmentCategory = EmploymentCategory.selfEmployed;
                break;

            case "unemployed":
                employmentCategory = EmploymentCategory.unemployed;
                break;
        }

        Gender gender = null;

        switch (genderType){

            case "male":
                gender = Gender.male;
                break;

            case "female":
                gender = Gender.female;
                break;
        }
        Person person = new Person(name,ageCategory, employmentCategory, isUSCitizen, taxID, gender);
        database.addPerson(person);
    }
}
