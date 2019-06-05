package modele;

import exceptions.VousEtesCoinceException;
import physique.Collisionneur;
import physique.GameObject;

/**
 * <h1>Air est un GameObject</h1>
 * 
 * @version 1.0
 * @author Romain
 *
 */

public abstract class Ennemi extends NonInventeriable{
	
	private int nbTourSaut;
	
	public Ennemi () {
		
		super () ;
		this.nbTourSaut=0;
		
	}
	
	public Ennemi (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c, Jeu jeu, double distanceDeplacement) {
		
		super (nom, pv, x, y, vitesseX, c, distanceDeplacement,jeu,ptsAtt) ;
		this.setObstacle() ;
		this.nbTourSaut=0;
	}
	
	
	public abstract void deplaceVersPerso(Personnage perso);
		
		
	
	public int positionXMap() {
		int position;
		
		position=(int)this.getX()/50;
		
		return position;
	}
	public int positionYMap() {
		int position;
		
		position=(int)this.getY()/50;
		
		return position;
	}
	
	public int getNbTourSaut() {
		return nbTourSaut;
	}
	public void setNbTourSaut(int nbTourSaut) {
		this.nbTourSaut= nbTourSaut;
	}


}
