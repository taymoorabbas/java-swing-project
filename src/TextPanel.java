/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 4:56 PM
Lau ji Ghauri aya fir
*/

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {

    private JTextArea textArea;

    public TextPanel(){

        this.setLayout(new BorderLayout());
        this.textArea = new JTextArea();

        this.add(new JScrollPane(this.textArea), BorderLayout.CENTER);
        this.setBorder(BorderFactory.
                createCompoundBorder(BorderFactory.createEmptyBorder(5,2,5,5),
                        BorderFactory.createLineBorder(Color.orange, 2,true) ));
    }

    public void appendText(String text){

        this.textArea.append(text);
    }
}
