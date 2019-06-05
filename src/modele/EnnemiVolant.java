package modele;

import physique.Collisionneur;

public abstract class EnnemiVolant extends Ennemi {

	public EnnemiVolant (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c, Jeu jeu, double distanceDeplacement) {
		
		super (nom,pv,  ptsAtt, x, y, vitesseX,  vitesseY, poids,  c, jeu,  distanceDeplacement) ;
		
	}


	@Override
	public abstract void deplaceVersPerso(Personnage perso);
}