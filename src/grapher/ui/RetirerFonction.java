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


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class RetirerFonction extends AbstractAction {

	Grapher grapher;
	JTable table;
	TableFonction tableFonction;
	
	public RetirerFonction(Grapher grapher, JTable table) {
		super();
		this.grapher = grapher;
		this.table = table;
		putValue(NAME, "Retirer une fonction");
		tableFonction = ((TableFonction) table.getModel());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(table.getSelectionModel().getMaxSelectionIndex() >= 0 ) { 
			tableFonction.retirerElement(table.getSelectionModel().getSelectedIndices()[0]);
			grapher.retirerFonction(table.getSelectionModel().getSelectedIndices()[0]);
		}		
	}

}