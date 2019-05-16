package physique;

import modele.* ;
import exceptions.* ;

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

	public boolean deplacementPossible (String direction, Terrain t, GameObject go, Moteur m) throws VousEtesCoinceException {

		boolean deplacementOK ;

		deplacementOK = false ;

		switch (direction) {

		case "haut" : deplacementOK = !tombeSurUnObstacle(direction,0, -1, t, m, go) ; break ;
		case "droite" : deplacementOK = !tombeSurUnObstacle(direction,1, 0, t, m, go) ; break ;
		case "bas" : deplacementOK = !tombeSurUnObstacle(direction,0, 1, t, m, go) ; break ;
		case "gauche" : deplacementOK = !tombeSurUnObstacle(direction,-1, 0, t, m, go) ; break ;

		}

		return deplacementOK ;

	}

	private boolean tombeSurUnObstacle (String direction, int x, int y, Terrain t, Moteur m, GameObject go) throws VousEtesCoinceException {

		boolean depassePlafond = this.yDeb + y * go.getDistanceDeplacement() < 0 ;
		boolean depasseMurDroite = this.xFin + x * go.getDistanceDeplacement() > t.getTailleX() ;
		boolean depasseFond = this.yFin + y * m.getGravite() * go.getDistanceDeplacement() > t.getTailleY() ;
		boolean depasseMurGauche = this.xDeb + x * go.getDistanceDeplacement() < 0 ;

		boolean rentreDansUnObstacle=true;
		boolean peutAvancer = !(depassePlafond || depasseMurDroite || depasseFond || depasseMurGauche) ;

		if (peutAvancer) {

			if (!this.chevaucheUnObstacle(t, m)) {

				switch (direction) {

				case "haut" :

					if (peutAvancer) rentreDansUnObstacle = laCaseDeCeCoteEstUnObstacle (this.getCoorYDebSuiv(y, m, go), this.getCoorXDebActuel(m), this.getCoorYDebSuiv(y, m, go), this.getCoorXFinActuel(m), t, m, go) ;

					break ;

				case "droite" :

					if (peutAvancer) rentreDansUnObstacle = laCaseDeCeCoteEstUnObstacle (this.getCoorYDebActuel(m), this.getCoorXFinSuiv(x, m, go), this.getCoorYFinActuel(m), this.getCoorXFinSuiv(x, m, go), t, m, go) ;

					break ;

				case "bas" :

					if (peutAvancer) 
						
						rentreDansUnObstacle = laCaseDeCeCoteEstUnObstacle (this.getCoorYFinSuiv(y, m, go), this.getCoorXDebActuel(m), this.getCoorYFinSuiv(y, m, go), this.getCoorXFinActuel(m), t, m, go) ;
					break ;

				case "gauche" :

					if (peutAvancer) rentreDansUnObstacle = laCaseDeCeCoteEstUnObstacle (this.getCoorYDebActuel(m), this.getCoorXDebSuiv(x, m, go), this.getCoorYFinActuel(m), this.getCoorXDebSuiv(x, m, go), t, m, go) ;

					break ;

				}

			}

			else {

				throw new VousEtesCoinceException (this, t, m) ;

			}

		}
		return rentreDansUnObstacle ;

	}

	// J'admets que c'est un peu lourd, mais c'est toujours mieux que de tout dupliquer dans tomberSurUnObstacle
	private boolean laCaseDeCeCoteEstUnObstacle (int coor1, int coor2, int coor3, int coor4, Terrain t, Moteur m, GameObject go) {
		
		return t.getListeLignes().get(coor1).get(coor2).estUnObstacle()
				|| t.getListeLignes().get(coor3).get(coor4).estUnObstacle() ;

	}

	private boolean chevaucheUnObstacle (Terrain t, Moteur m) {

		return t.getListeLignes().get(this.getCoorYDebActuel(m)).get(this.getCoorXDebActuel(m)).estUnObstacle()
				|| t.getListeLignes().get(this.getCoorYDebActuel(m)).get(this.getCoorXFinActuel(m)).estUnObstacle()
				|| t.getListeLignes().get(this.getCoorYFinActuel(m)).get(this.getCoorXDebActuel(m)).estUnObstacle()
				|| t.getListeLignes().get(this.getCoorYFinActuel(m)).get(this.getCoorXFinActuel(m)).estUnObstacle() ;

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

	private int getCoorXDebSuiv (int x, Moteur m, GameObject go) {

		return (int) ((this.getXDeb() + x * go.getDistanceDeplacement()) / m.getTailleTileX()) ;

	}
	
	private int getCoorYDebSuiv (int y, Moteur m, GameObject go) {

		return (int) ((this.getYDeb() + y * go.getDistanceDeplacement())  / m.getTailleTileY()) ;

	}
	
	private int getCoorXFinSuiv (int x, Moteur m, GameObject go) {

		return (int) ((this.getXFin() + x * go.getDistanceDeplacement())  / m.getTailleTileX()) ;

	}
	
	private int getCoorYFinSuiv (int y, Moteur m, GameObject go) {

		
		return (int) ((this.getYFin() + y * go.getDistanceDeplacement())  / m.getTailleTileY()) ;

	}
	
	public int getCoorXDebActuel (Moteur m) {

		return (int) ((this.getXDeb()) / m.getTailleTileX()) ;

	}
	
	public int getCoorYDebActuel (Moteur m) {

		return (int) ((this.getYDeb()) / m.getTailleTileY()) ;

	}
	
	public int getCoorXFinActuel (Moteur m) {

		return (int) ((this.getXFin()) / m.getTailleTileX()) ;

	}
	
	public int getCoorYFinActuel (Moteur m) {

		return (int) ((this.getYFin()) / m.getTailleTileY()) ;

	}

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
