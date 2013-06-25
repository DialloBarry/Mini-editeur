/**
 * @(#) Enregistreur.java
 */

package memento;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * La classe Enregistreur permet d'enregistrer une suite de commandes et de les
 * rejouer. Il s'agit du Caretaker du patron Memento (v2).
 */
public class Enregistreur {

	private Queue<Memento> mementos;
	private HashMap<String, CommandeEnregistrable> commandes;
	private boolean etatrec;

	public Enregistreur() {
		mementos = new LinkedList<Memento>();
		commandes = new HashMap<String, CommandeEnregistrable>();
	}

	/**
	 * Ajoute une commande à la liste.
	 * 
	 * @param c
	 *            la commande à ajouter
	 */
	public void enregistrer(CommandeEnregistrable c) {
		if (etatrec) {
			mementos.add(c.getMemento());
		}
	}

	/**
	 * Rejoue une macro préalablement enregistrée. Ne fonctionne pas si une
	 * macro est en cours d'enregistrement. S'il n'y a pas encore de macro, ne
	 * fait rien.
	 */
	public void rejouer() {
		if (!etatrec) {
			for (Iterator<Memento> it = mementos.iterator(); it.hasNext();) {
				Memento m = it.next();
				commandes.get(m.getType()).setMemento(m);
			}
		}
	}

	/**
	 * Vide la précédente liste de commandes, et commence l'enregistrement d'une
	 * nouvelle macro.
	 */
	public void commencerEnregistrement() {
		mementos.clear();
		etatrec = true;
	}

	/**
	 * Arrête l'enregistrement d'une macro.
	 */
	public void arreterEnregistrement() {
		etatrec = false;
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
		if (h.get("copier") == null)
			throw new Exception("commande \"copier\" manquante");
		if (h.get("coller") == null)
			throw new Exception("commande \"coller\" manquante");
		if (h.get("taper") == null)
			throw new Exception("commande \"taper\" manquante");
		if (h.get("supprimer") == null)
			throw new Exception("commande \"supprimer\" manquante");
		commandes = h;
	}

}
