/**
 * @(#) TaperEnregistrable.java
 */

package memento;

import invoker.IHM;
import receiver.Buffer;

import command.Taper;

/**
 * Version enregistrable de la commande Taper.
 */
public class TaperEnregistrable extends Taper implements CommandeEnregistrable {
	public TaperEnregistrable(Buffer buffer, IHM ihm, Enregistreur enr) {
		super(buffer, ihm);
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
		MementoTaper m = new MementoTaper();
		m.setState("" + ihm.getCar());
		return m;
	}

	@Override
	public void setMemento(Memento m) {
		char c = ((MementoTaper) m).getState().charAt(0);
		buffer.taper(c);
	}

}
