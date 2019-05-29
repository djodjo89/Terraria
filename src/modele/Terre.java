package modele;

import physique.Collisionneur;
import physique.GameObject;

public class Terre extends Bloc {
	
	
	public Terre (String tag) {
		
		super(tag, 100,true) ;
		this.setObstacle() ;
		
	}

}
