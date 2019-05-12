package physique;

import modele.* ;
import java.util.ArrayList;

import javafx.beans.property.*;

public class Collisionneur {
	
	private DoubleProperty xDeb;
	private DoubleProperty yDeb;
	private DoubleProperty xFin;
	private DoubleProperty yFin;
	
	public Collisionneur () {
		
		this (0., 0., 0., 0.);
		
	}
	
	public Collisionneur (double xDeb, double yDeb, double xFin, double yFin) {
		
		this.xDeb = new SimpleDoubleProperty(xDeb);
		this.yDeb = new SimpleDoubleProperty(yDeb);
		this.xFin = new SimpleDoubleProperty(xFin);
		this.yFin = new SimpleDoubleProperty(yFin);
		
	}

	
	public boolean deplacementPossible (String direction, double distanceDeplacement, Terrain t, Personnage p, Moteur m) {
		
		boolean deplacementOK ;
		
		deplacementOK = false ;
		
		switch (direction) {
		
			case "haut" : deplacementOK = !tombeSurUnObstacle(direction,0, -1, distanceDeplacement, t, m) ; break ;
			case "droite" : deplacementOK = !tombeSurUnObstacle(direction,1, 0, distanceDeplacement, t, m) ; break ;
			case "bas" : deplacementOK = !tombeSurUnObstacle(direction,0, 1, distanceDeplacement, t, m) ; break ;
			case "gauche" : deplacementOK = !tombeSurUnObstacle(direction,-1, 0, distanceDeplacement, t, m) ; break ;
		
		}
		
		return deplacementOK ;
		
	}
	
	public int getCoorXDeb (int x, double distanceDeplacement, Moteur m) {
		
		return (int) ((this.getXDeb() + x * distanceDeplacement) / m.getTailleTileX()) ;
		
	}
	
	public int getCoorYDeb (int y, double distanceDeplacement, Moteur m) {
		
		return (int) ((this.getYDeb() + y * distanceDeplacement)  / m.getTailleTileY()) ;
		
	}
	
	public int getCoorXFin (int x, double distanceDeplacement, Moteur m) {
		
		return (int) ((this.getXFin() + x * distanceDeplacement)  / m.getTailleTileX()) ;
		
	}
	
	public int getCoorYFin (int y, double distanceDeplacement, Moteur m) {
		
		return (int) ((this.getYFin() + y * distanceDeplacement)  / m.getTailleTileY()) ;
		
	}
	
