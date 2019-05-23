package physique;
import geometrie.*;

import modele.* ;
import javafx.beans.property.* ;

public class GameObject {
	
	String tag ;
	private DoubleProperty pv ;
	private DoubleProperty posX ;
	private DoubleProperty posY ;
	private Vecteur vecteurVitesse ; 
	private double masse ;
	private boolean estUnObstacle ;
	/**
	 * @see Collisionneur
	 */
	private Collisionneur collisionneur ;
	
	public GameObject (String tag) {
		
		this.tag = tag ;
		
	}
	
	public GameObject (String tag, double pv, double posX, double posY, double masse, Collisionneur collisionneur) {
		
		this.tag = tag ;
		this.posX = new SimpleDoubleProperty(pv) ;
		this.posX = new SimpleDoubleProperty(posX) ;
		this.posY = new SimpleDoubleProperty(posY) ;
		this.vecteurVitesse = new Vecteur(0, 0) ;
		this.masse = masse ;
		this.collisionneur = collisionneur ;
		this.estUnObstacle = false ;
		
	}
	
	public void setPv (double pv) {
		
		this.pv.set(pv) ;
		
	}
	
	public boolean estUnObstacle () {
		
		return this.estUnObstacle ;
		
	}
	
	public void setObstacle () {
		
		this.estUnObstacle = true ;
		
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
		return this.posX.getValue();
	}
	public double getY() {
		return this.posY.getValue();
	}
	
	public DoubleProperty getXProperty() {
		return this.posX;
	}
	public DoubleProperty getYProperty() {
		return this.posY;
	}
	
	private void setX(double x) {
		this.posX.setValue(x);
	}
	private void setY(double y) {
		this.posY.setValue(y);
	}
	
	public void changerVitesse (double x, double y) {
		
		this.vecteurVitesse.ajouter(x, y) ;
		
	}
	
	public void setVitesse (Vecteur vecteur) {
		
		this.vecteurVitesse = vecteur ;
		
	}
	
	public void deplacer () {
		
		this.setX(this.getX() + this.vecteurVitesse.getX()) ;
		this.setY(this.getY() + this.vecteurVitesse.getY()) ;
		this.collisionneur.getBoite().ajouterAChaquePoint(this.vecteurVitesse) ;
		
	}
	
	public Vecteur getVecteurVitesse () {
		
		return this.vecteurVitesse ;
		
	}
	
	public double getDistanceDeplacement () {
		
		return this.vecteurVitesse.getNorme() ;
		
	}
	
	public Collisionneur getCollisionneur () {
		
		return this.collisionneur ;
		
	}

}
