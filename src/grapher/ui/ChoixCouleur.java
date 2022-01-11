/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author corbillc
 */
package grapher.ui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


@SuppressWarnings("serial")
public class ChoixCouleur extends DefaultTableCellRenderer {
	
    public ChoixCouleur() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object couleur,boolean estSelect, boolean estVisee,int ligne, int colonne) {
        setBackground(null);
        Component tableCellRendererComponent = super.getTableCellRendererComponent(table, couleur, estSelect, estVisee, ligne, colonne);
        if (couleur instanceof Color) {
            setBackground((Color) couleur);
            setText("");
        }
        return tableCellRendererComponent;
    }

}