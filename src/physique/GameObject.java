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
	public double vitesseDeplacement ;
	private double masse ;
	private double hauteurSaut ;
	private boolean estUnObstacle ;
	/**
	 * @see Collisionneur
	 */
	private Collisionneur collisionneur ;
	
	public GameObject (String tag) {
		
		this.tag = tag ;
		
	}
	
	public GameObject (String tag, double pv, double posX, double posY, double masse, Collisionneur collisionneur) {
		
		this.vitesseDeplacement = 0.000001 ;
		this.hauteurSaut = 1 ;
		this.tag = tag ;
		this.posX = new SimpleDoubleProperty(pv) ;
		this.posX = new SimpleDoubleProperty(posX) ;
		this.posY = new SimpleDoubleProperty(posY) ;
		this.vecteurVitesse = new Vecteur(0, 0) ;
		this.masse = masse ;
		this.collisionneur = collisionneur ;
		this.estUnObstacle = false ;
		
	}
	
	public double getMasse () {
		
		return this.masse ;
		
	}
	
	private double getPuissanceSaut (Moteur m) {
		System.out.println(Math.pow(m.getTailleBoiteY(), 2));
		return -((51.9 * this.hauteurSaut + 48.9 * this.masse - 2007) / m.getTailleBoiteY()) ;
		
	}
	
	public double getHauteur (Moteur m, Terrain t) {
		
		int i ;
		int[] coordonnees ;
		
		i = 0 ;
		coordonnees = new int[2] ;
		
		this.collisionneur.getCoordonneesEntieresSurLaMap (new Point (this.getX(), this.getY()), m, coordonnees) ;
		
		while (!t.getCase(coordonnees, m).getTag().equals("T") && i < t.getDimY()) {
			
			coordonnees[1] += 1 ;
			i ++ ;
			
		}
		
		return i * m.getTailleBoiteY() ;
		
	}
	
	public void deplacerVers (String direction, Moteur m) {
		
		Vecteur vecteurDeplacement ;

		switch (direction) {

			case "haut" : vecteurDeplacement = new Vecteur (0, -this.getPuissanceSaut(m)) ;
	
			break ;
	
			case "droite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, 0) ;
	
			break ;
	
			case "bas" : vecteurDeplacement = new Vecteur (0, this.vitesseDeplacement) ;
	
			break ;
	
			case "gauche" : vecteurDeplacement = new Vecteur (-vitesseDeplacement, 0) ;
	
			break ;
			
			case "hautdroite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, -this.getPuissanceSaut(m)) ;
	
			break ;
			
			case "basdroite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, this.vitesseDeplacement) ;
	
			break ;
			
			case "basgauche" : vecteurDeplacement = new Vecteur (-vitesseDeplacement, this.vitesseDeplacement) ;
	
			break ;
			
			case "hautgauche" : vecteurDeplacement = new Vecteur (-this.vitesseDeplacement, -this.getPuissanceSaut(m)) ;
	
			break ;
			
			default : vecteurDeplacement = new Vecteur (0, 0) ;
			
			break ;

		}
		
		this.ajouter(vecteurDeplacement) ;
		
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
	
	public double getVitesseDeplacement () {
		
		return this.vitesseDeplacement ;
		
	}
	
	public void ajouter (Vecteur vecteur) {
		
		this.vecteurVitesse.ajouter(vecteur); ;
		
	}
	
	public void deplacer () {
		
		this.setX(this.getX() + this.vecteurVitesse.getX()) ;
		this.setY(this.getY() + this.vecteurVitesse.getY()) ;
		this.collisionneur.getBoite().ajouterAChaquePoint(this.vecteurVitesse) ;
		
	}
	
	public Vecteur getVecteurVitesse () {
		
		return this.vecteurVitesse ;
		
	}
	
	public void setCollisionneur (Collisionneur collisionneur) {
		
		this.collisionneur = collisionneur ;
		
	}
	
	public Collisionneur getCollisionneur () {
		
		return this.collisionneur ;
		
	}

}
