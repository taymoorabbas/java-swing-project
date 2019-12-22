/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 10-Dec-19
Time: 12:15 AM
Lau ji Ghauri aya fir
*/

package model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {

    private List<Person> people;
    private Connection connection;

    public Database(){

        this.people = new LinkedList<>();
    }

    public void connect(){

        if(connection != null){

            return;
        }
        try {
            System.out.println("Requesting for connection. . .");
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/database_1";
            connection = DriverManager.getConnection(url, "root", "471582");
            System.out.println("Connection established to: " + connection);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect(){

        try {
            if(this.connection != null){
                this.connection.close();
                System.out.println("Database disconnected");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPerson(Person person){

        this.people.add(person);
    }

    public void removePerson(int row){

        this.people.remove(row);
    }

    public List<Person> getPeople(){

        return Collections.unmodifiableList(this.people);
    }

    public void saveToFile(File file){

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
