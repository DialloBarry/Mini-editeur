/**
 * @(#) Buffer.java
 */

package receiver;

import java.util.ArrayList;
import java.util.Iterator;

import observer.Observer;
import observer.Subject;

/**
 * La classe Buffer gère les modifications apportées au texte et au
 * presse-papiers.
 */
public class Buffer extends Subject {

	private String texte;
	private PressePapiers pressePapiers;
	private Selection selection;

	public String getTexte() {
		return texte;
	}

	public Buffer() {
		observers = new ArrayList<Observer>();
		texte = "";
		pressePapiers = new PressePapiers();
		selection = new Selection();
	}

	/**
	 * Copie le texte sélectionné dans le presse-papiers en remplaçant l'ancien
	 * et supprime le texte sélectionné du buffer ; ne fait rien si la sélection
	 * est nulle.
	 */
	public void couper() {
		if (selection.getLongueur() > 0) {
			pressePapiers.setContenu(getSelection());
			texte = texte.substring(0, selection.getDebut())
					+ texte.substring(selection.getDebut()
							+ selection.getLongueur());
			selection.setLongueur(0);
			notifyObservers();
		}
	}

	/**
	 * Copie le texte sélectionné dans le presse-papiers le contenu précédent du
	 * presse-papiers est remplacé, sauf si la sélection est nulle.
	 */
	public void copier() {
		if (selection.getLongueur() > 0) {
			pressePapiers.setContenu(getSelection());
		}
	}

	/**
	 * Colle le contenu du presse-papiers dans le buffer, à l'emplacement du
	 * curseur ou à la place du texte sélectionné.
	 */
	public void coller() {
		int i = selection.getDebut();
		if (selection.getLongueur() == 0) {
			texte = texte.substring(0, i) + pressePapiers.getContenu()
					+ texte.substring(i);
			selection.setDebut(i + pressePapiers.getContenu().length());
		} else {
			texte = texte.substring(0, i) + pressePapiers.getContenu()
					+ texte.substring(i + selection.getLongueur());
			selection.setDebut(i + pressePapiers.getContenu().length());
			selection.setLongueur(0);
		}
		notifyObservers();
	}

	/**
	 * Insère le caractère tapé dans le buffer à l'emplacement du curseur, ou à
	 * la place du texte sélectionné.
	 * 
	 * @param c
	 *            le caractère tapé
	 */
	public void taper(char c) {
		int i = selection.getDebut();
		if (selection.getLongueur() == 0) {
			texte = texte.substring(0, i) + c + texte.substring(i);
			selection.setDebut(i + 1);
		} else {
			texte = texte.substring(0, i) + c
					+ texte.substring(i + selection.getLongueur());
			selection.setDebut(i + 1);
			selection.setLongueur(0);
		}
		notifyObservers();
	}

	/**
	 * Supprime le texte sélectionné dans le buffer ; si la sélection est nulle,
	 * supprime le caractère derrière le curseur.
	 */
	public void supprimer() {
		int i = selection.getDebut();
		if (selection.getLongueur() > 0) {
			texte = texte.substring(0, i)
					+ texte.substring(i + selection.getLongueur());
			selection.setLongueur(0);
		} else if (i > 0) {
			texte = texte.substring(0, i - 1) + texte.substring(i);
			selection.setDebut(i - 1);
		}		
		notifyObservers();
	}

	/**
	 * Renvoie la sous-chaîne du texte actuellement sélectionnée.
	 * 
	 * @return le texte sélectionné
	 */
	public String getSelection() {
		int i = selection.getDebut(), l = selection.getLongueur();
		if (selection.getLongueur() > 0) {
			return texte.substring(i, i + l);
		} else {
			return "";
		}
	}
	
	public int getSelectionDebut() {
		return selection.getDebut();
	}
	
	public int getSelectionLongueur() {
		return selection.getLongueur();
	}

	@Override
	public void notifyObservers() {
		for (Iterator<Observer> it = observers.iterator(); it.hasNext();) {
			Observer o = it.next();
			o.notifyMe();
		}
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);

	}

	@Override
	public void unregisterObserver(Observer o) {
		observers.remove(o);
	}

	public void setSelection(int debut, int longueur) {
		selection.setDebut(debut);
		selection.setLongueur(longueur);
	}

	public String getPPContenu() {
		return pressePapiers.getContenu();
	}
}
