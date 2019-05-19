package modele;

import physique.Collisionneur;
import physique.GameObject;

/**
 * <h1>Air est un GameObject</h1>
 * 
 * @version 1.0
 * @author Romain
 *
 */

public class Ennemi extends GameObject{
	
	public Ennemi(String tag, double pv, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur collisionneur, double distanceDeplacement) {
	
		super( tag,  pv,  x,  y,  vitesseX,  vitesseY,  poids,  collisionneur,  distanceDeplacement);
		
	}

}
