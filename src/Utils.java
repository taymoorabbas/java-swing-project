/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 09-Dec-19
Time: 6:33 PM
Lau ji Ghauri aya fir
*/

public class Utils {

    public static String getFileExtension(String fileName){

        int pointIndex = fileName.lastIndexOf(".");

        if(pointIndex == -1){

            return null;
        }
        if(pointIndex == fileName.length() - 1){

            return null;
        }
        return fileName.substring(pointIndex + 1);
    }
}
