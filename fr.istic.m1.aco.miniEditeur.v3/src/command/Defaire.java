package command;

import memento.Enregistreur;

public class Defaire extends Command {

	private Enregistreur enregistreur;
	
	public Defaire(Enregistreur e) {
		enregistreur = e;
	}

	@Override
	public void execute() {
		enregistreur.defaire();
	}

}
