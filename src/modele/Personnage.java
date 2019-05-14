package modele;

import physique.*;
import javafx.beans.property.* ;

/*
 * Un Personnage dispose de coordonnées modifiables et observables
 * Voici ses responsabilités :
 * - prendre un objet dans sa main
 * - renvoyer l'objet qu'il tient
 * - donner ses points d'attaque
 * - attaquer un objet
 */

public class Personnage extends GameObject {
	
	private DoubleProperty ptsAttaque ;
	private Outil main ;
	private Jeu jeu;
	private Inventaire i ;
	private Boolean saute;
	
	public Personnage () {
		
		super ("", new Collisionneur()) ;
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public Personnage (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c,Jeu jeu) {
		
		super (nom, pv, x, y, vitesseX, vitesseY, poids, c) ;
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		this.jeu=jeu;
		this.i = new Inventaire (20) ;
		this.saute=false;
		
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
	
	public Inventaire getInventaire () {
		
		return this.i ;
		
	}
	public int sauter(int nbTour,boolean bool) {
		int nb=nbTour;
		if(bool)
			nb=1;
		if(nb>0) {
			this.sauter(this.jeu.getMap(), this.jeu.getMoteur());
			nb++;
		}
		if(nb>20)
			nb=0;
		return nb;
	}

}
