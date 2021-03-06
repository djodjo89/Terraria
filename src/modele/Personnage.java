package modele;

import geometrie.Vecteur;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import physique.Collisionneur;
import physique.GameObject;
import physique.Moteur;

public abstract class Personnage extends GameObject implements Cliquable{
	
	private String nom ;
	private Jeu jeu;
	private DoubleProperty ptsAttaque ;
	public boolean peutSauter ;
	private double hauteurSaut;
	private boolean invincible;
	private int compteurFps;
	
	
	public Personnage () {
		
		super (1000, new Collisionneur(),false) ;
		this.invincible=false;
		this.compteurFps=0; 
	}
	
	public Personnage (String nom, double pv, double ptsAtt, double posX, double posY, double masse, double hauteurSaut, double vitesseDeplacement, Collisionneur collisionneur, Jeu jeu) {
		
		super (pv, posX, posY, masse, vitesseDeplacement, collisionneur, jeu) ;
		this.nom = nom ;
		this.jeu=jeu;
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;

		this.peutSauter = false ;
		this.hauteurSaut = hauteurSaut ; // ((51.9 * this.hauteurSaut + 48.9 * this.masse - 2007) / m.getTailleBoiteY()*650)
		this.invincible=false;
		this.compteurFps=0;
	}
	

	public void setSautPossible () {

		
		this.peutSauter = true ;
		
	}
	
	public void setSautImpossible () {
		
		this.peutSauter = false ;
		
	}
	public void verifSiPeutSauter (Vecteur v) {
		
		if (v.getY() == 0)
			
			this.setSautPossible() ;
		
		else
			
			this.setSautImpossible() ;
		

	}
	
	public double getPuissanceSaut () {

		return this.hauteurSaut;
	} 
		
	/**
	 * Ajoute au Vecteur vitesse du GameObject un nouveau Vecteur
	 * en fonction de la direction entrée en paramètre
	 * 
	 * @param direction
	 * @param m
	 */
	
	public void deplacerVers (String direction, Moteur m) {
		
		Vecteur vecteurDeplacement ;
		if(this.getPV()>0) {
		switch (direction) {

			case "haut" : if (this.peutSauter)
				
							vecteurDeplacement = new Vecteur (0, -this.getPuissanceSaut()) ;
			
						  else
							  
							vecteurDeplacement = new Vecteur (0, 0) ;
	
			break ;
	
			case "droite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, 0) ;
	
			break ;
	
			case "bas" : vecteurDeplacement = new Vecteur (0, this.vitesseDeplacement) ;
	
			break ;
	
			case "gauche" : vecteurDeplacement = new Vecteur (-vitesseDeplacement, 0) ;
	
			break ;
			
			case "hautdroite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, -this.getPuissanceSaut()) ;
	
			break ;
			
			case "basdroite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, this.vitesseDeplacement) ;
	
			break ;
			
			case "basgauche" : vecteurDeplacement = new Vecteur (-vitesseDeplacement, this.vitesseDeplacement) ;
	
			break ;
			
			case "hautgauche" : vecteurDeplacement = new Vecteur (-this.vitesseDeplacement, -this.getPuissanceSaut()) ;
	
			break ;
			
			default : vecteurDeplacement = new Vecteur (0, 0) ;
			
			break ;

		}
		
		this.ajouter(vecteurDeplacement) ;
		
		}
	}
	

	public double getPtsAttaque () {

		
		return this.ptsAttaque.getValue () ;
		
	}
	public Jeu getJeu() {
		return jeu;
	}

	@Override
	public void interactionClick(int x, int y, Jeu jeu) {
		this.perdrePV(10);
	}

	public boolean tMort() {
		if (this.getPV()<0) {
			return true;
		}
		return false;
	}
	public boolean getInvincible() {
		return this.invincible;
	}
	public void prendreDegat(double pv) {
		if(!this.invincible) {
			this.perdrePV(pv);
			this.invincible=true;
		}
	}
	
	public void vulnerable() {
		this.invincible=false;
		this.compteurFps=0;
	}
	
	public int compteInvincible() {
		this.compteurFps++;
		return this.compteurFps;
	}
	
	public String getNom () {
		
		return this.nom ;
		
	}
	
}
