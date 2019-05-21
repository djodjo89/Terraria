package modele;

import physique.*;
import exceptions.VousEtesCoinceException;
import javafx.beans.property.* ;

/*
 * Un Personnage dispose de coordonnées modifiables et observables
 * Voici ses responsabilités :
 * - prendre un objet dans sa main
 * - renvoyer l'objet qu'il tient
 * - donner ses points d'attaque
 * - attaquer un objet
 */

public class Personnage extends NonInventeriable {
	
	private DoubleProperty ptsAttaque ;
	private Outil main ;
	private Jeu jeu;
	private Inventaire i ;
	
	public Personnage () {
		
		super () ;
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public Personnage (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c, Jeu jeu, double distanceDeplacement) {
		
		super (nom, pv, x, y, vitesseX, vitesseY, poids, c, distanceDeplacement) ;
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		this.jeu=jeu;
		this.i = new Inventaire (20) ;
		
	}
	
	public void donner (Outil o) {
		
		this.main = o ;
		
	}
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue () ;
		
	}
	
	public GameObject getMain () {
		
		return this.main ;
		
	}
	
	public void attaque (GameObject o) {
		
		o.perdrePV (this.main.getPtsAttaque()) ;
		
	}
	public void ajouterObjetMain (Outil o) {
		
		this.donner((Outil)this.i.getInventaire().get(0)) ;
		
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
	
	public Inventaire getInventaire (){
		
		return this.i ;
	}
	
	public int sauter(int nbTour,boolean espaceActive) throws VousEtesCoinceException {
		int nb=nbTour;
		if(espaceActive && nbTour==0)
			nb=1;
		
		if(nb>0 && nb<20) {
			this.sauter(this.jeu.getTerrain(), this.jeu.getMoteur());
			nb++;
		}
		
		if(nb>=20 && !this.getCollisionneur().deplacementPossible("bas", this.jeu.getTerrain(), this, this.jeu.getMoteur()))
			nb=0;
		
		return nb;
	}

}
