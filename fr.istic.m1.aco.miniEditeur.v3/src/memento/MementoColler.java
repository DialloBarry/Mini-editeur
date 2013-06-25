/**
 * @(#) MementoColler.java
 */

package memento;

/**
 * Le memento associé à la commande enregistrable Coller.
 */
public class MementoColler extends ConcreteMemento {
	private final String type = "coller";

	@Override
	public String getType() {
		return type;
	}
	
	
}
