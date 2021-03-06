package modele;

import geometrie.Vecteur;
import physique.Collisionneur;

public class LicorneVolante extends EnnemiVolant {
	
	private Boolean haut;
	private Boolean bas;
	private int compteur;
	
	public LicorneVolante (String nom, double pv, double ptsAtt, double x, double y, double masse, double hauteurSaut, double poids, Collisionneur c, Jeu jeu, double distanceDeplacement) {
		
		super (nom,pv,  ptsAtt, x, y, masse,  hauteurSaut, 0.6,  c, jeu) ;
		this.haut=true;
		this.bas=false;
	}
	
	public LicorneVolante() {
		super();
	}

	@Override
	public void deplaceVersPerso(PersonnagePrincipal perso) {
		if(perso.getY()<(this.getY()+100)&&this.haut) {
			this.haut=false;
			this.bas=true;
			this.compteur=0;
			this.ajouter(new Vecteur(0,-this.getVecteurVitesse().getY()));
			this.setSautPossible();
			this.deplacerVers("haut", this.getJeu().getMoteur());
		}
		else if(perso.getY()-200>this.getY()&&this.bas) {
			this.bas=false;
			this.haut=true;
			this.deplacerVers("bas", this.getJeu().getMoteur());
		}
		this.compteur++;
		if (compteur==100)
			this.setSautPossible();
			this.haut=true;
		
	}
}
