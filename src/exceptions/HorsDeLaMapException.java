package exceptions;

import modele.* ;

/*
 * Avertit le joueur qu'il est situé en dehors des
 * bordures de la map.
 */

public class HorsDeLaMapException extends Exception {
	
	private Jeu jeu ;
	
	public HorsDeLaMapException (Jeu jeu) {
		
		this.jeu = jeu ;
		
	}
	
	public String toString () {
		
		return "Vous �tes apparu hors de la carte aux coordonn�es " + jeu.getPerso().getX() + ":" + jeu.getPerso().getY() + "alors qu'elle fait " + jeu.getTerrain().getDimX() * jeu.getMoteur().getTailleBoiteX() + ":" + jeu.getTerrain().getDimY() * jeu.getMoteur().getTailleBoiteY() ;
		
	}

}
