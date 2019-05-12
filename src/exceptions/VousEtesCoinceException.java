package exceptions;

import physique.* ;
import modele.* ;
import java.util.HashSet ;

public class VousEtesCoinceException extends Exception {
	
	private boolean coinBloque ;
	private Collisionneur c ;
	private Terrain t ;
	private Moteur m ;
	private HashSet <String> listeCoinsBloques ;
	
	
	public VousEtesCoinceException (Collisionneur c, Terrain t, Moteur m) {
		
		this.c = c ;
		this.t = t ;
		this.m = m ;
		this.listeCoinsBloques = new HashSet<> () ;
		this.coinceEnHautAGauche();this.coinceEnHautADroite();this.coinceEnBasADroite();this.coinceEnBasAGauche();
		
	}
	
	public void coinceEnHautAGauche () {
		
		this.coinBloque = this.m.estUnObstacle(this.m.getObstacles(), this.t.getListeLignes().get(this.c.getCoorYDebActuel(m)).get(this.c.getCoorXDebActuel(m)).getNom()) ;
		
		if (this.coinBloque)
			
			this.listeCoinsBloques.add(new String (this.c.getCoorXDebActuel(m) + ":" + this.c.getCoorYDebActuel(m))) ;
		
	}
	
	public void coinceEnHautADroite () {
		
		this.coinBloque = this.m.estUnObstacle(this.m.getObstacles(), this.t.getListeLignes().get(this.c.getCoorYDebActuel(m)).get(this.c.getCoorXFinActuel(m)).getNom()) ;
		
		if (this.coinBloque)
			
			this.listeCoinsBloques.add(new String (this.c.getCoorXFinActuel(m) + ":" + this.c.getCoorYDebActuel(m))) ;
		
	}
	
	public void coinceEnBasADroite () {
		
		this.coinBloque = this.m.estUnObstacle(this.m.getObstacles(), this.t.getListeLignes().get(this.c.getCoorYFinActuel(m)).get(this.c.getCoorXFinActuel(m)).getNom()) ;
		
		if (this.coinBloque)
			
			this.listeCoinsBloques.add(new String (this.c.getCoorXFinActuel(m) + ":" + this.c.getCoorYFinActuel(m))) ;
		
	}
	
	public void coinceEnBasAGauche () {
		
		this.coinBloque = this.m.estUnObstacle(this.m.getObstacles(), this.t.getListeLignes().get(this.c.getCoorYFinActuel(m)).get(this.c.getCoorXDebActuel(m)).getNom()) ;
		
		if (this.coinBloque)
			
			this.listeCoinsBloques.add(new String (this.c.getCoorXDebActuel(m) + ":" + this.c.getCoorYFinActuel(m))) ;
		
	}
	
	public String toString () {
		
		String message ;
		
		message = "Désolé, vous êtes coincé en : " ;
		
		for (String coordonnees : this.listeCoinsBloques) {
			
			message += coordonnees + "; " ;
			
		}
		
		return message ;
		
	}

}
