package modele;

import physique.Collisionneur;

public class Licorne extends Ennemi{
	
	
	public Licorne (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c, Jeu jeu, double distanceDeplacement) {
		
		super (nom,pv,  ptsAtt, x, y, vitesseX,  vitesseY, poids,  c, jeu,  distanceDeplacement) ;
		
	}
	
	public void deplaceVersPerso(Personnage perso) {
		
	}


}
