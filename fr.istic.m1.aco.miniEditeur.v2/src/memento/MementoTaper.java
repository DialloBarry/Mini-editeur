/**
 * @(#) MementoTaper.java
 */

package memento;

/**
 * Le memento associé à la commande enregistrable Taper.
 */
public class MementoTaper extends ConcreteMemento {
	private final String type = "taper";

	@Override
	public String getType() {
		return type;
	}

}
