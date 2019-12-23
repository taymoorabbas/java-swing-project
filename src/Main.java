/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 04-Dec-19
Time: 3:46 PM
Lau ji Ghauri aya fir
*/

import ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        UIManager.put("TabbedPane.background", Color.GRAY);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new MainFrame("Hello world");
            }
        });
    }
}
