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


import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PanelGauche extends JPanel {
	public PanelGauche(String[] data, Grapher grapher, JTable table) {
		this.setLayout(new BorderLayout());
		BoutonsAction boutonsAction = new BoutonsAction(grapher,table); 
		
		this.add(boutonsAction, BorderLayout.NORTH);		
		this.add(table, BorderLayout.CENTER);	
	}
}