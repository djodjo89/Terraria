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
	
	public Personnage () {
		
		super ("", new Collisionneur()) ;
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public Personnage (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c) {
		
		super (nom, pv, x, y, vitesseX, vitesseY, poids, c) ;
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		
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

}
