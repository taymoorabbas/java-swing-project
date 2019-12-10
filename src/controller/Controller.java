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

import java.io.File;
import java.util.ArrayList;

public class Controller {
    private Database database = new Database();

    public ArrayList<Person> getPeople(){

        return database.getPeople();
    }
    public void addPerson(FormEvent event){

        String name = event.getName();
        String occupation = event.getOccupation();
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

        EmploymentCategory employmentCategory = EmploymentCategory.other;

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
        Person person = new Person(name, occupation, ageCategory, employmentCategory, isUSCitizen, taxID, gender);
        database.addPerson(person);
    }

    public void saveToFile(File file){

        this.database.savToFile(file);
    }

    public void loadFromFile(File file){

        this.database.loadFromFile(file);
    }
}
