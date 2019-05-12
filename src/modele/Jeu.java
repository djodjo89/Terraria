package modele;
import physique.*;
import ressources.TraducteurFichier;

import java.io.IOException;

import exceptions.VousEtesCoinceException;
import physique.Collisionneur;

public class Jeu {
	
	private Moteur m ;
	private Personnage p ;
	private Terrain t ;
	private TraducteurFichier tf ;
	
	public Jeu (String nomF, double taillePixelsXCase, double taillePixelsYCase, double posXJoueur, double posYJoueur) throws IOException {
		
		this.m = new Moteur (taillePixelsXCase, taillePixelsYCase, 10., 9.81) ;
		this.p = new Personnage ("Wall-E", 100., 10., posXJoueur, posYJoueur, 1., 1., 1., new Collisionneur (posXJoueur, posYJoueur, m.getTailleTileY() + posXJoueur - 1, m.getTailleTileX() + posYJoueur - 1)) ;
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
	
	public void deplacementPersoPrinc (String direction) throws VousEtesCoinceException {
		
		switch (direction) {
		
			case "haut" : if (this.p.getCollisionneur().deplacementPossible ("haut", this.t, this.p, this.m)) this.p.deplace("haut", this.t, this.m) ; break ;
			
			case "droite" : if (this.p.getCollisionneur().deplacementPossible ("droite", this.t, this.p, this.m)) this.p.deplace("droite", this.t, this.m) ; break ;
			
			case "bas" : if (this.p.getCollisionneur().deplacementPossible ("bas", this.t, this.p, this.m)) this.p.deplace("bas", this.t, this.m) ; break ;
			
			case "gauche" : if (this.p.getCollisionneur().deplacementPossible ("gauche", this.t,this.p, this.m)) this.p.deplace("gauche", this.t, this.m) ; break ;
		
		}
		
	}

}
