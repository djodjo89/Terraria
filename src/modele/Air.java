package modele;

import physique.Collisionneur;

/**
 * <h1>Air est un bloc non Inventeriable traversable</h1>
 * 
 * @version 1.1
 * @author Mathys
 *
 */

public class Air extends Bloc {
	
	public Air (String tag) {
		
		super(tag) ;
		
	}
	
	public Air (String tag, double pv, Collisionneur collisionneur) {
		
		super(tag, pv, collisionneur) ;
		
	}

}
