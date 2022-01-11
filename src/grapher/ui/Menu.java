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

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	
	public Menu(Grapher grapher, JTable table) {
		JMenu menu = new JMenu("Menu");
		JMenuItem retirerFonction = new JMenuItem();
		JMenuItem ajouterFonction = new JMenuItem();
		
		ajouterFonction.setAction(new AjouterFonction(grapher, table));
		retirerFonction.setAction(new RetirerFonction(grapher, table));
		
		add(menu);
		menu.add(ajouterFonction);
		menu.add(retirerFonction);
	}
}