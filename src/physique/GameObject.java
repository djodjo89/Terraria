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
	private double poids ;
	private boolean estUnObstacle ;
	/**
	 * @see Collisionneur
	 */
	private Collisionneur collisionneur ;
	
	// Pour les objets statiques
	public GameObject (String tag, double pv, double posX, double posY, Collisionneur c) {
		
		this(tag, pv, posX, posY, 1., c) ;
		
	}
	
	public GameObject (String tag, double pv, double posX, double posY, double poids, Collisionneur collisionneur) {
		
		this.tag = tag ;
		this.posX = new SimpleDoubleProperty(pv) ;
		this.posX = new SimpleDoubleProperty(posX) ;
		this.posY = new SimpleDoubleProperty(posY) ;
		this.vecteurVitesse = new Vecteur(0, 0) ;
		this.poids = poids ;
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
	
	// Déplace le gameObject de x et y multipliés par la distance
	// de déplacement du moteur
	
	public void deplacementSansVerif () {
		
		this.deplacementSansCollisionneur() ;		
		this.collisionneur.getBoite().ajouterAChaquePoint(this.vecteurVitesse) ;
				
	}
	
	private void deplacementSansCollisionneur () {
		
		this.setX(this.getX() + this.vecteurVitesse.getX()) ;
		this.setY(this.getX() + this.vecteurVitesse.getX()) ;
		
	}
	
	public double getDistanceDeplacement () {
		
		return this.vecteurVitesse.getNorme() ;
		
	}
	
	public Collisionneur getCollisionneur () {
		
		return this.collisionneur ;
		
	}
	
	public void sauter(Terrain t,Moteur m) {
		try {
			if (this.getCollisionneur().deplacementPossible ("haut", t, this, m))
				this.deplacementSansVerif(0,-(m.getGravite()+1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
