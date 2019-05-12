package modele;
import physique.*;
import ressources.TraducteurFichier;

import java.io.IOException;

import physique.Collisionneur;

public class Jeu {
	
	private Moteur m ;
	private Personnage p ;
	private Terrain t ;
	private TraducteurFichier tf ;
	
	public Jeu (String nomF, double taillePixelsXCase, double taillePixelsYCase) throws IOException {
		
		this.m = new Moteur (taillePixelsXCase, taillePixelsYCase, 10.) ;
		this.p = new Personnage ("Wall-E", 100., 10., 0., 0, 1., new Collisionneur (0, 0, 49, 49)) ;
		this.tf = new TraducteurFichier(nomF) ;
		this.t = new Terrain (this.tf.getTabMap(), this.m.getTailleTileX(), this.m.getTailleTileY()) ;
		this.setObstacles() ;
		
	}
	
	public Moteur getMoteur () {
		
		return this.m ;
		
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
		
			case "haut" : if (this.p.getCollisionneur().deplacementPossible ("haut", this.m.getDistanceDeplacement(), this.t,this.p, this.m)) this.p.deplace("haut", this.m.getDistanceDeplacement()) ; break ;
			
			case "droite" : if (this.p.getCollisionneur().deplacementPossible ("droite", this.m.getDistanceDeplacement(), this.t,this.p, this.m)) this.p.deplace("droite", this.m.getDistanceDeplacement()) ; break ;
			
			case "bas" : if (this.p.getCollisionneur().deplacementPossible ("bas", this.m.getDistanceDeplacement(), this.t,this.p, this.m)) this.p.deplace("bas", this.m.getDistanceDeplacement()) ; break ;
			
			case "gauche" : if (this.p.getCollisionneur().deplacementPossible ("gauche", this.m.getDistanceDeplacement(), this.t,this.p, this.m)) this.p.deplace("gauche", this.m.getDistanceDeplacement()) ; break ;
		
		}
		
	}

}
