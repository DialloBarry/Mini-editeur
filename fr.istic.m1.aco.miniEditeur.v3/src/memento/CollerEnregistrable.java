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
		if (buffer.getPPContenu().length() > 0) {
			// on enregistre seulement s'il y a quelque chose à coller
			enregistreur.enregistrer(this);
		}
		super.execute();
	}

	@Override
	public Memento getMemento() {
		MementoColler m = new MementoColler();
		State s = m.getState();
		s.setTexteSelect(buffer.getSelection());
		s.setDebSelec(buffer.getSelectionDebut());
		s.setLongSelec(buffer.getSelectionLongueur());
		s.setPPTexte(buffer.getPPContenu());
		m.setState(s);
		return m;
	}

	@Override
	public void setMemento(Memento m) {
		State s = ((MementoColler) m).getState();
		buffer.setSelection(s.getDebSelec(), s.getLongSelec());
		super.execute();
	}

}
