/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 09-Dec-19
Time: 11:49 PM
Lau ji Ghauri aya fir
*/

package model;

import java.io.Serializable;

public class Person implements Serializable {

    public static final long serialVersionUID = -4244178739045579171L;
    private static int count = 1;

    private int id;
    private String name;
    private String occupation;
    private AgeCategory age;
    private EmploymentCategory employment;
    private boolean isUSCitizen;
    private String taxID;
    private Gender gender;

    public Person(String name, String occupation, AgeCategory age,
                  EmploymentCategory employment, boolean isUSCitizen,
                  String taxID, Gender gender) {

        this.id = count++;
        this.name = name;
        this.occupation = occupation;
        this.age = age;
        this.employment = employment;
        this.isUSCitizen = isUSCitizen;
        this.taxID = taxID;
        this.gender = gender;
    }

    public Person(int id, String name, String occupation, AgeCategory age,
                  EmploymentCategory employment, boolean isUSCitizen,
                  String taxID, Gender gender) {

        this(name, occupation, age, employment, isUSCitizen, taxID, gender);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public AgeCategory getAge() {
        return age;
    }

    public void setAge(AgeCategory age) {
        this.age = age;
    }

    public EmploymentCategory getEmployment() {
        return employment;
    }

    public void setEmployment(EmploymentCategory employment) {
        this.employment = employment;
    }

    public boolean isUSCitizen() {
        return isUSCitizen;
    }

    public void setUSCitizen(boolean USCitizen) {
        isUSCitizen = USCitizen;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name;
    }
}
