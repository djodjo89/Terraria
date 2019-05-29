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
	
	
	private Inventeriable main ;

	private Inventaire i ;
	
	public Personnage () {
		
		super () ;
		this.main = null;
		this.i = new Inventaire(10);
		
		
	}
	
	public Personnage (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c, Jeu jeu, double distanceDeplacement) {
		
		super (nom, pv, x, y, vitesseX, vitesseY, poids, c, distanceDeplacement,jeu,ptsAtt) ;
		
		this.i = new Inventaire (20) ;
		
	}
	
	public void donner (Inventeriable o) {
		
		this.main = o ;
		
	}
	

	
	public GameObject getMain () {
		
		return this.main ;
		
	}
	
	public void attaque (GameObject o) {
		
		if(this.main instanceof Outil ) 
			o.perdrePV (((Outil) this.main).getPtsAttaque()) ;
		
		else 
			o.perdrePV(this.getPtsAttaque());
		
	}
	public void ajouterObjetMain (Outil o) {
		
		this.donner((Outil)this.i.getInventaire().get(0)) ;
		
	}
	

	
	public Inventaire getInventaire (){
		
		return this.i ;
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
