package exceptions;

import modele.* ;

public class HorsDeLaMapException extends Exception {
	
	private Terrain t ;
	private Personnage p ;
	
	public HorsDeLaMapException (Terrain t, Personnage p) {
		
		this.t = t ;
		this.p = p ;
		
	}
	
	public String toString () {
		
		return p.getTag() + " est apparu hors de la carte : ses coordonnées sont " + p.getX() + ":" + p.getY()
		+ " alors que la carte a pour dimensions : " + t.getTailleX() + ":" + t.getTailleY() ;
		
	}

}
