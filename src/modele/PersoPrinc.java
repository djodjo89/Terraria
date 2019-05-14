package modele;

import physique.Collisionneur;

/*
 * Un Personnage Principal est un personnage qui possède un
 * inventaire.
 * Voici ses responsabilités :
 * - ajouter l'objet qu'il a dans la main a son inventaire
 * - fournir son inventaire
 */

public class PersoPrinc extends Personnage {
	
	private Inventaire i ;
	
	public PersoPrinc () {
		
		super () ;
		
	}
	
	public PersoPrinc (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c) {
		
		super (nom, pv, ptsAtt, x, y, vitesseX, vitesseY, poids, c) ;
		this.i = new Inventaire (20) ;
		
	}
	
	public void ajouterObjetMain (Outil o) {
		
		super.donner((Outil)this.i.getInventaire().get(0)) ;
		
	}
	
	public Inventaire getInventaire () {
		
		return this.i ;
		
	}

}
