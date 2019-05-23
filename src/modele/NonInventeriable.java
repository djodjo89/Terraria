package modele;

import javafx.beans.property.SimpleDoubleProperty;
import physique.Collisionneur;
import physique.GameObject;

public abstract class NonInventeriable extends GameObject{
	
	public NonInventeriable () {
		
		super ("", 1000, new Collisionneur(),false) ;
		
	}
	
	public NonInventeriable (String nom, double pv, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c, double distanceDeplacement) {
		
		super (nom, pv, x, y, vitesseX, vitesseY, poids, c, distanceDeplacement) ;
		
	}

}
