package physique;

import java.util.ArrayList;

public class Moteur {
	
	private ArrayList<String> obstacles  ;
	private double distanceDeplacementPersos ;
	private double tailleTileX ;
	private double tailleTileY ;
	
	public Moteur (double distanceDeplacementPersos, double tailleTileX, double tailleTileY) {
		
		this.obstacles = new ArrayList<> () ;
		this.tailleTileX = tailleTileX ;
		this.tailleTileY = tailleTileY ;
		this.distanceDeplacementPersos = distanceDeplacementPersos ;
		
	}
	
	public double getDistanceDeplacementPersos () {
		
		return this.distanceDeplacementPersos ;
		
	}
	
	public void setDistanceDeplacementPersos (double d) {
		
		this.distanceDeplacementPersos = d ;
		
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
	
	public ArrayList<String> getObstacles () {
		
		return this.obstacles ;
		
	}

}
