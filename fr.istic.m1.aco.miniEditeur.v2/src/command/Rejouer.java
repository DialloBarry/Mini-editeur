/**
 * @(#) Rejouer.java
 */

package command;

import memento.Enregistreur;

public class Rejouer extends Command {
	
	private Enregistreur enregistreur;
	
	public Rejouer(Enregistreur e) {
		enregistreur = e;
	}
	
	@Override
	public void execute() {
		enregistreur.rejouer();
	}

}
