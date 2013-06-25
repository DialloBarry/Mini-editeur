/**
 * @(#) SupprimerEnregistrable.java
 */

package memento;

import receiver.Buffer;
import command.Supprimer;

/**
 * Version enregistrable de la commande Supprimer.
 */
public class SupprimerEnregistrable extends Supprimer implements
		CommandeEnregistrable {
	public SupprimerEnregistrable(Buffer buf, Enregistreur enr) {
		super(buf);
		enregistreur = enr;
	}

	private Enregistreur enregistreur;

	@Override
	public void execute() {
		enregistreur.enregistrer(this);
		super.execute();
	}

	@Override
	public Memento getMemento() {
		return new MementoSupprimer();
	}

	@Override
	public void setMemento(Memento m) {
		// pas d'état à restaurer
		super.execute();
	}

}
