/**
 * @(#) ConcreteMemento.java
 */

package memento;

/**
 * Classe ConcreteMemento du patron Memento.
 */
public abstract class ConcreteMemento implements Memento {

	private String state;

	public String getState() {
		System.out.println(this.toString() + ":getState: " + state);
		return state;
	}

	public void setState(String s) {
		System.out.println(this.toString() + ":setState: " + s);
		state = s;
	}

}
