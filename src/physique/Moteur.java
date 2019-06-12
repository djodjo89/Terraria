package physique;

import exceptions.HorsDeLaMapException;
import exceptions.VousEtesCoinceException;
import geometrie.*;
import java.util.ArrayList;
import modele.*;
import objetRessources.*;

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
	
	public void appliquerForces (Personnage go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
		this.appliquerPesanteur(go, t);
		this.appliquerFrottements(go, t);
		go.verifSiPeutSauter(this.appliquerForcesMecaniques(go, t)) ;
		go.deplacer () ;
		
	}
	
	/**
	 * Applique les frottements à un objet sur un Terrain
	 * 
	 * @param go
	 * @param t
	 * @param v
	 */
	
	public Vecteur appliquerFrottements (GameObject go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
		int i ;
		int[] coordonnees ;
		double sommeCoeffsFrottement ;
		Vecteur frottements ;
		Vecteur vFinal ;
		
		i = 0 ;
		coordonnees = new int[2] ;
		sommeCoeffsFrottement = 0 ;
		
		while ( i < go.getCollisionneur().getBoite().nbSommets()) {
			go.getCollisionneur().getCoordonneesEntieresSurLaMap(go.getCollisionneur().getBoite().get(i), this, coordonnees);
			sommeCoeffsFrottement += t.getCase(coordonnees, this).getCoeffFrottement() ;
			i ++ ;
			System.out.println("i : " + t.getCase(coordonnees, this).getCoeffFrottement());
		}
		
		//go.getVecteurVitesse().ajouter((go.getVecteurVitesse().getX() / 0.9) / 100, 0) ;
		System.out.println(i);
		frottements = new Vecteur (-(sommeCoeffsFrottement/i / 2), -(sommeCoeffsFrottement/i)) ;
		System.out.println(sommeCoeffsFrottement/i);
		
		vFinal = this.appliquerForceElectromagnetique(go, new Vecteur (frottements.getX() + go.getVecteurVitesse().getX(), frottements.getY() + go.getVecteurVitesse().getY()), t) ;
		go.setVitesse(vFinal) ;
		
		return vFinal ;
		
	}
	
	/**
	 * Applique la force de pesanteur sur un objet sur un Terrain
	 * 
	 * @param go
	 * @param t
	 * @throws VousEtesCoinceException
	 * @throws HorsDeLaMapException
	 */
	
	public Vecteur appliquerPesanteur (GameObject go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
		Vecteur poids ;
		Vecteur vFinal ;
		
		poids = new Vecteur (0, go.getMasse() * this.accelerationDePesanteur) ;
		
		vFinal = this.appliquerForceElectromagnetique(go, new Vecteur (poids.getX() + go.getVecteurVitesse().getX(), poids.getY() + go.getVecteurVitesse().getY()), t) ;
		go.setVitesse(vFinal) ;
		
		return vFinal ;
	}
	
	/**
	 * Applique la force éléctromagnétique à un objet sur un Terrain
	 * 
	 * @param go
	 * @param t
	 * @throws VousEtesCoinceException
	 * @throws HorsDeLaMapException
	 */
	
	public Vecteur appliquerForcesMecaniques (GameObject go, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
		Vecteur vInitial ;
		Vecteur vFinal ;
		
		vInitial = go.getVecteurVitesse() ;
		
		vFinal = this.appliquerForceElectromagnetique(go, vInitial, t) ;
		go.setVitesse(vFinal) ;
		
		return vFinal ;
		
	}
	
	public Vecteur appliquerForceElectromagnetique (GameObject go, Vecteur vInitial, Terrain t) throws VousEtesCoinceException, HorsDeLaMapException {
		
		Vecteur vFinal ;
		
		vFinal = go.getCollisionneur().deplacementPossible(vInitial, t, this) ;
		
		if (!vInitial.equals(vFinal))
			
			vFinal.ajouter(-vInitial.getX() / 100, -vInitial.getY() / 100);
		
		if (vFinal.getY() == 0)
			
			vFinal = new Vecteur (vInitial.getX(), 0) ;
		
		vFinal = go.getCollisionneur().deplacementPossible(vFinal, t, this) ;
		
		return vFinal ;
		
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
