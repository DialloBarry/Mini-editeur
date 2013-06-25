/**
 * @(#) Client.java
 */

package client;

import java.util.HashMap;

import observer.IHMObserver;
import receiver.Buffer;

import command.*;

/**
 * Classe principale : instancie et relie les différents composants de
 * l'application.
 */
public class Client {
	private static final int ZONE_TEXTE_ROWS = 20;
	private static final int ZONE_TEXTE_COLS = 60;

	private static Buffer buffer;
	private static IHMObserver ihm;
	private static HashMap<String, Command> commmandes;

	public static void main(String[] args) throws Exception {
		// initialisation
		buffer = new Buffer();
		ihm = new IHMObserver(buffer);

		// création des commandes
		commmandes = new HashMap<String, Command>();
		commmandes.put("couper", new Couper(buffer));
		commmandes.put("copier", new Copier(buffer));
		commmandes.put("coller", new Coller(buffer));
		commmandes.put("selectionner", new Selectionner(buffer, ihm));
		commmandes.put("taper", new Taper(buffer, ihm));
		commmandes.put("supprimer", new Supprimer(buffer));
		ihm.setCommands(commmandes);

		// mise en place de l'observer
		buffer.registerObserver(ihm);

		// finalisation de l'ihm
		ihm.creerZoneTexte(ZONE_TEXTE_ROWS, ZONE_TEXTE_COLS);
		ihm.creerTousBoutons();
		ihm.afficher();
	}

}
