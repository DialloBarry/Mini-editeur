/**
 * @(#) MementoSupprimer.java
 */

package memento;

/**
 * Le memento associé à la commande enregistrable Supprimer.
 */
public class MementoSupprimer extends ConcreteMemento{
	private final String type = "supprimer";

	@Override
	public String getType() {
		return type;
	}
	
	
}
