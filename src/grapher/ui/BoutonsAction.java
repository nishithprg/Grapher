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

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class BoutonsAction extends JToolBar {
	public BoutonsAction( Grapher grapher, JTable table) {
		RetirerFonction retirerFonction = new RetirerFonction(grapher, table);
		AjouterFonction ajouterFonction = new AjouterFonction(grapher, table);
		JButton boutonAjouter = new JButton();
		JButton boutonRetirer = new JButton();
		
		
		boutonAjouter.setAction(ajouterFonction);
		boutonAjouter.setText("+");
		boutonRetirer.setAction(retirerFonction);
		boutonRetirer.setText("-");
		
		this.add(boutonAjouter);
		this.add(boutonRetirer);
		
	}
}

