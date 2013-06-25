/**
 * @(#) Subject.java
 */

package observer;

import java.util.Collection;

/**
 * La classe Subject du patron de conception Observer. Son rôle est de notifier
 * ses observers quand une mise à jour est nécessaire.
 * 
 */
public abstract class Subject {
	protected Collection<Observer> observers;

	/**
	 * Notifie tous les <i>Observers</i> enregistrés.
	 */
	public abstract void notifyObservers();

	/**
	 * Inscrit un nouvel <i>Observer</i>.
	 * 
	 * @param o
	 *            l'observer à enregistrer
	 */
	public abstract void registerObserver(Observer o);

	/**
	 * Désinscrit un <i>Observer</i> précédemment inscrit.
	 * 
	 * @param o
	 *            l'observer à désinscrire ; si o n'est pas dans observers,
	 *            cette méthode ne fait rien.
	 */
	public abstract void unregisterObserver(Observer o);

}
