/* grapher.ui.Main
 * (c) blanch@imag.fr 2021–                                                */

package grapher.ui;

import java.awt.Color;


import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



@SuppressWarnings("serial")
public class Main extends JFrame {
	Main(String title, String[] expressions) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Grapher grapher = new Grapher();		
		TableFonction tableModel = new TableFonction();
		JTable table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new ChoixCouleur());
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				grapher.setSelectedFunction(table.getSelectionModel().getSelectedIndices()[0]);
				if(table.getSelectedColumn() == 1) { 
					Color couleur = JColorChooser.showDialog(null, "Choisir une couleur", (Color) table.getModel().getValueAt(table.getSelectedRow(), 1));
					if(couleur == null) 
						return;
					grapher.changerCouleur(couleur, table.getSelectedRow());
					tableModel.changerCouleur(couleur, table.getSelectedRow());
				}
			}
		});
		
		PanelGauche panelGauche =  new PanelGauche(expressions, grapher, table);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelGauche, grapher);
		for(String expression : expressions) {
			grapher.ajouterFonction(expression, Color.black);
			tableModel.ajouterElement(expression, Color.black);
		}		
		setJMenuBar(new Menu(grapher, table));
		add(splitPane);
		pack();
	}

	public static void main(String[] argv) {
		final String[] expressions = argv;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				new Main("grapher", expressions).setVisible(true); 
			}
		});
	}
}