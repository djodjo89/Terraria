package modele;

import physique.Collisionneur;

public class PersoPrinc extends Personnage {
	
	private Inventaire i ;
	
	public PersoPrinc () {
		
		super () ;
		
	}
	
	public PersoPrinc (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c) {
		
		super (nom, pv, ptsAtt, x, y, vitesseX, vitesseY, poids, c) ;
		this.i = new Inventaire (10) ;
		
	}
	
	public void ajouterObjetMain (Outil o) {
		
		if (this.i.getInventaire().get(this.i.getInventaire().indexOf(o)).getClass().equals("Outil"))
		
		super.donner((Outil)this.i.getInventaire().get(this.i.getInventaire().indexOf(o))) ;
		
	}

}
