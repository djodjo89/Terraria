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
	private Outil main ;
	private DoubleProperty x;
	private DoubleProperty y;
		
	public Personnage (String nom, int x, int y) {
		
		this.nom = nom ;
		this.x= new SimpleDoubleProperty(x);
		this.y= new SimpleDoubleProperty(y);
		
	}
	
	public double getX() {
		return this.x.getValue();
	}
	public double getY() {
		return this.y.getValue();
	}
	public DoubleProperty getXProperty() {
		return this.x;
	}
	public DoubleProperty getYProperty() {
		return this.y;
	}
	public void setX(double x) {
		this.x.setValue(x);
	}
	public void setY(double y) {
		this.y.setValue(y);
	}
	
	public void donner (Outil o) {
		
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
	
	
	public void deplace (double x, double y) {
		
		this.setY(this.y.getValue()+y);
		this.setX(this.x.getValue()+x);
		
	}
	
	public void deplace (String direction) {
		
		switch (direction) {
		
			case "haut" : 
				this.deplace(0, -10); 
				break ;
			case "droite" : 
				this.deplace(10, 0); 
				break ;
			case "bas" : 
				this.deplace(0,10); 
				break ;
			case "gauche" : 
				this.deplace(-10, 0); 
				break ;
		
		}
		
	}
	
	public void attaque (Objet o) {
		
		o.perdrePV (this.main.getPtsAttaque()) ;
		
	}

}
