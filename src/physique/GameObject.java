package physique;

import javafx.beans.property.* ;

public class GameObject {
	
	String tag ;
	private DoubleProperty pv ;
	private DoubleProperty x;
	private DoubleProperty y;
	private DoubleProperty vitesse ;
	private Collisionneur collisionneur ;
	
	public GameObject (String tag, Collisionneur c) {
		
		this (tag, 0., 0., 0., 0., c) ;
		
	}
	
	public GameObject (String tag, double pv, double x, double y, double vitesse, Collisionneur collisionneur) {
		
		this.tag = tag ;
		this.x = new SimpleDoubleProperty(pv) ;
		this.x = new SimpleDoubleProperty(x) ;
		this.y = new SimpleDoubleProperty(y) ;
		this.vitesse = new SimpleDoubleProperty(vitesse) ;
		this.collisionneur = collisionneur ;
		
	}
	
	public void changerNom (String nom) {
		
		this.tag = nom ;
		
	}
	
	public String getTag () {
		
		return this.tag ;
		
	}
	
	public double getPV () {
		
		return this.pv.getValue() ;
		
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
	
	public void deplace (double x, double y) {
		
		//double nvY = this.getY()+y ;
		//double nvX = this.getX()+x ;
		
		//this.setY((double)Math.round(nvY * 10) / 10);
		//this.setX((double)Math.round(nvX * 10) / 10);
		
		this.setX(this.collisionneur.getXDeb() + x) ;
		this.setY(this.collisionneur.getYDeb() + y) ;
		
		this.collisionneur.setXDeb(this.collisionneur.getXDeb() + x);
		this.collisionneur.setYDeb(this.collisionneur.getYDeb() + y);
		this.collisionneur.setXFin(this.collisionneur.getXFin() + x);
		this.collisionneur.setYFin(this.collisionneur.getYFin() + y);
		
	}
	
	public void deplace (String direction, double distanceDeplacement) {
		
		switch (direction) {
		
			case "haut" : 
				this.deplace(0, -distanceDeplacement * this.getVitesse());
				break ;
			case "droite" : 
				this.deplace(distanceDeplacement * this.getVitesse(), 0); 
				break ;
			case "bas" : 
				this.deplace(0, distanceDeplacement * this.getVitesse()); 
				break ;
			case "gauche" : 
				this.deplace(-distanceDeplacement * this.getVitesse(), 0); 
				break ;
		
		}
		
		System.out.println("x :" + this.getX() + ", y : " + this.getY()) ;
		
	}
	
	public double getVitesse () {
		
		return this.vitesse.getValue() ;
		
	}
	
	public void setVitesse (double v) {
		
		this.vitesse.set(v);
		
	}
	
	public Collisionneur getCollisionneur () {
		
		return this.collisionneur ;
		
	}

}
