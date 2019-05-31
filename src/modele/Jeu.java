package modele ;
import physique.* ;
import exceptions.* ;
import geometrie.* ;
import ressources.TraducteurFichier ;

import java.io.IOException;

import controleur.ControleurTouches;

/*
 * Jeu est la classe principale du jeu.
 * Elle possède un moteur qui gère la physique du jeu,
 * un personnage principal, une map observable et un
 * traducteur de fichier servant à créer le terrain.
 * Voici ses responsabilités :
 * - Fournir un moteur, un terrain et un personnage
 * principal liés les uns aux autres
 * - déplacer le personnage principal
 */

public class Jeu {
	
	private Moteur moteur ;
	private Personnage perso ;
	private Terrain terrain ;
	private TraducteurFichier tf ;
	
	public Jeu (String nomF, double taillePixelsXCase, double taillePixelsYCase, double posXJoueur, double posYJoueur) throws IOException, HorsDeLaMapException {
		
		this.moteur = new Moteur (taillePixelsXCase, taillePixelsYCase, 0.1) ;
		Point p1, p2, p3, p4 ;		
		p1 = new Point (posXJoueur, posYJoueur) ;
		p2 = new Point (posXJoueur + taillePixelsXCase, posYJoueur) ;
		p3 = new Point (posXJoueur, posYJoueur + taillePixelsYCase) ;
		p4 = new Point (posXJoueur + taillePixelsXCase, posYJoueur + taillePixelsYCase) ;
		this.perso = new Personnage ("Wall-E", 100., 50., posXJoueur, posYJoueur, 1., new Collisionneur (p1, p2, p3, p4),this) ;
		this.tf = new TraducteurFichier(nomF) ;
		this.terrain = new Terrain (this.tf.getTabMap(), this.moteur.getTailleBoiteX(), this.moteur.getTailleBoiteY()) ;
		
	}
	
	public Moteur getMoteur () {
		
		return this.moteur ;
		
	}
	
	public Terrain getMap () {
		
		return this.terrain ;
		
	}
	
	public Personnage getPerso () {
		      
		return this.perso ;
		
	} 
	
	public void evoluer(int nbTour, ControleurTouches controlTouche) throws VousEtesCoinceException, HorsDeLaMapException{
		
		this.moteur.appliquerForces(this.perso, this.terrain);
		
	}

}
