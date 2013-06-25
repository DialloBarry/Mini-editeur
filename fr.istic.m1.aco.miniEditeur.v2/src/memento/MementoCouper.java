/**
 * @(#) MementoCouper.java
 */

package memento;


/**
 * Le memento associé à la commande enregistrable Couper.
 */
public class MementoCouper extends ConcreteMemento{
	private final String type = "couper";

	@Override
	public String getType() {
		return type;
	}
	
	
}
