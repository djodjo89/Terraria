package modele;

import exceptions.VousEtesCoinceException;
import physique.Collisionneur;


/**
 * <h1>Air est un GameObject</h1>
 * 
 * @version 1.0
 * @author Romain
 *
 */

public abstract class Ennemi extends Personnage {
	
	private int nbTourSaut;
	
	public Ennemi () {
		
		super () ;
		this.nbTourSaut=0;
		
	}


	
	public Ennemi (String nom, double pv, double ptsAtt, double posX, double posY, double masse, double hauteurSaut, double vitesseDeplacement, Collisionneur collisionneur, Jeu jeu) {
		

		super (nom, pv,ptsAtt, posX, posY, masse, hauteurSaut, vitesseDeplacement, collisionneur, jeu) ;

		this.setObstacle() ;
	}
	
	public void deplace(PersonnagePrincipal perso) {
		if(perso.getX()>this.getX()) {
			if(super.getJeu().getTerrain().getListeLignes().get(this.positionYMap()).get(this.positionXMap()+1).getTag()=="air")
				this.deplacerVers("droite", super.getJeu().getMoteur());
			else
				this.deplacerVers("haut", super.getJeu().getMoteur());

		}
		else if(perso.getX()<this.getX()) {
			if(super.getJeu().getTerrain().getListeLignes().get(this.positionYMap()+1).get(this.positionXMap()-1).getTag()=="air")
				this.deplacerVers("gauche", super.getJeu().getMoteur());
			else
				this.deplacerVers("haut", super.getJeu().getMoteur());
		}
		this.deplaceVersPerso(perso);
	}
	
	public abstract void deplaceVersPerso(PersonnagePrincipal perso);
		
		
	
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


	


}
