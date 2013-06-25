/**
 * @(#) Bouton.java
 */

package invoker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import command.Command;

/**
 * Élément de l'IHM chargé d'exécuter une commande lorsqu'il reçoit un clic.
 */
@SuppressWarnings("serial")
public class Bouton extends JButton {
	private String nom;

	public String getNom() {
		return nom;
	}

	/**
	 * Constructeur de la classe Bouton.
	 * 
	 * @param nom
	 *            le nom visible du bouton
	 * @param cmd
	 *            la commande à exécuter lors d'un clic
	 */
	public Bouton(String nom, final Command cmd) {
		super(nom);
		this.nom = nom;
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cmd.execute();
			}
		});
	}

}
