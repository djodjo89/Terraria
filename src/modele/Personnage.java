package modele;

import physique.*;
import javafx.beans.property.* ;

/*
 * Un personnage dispose de coordonnées modifiables et accessibles
 */

public class Personnage extends GameObject {
	
	private DoubleProperty ptsAttaque ;
	private Outil main ;
	
	public Personnage () {
		
		super ("", new Collisionneur()) ;
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public Personnage (String nom, double pv, double ptsAtt, double x, double y, double vitesse, Collisionneur c) {
		
		super (nom, pv, x, y, vitesse, c) ;
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		
	}
	
	public void donner (Outil o) {
		
		this.main = o ;
		
	}
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue () ;
		
	}
	
	public Objet getMain () {
		
		return this.main ;
		
	}
	
	public void attaque (Objet o) {
		
		o.perdrePV (this.main.getPtsAttaque()) ;
		
	}

}
