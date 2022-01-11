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

import javax.swing.DefaultListModel;


import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ListeFonctions extends JList<String> {
	
	private static DefaultListModel<String> liste = new DefaultListModel<String>();
	
	public ListeFonctions(String[] listeBase, Grapher grapher) {
		super(liste);
		for(String fonction : listeBase) 
			liste.addElement(fonction);
		
		this.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) { grapher.fonctionSelect(getSelectedIndex()); }	
		});
	}
	
	public void ajouterElement(String fonction){ liste.addElement(fonction); }
	public void retirerElement(int index) { liste.removeElementAt(index); }
}