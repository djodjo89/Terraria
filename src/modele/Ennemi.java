package modele;

import physique.Collisionneur;
import physique.GameObject;

public class Ennemi extends GameObject{
	
	public Ennemi(String tag, double pv, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur collisionneur, double distanceDeplacement) {
		super( tag,  pv,  x,  y,  vitesseX,  vitesseY,  poids,  collisionneur,  distanceDeplacement);
	}

}
