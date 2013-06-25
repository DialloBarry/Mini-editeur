/**
 * @(#) MementoCopier.java
 */

package memento;

/**
 * Le memento associé à la commande enregistrable Copier.
 */
public class MementoCopier extends ConcreteMemento {
	private final String type = "copier";

	@Override
	public String getType() {
		return type;
	}

}
