package modele ;
import physique.* ;
import exceptions.* ;
import geometrie.* ;
import ressources.TraducteurFichier ;

import java.io.IOException;
import java.util.ArrayList;

import controleur.* ;

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
	private PersonnagePrincipal perso ;
	private Terrain terrain ;
	private Licorne ennemi;
	private LicorneVolante ennemiVol;
	private TraducteurFichier tf ;
	private ArrayList<Personnage> listePerso;
	
	public Jeu (String nomF, double taillePixelsXCase, double taillePixelsYCase, double posXJoueur, double posYJoueur) throws IOException, HorsDeLaMapException {
		
		this.moteur = new Moteur (taillePixelsXCase, taillePixelsYCase, 0.1) ;
		Point p1, p2, p3, p4 ;		
		p1 = new Point (posXJoueur, posYJoueur) ;
		p2 = new Point (posXJoueur + 41, posYJoueur) ;
		p3 = new Point (posXJoueur, posYJoueur + taillePixelsYCase) ;
		p4 = new Point (posXJoueur + 41, posYJoueur + taillePixelsYCase) ;
		this.ennemi= new Licorne("first", 100, 10,posXJoueur, posYJoueur, 1., 8.5, 1., new Collisionneur (p1, p2, p3, p4), this, 5);
		this.ennemiVol= new LicorneVolante("second", 100, 10,posXJoueur, posYJoueur, 1., 8.5, 1., new Collisionneur (p1, p2, p3, p4), this, 5);		
		this.perso = new PersonnagePrincipal ("Wall-E", 100., 20., posXJoueur, posYJoueur, 1., 8.5, 1., new Collisionneur (p1, p2, p3, p4),this) ;
		
		this.tf = new TraducteurFichier(nomF) ;
		
		this.terrain = new Terrain (this.tf.getTabMap(), this.moteur.getTailleBoiteX(), this.moteur.getTailleBoiteY()) ;
		this.listePerso=new ArrayList<Personnage>();
		this.listePerso.add(ennemi);
		this.listePerso.add(ennemiVol);
	}
	
	public Jeu() {
	}

	public Moteur getMoteur () {
		
		return this.moteur ;
		
	}
	
	public Terrain getTerrain () {
		
		return this.terrain ;
		
	}
	
	public PersonnagePrincipal getPerso () {
		      
		return this.perso ;
		
	} 
	
	public void evoluer(ControleurTouches controlTouche, ControleurTerraria controlTerra) throws VousEtesCoinceException, HorsDeLaMapException{
		
		if(!controlTouche.getMenu().estAffiche()) {
			controlTerra.genererMap () ;
			this.moteur.appliquerForces(this.perso, this.terrain);
			this.moteur.appliquerForces(this.ennemi, this.terrain);
			this.moteur.appliquerForces(this.ennemiVol, this.terrain);
			this.ennemiVol.deplace(this.perso);
			this.ennemi.deplace(this.perso);
		}
	
	}

	public ArrayList<Personnage> getEnnemi() {
		return this.listePerso;
	}

}
