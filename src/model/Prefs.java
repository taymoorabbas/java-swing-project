/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 23-Dec-19
Time: 7:23 PM
Lau ji Ghauri aya fir
*/

package model;

import java.util.prefs.Preferences;

public class Prefs {

    private static Preferences preferences = Preferences.userRoot().node("Myprefs");

    public static void saveCount(int count){

        preferences.putInt("count", count);
    }

    public static int getCount(){

        return preferences.getInt("count", 1);
    }

    public static void savePort(int port){

        preferences.putInt("port", port);
    }

    public static int getPort(){

        return preferences.getInt("port", 3306);
    }

    public static void saveUsername(String username){

        preferences.put("username", username);
    }

    public static String getUsername(){

        return preferences.get("username", "");
    }

    public static void savePassword(String password){

        preferences.put("password", password);
    }

    public static String getPassword(){

        return preferences.get("password", "");
    }
}
