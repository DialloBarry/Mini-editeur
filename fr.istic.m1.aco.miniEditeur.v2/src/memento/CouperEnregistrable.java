/**
 * @(#) CouperEnregistrable.java
 */

package memento;

import receiver.Buffer;
import command.Couper;

/**
 * Version enregistrable de la commande Couper.
 */
public class CouperEnregistrable extends Couper implements
		CommandeEnregistrable {
	public CouperEnregistrable(Buffer buf, Enregistreur enr) {
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
		return new MementoCouper();
	}

	@Override
	public void setMemento(Memento m) {
		// pas d'état à restaurer
		super.execute();
	}

}
