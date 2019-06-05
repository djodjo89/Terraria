package modele;

import geometrie.Vecteur;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import physique.Collisionneur;
import physique.GameObject;
import physique.Moteur;

public abstract class NonInventeriable extends GameObject{
	
	private Jeu jeu;
	private DoubleProperty ptsAttaque ;
	public boolean peutSauter ;
	
	
	public NonInventeriable () {
		
		super ("", 1000, new Collisionneur(),false) ;
		
	}
	
	public NonInventeriable (String nom, double pv, double x, double y, double poids, Collisionneur c, double distanceDeplacement,Jeu jeu,double ptsAtt) {
		
		super (nom, pv, x, y, poids, c, distanceDeplacement) ;
		this.jeu=jeu;
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		System.out.println("voici mon attaque :"+this.ptsAttaque.getValue());
		this.peutSauter = false ;
		
	}
	
	public void setSautPossible () {
		
		this.peutSauter = true ;
		
	}
	
	public void setSautImpossible () {
		
		this.peutSauter = false ;
		
	}
	public void verifSiPeutSauter (Vecteur v) {
		
		if (v.getY() == 0.0)
			
			this.setSautPossible() ;
		
		else
			
			this.setSautImpossible() ;
		
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
				
							vecteurDeplacement = new Vecteur (0, -super.getPuissanceSaut(m)) ;
			
						  else
							  
							vecteurDeplacement = new Vecteur (0, 0) ;
	
			break ;
	
			case "droite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, 0) ;
	
			break ;
	
			case "bas" : vecteurDeplacement = new Vecteur (0, this.vitesseDeplacement) ;
	
			break ;
	
			case "gauche" : vecteurDeplacement = new Vecteur (-vitesseDeplacement, 0) ;
	
			break ;
			
			case "hautdroite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, -this.getPuissanceSaut(m)) ;
	
			break ;
			
			case "basdroite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, this.vitesseDeplacement) ;
	
			break ;
			
			case "basgauche" : vecteurDeplacement = new Vecteur (-vitesseDeplacement, this.vitesseDeplacement) ;
	
			break ;
			
			case "hautgauche" : vecteurDeplacement = new Vecteur (-this.vitesseDeplacement, -this.getPuissanceSaut(m)) ;
	
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
