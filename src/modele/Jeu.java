package modele ;
import physique.* ;
import exceptions.* ;
import geometrie.* ;
import ressources.TraducteurFichier ;

import java.io.IOException;
import java.util.ArrayList;

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
	private PersonnagePrincipal perso ;
	private Terrain terrain ;
	private Licorne ennemi;
	private LicorneVolante ennemiVol;
	private TraducteurFichier tf ;
	private ArrayList<Personnage> listePerso;
	
	public Jeu (String nomF, double taillePixelsXCase, double taillePixelsYCase, double posXJoueur, double posYJoueur) throws IOException, HorsDeLaMapException {
		
		this.moteur = new Moteur (taillePixelsXCase, taillePixelsYCase, 0.1) ;
		Point p1, p2, p3, p4,p5,p6,p7,p8,p9,p10,p11,p12 ;		
		p1 = new Point (posXJoueur, posYJoueur) ;
		p2 = new Point (posXJoueur + 41, posYJoueur) ;
		p3 = new Point (posXJoueur, posYJoueur + taillePixelsYCase) ;
		p4 = new Point (posXJoueur + 41, posYJoueur + taillePixelsYCase) ;
		p5 = new Point (posXJoueur+100, posYJoueur) ;
		p6 = new Point (posXJoueur + 141, posYJoueur) ;
		p7 = new Point (posXJoueur+100, posYJoueur + taillePixelsYCase) ;
		p8 = new Point (posXJoueur + 141, posYJoueur + taillePixelsYCase) ;
		p9 = new Point (posXJoueur+200, posYJoueur) ;
		p10 = new Point (posXJoueur + 241, posYJoueur) ;
		p11= new Point (posXJoueur+200, posYJoueur + taillePixelsYCase) ;
		p12= new Point (posXJoueur + 241, posYJoueur + taillePixelsYCase) ;
		this.ennemi= new Licorne("first", 100, 10,posXJoueur+100, posYJoueur, 1., 8.5, 1., new Collisionneur (p5, p6, p7, p8), this, 5);
		System.out.println(ennemi.getCollisionneur().getBoite().get(2));
		this.ennemiVol= new LicorneVolante("second", 100, 10,posXJoueur+200, posYJoueur, 1., 8.5, 1., new Collisionneur (p9, p10, p11, p12), this, 5);		
		System.out.println(ennemiVol.getCollisionneur().getBoite().get(2));
		this.perso = new PersonnagePrincipal ("Wall-E", 100., 20., posXJoueur, posYJoueur, 1., 8.5, 1., new Collisionneur (p1, p2, p3, p4),this) ;
		System.out.println(perso.getCollisionneur().getBoite().get(2));
		this.tf = new TraducteurFichier(nomF) ;
		
		this.terrain = new Terrain (this.tf.getTabMap(), this.moteur.getTailleBoiteX(), this.moteur.getTailleBoiteY()) ;
		this.listePerso=new ArrayList<Personnage>();
		this.listePerso.add(perso);
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
	
	public void evoluer(ControleurTouches controlTouche) throws VousEtesCoinceException, HorsDeLaMapException{
		
		if(!controlTouche.getMenu().estAffiche()) {
			this.modifieListePourAppliquerForce(this.perso, this.terrain);
			this.modifieListePourAppliquerForce(this.ennemi, this.terrain);
			this.modifieListePourAppliquerForce(this.ennemiVol, this.terrain);
			this.ennemiVol.deplace(this.perso);
			this.ennemi.deplace(this.perso);
			
			if(perso.getInvincible())
				if(perso.compteInvincible()>30)
					perso.vulnerable();
			if (perso.tMort()) {
				System.exit(0);
			}
		}
	
	}

	public void modifieListePourAppliquerForce(Personnage perso,Terrain terre) throws VousEtesCoinceException, HorsDeLaMapException {
		listePerso.remove(perso);
		this.moteur.appliquerForces(perso, terre,listePerso);
		//System.out.println(perso.getCollisionneur().getBoite().get(2));
		listePerso.add(perso);
		
	}
	public ArrayList<Personnage> getEnnemi() {
		return this.listePerso;
	}

}
