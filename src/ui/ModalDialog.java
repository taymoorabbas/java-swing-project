/*
Created by: Taymoor Ghazanfar
R.no: 3625-BSSE-F17-C
Date: 24-Dec-19
Time: 1:29 AM
Lau ji Ghauri aya fir
*/

package ui;

import javax.swing.*;
import java.awt.*;

public class ModalDialog extends JDialog {

    public ModalDialog(Window parent){

        super(parent, "Messages downloading...", ModalityType.APPLICATION_MODAL);
        setSize(400, 200);

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        this.add(new JLabel("Modal dialog demonstration"), gridBagConstraints);
    }
}
