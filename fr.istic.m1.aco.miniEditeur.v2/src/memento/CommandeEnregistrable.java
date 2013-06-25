/**
 * @(#) CommandeEnregistrable.java
 */

package memento;

/**
 * Interface pour les commandes enregistrables de la v2. Il s'agit de
 * l'Originator du patron Memento.
 */
public interface CommandeEnregistrable {

	public void execute();
	public Memento getMemento();
	public void setMemento(Memento m);

}
