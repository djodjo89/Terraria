package physique;

import javafx.beans.property.* ;

public class GameObject {
	
	String nom ;
	private DoubleProperty pv ;
	private DoubleProperty x;
	private DoubleProperty y;
	private DoubleProperty distanceDeplacement ;
	private DoubleProperty vitesse ;
	
	public GameObject () {
		
		this ("", 0, 0, 0 , 1, 1) ;
		
	}
	
	public GameObject (String nom, double pv, double x, double y, double d, double v) {
		
		this.nom = nom ;
		this.x = new SimpleDoubleProperty(pv) ;
		this.x = new SimpleDoubleProperty(x) ;
		this.y = new SimpleDoubleProperty(y) ;
		this.distanceDeplacement = new SimpleDoubleProperty(d) ;
		this.vitesse = new SimpleDoubleProperty(v) ;
		
	}
	
	public void changerNom (String nom) {
		
		this.nom = nom ;
		
	}
	
	public String getNom () {
		
		return this.nom ;
		
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

}
