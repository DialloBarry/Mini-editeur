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
		if (buffer.getSelectionDebut() > 0 || buffer.getSelectionLongueur() > 0) {
			// on enregistre le supprimer seulement s'il a un effet 
			enregistreur.enregistrer(this);
		}
		super.execute();
	}

	@Override
	public Memento getMemento() {
		MementoSupprimer m = new MementoSupprimer();
		State s = m.getState();
		if (buffer.getSelectionLongueur() > 0) {
			s.setTexteSelect(buffer.getSelection());
		} else {
			int i = buffer.getSelectionDebut();
			if (i > 0) {
				s.setCar(buffer.getTexte().charAt(i - 1));
			}
		}
		s.setDebSelec(buffer.getSelectionDebut());
		s.setLongSelec(buffer.getSelectionLongueur());
		m.setState(s);
		return m;
	}

	@Override
	public void setMemento(Memento m) {
		State s = ((MementoSupprimer) m).getState();
		buffer.setSelection(s.getDebSelec(), s.getLongSelec());
		buffer.supprimer();
	}

}
