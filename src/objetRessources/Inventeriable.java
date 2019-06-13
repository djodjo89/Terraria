package objetRessources;

import modele.Jeu;

import physique.Collisionneur;
import physique.GameObject;

public abstract class Inventeriable extends GameObject {
	public void run() {
		
	}
	

	
	public Inventeriable() {
		super();
	}
	
	public Inventeriable(double pv, Collisionneur c, boolean estUnObstacle) {
		super (pv, c, estUnObstacle) ;
	}
	
	public abstract void utilisation(int x, int y,Jeu jeu);
	
	
}
	
	