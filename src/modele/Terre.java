package modele;

import physique.Collisionneur;
import physique.GameObject;

public class Terre extends GameObject {
	
	public Terre (String tag) {
		
		super(tag) ;
		this.setObstacle() ;
		
	}
	
	public Terre (String tag, double pv, Collisionneur collisionneur) {
		
		super(tag, pv, collisionneur) ;
		this.setObstacle() ;
		
	}

}
