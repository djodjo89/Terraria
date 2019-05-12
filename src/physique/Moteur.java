package physique;

import java.util.ArrayList;

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
	
	public void appliquerGravite (GameObject go) {
		
		
		
	}
	
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

}
