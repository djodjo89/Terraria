package modele;

import physique.Collisionneur;
import physique.GameObject;

public abstract class Inventeriable extends GameObject {
	public void run() {
		
	}
	
	private Jeu jeu;
	
	public Inventeriable(String tag) {
		super(tag);
	}
	
	public Inventeriable(String tag, double pv, Collisionneur c, boolean estUnObstacle) {
		super (tag, pv, c, estUnObstacle) ;
	}
	
}
