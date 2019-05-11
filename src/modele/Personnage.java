package modele;

import physique.GameObject;
import javafx.beans.property.* ;

/*
 * Un personnage dispose de coordonnées modifiables et accessibles
 */

public class Personnage extends GameObject {
	
	private DoubleProperty ptsAttaque ;
	private Outil main ;
	
	public Personnage () {
		
		super () ;
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public Personnage (String nom, double x, double y, double ptsAtt) {
		
		super () ;
		this.ptsAttaque.set(ptsAtt) ;
		
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
