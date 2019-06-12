package objetRessources;

import physique.Collisionneur;
import physique.GameObject;

public class Terre extends Bloc {
	
	
	public Terre () {
		
		super (100, true) ;
		
	}
	
	public Terre (String tag, double pv, double x, double y, double masse, Collisionneur collisionneur) {
		
		super(100,true) ;
		this.setObstacle() ;
		super.setCoeffFrottement(10) ;
		
	}

}
