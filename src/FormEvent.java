/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 06-Dec-19
Time: 8:34 PM
Lau ji Ghauri aya fir
*/

import java.util.EventObject;

//this class can handle swing events ie. ActionEvents
public class FormEvent extends EventObject {

    private String name, occupation, employmentCategory;
    private int ageCategory;
    private boolean isUSCitizen;
    private String taxID;
    private String gender;

      public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String occupation, int ageCategory, String employmentCategory,
                     boolean isUSCitizen, String taxID, String gender){

          super(source);
          this.name = name;
          this.occupation = occupation;
          this.ageCategory = ageCategory;
          this.employmentCategory = employmentCategory;
          this.isUSCitizen = isUSCitizen;
          this.taxID = taxID;
          this.gender = gender;
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

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getEmploymentCategory() {
        return employmentCategory;
    }

    public void setEmploymentCategory(String employmentCategory) {
        this.employmentCategory = employmentCategory;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
