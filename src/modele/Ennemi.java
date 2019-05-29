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

public class Ennemi extends NonInventeriable{
	
	public Ennemi () {
		
		super () ;

		
		
	}
	
	public Ennemi (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c, Jeu jeu, double distanceDeplacement) {
		
		super (nom, pv, x, y, vitesseX, vitesseY, poids, c, distanceDeplacement,jeu,ptsAtt) ;
		this.setObstacle() ;
	}
	
	
	public void deplaceVersPerso(Personnage perso) throws VousEtesCoinceException {
		if (perso.getX()<this.getX()) {
			this.deplacementColision("gauche");
		}
		else  {
			this.deplacementColision("droite");
		}
	}
	

}
