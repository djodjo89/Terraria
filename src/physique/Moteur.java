package physique;

import java.util.ArrayList;

import exceptions.HorsDeLaMapException;
import modele.*;

//!\\ MODIFIABLE
/*
 * Moteur gère la physique du jeu, pour l'instant il
 * y en a assez peu mais le reste viendra plus tard.
 * Il a une liste d'obstacles à laquelle il peut en
 * ajouter de nouveau. Il a également une distance de
 * déplacement qui sera partagée par tous les objets
 * utilisant le moteur. Enfin il peut envoyer une exception
 * si un personnage apparaît bien dans un Terrain à partir
 * de ses coordonnées.
 */

public class Moteur {
	
	private ArrayList<String> obstacles  ;
	private double tailleTileX ;
	private double tailleTileY ;
	private double distanceDeplacement ;
	private double gravite ;
	
	public Moteur (double tailleTileX, double tailleTileY, double distanceDeplacement, double gravite) {
		
		this.obstacles = new ArrayList<> () ;
		this.tailleTileX = tailleTileX ;
		this.tailleTileY = tailleTileY ;
		this.distanceDeplacement = distanceDeplacement ;
		this.gravite = gravite ;
		
	}
	
	public double getGravite () {
		
		return this.gravite ;
		
	}
	
	// Ajoute un obstacle à la liste d'obstacles
	
	public void ajouterObstacle (String o) {
		
		this.obstacles.add(o) ;
		
	}
	
	public double getTailleTileX () {
		
		return this.tailleTileX ;
		
	}
	
	public double getTailleTileY () {
		
		return this.tailleTileY ;
		
	}
	
	public double getDistanceDeplacement () {
		
		return this.distanceDeplacement ;
		
	}
	
	public ArrayList<String> getObstacles () {
		
		return this.obstacles ;
		
	}
	
	public boolean estUnObstacle (ArrayList<String> obstacles, String o) {
		
		return obstacles.contains(o) ;
		
	}

	public void apparaitDansLaMap (Personnage p, Terrain t) throws HorsDeLaMapException {
		
		if (		
		p.getCollisionneur().getXDeb() < 0
		|| p.getCollisionneur().getYDeb() < 0
		|| p.getCollisionneur().getXFin() >= t.getTailleX()
		|| p.getCollisionneur().getYFin() >= t.getTailleY())
		throw new HorsDeLaMapException (t, p) ;
		
	}
	
	public void appliquerGraviter(GameObject perso) {
		perso.deplace("bas", this);
	}
	
}
