/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 10-Dec-19
Time: 1:00 AM
Lau ji Ghauri aya fir
*/

package ui;

import model.EmploymentCategory;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanel extends JPanel {

    private JTable table;
    private PersonTableModel tableModel;
    private JPopupMenu popupMenu;
    private PersonTableListener personTableListener;

    public TablePanel(){

        this.setLayout(new BorderLayout());

        this.tableModel = new PersonTableModel();

        this.table = new JTable(this.tableModel);

        //adding custom renderer or this cell todo: implement all other cells by yourself
        this.table.setDefaultRenderer(EmploymentCategory.class, new EmploymentCategoryRenderer());
        this.table.setDefaultEditor(EmploymentCategory.class, new EmploymentCategoryEditor());

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

        this.popupMenu = new JPopupMenu();
        JMenuItem deleteRowItem = new JMenuItem("Delete row");
        deleteRowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow(); //single row
                System.out.println("row: " + row);

                if(personTableListener != null){

                    personTableListener.deleteRow(row);
                    tableModel.fireTableRowsDeleted(row, row);
                }

                int[] rows = table.getSelectedRows();
                //System.out.println(Arrays.toString(rows)); multiple rows
            }
        });
        this.popupMenu.add(deleteRowItem);

        this.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                //single row
                int row = table.rowAtPoint(e.getPoint()); //gets row at given x,y coordinate
                table.getSelectionModel().setSelectionInterval(row, row);
                
                //multiple rows
//                int[] rows = table.getSelectedRows();
//                table.getSelectionModel().setSelectionInterval(rows[0], rows[rows.length - 1]);

                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(table, e.getX(), e.getY()); //x, y == location where mouse was clicked
                }
            }
        });

        this.add(new JScrollPane(this.table), BorderLayout.CENTER); //table should be scrollable
    }

    //to get a sorter for each column
    public void autoSort(boolean autoSort){

        this.table.setAutoCreateRowSorter(autoSort);
    }
    public void newRow(){

        int row = (table.getModel().getRowCount()) - 1;
        table.getSelectionModel().setSelectionInterval(row, row);
    }

    public void setPersonTableListener(PersonTableListener personTableListener) {
        this.personTableListener = personTableListener;
    }

    public void setData(List<Person> people){

        this.tableModel.setData(people);
    }

    public void refresh(){

        this.tableModel.fireTableDataChanged();
    }

}
