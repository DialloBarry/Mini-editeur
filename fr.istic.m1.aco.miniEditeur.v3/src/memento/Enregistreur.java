/**
 * @(#) Enregistreur.java
 */

package memento;

import java.util.HashMap;
import java.util.Stack;

import receiver.Buffer;

/**
 * La classe Enregistreur permet d'enregistrer les commandes pour les annuler
 * (défaire), et éventuellement les restaurer (refaire). Il s'agit du Caretaker
 * du patron Memento (v3).
 */
public class Enregistreur {

	private Stack<Memento> aDefaire;
	private Stack<Memento> aRefaire;
	private HashMap<String, CommandeEnregistrable> commandes;
	private Buffer buffer;

	public Enregistreur(Buffer b) {
		aDefaire = new Stack<Memento>();
		aRefaire = new Stack<Memento>();
		commandes = new HashMap<String, CommandeEnregistrable>();
		buffer = b;
	}

	/**
	 * Place une commande dans la liste des commandes à défaire. Comme il s'agit
	 * d'une nouvelle action, la liste des commandes précedemment annulées
	 * (aRefaire) est vidée.
	 * 
	 * @param c
	 *            la nouvelle commande
	 */
	public void enregistrer(CommandeEnregistrable c) {
		aRefaire.clear();
		Memento m = c.getMemento();
		System.out.println("enregistre : " + m.getType());
		aDefaire.push(m);
	}

	/**
	 * Défait la dernière commande enregistrée dans la pile aDefaire.
	 */
	public void defaire() {
		if (!aDefaire.empty()) {
			Memento m = aDefaire.pop();
			aRefaire.push(m);
			State s = ((ConcreteMemento) m).getState();
			System.out.println("* défaire : " + m.getType());
			if (m.getType() == "couper") {

				buffer.setSelection(s.getDebSelec(), 0);
				for (int i = 0; i < s.getTexteSelec().length(); i++) {
					buffer.taper(s.getTexteSelec().charAt(i));
				}

			} else if (m.getType() == "coller") {

				buffer.setSelection(s.getDebSelec(), s.getPPTexte().length());
				buffer.supprimer();
				for (int i = 0; i < s.getTexteSelec().length(); i++) {
					buffer.taper(s.getTexteSelec().charAt(i));
				}

			} else if (m.getType() == "taper") {

				buffer.setSelection(s.getDebSelec(), 1);
				buffer.supprimer();
				for (int i = 0; i < s.getTexteSelec().length(); i++) {
					buffer.taper(s.getTexteSelec().charAt(i));
				}

			} else if (m.getType() == "supprimer") {

				if (s.getLongSelec() == 0) {
					buffer.setSelection(s.getDebSelec() - 1, 0);
					buffer.taper(s.getCar());
				} else {
					buffer.setSelection(s.getDebSelec(), 0);
					for (int i = 0; i < s.getTexteSelec().length(); i++) {
						buffer.taper(s.getTexteSelec().charAt(i));
					}
				}

			}
		}
	}

	/**
	 * Rejoue une commande précédemment défaite.
	 */
	public void refaire() {
		if (!aRefaire.empty()) {
			Memento m = aRefaire.pop();
			aDefaire.push(m);
			System.out.println("* refaire : " + m.getType());
			commandes.get(m.getType()).setMemento(m);
		}
	}

	/**
	 * Initialise les commandes.
	 * 
	 * @param h
	 *            Une hashmap contenant les commandes.
	 * @throws Exception
	 *             Une exception est levée si la hashmap ne contient pas les
	 *             commandes couper, copier, coller, taper et supprimer.
	 */
	public void setCommandes(HashMap<String, CommandeEnregistrable> h)
			throws Exception {
		if (h.get("couper") == null)
			throw new Exception("commande \"couper\" manquante");
		if (h.get("coller") == null)
			throw new Exception("commande \"coller\" manquante");
		if (h.get("taper") == null)
			throw new Exception("commande \"taper\" manquante");
		if (h.get("supprimer") == null)
			throw new Exception("commande \"supprimer\" manquante");
		commandes = h;
	}

}
