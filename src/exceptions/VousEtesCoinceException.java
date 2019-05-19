package exceptions;

import physique.* ;
import modele.* ;
import java.util.HashSet ;

/*
 * Gère les case où le personnage apparaît ou
 * atterrit dans un obstacle. Il est alors 
 * coincé et ne peut plus bouger.
 * Afficher les coordonnées où le joueur est
 * bloqué
 */

public class VousEtesCoinceException extends Exception {
	
	private boolean coinBloque ;
	private Collisionneur c ;
	private Terrain t ;
	private Moteur m ;
	private HashSet <String> listeCoinsBloques ;
	
	
	public VousEtesCoinceException (Collisionneur c, Terrain t, Moteur m) {
		
		this.c = c ;
		this.t = t ;
		this.m = m ;
		this.listeCoinsBloques = new HashSet<> () ;
		
	}
	
	// Ces méthodes ajoutent les coordonnées des coins de la tuile du joueur bloqués
	// à la liste de coins bloqués
	
	// Dit au joueur où il est bloqué
	
	public String toString () {
		
		String message ;
		
		message = "Désolé, vous êtes coincé en : " ;
		
		for (String coordonnees : this.listeCoinsBloques) {
			
			message += coordonnees + "; " ;
			
		}
		
		return message ;
		
	}

}
