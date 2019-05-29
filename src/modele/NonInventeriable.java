package modele;

import exceptions.VousEtesCoinceException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import physique.Collisionneur;
import physique.GameObject;

public abstract class NonInventeriable extends GameObject{
	
	private Jeu jeu;
	private DoubleProperty ptsAttaque ;
	
	
	
	public NonInventeriable () {
		
		super ("", 1000, new Collisionneur(),false) ;
		
	}
	
	public NonInventeriable (String nom, double pv, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c, double distanceDeplacement,Jeu jeu,double ptsAtt) {
		
		super (nom, pv, x, y, vitesseX, vitesseY, poids, c, distanceDeplacement) ;
		this.jeu=jeu;
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		
	}
	
	public void deplacementColision (String direction) throws VousEtesCoinceException {
		
		switch (direction) {
		
			case "haut" : this.sauter(this.jeu.getTerrain(),this.jeu.getMoteur()); break;
			
			case "droite" : if (this.getCollisionneur().deplacementPossible ("droite", this.jeu.getTerrain(), this, this.jeu.getMoteur())) this.deplacementSansVerif("droite") ; break ;
			
			case "bas" : if (this.getCollisionneur().deplacementPossible ("bas", this.jeu.getTerrain(), this, this.jeu.getMoteur())) this.deplacementSansVerif("bas"); break ;
			
			case "gauche" : if (this.getCollisionneur().deplacementPossible ("gauche", this.jeu.getTerrain(), this, this.jeu.getMoteur())) this.deplacementSansVerif("gauche") ; break ;
		
		}
		
	}
	
	public boolean jePeuxMeDeplacerLa(String direction) throws VousEtesCoinceException {
		
		if (direction=="droite")
			return (this.getCollisionneur().deplacementPossible ("droite", this.jeu.getTerrain(), this, this.jeu.getMoteur()));
		if (direction=="gauche")
			return (this.getCollisionneur().deplacementPossible ("gauche", this.jeu.getTerrain(), this, this.jeu.getMoteur()));
		return false;

	}
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue () ;
		
	}
	public Jeu getJeu() {
		return jeu;
	}

	public int sauter(int nbTour,boolean espaceActive) throws VousEtesCoinceException {
		int nb=nbTour;
		if(espaceActive && nbTour==0)
			nb=1;
		
		if(nb>0 && nb<20) {
			this.sauter(this.getJeu().getTerrain(), this.getJeu().getMoteur());
			nb++;
		}
		
		if(nb>=20 && !this.getCollisionneur().deplacementPossible("bas", this.getJeu().getTerrain(), this, this.getJeu().getMoteur()))
			nb=0;
		
		return nb;
	}
}
