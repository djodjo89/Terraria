package objetRessources;

import modele.Jeu;

import physique.Collisionneur;
import physique.GameObject;

public abstract class Inventeriable extends GameObject {
	public void run() {
		
	}
	

	
	public Inventeriable(String tag) {
		super(tag);
	}
	
	public Inventeriable(String tag, double pv, Collisionneur c, boolean estUnObstacle) {
		super (tag, pv, c, estUnObstacle) ;
	}
	
	public abstract void utilisation(int x, int y,Jeu jeu);
	
	
}
	
	