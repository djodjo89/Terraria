package modele;

import physique.Collisionneur;
import physique.GameObject;

public class Air extends Bloc {
	
	public Air (String tag) {
		
		super(tag) ;
		
	}
	
	public Air (String tag, double pv, Collisionneur collisionneur) {
		
		super(tag, pv, collisionneur) ;
		
	}

}
