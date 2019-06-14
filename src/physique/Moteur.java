package physique;

import java.util.ArrayList;

import exceptions.HorsDeLaMapException;
import geometrie.Vecteur;
import modele.*;

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
	
	public void appliquerForces (Personnage go, Jeu jeu) throws HorsDeLaMapException {
		
		
		this.appliquerPesanteur(go, jeu);
		go.verifSiPeutSauter(this.appliquerForceElectromagnetique(go, jeu)) ;
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
	
	public void appliquerPesanteur (GameObject go, Jeu jeu) throws HorsDeLaMapException {
		
		Vecteur poids ;
		poids = new Vecteur (0, go.getMasse() * this.accelerationDePesanteur) ;
		go.ajouter(go.getCollisionneur().deplacementPossible(poids, jeu)) ;

		
	}
	
	/**
	 * Applique la force éléctromagnétique à un objet sur un Terrain
	 * 
	 * @param go
	 * @param t
	 * @throws VousEtesCoinceException
	 * @throws HorsDeLaMapException
	 */
	
	public Vecteur appliquerForceElectromagnetique (GameObject go, Jeu jeu) throws HorsDeLaMapException {
		
//		go.ajouter(go.getCollisionneur().deplacementPossible(go.getVecteurVitesse(), t, this)) ;
		Vecteur v ;
		
		v = go.getCollisionneur().deplacementPossible(go.getVecteurVitesse(), jeu) ;
		go.setVitesse(v) ;
		
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
	
	public Vecteur getGraviteInverse(GameObject go) {
		return new Vecteur (0, -(go.getMasse() * this.accelerationDePesanteur));
	}
	
}
