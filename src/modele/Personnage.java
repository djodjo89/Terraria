package modele;

import javafx.geometry.Point2D ;
import javafx.beans.property.* ;

/*
 * Un personnage dispose de coordonnées modifiables et accessibles
 */

public class Personnage {
	
	private DoubleProperty pv ;
	private DoubleProperty ptsAttaque ;
	private String nom ;
	private Objet main ;
	Point2D coordonnees ;
	
	public Personnage () {
		
		this ("", 0, 0) ;
		
	}
	
	public Personnage (String nom, int x, int y) {
		
		this.nom = nom ;
		this.coordonnees = new Point2D (x, y) ;
		
	}
	
	public void donner (Objet o) {
		
		this.main = o ;
		
	}
	
	public double getPV () {
		
		return this.pv.getValue() ;
		
	}
	
	public void changerNom (String nom) {
		
		this.nom = nom ;
		
	}
	
	public String getNom () {
		
		return this.nom ;
		
	}
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue () ;
		
	}
	
	public Objet getMain () {
		
		return this.main ;
		
	}
	
	public Point2D getCoordonnees () {
		
		return this.coordonnees ;
		
	}
	
	public void deplace (int x, int y) {
		
		this.coordonnees = this.coordonnees.add(x, y) ;
		
	}
	
	public void deplace (String direction) {
		
		switch (direction) {
		
			case "haut" : this.deplace(0, -1); break ;
			case "droite" : this.deplace(1, 0); break ;
			case "bas" : this.deplace(0, 1); break ;
			case "gauche" : this.deplace(-1, 0); break ;
		
		}
		
	}

}
