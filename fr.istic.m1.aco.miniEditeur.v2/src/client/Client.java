/**
 * @(#) Client.java
 */

package client;

import java.util.HashMap;

import observer.IHMObserver;
import receiver.Buffer;

import command.*;
import memento.*;

/**
 * Classe principale : instancie et relie les différents composants de
 * l'application.
 */
public class Client {
	private static final int ZONE_TEXTE_ROWS = 20;
	private static final int ZONE_TEXTE_COLS = 60;

	private static Buffer buffer;
	private static IHMObserver ihm;
	private static Enregistreur enregistreur;
	private static HashMap<String, Command> commandes;

	public static void main(String[] args) throws Exception {
		// initialisation
		buffer = new Buffer();
		ihm = new IHMObserver(buffer);
		
		// création de l'enregistreur et des commandes
		enregistreur = new Enregistreur();
		commandes = new HashMap<String, Command>();
		HashMap<String, CommandeEnregistrable> ce = new HashMap<String, CommandeEnregistrable>();
		
		CouperEnregistrable couper = new CouperEnregistrable(buffer, enregistreur);
		commandes.put("couper", couper);
		ce.put("couper", couper);
		
		CopierEnregistrable copier = new CopierEnregistrable(buffer, enregistreur);
		commandes.put("copier", copier);
		ce.put("copier", copier);
		
		CollerEnregistrable coller = new CollerEnregistrable(buffer, enregistreur);
		commandes.put("coller", coller);
		ce.put("coller", coller);
		
		commandes.put("selectionner", new Selectionner(buffer, ihm));
		
		TaperEnregistrable taper = new TaperEnregistrable(buffer, ihm, enregistreur);
		commandes.put("taper", taper);
		ce.put("taper", taper);
		
		SupprimerEnregistrable supprimer = new SupprimerEnregistrable(buffer, enregistreur);
		commandes.put("supprimer", supprimer);
		ce.put("supprimer", supprimer);

		commandes.put("debutEnregistrer", new DebutEnregistrer(enregistreur));
		commandes.put("finEnregistrer", new FinEnregistrer(enregistreur));
		commandes.put("rejouer", new Rejouer(enregistreur));
		
		ihm.setCommandes(commandes);
		enregistreur.setCommandes(ce);

		// mise en place de l'observer
		buffer.registerObserver(ihm);

		// finalisation de l'ihm
		ihm.creerZoneTexte(ZONE_TEXTE_ROWS, ZONE_TEXTE_COLS);
		ihm.creerTousBoutons();
		ihm.afficher();
	}

}
