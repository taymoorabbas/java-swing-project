/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 5:05 AM
Lau ji Ghauri aya fir
*/

package ui;

import model.EmploymentCategory;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class EmploymentCategoryRenderer implements TableCellRenderer {

    private JComboBox comboBox;

    public EmploymentCategoryRenderer(){

        this.comboBox = new JComboBox(EmploymentCategory.values());
    }
    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row,
                                                   int column) {

        comboBox.setSelectedItem(value);


        return this.comboBox;
    }
}
