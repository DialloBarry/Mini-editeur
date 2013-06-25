/**
 * @(#) CollerEnregistrable.java
 */

package memento;

import receiver.Buffer;
import command.Coller;

/**
 * Version enregistrable de la commande Coller.
 */
public class CollerEnregistrable extends Coller implements
		CommandeEnregistrable {
	
	public CollerEnregistrable(Buffer buf, Enregistreur enr) {
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
		return new MementoColler();
	}

	@Override
	public void setMemento(Memento m) {
		// pas d'état à restaurer
		super.execute();
	}

}
