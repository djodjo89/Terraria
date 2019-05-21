package modele;

import physique.Collisionneur;
import physique.GameObject;

public class Air extends GameObject {
	
	public Air (String tag, double pv, double x, double y, Collisionneur collisionneur) {
		
		super(tag, pv, x, y, collisionneur) ;
		
	}

}
