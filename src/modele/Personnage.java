package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import physique.Collisionneur;
import physique.GameObject;

public abstract class Personnage extends GameObject{
	
	private DoubleProperty ptsAttaque ;
	private double hauteurSaut ;
	
	public Personnage (String nom, double pv, double ptsAtt, double posX, double posY, double masse, double hauteurSaut, double vitesseDeplacement, Collisionneur collisionneur, Jeu jeu) {
		
		super (nom, pv, posX, posY, masse, vitesseDeplacement, collisionneur, jeu) ;
		this.hauteurSaut = hauteurSaut ; // ((51.9 * this.hauteurSaut + 48.9 * this.masse - 2007) / m.getTailleBoiteY()*650)
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		System.out.println("voici mon attaque :"+this.ptsAttaque.getValue());
		
	}
	
	/**
	 * Calcule et renvoie la puissance du saut du GameObject selon le Moteur
	 * 
	 * @param moteur
	 */
	
	public double getPuissanceSaut () {

		return ((51.9 * this.hauteurSaut + 48.9 * super.getMasse() - 2007) / super.getJeu().getMoteur().getTailleBoiteY()*650) ;
		
	} 
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue () ;
		
	}
	
}
