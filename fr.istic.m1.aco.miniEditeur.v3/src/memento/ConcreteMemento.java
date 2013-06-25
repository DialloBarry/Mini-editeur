/**
 * @(#) ConcreteMemento.java
 */

package memento;

public abstract class ConcreteMemento implements Memento {
	private State state;
	
	public ConcreteMemento() {
		state = new State();
	}

	public State getState() {
		return new State(state);
	}

	public void setState(State s) {
		state = s;
	}

}
