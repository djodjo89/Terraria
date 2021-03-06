package modele;

import physique.Collisionneur;

public abstract class EnnemiVolant extends Ennemi {

	public EnnemiVolant (String nom, double pv, double ptsAtt, double x, double y, double masse, double hauteurSaut, double poids, Collisionneur c, Jeu jeu) {
		
		super (nom,pv,  ptsAtt, x, y, masse,  hauteurSaut, 0.6,  c, jeu) ;
		
	}


	public EnnemiVolant() {
		super();
	}


	@Override
	public abstract void deplaceVersPerso(PersonnagePrincipal perso);
}