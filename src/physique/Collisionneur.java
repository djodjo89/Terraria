package physique;

import modele.* ;

public class Collisionneur {
	
	private double xDeb;
	private double yDeb;
	private double xFin;
	private double yFin;
	
	public Collisionneur () {
		
		this (0., 0., 0., 0.);
		
	}
	
	public Collisionneur (double xDeb, double yDeb, double xFin, double yFin) {
		
		this.xDeb = xDeb ;
		this.yDeb = yDeb ;
		this.xFin = xFin ;
		this.yFin = yFin ;
		
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
		
		int ligneMapDeb, colMapDeb, ligneMapFin, colMapFin ;
		
		boolean depasseMurGauche, depassePlafond, depasseFond, depasseMurDroite, rentreDansUnObstacle ;
		
		rentreDansUnObstacle = true ;
			
			colMapDeb = this.getCoorXDeb(x, distanceDeplacement, m) ;
			ligneMapDeb = this.getCoorYDeb(y, distanceDeplacement, m) ;
			colMapFin = this.getCoorXFin(x, distanceDeplacement, m) ;
			ligneMapFin = this.getCoorYFin(y, distanceDeplacement, m) ;
			
			switch (direction) {

				case "haut" :
					depassePlafond = this.yDeb + y * distanceDeplacement < 0 ;
					if (!depassePlafond)
						rentreDansUnObstacle = nePeutPasRentrerDansCetteCase(x, y, distanceDeplacement, ligneMapDeb, colMapDeb, ligneMapDeb, colMapFin, t, m) ;
				break ;
				
				case "droite" :
					depasseMurDroite = this.xFin + x * distanceDeplacement > t.getTailleX() ;
					if (!depasseMurDroite)
						rentreDansUnObstacle = nePeutPasRentrerDansCetteCase(x, y, distanceDeplacement, ligneMapDeb, colMapFin, ligneMapFin, colMapFin, t, m) ;
				break ;
				
				case "bas" :
					depasseFond = this.yFin + y * distanceDeplacement > t.getTailleY() ;
					if (!depasseFond)
						rentreDansUnObstacle = nePeutPasRentrerDansCetteCase(x, y, distanceDeplacement, ligneMapFin, colMapDeb, ligneMapFin, colMapFin, t, m) ;
				break ;
				
				case "gauche" :
					depasseMurGauche = this.xDeb + x * distanceDeplacement < 0 ;
					if (!depasseMurGauche)
						rentreDansUnObstacle = nePeutPasRentrerDansCetteCase(x, y, distanceDeplacement, ligneMapDeb, colMapDeb, ligneMapFin, colMapDeb, t, m) ;
				break ;
			
			}
		
		return rentreDansUnObstacle ;
		
	}
	
	// Vérifie que laou les case(s) d'à côté n'est ou ne sont pas un/des obstacle(s) au collisionneur
	public boolean nePeutPasRentrerDansCetteCase (int x, int y, double distanceDeplacement, int ligne1, int col1, int ligne2, int col2, Terrain t, Moteur m) {
		
		return m.estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligne1).get(col1).getNom())
				|| m.estUnObstacle(m.getObstacles(), t.getListeLignes().get(ligne2).get(col2).getNom()) ;
		
	}

	
	public double getXDeb() {
		return this.xDeb ;
	}
	public double getYDeb() {
		return this.yDeb ;
	}
	public double getXFin() {
		return this.xFin ; 
	}
	public double getYFin() {
		return this.yFin ;
	}
	
	/*public DoubleProperty getXDebProperty() {
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
	}*/
	
	public void setXDeb(double x) {
		this.xDeb = x;
	}
	public void setYDeb(double y) {
		this.yDeb = y ;
	}
	public void setXFin(double x) {
		this.xFin = x ;
	}
	public void setYFin(double y) {
		this.yFin = y ;
	}
	
	public String toString () {
		
		return "xDeb : " + this.xDeb + ", yDeb : " + this.yDeb + "\nxFin : " + this.xFin + ", yFin : " + this.yFin ;
		
	}

}
