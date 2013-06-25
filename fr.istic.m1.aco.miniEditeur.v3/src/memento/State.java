/**
 * @(#) State.java
 */

package memento;

public class State {

	private int debSelec;
	private int longSelec;
	private String texteSelec;
	private String ppTexte;
	private char car;

	public State() {
		debSelec = 0;
		longSelec = 0;
		texteSelec = "";
		ppTexte = "";
		car = 'x';
	}
	
	public State(State s2) {
		debSelec = s2.debSelec;
		longSelec = s2.longSelec;
		texteSelec = s2.texteSelec;
		ppTexte = s2.ppTexte;
		car = s2.car;
	}

	public int getDebSelec() {
		return debSelec;
	}

	public void setDebSelec(int d) {
		debSelec = d;
	}

	public int getLongSelec() {
		return longSelec;
	}

	public void setLongSelec(int l) {
		longSelec = l;
	}

	public String getTexteSelec() {
		return texteSelec;
	}

	public void setTexteSelect(String t) {
		texteSelec = t;
	}

	public String getPPTexte() {
		return ppTexte;
	}

	public void setPPTexte(String t) {
		ppTexte = t;
	}

	public char getCar() {
		return car;
	}

	public void setCar(char c) {
		car = c;
	}

}
