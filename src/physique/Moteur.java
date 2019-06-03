package physique;

import exceptions.HorsDeLaMapException;
import exceptions.VousEtesCoinceException;
import geometrie.Vecteur;
import modele.Terrain;

/**<h1>Le Moteur gère la physique d'un Jeu</h1>
 * <p>Il peut :</p>
 * <ul>
 * 		<li>Appliquer toutes les forces en même temps sur un objet</li>
 * 		<li>Appliquer les frottements à un objet</li>
 * 		<li>Appliquer la force de pesanteur sur un objet</li>
 * 		<li>Appliquer la force éléctromagnétique sur un objet</li>
 * 		<li>Donner la gravité</li>
 * 		<li>Donner la largeur d'une case</li>
 * 		<li>Donner la hauteur d'une case</li>
 * 
 * 
 * @author Mathys
 *
 */

public class Moteur {
	
	private double tailleBoiteX ;
	private double tailleBoiteY ;
	private Vecteur gravite ;
	final double accelerationDePesanteur = 0.3 ;
	final double poidsDeLaTerre = 5.972 ; // x10^24 (6.67 * this.poidsDeLaTerre * Math.pow(10, -13) * go.getMasse()) / go.getHauteur(this, t)
	final double constanteGravitationnelle = 6.67 ; // x10^-11
	
	/**
	 * Constructeur qui initialise la largeur et la
	 * hauteur des cases avec une certaine gravité
	 * 
	 * @param tailleBoiteX
	 * @param tailleBoiteY
	 * @param gravite
	 */
	
	public Moteur (double tailleBoiteX, double tailleBoiteY, double gravite) {
		
		this.tailleBoiteX = tailleBoiteX ;
		this.tailleBoiteY = tailleBoiteY ;
		this.gravite = new Vecteur (0, gravite) ;
		
	}
	
	/**
	 * Applique les forces physiques à un objet sur un Terrain
	 * 
	 * @param go
	 * @param t
	 * @throws VousEtesCoinceException
	 * @throws HorsDeLaMapException
	 */
	
	public void appliquerForces (GameObject go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
		this.appliquerPesanteur(go, t);
		this.appliquerForceElectromagnetique(go, t);
		go.deplacer () ;
		
	}
	
	/**
	 * Applique les frottements à un objet sur un Terrain
	 * 
	 * @param go
	 * @param t
	 * @param v
	 */
	
	public void appliquerFrottements (GameObject go, Terrain t) {
		
		
	}
	
	/**
	 * Applique la force de pesanteur sur un objet sur un Terrain
	 * 
	 * @param go
	 * @param t
	 * @throws VousEtesCoinceException
	 * @throws HorsDeLaMapException
	 */
	
	public void appliquerPesanteur (GameObject go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
		Vecteur poids ;
		poids = new Vecteur (0, go.getMasse() * this.accelerationDePesanteur) ;
		go.ajouter(go.getCollisionneur().deplacementPossible(poids, t, this)) ;
		
	}
	
	/**
	 * Applique la force éléctromagnétique à un objet sur un Terrain
	 * 
	 * @param go
	 * @param t
	 * @throws VousEtesCoinceException
	 * @throws HorsDeLaMapException
	 */
	
	public Vecteur appliquerForceElectromagnetique (GameObject go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
//		go.ajouter(go.getCollisionneur().deplacementPossible(go.getVecteurVitesse(), t, this)) ;
		Vecteur v ;
		
		v = go.getCollisionneur().deplacementPossible(go.getVecteurVitesse(), t, this) ;
		go.setVitesse(go.getCollisionneur().deplacementPossible(go.getVecteurVitesse(), t, this)) ;
		
		return v ;
		
	}
	
	/**
	 * Retourne la gravité du Moteur
	 */
	
	public Vecteur getGravite () {
		
		return this.gravite ;
		
	}
	
	/**
	 * Retourne la largeur d'une case
	 */
	
	public double getTailleBoiteX () {
		
		return this.tailleBoiteX ;
		
	}
	
	/**
	 * Retourne la hauteur d'une case
	 */
	
	public double getTailleBoiteY () {
		
		return this.tailleBoiteY ;
		
	}
	
}
