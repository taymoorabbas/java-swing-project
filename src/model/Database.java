/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 10-Dec-19
Time: 12:15 AM
Lau ji Ghauri aya fir
*/

package model;

import java.util.ArrayList;

public class Database {

    private ArrayList<Person> people;

    public Database(){

        this.people = new ArrayList<>();
    }

    public void addPerson(Person person){

        this.people.add(person);
    }

    public ArrayList<Person> getPeople(){

        return this.people;
    }
}
