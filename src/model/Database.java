/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 10-Dec-19
Time: 12:15 AM
Lau ji Ghauri aya fir
*/

package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    public void savToFile(File file){

        //to avoid serializable class
        Person[] peopleArray = this.people.toArray(new Person[people.size()]);

        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(peopleArray);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadFromFile(File file){

        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            Person[] peopleArray = (Person[]) objectInputStream.readObject();
            this.people.clear();
            this.people.addAll(Arrays.asList(peopleArray));
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
