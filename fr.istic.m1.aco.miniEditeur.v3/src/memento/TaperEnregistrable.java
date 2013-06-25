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
		State s = m.getState();
		s.setTexteSelect(buffer.getSelection());
		s.setDebSelec(buffer.getSelectionDebut());
		s.setLongSelec(buffer.getSelectionLongueur());
		s.setCar(ihm.getCar());
		m.setState(s);
		return m;
	}

	@Override
	public void setMemento(Memento m) {
		State s = ((MementoTaper) m).getState();
		buffer.setSelection(s.getDebSelec(), s.getLongSelec());
		buffer.taper(s.getCar());
	}

}
