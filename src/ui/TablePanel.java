/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 10-Dec-19
Time: 1:00 AM
Lau ji Ghauri aya fir
*/

package ui;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TablePanel extends JPanel {

    private JTable table;
    private PersonTableModel tableModel;

    public TablePanel(){

        this.setLayout(new BorderLayout());

        this.tableModel = new PersonTableModel();

        this.table = new JTable(this.tableModel);
        this.table.setFont(new Font("SanSerif", Font.BOLD, 14));
        this.table.setRowHeight(25);
        this.table.getTableHeader().setOpaque(false);
        this.table.getTableHeader().setFont(new Font("SanSerif", Font.BOLD, 16));
        this.table.getTableHeader().setBackground(Color.DARK_GRAY);
        this.table.getTableHeader().setForeground(Color.YELLOW);
        table.getColumnModel().getColumn(0).setPreferredWidth(3);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(3);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(6).setPreferredWidth(40);
        table.getColumnModel().getColumn(7).setPreferredWidth(40);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        this.add(new JScrollPane(this.table), BorderLayout.CENTER); //table should be scrollable
    }
    public void setData(ArrayList<Person> people){

        this.tableModel.setData(people);
    }

    public void refresh(){

        this.tableModel.fireTableDataChanged();
    }

}
