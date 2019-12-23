/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 10-Dec-19
Time: 12:15 AM
Lau ji Ghauri aya fir
*/

package model;

import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {

    private List<Person> people;
    private Connection connection;

    private int port;
    private String username;
    private String password;

    public Database(){

        this.people = new LinkedList<>();
    }

    public void configure(int port, String username, String password) throws SQLException, ClassNotFoundException {

        this.port = port;
        this.username = username;
        this.password = password;

        if(connection != null){

            disconnect();
            connect();
        }
    }

    public void connect() throws ClassNotFoundException, SQLException {

        if(connection != null){

            return;
        }
        System.out.println("Requesting for connection. . .");
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/java_swing_project";
        connection = DriverManager.getConnection(url, "root", "471582");
        System.out.println("Connection established to: " + connection);
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

    public void save() throws SQLException {

        String query = "SELECT COUNT(*) AS count FROM people WHERE id = ?;";
        String insertQuery = "INSERT INTO people VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        String updateQuery = "UPDATE people SET person_name = ?, age = ?, employment_status = ?," +
                " us_citizen = ?, tax_id = ?, gender = ?, occupation = ? WHERE id = ?;";

        PreparedStatement statementCount;
        PreparedStatement statementInsert;
        PreparedStatement statementUpdate;

        statementCount = connection.prepareStatement(query);
        statementInsert = connection.prepareStatement(insertQuery);
        statementUpdate = connection.prepareStatement(updateQuery);

        for(Person person : people){

            int id = person.getId();
            String name = person.getName();
            AgeCategory age = person.getAge();
            EmploymentCategory employment_status = person.getEmployment();
            boolean isUSCitizen = person.isUSCitizen();
            String taxID = person.getTaxID();
            Gender gender = person.getGender();
            String occupation = person.getOccupation();

            statementCount.setInt(1, person.getId());
            ResultSet resultSet = statementCount.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);

            if(count == 0){

                statementInsert.setInt(1, id);
                statementInsert.setString(2, name);
                statementInsert.setString(3, age.name());
                statementInsert.setString(4, employment_status.name());
                statementInsert.setBoolean(5, isUSCitizen);
                statementInsert.setString(6, taxID);
                statementInsert.setString(7, gender.name());
                statementInsert.setString(8, occupation);

                statementInsert.executeUpdate();
                System.out.println("Inserting person with ID: " + person.getId());
            }
            else{
                statementUpdate.setString(1, name);
                statementUpdate.setString(2, age.name());
                statementUpdate.setString(3, employment_status.name());
                statementUpdate.setBoolean(4, isUSCitizen);
                statementUpdate.setString(5, taxID);
                statementUpdate.setString(6, gender.name());
                statementUpdate.setString(7, occupation);
                statementUpdate.setInt(8, id);

                statementUpdate.executeUpdate();
                System.out.println("Updating person with ID: " + person.getId());
            }
        }

        if (statementCount != null && statementInsert != null && statementUpdate != null) {
            statementCount.close();
            statementInsert.close();
            statementUpdate.close();
        }
    }

    public void load() throws SQLException {

        people.clear();
        String query = "SELECT * FROM people ORDER BY person_name";
        PreparedStatement statement;

        statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet != null){

            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String name = resultSet.getString("person_name");
                AgeCategory age = AgeCategory.valueOf(resultSet.getString("age"));
                EmploymentCategory employment = EmploymentCategory.valueOf(resultSet.getString("employment_status"));
                boolean isUSCitizen = resultSet.getBoolean("us_citizen");
                String taxID = resultSet.getString("tax_id");
                Gender gender = Gender.valueOf(resultSet.getString("gender"));
                String occupation = resultSet.getString("occupation");

                people.add(new Person(id, name, occupation, age, employment, isUSCitizen, taxID, gender));
            }
            for(Person person : people){

                System.out.println(person.toString());
            }
        }
        try {
            statement.close();
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
