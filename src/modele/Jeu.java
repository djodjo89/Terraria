package modele;
import physique.*;
import exceptions.* ;
import ressources.TraducteurFichier;

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
	
	private Moteur m ;
	private Personnage p ;
	private Ennemi ennemi;
	private Terrain t ;
	private TraducteurFichier tf ;
	
	public Jeu (String nomF, double taillePixelsXCase, double taillePixelsYCase, double posXJoueur, double posYJoueur) throws IOException, HorsDeLaMapException {
		
		this.m = new Moteur (taillePixelsXCase, taillePixelsYCase, 0.80) ;
		this.ennemi= new Ennemi("first", 100, 10,posXJoueur, posYJoueur, 1., 1., 1., new Collisionneur (posXJoueur, posYJoueur, m.getTailleTileY() + posXJoueur - 1, m.getTailleTileX() + posYJoueur - 1), this, 5);
		this.p = new Personnage ("Wall-E", 100., 20., posXJoueur, posYJoueur, 1., 1., 1., new Collisionneur (posXJoueur, posYJoueur, m.getTailleTileY() + posXJoueur - 1, m.getTailleTileX() + posYJoueur - 1),this, 10) ;
		Outil o = new Outil("Torche", new Collisionneur(posXJoueur, posYJoueur, posXJoueur + this.m.getTailleTileX(), posYJoueur + this.m.getTailleTileY())) ;
		this.p.getInventaire().ajouterObjet(o) ;
		//this.p.ajouterObjetMain(o);
		this.tf = new TraducteurFichier(nomF) ;
		this.t = new Terrain (this.tf.getTabMap(), this.m.getTailleTileX(), this.m.getTailleTileY()) ;
		this.m.apparaitDansLaMap(this.p, this.t) ;
		
	}
	
	public Jeu() {
	}

	public Moteur getMoteur () {
		
		return this.m ;
		
	}
	
	public Terrain getTerrain () {
		
		return this.t ;
		
	}
	
	public Personnage getPerso () {
		
		return this.p ;
		
	}
	
	public Ennemi getEnnemi() {
		return this.ennemi;
	}
	
	public int evoluer(int nbTour, ControleurTouches controlTouche) throws VousEtesCoinceException{
		nbTour=this.getPerso().sauter(nbTour,controlTouche.espaceActive());
		controlTouche.setEspaceFalse();
		this.p.deplacementColision("bas");
		this.ennemi.deplacementColision("bas");
		this.ennemi.deplaceVersPerso(this.p);
		return nbTour;
	}

}
