/**
 * @(#) DebutEnregistrer.java
 */

package command;

import memento.Enregistreur;

public class DebutEnregistrer extends Command {
	
	private Enregistreur enregistreur;
	
	public DebutEnregistrer(Enregistreur e) {
		enregistreur = e;
	}
	
	@Override
	public void execute() {
		enregistreur.commencerEnregistrement();
	}

}
