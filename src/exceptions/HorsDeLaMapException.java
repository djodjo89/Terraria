package exceptions;

import modele.* ;

/*
 * Avertit le joueur qu'il est situ√© en dehors des
 * bordures de la map.
 */

public class HorsDeLaMapException extends Exception {
	
	private Terrain t ;
	
	public HorsDeLaMapException (Terrain t) {
		
		this.t = t ;
		
	}
	
	public String toString () {
		
		return "Vous Ítes apparu hors de la carte" ;
		
	}

}
