package physique;

import modele.* ;

import java.util.ArrayList;
import java.util.Vector;

import exceptions.* ;
import geometrie.Vecteur;
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

	Polygone boite ;

	public Collisionneur () {
		
		this.boite = new Polygone () ;

	}
	
	public boolean depasseLesLimitesDeLaMap (Terrain t) throws HorsDeLaMapException {
		
		int i = 0 ;
		boolean depasse = false ;
		
		while (i < this.boite.nbSommets()) {
			
			if (this.boite.get(i).getY() < 0 || this.boite.get(i).getX() < 0 || this.boite.get(i).getY() > t.getTailleY() || this.boite.get(i).getX() > t.getTailleX())
				
				throw new HorsDeLaMapException (t) ;
			
		}
		
		return depasse ;
		
	}
	
	// Renvoie la distance dont peut se d�placer le perso dans la direction donn�e

	public double deplacementPossible (Vecteur v, Terrain t, Moteur m) throws VousEtesCoinceException, HorsDeLaMapException {
		
			if (!depasseLesLimitesDeLaMap(t))
				
				System.out.println("ok");

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
	
	public boolean chevauche (Collisionneur c) {
		
		int i ;
		int j ;
		double a1, a2, b1, b2, c1, c2, denominateur, intersectX, intersectY, ratioIntersectY, ratioIntersectX ;
		boolean chevauche ;
		
		i = 0 ;
		j = 0 ;
		chevauche = false ;
		
		Point2D p0, p1, p2, p3 ;
		
		while (!chevauche && i < this.boite.nbSommets()) {
			
			p0 = this.boite.get(i) ;
			p1 = this.boite.get((i + 1) % this.boite.nbSommets()) ;
			
			while (!chevauche && j < c.getBoite().nbSommets()) {
				
				p2 = c.getBoite().get(j) ;
				p3 = c.getBoite().get(j) ;
				
				a1 = p1.getY() - p0.getY() ;
				b1 = p0.getX() - p1.getX() ;
				c1 = a1 * p0.getX() + b1 * p0.getY() ;
				
				a2 = p3.getY() - p2.getY() ;
				b2 = p2.getX() - p3.getX() ;
				c2 = a2 * p2.getX() + b2 * p2.getY() ;
				
				denominateur = a1 * b2 - a2 * b1 ;
				
				intersectX = (b2 * c1 - b1 * c2) / denominateur ;
				intersectY = (a1 * c2 - a2 * c1) / denominateur ;
				ratioIntersectX = (intersectX - p0.getX()) / (p1.getX() - p0.getX()) ;
				ratioIntersectY = (intersectY - p0.getY()) / (p)
				
				j ++ ;
				
			}
			
			i ++ ;
			
		}
		
		return chevauche ;
		
	}
	
	public Polygone getBoite () {
		
		return this.boite ;
		
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
