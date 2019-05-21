package modele;

import physique.*;
import exceptions.HorsDeLaMapException;
import exceptions.VousEtesCoinceException;
import geometrie.Vecteur;
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
	
	public Personnage (String nom, double pv, double ptsAtt, double x, double y, double poids, Collisionneur c, Jeu jeu) {
		
		super (nom, pv, x, y, poids, c) ;
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
	
	public Inventaire getInventaire (){
		
		return this.i ;
	}
	
	public int sauter(int nbTour,boolean espaceActive) throws VousEtesCoinceException, HorsDeLaMapException {
		int nb=nbTour;
		if(espaceActive && nbTour==0)
			nb=1;
		
		if(nb>0 && nb<20) {
			this.sauter(this.jeu.getMap(), this.jeu.getMoteur());
			nb++;
		}
		
		if(nb>=20 && this.getCollisionneur().deplacementPossible(new Vecteur (0,1), this.jeu.getMap(), this.jeu.getMoteur()).getY() != 0)
			nb=0;
		
		return nb;
	}

}
