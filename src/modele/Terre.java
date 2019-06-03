package modele;

import physique.Collisionneur;
import physique.GameObject;

public class Terre extends GameObject {
	
	public Terre (String tag) {
		
		super (tag) ;
		
	}
	
	public Terre (String tag, double pv, double x, double y, double masse, Collisionneur collisionneur) {
		
		super(tag, pv, x, y, masse, collisionneur) ;
		this.setObstacle() ;
		super.setCoeffFrottement(10) ;
		
	}

}
