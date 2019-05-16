package physique;

import modele.* ;

import java.util.ArrayList;
import java.util.Vector;

import exceptions.* ;
import javafx.geometry.Point2D;

/*
 * Ma classe préférée :) J'ai passé un week-end dessus
 * Collisionneur est une "boîte" délimitée par quatre
 * côtés : xDeb pour le gauche, yDeb pour le haut, 
 * xFin pour le droit et yFin pour celui du bas
 * Il peut dire si un déplacement est possible à partir
 * d'une direction de déplacement, d'un terrain et d'un
 * moteur et s'afficher.
 */

public class Collisionneur {

	ArrayList<Point2D> listeSommets ;

	public Collisionneur () {
		
		this.listeSommets = new ArrayList<Point2D> () ;

	}
	
	public void initCollisionneur (GameObject go, Moteur m) {
		
		this.ajouterSommet(go.getX(), go.getY());
		this.ajouterSommet(go.getX() + m.getTailleBoiteX(), go.getY());
		this.ajouterSommet(go.getX(), go.getY() + m.getTailleBoiteY());
		this.ajouterSommet(go.getX() + m.getTailleBoiteX(), go.getY() + m.getTailleBoiteY());
		
	}
	
	public void ajouterSommet (double x, double y) {
		
		this.listeSommets.add(new Point2D(x, y)) ;
		
	}
	
	public double depasseLesLimitesDeLaMap () {
		
		
		
	}
	
	// Renvoie la distance dont peut se d�placer le perso dans la direction donn�e

	private double deplacementPossible (VecteurVitesse v, Terrain t, Moteur m) throws VousEtesCoinceException {

		/*boolean depassePlafond = this.yDeb + y * go.getDistanceDeplacement() < 0 ;
		boolean depasseMurDroite = this.xFin + x * go.getDistanceDeplacement() > t.getTailleX() ;
		boolean depasseFond = this.yFin + y * m.getGravite() * go.getDistanceDeplacement() > t.getTailleY() ;
		boolean depasseMurGauche = this.xDeb + x * go.getDistanceDeplacement() < 0 ;

		boolean rentreDansUnObstacle=true;
		boolean peutAvancer = !(depassePlafond || depasseMurDroite || depasseFond || depasseMurGauche) ;*/

		double deplacementPossible ;
		
			if (!this.chevaucheUnObstacle(t, m))
				
				

			else {

				throw new VousEtesCoinceException (this, t, m) ;

			}
		return 0 ;

	}

	private boolean chevaucheUnObstacle (Terrain t, Moteur m) {

		return t.getListeLignes().get(this.getCoorYDebActuel(m)).get(this.getCoorXDebActuel(m)).estUnObstacle()
				|| t.getListeLignes().get(this.getCoorYDebActuel(m)).get(this.getCoorXFinActuel(m)).estUnObstacle()
				|| t.getListeLignes().get(this.getCoorYFinActuel(m)).get(this.getCoorXDebActuel(m)).estUnObstacle()
				|| t.getListeLignes().get(this.getCoorYFinActuel(m)).get(this.getCoorXFinActuel(m)).estUnObstacle() ;

	}
	
	public double chevauche (Collisionneur c) {
		
		
		
	}

	private int getCoorXSuiv (int x, Moteur m, GameObject go) {

		return (int) ((this.getXDeb() + x * go.getDistanceDeplacement()) / m.getTailleTileX()) ;

	}
	
	private int getCoorYSuiv (int y, Moteur m, GameObject go) {

		return (int) ((this.getYDeb() + y * go.getDistanceDeplacement()-10)  / m.getTailleTileY()) ;

	}
	
	public int getCoorXDebActuel (Moteur m) {

		return (int) ((this.getXDeb()) / m.getTailleTileX()) ;

	}
	
	public int getCoorYDebActuel (Moteur m) {

		return (int) ((this.getYDeb()) / m.getTailleTileY()) ;

	}

	public String toString () {

		return "xDeb : " + this.xDeb + ", yDeb : " + this.yDeb + "\nxFin : " + this.xFin + ", yFin : " + this.yFin ;

	}

}
