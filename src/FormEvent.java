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

    private String name, occupation;

      public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String occupation){

          super(source);
          this.name = name;
          this.occupation = occupation;
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
}
