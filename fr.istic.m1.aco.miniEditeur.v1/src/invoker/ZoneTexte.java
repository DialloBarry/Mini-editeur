/**
 * @(#) ZoneTexte.java
 */

package invoker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import command.Command;

/**
 * La zone de texte de notre éditeur. Elle affiche le contenu du buffer, permet
 * la manipulation de la sélection et se rafraîchit après une modification du
 * buffer.
 */
@SuppressWarnings("serial")
public class ZoneTexte extends JTextArea {

	private int debutSelection;
	private int longueurSelection;
	private String texte;
	private char dernierCar;

	public int getDebutSelection() {
		return debutSelection;
	}

	public int getLongueurSelection() {
		return longueurSelection;
	}

	public void setSelection(int debut, int longueur) {
		debutSelection = debut;
		longueurSelection = longueur;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
		this.setText(texte);
	}

	public char getDernierCar() {
		return dernierCar;
	}

	/**
	 * Constructeur de la zone de texte du mini-éditeur. On lui passe une
	 * HashMap de commandes pour ne pas avoir à gérer un trop grand nombre de
	 * paramètres.
	 * 
	 * @param h
	 *            la HashMap de commandes. Elle doit contenir les commandes
	 *            sélectionner, taper et supprimer.
	 */
	public ZoneTexte(HashMap<String, Command> h) {
		/*
		 * les commandes sont "finales" car elles doivent pouvoir être utilisées
		 * par les classes de Listeners que nous avons déclarées dynamiquement
		 */
		final Command selectionner = h.get("selectionner");
		final Command taper = h.get("taper");
		final Command supprimer = h.get("supprimer");

		addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				int i = Math.min(e.getDot(), e.getMark());
				int j = Math.max(e.getDot(), e.getMark());
				int l = j - i;

				if (i != getDebutSelection() || l != getLongueurSelection()) {
					setSelection(i, l);
					selectionner.execute();
				}
			}

		});

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.consume(); // annule le comportement par défaut
				char keyChar = e.getKeyChar();
				if (keyChar != '\b') {
					dernierCar = keyChar;
					taper.execute();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (!e.isActionKey()) {// si la touche est un caractère "normal"
					e.consume(); // annule le comportement par défaut
					if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
						supprimer.execute();
					}
				}
			}
		});
	}

	/**
	 * Rafraîchit la zone de texte pour afficher les modifications apportées au
	 * buffer.
	 * 
	 * @param texte
	 * @param start
	 * @param lg
	 */
	public void rafraichir(String texte, int start, int lg) {
		setTexte(texte);
		setSelectionStart(start);
		setSelectionEnd(start + lg);
		setSelection(start, lg);
		requestFocusInWindow();
	}

}
