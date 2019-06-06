package modele;

import physique.Collisionneur;

public class Licorne extends Ennemi{
	
	
	public Licorne (String nom, double pv, double ptsAtt, double x, double y, double masse, double hauteurSaut, double poids, Collisionneur c, Jeu jeu, double distanceDeplacement) {
		
		super (nom,pv,  ptsAtt, x, y, masse,  hauteurSaut, poids,  c, jeu) ;
		
	}
	
	public void deplaceVersPerso(PersonnagePrincipal perso) {
		
	}


}
