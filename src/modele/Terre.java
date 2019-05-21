package modele;

import physique.Collisionneur;
import physique.GameObject;

public class Terre extends GameObject {
	
	public Terre (String tag, double pv, double x, double y, Collisionneur collisionneur) {
		
		super(tag, pv, x, y, collisionneur) ;
		this.setObstacle() ;
		
	}

}
