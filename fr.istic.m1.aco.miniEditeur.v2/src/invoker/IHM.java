/**
 * @(#) IHM.java
 */

package invoker;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import command.Command;

public class IHM {

	private JFrame frame;
	private Box boxTexte;
	private Box boxBoutonsV1;
	private Box boxBoutonsV2;
	private Collection<Bouton> boutons;

	// zoneTexte est protected pour pouvoir être manipulée par IHMObserver
	protected ZoneTexte zoneTexte;

	// nous avons préféré nommmer explicitement chacune des commandes
	private Command couper;
	private Command copier;
	private Command coller;
	private Command selectionner;
	private Command taper;
	private Command supprimer;
	private Command debutEnregistrer;
	private Command finEnregistrer;
	private Command rejouer;

	public IHM() {
		frame = new JFrame("Éditeur v2 : macros");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		boxTexte = Box.createHorizontalBox();
		boxBoutonsV1 = Box.createHorizontalBox();
		boxBoutonsV2 = Box.createHorizontalBox();

		boutons = new ArrayList<Bouton>();
	}

	/**
	 * Paramètre les options d'affichage de la fenêtre, assemble les composants
	 * graphiques, puis affiche la fenêtre.
	 * 
	 */
	public void afficher() {
		boxTexte.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		frame.add(boxTexte);
		boxBoutonsV1.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		frame.add(boxBoutonsV1);
		boxBoutonsV2.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		frame.add(boxBoutonsV2);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Crée un bouton dans l'IHM et l'ajoute au conteneur donné.
	 * 
	 * @param nom
	 *            le nom visible du bouton
	 * @param cmd
	 *            l'action associée au bouton
	 * @param box
	 *            le conteneur auquel ajouter le bouton
	 */
	private void creerBouton(String nom, Command cmd, Box box) {
		Bouton bouton = new Bouton(nom, cmd);
		boutons.add(bouton);
		bouton.setMaximumSize(new Dimension(Short.MAX_VALUE, bouton
				.getPreferredSize().height));
		bouton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		// rend le focus à la zone de texte après un clic sur un bouton
		bouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				zoneTexte.requestFocusInWindow();
			}
		});

		box.add(bouton);
	}

	/**
	 * Crée tous les boutons de l'IHM.
	 */
	public void creerTousBoutons() {
		creerBouton("Couper", couper, boxBoutonsV1);
		creerBouton("Copier", copier, boxBoutonsV1);
		creerBouton("Coller", coller, boxBoutonsV1);
		creerBouton("Début macro", debutEnregistrer, boxBoutonsV2);
		creerBouton("Fin macro", finEnregistrer, boxBoutonsV2);
		creerBouton("Rejouer", rejouer, boxBoutonsV2);
	}

	/**
	 * Crée et affiche une zone de texte éditable dans l'IHM.
	 */
	public void creerZoneTexte(int rows, int cols) {
		HashMap<String, Command> h = new HashMap<String, Command>();
		h.put("selectionner", selectionner);
		h.put("taper", taper);
		h.put("supprimer", supprimer);
		zoneTexte = new ZoneTexte(h);
		zoneTexte.setRows(rows);
		zoneTexte.setColumns(cols);
		JScrollPane scroll = new JScrollPane(zoneTexte);
		boxTexte.add(scroll);
	}

	/**
	 * Obtient le plus récent caractère tapé dans la zone de texte.
	 * 
	 * @return char c
	 */
	public char getCar() {
		return zoneTexte.getDernierCar();
	}

	/**
	 * Obtient la position de début du texte sélectionné dans la zone de texte
	 * 
	 * @return int debut
	 */
	public int getDebutSelection() {
		return zoneTexte.getDebutSelection();
	}

	/**
	 * Obtient la longueur du texte sélectionné dans la zone de texte
	 * 
	 * @return int longueur
	 */
	public int getLongueurSelection() {
		return zoneTexte.getLongueurSelection();
	}

	/**
	 * Setter pour initialiser toutes les commandes.
	 * 
	 * @param h
	 *            une HashMap contenant les commandes.
	 * @throws Exception
	 *             La méthode s'assure que <b>h</b> contient au moins les
	 *             commandes "couper", "copier", "coller", "sélectionner",
	 *             "taper" et "supprimer". Si l'une de ces commandes est
	 *             manquante, une exception sera lancée.
	 */
	public void setCommandes(HashMap<String, Command> h) throws Exception {
		this.couper = h.get("couper");
		if (this.couper == null)
			throw new Exception("commande \"couper\" manquante");

		this.copier = h.get("copier");
		if (this.copier == null)
			throw new Exception("commande \"copier\" manquante");

		this.coller = h.get("coller");
		if (this.coller == null)
			throw new Exception("commande \"coller\" manquante");

		this.selectionner = h.get("selectionner");
		if (this.selectionner == null)
			throw new Exception("commande \"selectionner\" manquante");

		this.taper = h.get("taper");
		if (this.taper == null)
			throw new Exception("commande \"taper\" manquante");

		this.supprimer = h.get("supprimer");
		if (this.supprimer == null)
			throw new Exception("commande \"supprimer\" manquante");

		this.debutEnregistrer = h.get("debutEnregistrer");
		if (this.debutEnregistrer == null)
			throw new Exception("commande \"debutEnregistrer\" manquante");

		this.finEnregistrer = h.get("finEnregistrer");
		if (this.finEnregistrer == null)
			throw new Exception("commande \"finEnregistrer\" manquante");

		this.rejouer = h.get("rejouer");
		if (this.rejouer == null)
			throw new Exception("commande \"rejouer\" manquante");
	}

}
