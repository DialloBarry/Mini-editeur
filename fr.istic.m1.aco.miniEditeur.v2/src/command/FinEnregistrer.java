/**
 * @(#) FinEnregistrer.java
 */

package command;

import memento.Enregistreur;

public class FinEnregistrer extends Command {
	
	private Enregistreur enregistreur;

	public FinEnregistrer(Enregistreur e) {
		enregistreur = e;
	}
	
	@Override
	public void execute() {
		enregistreur.arreterEnregistrement();
	}

}
