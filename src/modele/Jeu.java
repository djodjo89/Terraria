package modele;
import physique.Moteur;

import java.io.IOException;

import physique.Collisionneur;

public class Jeu {
	
	private Moteur m ;
	private Personnage p ;
	private Terrain t ;
	private TraducteurFichier tf ;
	
	public Jeu (String nomF) throws IOException {
		
		this.m = new Moteur (0.2, 50, 50) ;
		this.p = new Personnage () ;
		this.tf = new TraducteurFichier(nomF) ;
		this.t = new Terrain (this.tf.getTabMap()) ;
		this.setObstacles() ;
		
	}
	
	public void setObstacles () {
		
		this.m.ajouterObstacle("T") ;
		
	}
	
	public void setPerso (Personnage p) {
		
		this.p =p ;
		
	}
	
	public Terrain getMap () {
		
		return this.t ;
		
	}
	
	public Personnage getPerso () {
		
		return this.p ;
		
	}
	
	public void deplacementPersoPrinc (String direction) {
		
		switch (direction) {
		
			case "haut" : if (Collisionneur.deplacementPossible ("haut", this.t,this.p, this.m)) this.p.deplace("haut") ; break ;
			
			case "droite" : if (Collisionneur.deplacementPossible ("droite", this.t,this.p, this.m)) this.p.deplace("droite") ; break ;
			
			case "bas" : if (Collisionneur.deplacementPossible ("bas", this.t,this.p, this.m)) this.p.deplace("bas") ; break ;
			
			case "gauche" : if (Collisionneur.deplacementPossible ("gauche", this.t,this.p, this.m)) this.p.deplace("gauche") ; break ;
		
		}
		
	}

}
