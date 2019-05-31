package physique;

import exceptions.HorsDeLaMapException;
import exceptions.VousEtesCoinceException;
import geometrie.Vecteur;
import modele.Terrain;

public class Moteur {
	
	private double tailleBoiteX ;
	private double tailleBoiteY ;
	private Vecteur gravite ;
	final double accelerationDePesanteur = 0.3 ;
	final double poidsDeLaTerre = 5.972 ; // x10^24 (6.67 * this.poidsDeLaTerre * Math.pow(10, -13) * go.getMasse()) / go.getHauteur(this, t)
	final double constanteGravitationnelle = 6.67 ; // x10^-11
	
	public Moteur (double tailleBoiteX, double tailleBoiteY, double gravite) {
		
		this.tailleBoiteX = tailleBoiteX ;
		this.tailleBoiteY = tailleBoiteY ;
		this.gravite = new Vecteur (0, gravite) ;
		
	}
	
	public void appliquerForces (GameObject go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
		this.appliquerGravite(go, t);
		this.appliquerForceElectromagnetique(go, t);
		go.deplacer () ;
		
	}
	
	public void appliquerGravite (GameObject go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
		Vecteur poids ;
		poids = new Vecteur (0, go.getMasse() * this.accelerationDePesanteur) ;
		go.ajouter(go.getCollisionneur().deplacementPossible(poids, t, this)) ;
		
	}
	
	public void appliquerForceElectromagnetique (GameObject go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
//		go.ajouter(go.getCollisionneur().deplacementPossible(go.getVecteurVitesse(), t, this)) ;
		go.setVitesse((go.getCollisionneur().deplacementPossible(go.getVecteurVitesse(), t, this))) ;
		
	}
	
	public Vecteur getGravite () {
		
		return this.gravite ;
		
	}
	
	public double getTailleBoiteX () {
		
		return this.tailleBoiteX ;
		
	}
	
	public double getTailleBoiteY () {
		
		return this.tailleBoiteY ;
		
	}
	
}