	public  boolean tombeSurUnObstacle (String direction, int x, int y, double distanceDeplacement, Terrain t, Moteur m) {
		
		int ligneMapDeb, colMapDeb, ligneMapFin, colMapFin, mesCoorsXDeb, mesCoorsYDeb, mesCoorsXFin, mesCoorsYFin ;
		
		boolean depasseMurGauche, depassePlafond, depasseFond, depasseMurDroite, rentreDansUnObstacle ;
		depasseMurGauche = this.getXDeb() + x * distanceDeplacement < 0 ;
		depasseMurDroite = (this.getXFin() + x * distanceDeplacement) / m.getTailleTileX() >= t.getTailleX() ;
		depassePlafond = this.getYDeb() + y * distanceDeplacement < 0 ;
		depasseFond = (this.getYFin() + y * distanceDeplacement) / m.getTailleTileY() >= t.getTailleY() ;
		rentreDansUnObstacle = false ;
		if (!(depasseMurGauche || depasseMurDroite || depassePlafond || depasseFond)) {
			
			colMapDeb = 0 ;
			ligneMapDeb = 0 ;
			colMapFin = 0 ;
			ligneMapFin = 0 ;
			mesCoorsXDeb = this.getCoorXDeb(x, distanceDeplacement, m) ;
			mesCoorsYDeb = this.getCoorYDeb(y, distanceDeplacement, m) ;
			mesCoorsXFin = this.getCoorXFin(x, distanceDeplacement, m) ;
			mesCoorsYFin = this.getCoorYFin(y, distanceDeplacement, m) ;
			
			switch (direction) {
			//	public boolean seSuperposeA (int x, int y, double distanceDeplacement, Collisionneur c)
				case "haut" :
					colMapDeb = mesCoorsXDeb ;
					ligneMapDeb = mesCoorsYDeb ;
					colMapFin = mesCoorsXFin ;
					ligneMapFin = mesCoorsYFin ;
					rentreDansUnObstacle = ((this.seSuperposeA(x, y, distanceDeplacement, t.getListeLignes().get(ligneMapDeb).get(colMapDeb).getCollisionneur()) 
							&& estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligneMapDeb).get(colMapDeb).getNom()))
							||(this.seSuperposeA(x, y, distanceDeplacement, t.getListeLignes().get(ligneMapDeb).get(colMapFin).getCollisionneur()) 
									&& estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligneMapDeb).get(colMapFin).getNom()))) ;
				break ;
				
				case "droite" :
					colMapDeb = mesCoorsXDeb ;
					ligneMapDeb = mesCoorsYDeb ;
					colMapFin = mesCoorsXFin ;
					ligneMapFin = mesCoorsYFin ;
					rentreDansUnObstacle = ((this.seSuperposeA(x, y, distanceDeplacement, t.getListeLignes().get(ligneMapDeb).get(colMapFin).getCollisionneur()) 
							&& estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligneMapDeb).get(colMapFin).getNom()))
							||(this.seSuperposeA(x, y, distanceDeplacement, t.getListeLignes().get(ligneMapFin).get(colMapFin).getCollisionneur()) 
									&& estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligneMapFin).get(colMapFin).getNom()))) ;
				break ;
				
				case "bas" :
					colMapDeb = mesCoorsXDeb ;
					ligneMapDeb = mesCoorsYDeb ;
					colMapFin = mesCoorsXFin ;
					ligneMapFin = mesCoorsYFin ;
					rentreDansUnObstacle = ((this.seSuperposeA(x, y, distanceDeplacement, t.getListeLignes().get(ligneMapFin).get(colMapDeb).getCollisionneur()) 
							&& estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligneMapDeb).get(colMapDeb).getNom()))
							||(this.seSuperposeA(x, y, distanceDeplacement, t.getListeLignes().get(ligneMapFin).get(colMapFin).getCollisionneur()) 
									&& estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligneMapFin).get(colMapFin).getNom()))) ;
				break ;
				
				case "gauche" :
					colMapDeb = mesCoorsXDeb ;
					ligneMapDeb = mesCoorsYDeb ;
					colMapFin = mesCoorsXFin ;
					ligneMapFin = mesCoorsYFin ;
					rentreDansUnObstacle = ((this.seSuperposeA(x, y, distanceDeplacement, t.getListeLignes().get(ligneMapDeb).get(colMapDeb).getCollisionneur()) 
							&& estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligneMapDeb).get(colMapDeb).getNom()))
							||(this.seSuperposeA(x, y, distanceDeplacement, t.getListeLignes().get(ligneMapFin).get(colMapDeb).getCollisionneur()) 
									&& estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligneMapFin).get(colMapFin).getNom()))) ;
				break ;
			
			}
			
			System.out.println();
			System.out.println("xDeb : " + this.getXDeb() + ", yDeb : " + this.getYDeb());
			System.out.println("xFin: " + this.getXFin() + ", yFin : " + this.getYFin());
			System.out.println("LigneDeb : " + ligneMapDeb + ", colonneDeb : " + colMapDeb);
			System.out.println("LigneFin : " + ligneMapFin + ", colonneFin : " + colMapFin);
			
		}
		
		return depasseMurGauche || depasseMurDroite || depassePlafond || depasseFond || rentreDansUnObstacle ;
		
	}
	
	public boolean seSuperposeA (int x, int y, double distanceDeplacement, Collisionneur c) {
		
		boolean xDS, yDS, xFS, yFS ;
		
		xDS = c.getXDeb() <= this.getXDeb() + x * distanceDeplacement && this.getXDeb() + x * distanceDeplacement <= c.getXFin() ;
		xFS = c.getXDeb() <= this.getXFin() + x * distanceDeplacement && this.getXFin() + x * distanceDeplacement <= c.getXFin() ;
		yDS = c.getYDeb() <= this.getYDeb() + x * distanceDeplacement && this.getYDeb() + x * distanceDeplacement <= c.getYFin() ;
		yFS = c.getYDeb() <= this.getYFin() + x * distanceDeplacement && this.getYFin() + x * distanceDeplacement <= c.getYFin() ;
		//System.out.println("xDS : " + xDS + " xFS : " + xFS + " yDS : " + yDS + " yFS : " + yFS);
		//System.out.println("xDebC : " + c.getXDeb() + " xFinC : " + c.getXFin() + " yDebC : " + c.getYDeb() + " yFinC : " + c.getYFin());
		return (xDS || yDS) || (xFS || yFS) ;
		
	}
	
	public static boolean estUnObstacle (ArrayList<String> obstacles, String o) {
		
		return obstacles.contains(o) ;
		
	}
	
	public double getXDeb() {
		return this.xDeb.getValue();
	}
	public double getYDeb() {
		return this.yDeb.getValue();
	}
	public double getXFin() {
		return this.xFin.getValue();
	}
	public double getYFin() {
		return this.yFin.getValue();
	}
	
	public DoubleProperty getXDebProperty() {
		return this.xFin;
	}
	public DoubleProperty getYDebProperty() {
		return this.yFin;
	}
	public DoubleProperty getXFinProperty() {
		return this.xFin;
	}
	public DoubleProperty getYFinProperty() {
		return this.yFin;
	}
	
	public void setXDeb(double x) {
		this.xDeb.setValue(x);
	}
	public void setYDeb(double y) {
		this.yDeb.setValue(y);
	}
	public void setXFin(double x) {
		this.xFin.setValue(x);
	}
	public void setYFin(double y) {
		this.yFin.setValue(y);
	}
	
	public String toString () {
		
		return "xDeb : " + this.getXDeb() + ", yDeb : " + this.getYDeb() + "\nxFin : " + this.getXFin() + ", yFin : " + this.getYFin() ;
		
	}

}
