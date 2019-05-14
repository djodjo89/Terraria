package physique;

import modele.* ;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.* ;
import javafx.util.Duration;

public class GameObject {
	
	String tag ;
	private DoubleProperty pv ;
	private DoubleProperty x;
	private DoubleProperty y;
	private double vitesseX ;
	private double vitesseY ;
	private double poids ;
	private Collisionneur collisionneur ;
	
	public GameObject (String tag, Collisionneur c) {
		
		this (tag, 0., 0., 0., 0., 0., 0., c) ;
		
	}
	
	public GameObject (String tag, double pv, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur collisionneur) {
		
		this.tag = tag ;
		this.x = new SimpleDoubleProperty(pv) ;
		this.x = new SimpleDoubleProperty(x) ;
		this.y = new SimpleDoubleProperty(y) ;
		this.vitesseX = vitesseX ;
		this.vitesseY = vitesseY ;
		this.poids = poids ;
		this.collisionneur = collisionneur ;
		
	}
	
	public void changerTag (String tag) {
		
		this.tag = tag ;
		
	}
	
	public String getTag () {
		
		return this.tag ;
		
	}
	
	public double getPV () {
		
		return this.pv.getValue() ;
		
	}
	
	public void perdrePV (double pv) {
		
		this.pv.set(this.pv.getValue() - pv);
		
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
	
	private void setX(double x) {
		this.x.setValue(x);
	}
	private void setY(double y) {
		this.y.setValue(y);
	}
	
	// Déplace le gameObject de x et y multipliés par la distance
	// de déplacement du moteur
	
	public void deplace (double x, double y, Moteur m) {
		
		this.setX(this.collisionneur.getXDeb() + x * m.getDistanceDeplacement()) ;
		this.setY(this.collisionneur.getYDeb() + y * m.getDistanceDeplacement()) ;
		
		this.collisionneur.setXDeb(this.collisionneur.getXDeb() + x * m.getDistanceDeplacement());
		this.collisionneur.setYDeb(this.collisionneur.getYDeb() + y * m.getDistanceDeplacement());
		this.collisionneur.setXFin(this.collisionneur.getXFin() + x * m.getDistanceDeplacement());
		this.collisionneur.setYFin(this.collisionneur.getYFin() + y * m.getDistanceDeplacement());
		
	}
	
	// Déplace le gameObject dans la direction indiquée
	
	public void deplace (String direction, Moteur m) {
		
		switch (direction) {
		
			case "haut" : this.deplace(0,-1, m) ; break ;
			
			case "droite" : this.deplace(1, 0, m) ; break ;
			
			case "bas" : this.deplace(0, 1, m) ; break ;
			
			case "gauche" : this.deplace(-1, 0, m) ; break ;
		
		}
		
	}
	

    
	
	public double getVitesseX () {
		
		return this.vitesseX ;
		
	}
	
	public void setVitesseX (double vX) {
		
		this.vitesseX = vX ;
		
	}
	
	public double getVitesseY () {
		
		return this.vitesseY ;
		
	}
	
	public void setVitesseY (double vY) {
		
		this.vitesseY = vY ;
		
	}
	
	public Collisionneur getCollisionneur () {
		
		return this.collisionneur ;
		
	}
	public void sauter(Terrain t,Moteur m) {
		try {
			if (this.getCollisionneur().deplacementPossible ("haut", t, this, m))
				this.deplace(0,-(m.getGravite()+1),m);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
