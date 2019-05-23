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
	
	public Bloc (String tag) {
		
		super(tag);
		
	}
	public Bloc(String tag, double pv, Collisionneur c,boolean estUnObstacle) {
		
		super (tag,pv,c,estUnObstacle);
		
	}
	
}
