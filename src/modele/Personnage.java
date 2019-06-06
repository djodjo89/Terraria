package modele;

import geometrie.Vecteur;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import physique.Collisionneur;
import physique.GameObject;
import physique.Moteur;

public abstract class Personnage extends GameObject{
	
	private Jeu jeu;
	private DoubleProperty ptsAttaque ;
	public boolean peutSauter ;
	private double hauteurSaut;
	
	
	public Personnage () {
		
		super ("", 1000, new Collisionneur(),false) ;
		
	}
	
	public Personnage (String nom, double pv, double ptsAtt, double posX, double posY, double masse, double hauteurSaut, double vitesseDeplacement, Collisionneur collisionneur, Jeu jeu) {
		
		super (nom, pv, posX, posY, masse, vitesseDeplacement, collisionneur, jeu) ;
		this.jeu=jeu;
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		System.out.println("voici mon attaque :"+this.ptsAttaque.getValue());
		this.peutSauter = false ;
		this.hauteurSaut = hauteurSaut ; // ((51.9 * this.hauteurSaut + 48.9 * this.masse - 2007) / m.getTailleBoiteY()*650)
		
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
		
		System.out.println(v.getY());
		
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
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue () ;
		
	}
	public Jeu getJeu() {
		return jeu;
	}
	
}
