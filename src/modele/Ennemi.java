package modele;

import exceptions.VousEtesCoinceException;
import geometrie.Vecteur;
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
	private int portee;
	
	public Ennemi () {
		
		super () ;
		this.portee=10;
		this.nbTourSaut=0;
		
	}


	
	public Ennemi (String nom, double pv, double ptsAtt, double posX, double posY, double masse, double hauteurSaut, double vitesseDeplacement, Collisionneur collisionneur, Jeu jeu) {
		

		super (nom, pv,10, posX, posY, masse, hauteurSaut, vitesseDeplacement, collisionneur, jeu) ;
		this.portee=98;
		this.setObstacle() ;
	}
	
	public void deplace(PersonnagePrincipal perso) {
		if (this.estADistance(perso)) {
			if(perso.getX()>this.getX()) {
				this.attaqueLePerso(perso);
				if(super.getJeu().getTerrain().getListeLignes().get(this.positionYMap()).get(this.positionXMap()+1).getTag()=="air")
					this.deplacerVers("droite", super.getJeu().getMoteur());
				else
					this.deplacerVers("haut", super.getJeu().getMoteur());
	
			}
			else if(perso.getX()<this.getX()) {
				this.attaqueLePerso(perso);
				if(super.getJeu().getTerrain().getListeLignes().get(this.positionYMap()+1).get(this.positionXMap()-1).getTag()=="air")
					this.deplacerVers("gauche", super.getJeu().getMoteur());
				else
					this.deplacerVers("haut", super.getJeu().getMoteur());
			}
			
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
	
	public boolean estADistance(Personnage perso) {
		if(perso.getX()+this.portee>=this.getX()&& perso.getX()-this.portee<=this.getX()) {
			System.out.println("looooooooooooool");
			return true;
		}
		this.ajouter(new Vecteur(-this.getVecteurVitesse().getX(),0));
		return false;
	}


	@Override
	public void interactionClick(int x, int y, Jeu jeu) {
		// TODO Auto-generated method stub
		
	}
	
	public void attaqueLePerso(Personnage perso) {
		System.out.println("J'ATTAQUE");
		perso.prendreDegat(this.getPtsAttaque());
	}



}
