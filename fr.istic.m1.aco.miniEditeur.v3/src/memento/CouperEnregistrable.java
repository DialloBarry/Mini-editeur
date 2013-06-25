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
		if (buffer.getSelectionLongueur() > 0) {
			// on enregistre seulement s'il y a quelque chose à couper
			enregistreur.enregistrer(this);
		}
		super.execute();
	}

	@Override
	public Memento getMemento() {
		MementoCouper m = new MementoCouper();
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
		State s = ((MementoCouper) m).getState();
		buffer.setSelection(s.getDebSelec(), s.getLongSelec());
		// on appelle supprimer et non pas couper, car un rejouer ne doit pas
		// influencer le presse-papiers
		buffer.supprimer();
	}

}
