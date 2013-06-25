package command;

import memento.Enregistreur;

public class Refaire extends Command {

	private Enregistreur enregistreur;

	public Refaire(Enregistreur e) {
		enregistreur = e;
	}

	@Override
	public void execute() {
		enregistreur.refaire();
	}

}
