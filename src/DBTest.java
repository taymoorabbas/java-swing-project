/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 22-Dec-19
Time: 10:12 PM
Lau ji Ghauri aya fir
*/

import model.*;

public class DBTest {

    public static void main(String[] args) {

        Database database = new Database();
        database.addPerson(new Person("taymoor", "software", AgeCategory.adult,
                EmploymentCategory.employed, true, "11223", Gender.male));

        database.addPerson(new Person("ashraf chaudhry", "sales", AgeCategory.adult,
                EmploymentCategory.employed, false, null , Gender.male));

//        database.connect();
//        database.load();
//        database.disconnect();
    }
}
