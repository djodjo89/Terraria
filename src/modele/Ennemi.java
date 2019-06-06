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

public class Ennemi extends NonInventeriable implements Cliquable{
	
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
	
	
	public void deplaceVersPerso(Personnage perso) throws VousEtesCoinceException {

		if (perso.getX()<this.getX()) {
			this.deplacerVers("gauche", super.getJeu().getMoteur());
		}
		else  {
			this.deplacerVers("bas", super.getJeu().getMoteur());
		}
	}
	
	public int getNbTourSaut() {
		return nbTourSaut;
	}
	public void setNbTourSaut(int nbTourSaut) {
		this.nbTourSaut= nbTourSaut;
	}

	@Override
	public void interactionClick(int x, int y, Jeu jeu) {
		// TODO Auto-generated method stub
		
	}


}
