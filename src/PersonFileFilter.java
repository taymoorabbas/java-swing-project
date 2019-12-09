/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 09-Dec-19
Time: 6:30 PM
Lau ji Ghauri aya fir
*/

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PersonFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {

        if(f.isDirectory()){

            return true;
        }

        String name = f.getName();
        String extension = Utils.getFileExtension(name);

        if (extension == null) {

            return false;
        }
        return extension.equals("per");
    }

    @Override
    public String getDescription() {
        return "Person database files (*.per)";
    }
}
