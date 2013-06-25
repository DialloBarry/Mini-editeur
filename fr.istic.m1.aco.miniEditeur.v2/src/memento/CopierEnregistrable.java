/**
 * @(#) CopierEnregistrable.java
 */

package memento;

import receiver.Buffer;
import command.Copier;

/**
 * Version enregistrable de la commande Copier.
 */
public class CopierEnregistrable extends Copier implements
		CommandeEnregistrable {

	public CopierEnregistrable(Buffer buf, Enregistreur enr) {
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
		return new MementoCopier();
	}

	@Override
	public void setMemento(Memento m) {
		// pas d'état à restaurer
		super.execute();
	}

}
