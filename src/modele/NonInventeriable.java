package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import physique.Collisionneur;
import physique.GameObject;

public abstract class NonInventeriable extends GameObject{
	
	private Jeu jeu;
	private DoubleProperty ptsAttaque ;
	
	
	
	public NonInventeriable () {
		
		super ("", 1000, new Collisionneur(),false) ;
		
	}
	
	public NonInventeriable (String nom, double pv, double x, double y, double poids, Collisionneur c, double distanceDeplacement,Jeu jeu,double ptsAtt) {
		
		super (nom, pv, x, y, poids, c, distanceDeplacement) ;
		this.jeu=jeu;
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		System.out.println("voici mon attaque :"+this.ptsAttaque.getValue());
		
	}
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue () ;
		
	}
	public Jeu getJeu() {
		return jeu;
	}
	
}
