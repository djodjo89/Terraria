package modele;

import physique.Collisionneur;

/**
 * <h1>Bloc est un bloc Inventeriable</h1>
 * 
 * @version 1.0
 * @author Romain
 *
 */

public class Bloc extends Inventeriable {
	
	
	public Bloc(String tag, double pv,boolean estUnObstacle) {
		
		super (tag,pv,null,estUnObstacle);
		
	}
	
}
