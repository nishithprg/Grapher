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
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class AjouterFonction extends AbstractAction {
	private Grapher grapher;
	TableFonction tableFonction;
	public AjouterFonction(Grapher grapher, JTable table) {
		super();
		this.grapher = grapher;
		putValue(NAME, "Ajouter une fonction");
		tableFonction = ((TableFonction) table.getModel());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	     String nouvelleFonction = JOptionPane.showInputDialog("Nouvelle expression :"); 
	     tableFonction.ajouterElement(nouvelleFonction, Color.BLACK);
	     grapher.ajouterFonction(nouvelleFonction, Color.BLACK);
	}
}
