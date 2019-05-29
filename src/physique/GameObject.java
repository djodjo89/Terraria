package physique;

import modele.* ;
import javafx.beans.property.* ;

public class GameObject {
	
	String tag ;
	private DoubleProperty pv ;
	private DoubleProperty x;
	private DoubleProperty y;
	private double vitesseX ;
	private double vitesseY ;
	private double poids ;
	private boolean estUnObstacle ;
	private Collisionneur collisionneur ;
	private double distanceDeplacement ;
	
	public GameObject (String tag) {
		
		this(tag, 100, 0., 0., 0., 0., 0., null, 0.) ;
		
	}
	
	// Pour les objets statiques
	public GameObject (String tag, double pv, Collisionneur c,boolean estUnObstacle) {
		
		this(tag, pv, 0, 0, 0., 0., 0., c, 0.) ;
		this.estUnObstacle=estUnObstacle;
		
	}
	
	public GameObject (String tag, double pv, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur collisionneur, double distanceDeplacement) {
		
		this.tag = tag ;
		this.pv = new SimpleDoubleProperty(pv);
		this.x = new SimpleDoubleProperty(x) ;
		this.y = new SimpleDoubleProperty(y) ;
		this.vitesseX = vitesseX ;
		this.vitesseY = vitesseY ;
		this.poids = poids ;
		this.collisionneur = collisionneur ;
		this.estUnObstacle = false ;
		this.distanceDeplacement=distanceDeplacement;
		
	}
	
	public void setCollisionneur (Collisionneur c) {
		
		this.collisionneur = c ;
		this.x.set(c.getXDeb()) ;
		this.y.set(c.getYDeb()) ;
		
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
	
	public void deplacementSansVerif (double x, double y) {
		
		
		this.setX(this.collisionneur.getXDeb() + x * this.distanceDeplacement) ;
		this.setY(this.collisionneur.getYDeb() + y * this.distanceDeplacement) ;
		
		this.collisionneur.setXDeb(this.collisionneur.getXDeb() + x * this.distanceDeplacement);
		this.collisionneur.setYDeb(this.collisionneur.getYDeb() + y * this.distanceDeplacement);
		this.collisionneur.setXFin(this.collisionneur.getXFin() + x * this.distanceDeplacement);
		this.collisionneur.setYFin(this.collisionneur.getYFin() + y * this.distanceDeplacement) ;
		
		
	}
	
	// Déplace le gameObject dans la direction indiquée
	
	public void deplacementSansVerif (String direction) {
		
		switch (direction) {
		
			case "haut" : this.deplacementSansVerif(0,-1) ; break ;
			
			case "droite" : this.deplacementSansVerif(1, 0) ; break ;
			
			case "bas" : this.deplacementSansVerif(0, 1) ; break ;
			
			case "gauche" : this.deplacementSansVerif(-1, 0) ; break ;
		
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
	
	public double getDistanceDeplacement () {
		
		return this.distanceDeplacement ;
		
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
