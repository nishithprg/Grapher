/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grapher.ui;

/**
 *
 * @author corbillc
 */

import java.awt.Color;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableFonction extends AbstractTableModel {

    private String[] colonnes = {
			"Fonction",
			"Couleur"
		};
    
    private Object[][] fonctions = new Object[0][2];
    
    public TableFonction() { super(); };

    
	public void retirerElement(int indice) {
		if(fonctions.length <= 0) return;
		
		if(fonctions.length == 1) {
			Object[][] listeAJour = new Object[0][2];
	        fireTableCellUpdated(fonctions.length-1, 0);
	        fireTableCellUpdated(fonctions.length-1, 1);
	        fonctions = listeAJour;
	        return;
		}
		
		Object[][] listeAJour = new Object[fonctions.length-1][2];
		
		for(int i = 0; i < fonctions.length; i++) {
			if(i < indice) {
				listeAJour[i][0] = fonctions[i][0]; listeAJour[i][1] = fonctions[i][1];
			} else if(i > indice) {
				listeAJour[i - 1][0] = fonctions[i][0]; listeAJour[i - 1][1] = fonctions[i][1];
			}
		}
        
		fireTableCellUpdated(fonctions.length-1, 0);
        fireTableCellUpdated(fonctions.length-1, 1);
        fonctions = listeAJour;
        fireTableCellUpdated(fonctions.length-1, 0);
        fireTableCellUpdated(fonctions.length-1, 1);
	}
	
	public void ajouterElement(String s,Color couleur) {
		Object[][] listeAJour = new Object[fonctions.length+1][2];
		for(int i = 0; i < fonctions.length; i++) {
			listeAJour[i][0] = fonctions[i][0]; listeAJour[i][1] = fonctions[i][1];
		}
		listeAJour[fonctions.length][0] = s;
		listeAJour[fonctions.length][1] = couleur;
		fonctions = listeAJour;
        fireTableCellUpdated(fonctions.length-1, 0);
        fireTableCellUpdated(fonctions.length-1, 1);
 	}
	
	public int getRowCount() { return fonctions.length; }
	public int getColumnCount() { return colonnes.length; }

	public Object getValueAt(int ligne, int colonne) {
		if(colonne >= colonnes.length || ligne >= fonctions.length) return null;
		return fonctions[ligne][colonne];
	}
	


	
	public void changerCouleur(Color couleur, int ligne) {
		fonctions[ligne][1] = couleur;
        fireTableCellUpdated(ligne, 1);
	}
	

}