package modele;

import exceptions.VousEtesCoinceException;
import physique.Collisionneur;

public class Ennemi extends Personnage{
	
	public Ennemi (String nom, double pv, double ptsAtt, double posX, double posY, double masse, double hauteurSaut, double vitesseDeplacement, Collisionneur collisionneur, Jeu jeu) {
		
		super (nom, pv,ptsAtt, posX, posY, masse, hauteurSaut, vitesseDeplacement, collisionneur, jeu) ;
		this.setObstacle() ;
	}
	
	public void deplaceVersPerso(Personnage perso) throws VousEtesCoinceException {

		if (perso.getX()<this.getX()) {
			this.deplacerVers("gauche", super.getJeu().getMoteur());
		}
		else  {
			this.deplacerVers("bas", super.getJeu().getMoteur());
		}
	}

}
