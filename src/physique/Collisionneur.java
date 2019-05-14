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

	public boolean deplacementPossible (String direction, Terrain t, GameObject p, Moteur m) throws VousEtesCoinceException {

		boolean deplacementOK ;

		deplacementOK = false ;

		switch (direction) {

		case "haut" : deplacementOK = !tombeSurUnObstacle(direction,0, -1, t, m) ; break ;
		case "droite" : deplacementOK = !tombeSurUnObstacle(direction,1, 0, t, m) ; break ;
		case "bas" : deplacementOK = !tombeSurUnObstacle(direction,0, 1, t, m) ; break ;
		case "gauche" : deplacementOK = !tombeSurUnObstacle(direction,-1, 0, t, m) ; break ;

		}

		return deplacementOK ;

	}

	private boolean tombeSurUnObstacle (String direction, int x, int y, Terrain t, Moteur m) throws VousEtesCoinceException {

		boolean depasseMurGauche, depassePlafond, depasseFond, depasseMurDroite, rentreDansUnObstacle, peutAvancer ;

		rentreDansUnObstacle = true ;

		depassePlafond = this.yDeb + y * m.getDistanceDeplacement() < 0 ;
		depasseMurDroite = this.xFin + x * m.getDistanceDeplacement() > t.getTailleX() ;
		depasseFond = this.yFin + y * m.getGravite()*m.getDistanceDeplacement() > t.getTailleY() ;
		depasseMurGauche = this.xDeb + x * m.getDistanceDeplacement() < 0 ;

		peutAvancer = !(depassePlafond || depasseMurDroite || depasseFond || depasseMurGauche) ;

		if (peutAvancer) {

			if (!this.chevaucheUnObstacle(t, m)) {

				switch (direction) {

				case "haut" :

					if (peutAvancer) rentreDansUnObstacle = laCaseDeCeCoteEstUnObstacle (this.getCoorYDebSuiv(y, m), this.getCoorXDebActuel(m), this.getCoorYDebSuiv(y, m), this.getCoorXFinActuel(m), t, m) ;

					break ;

				case "droite" :

					if (peutAvancer) rentreDansUnObstacle = laCaseDeCeCoteEstUnObstacle (this.getCoorYDebActuel(m), this.getCoorXFinSuiv(x, m), this.getCoorYFinActuel(m), this.getCoorXFinSuiv(x, m), t, m) ;

					break ;

				case "bas" :

					if (peutAvancer) 
						
						rentreDansUnObstacle = laCaseDeCeCoteEstUnObstacle (this.getCoorYFinSuiv(y, m), this.getCoorXDebActuel(m), this.getCoorYFinSuiv(y, m), this.getCoorXFinActuel(m), t, m) ;

					break ;

				case "gauche" :

					if (peutAvancer) rentreDansUnObstacle = laCaseDeCeCoteEstUnObstacle (this.getCoorYDebActuel(m), this.getCoorXDebSuiv(x, m), this.getCoorYFinActuel(m), this.getCoorXDebSuiv(x, m), t, m) ;

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
	private boolean laCaseDeCeCoteEstUnObstacle (int coor1, int coor2, int coor3, int coor4, Terrain t, Moteur m) {

		return m.estUnObstacle(m.getObstacles(), t.getListeLignes().get(coor1).get(coor2).getTag())
				|| m.estUnObstacle(m.getObstacles(), t.getListeLignes().get(coor3).get(coor4).getTag()) ;

	}

	private boolean chevaucheUnObstacle (Terrain t, Moteur m) {

		return m.estUnObstacle(m.getObstacles(), t.getListeLignes().get(this.getCoorYDebActuel(m)).get(this.getCoorXDebActuel(m)).getTag())
				|| m.estUnObstacle(m.getObstacles(), t.getListeLignes().get(this.getCoorYDebActuel(m)).get(this.getCoorXFinActuel(m)).getTag())
				|| m.estUnObstacle(m.getObstacles(), t.getListeLignes().get(this.getCoorYFinActuel(m)).get(this.getCoorXDebActuel(m)).getTag())
				|| m.estUnObstacle(m.getObstacles(), t.getListeLignes().get(this.getCoorYFinActuel(m)).get(this.getCoorXFinActuel(m)).getTag()) ;

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

	private int getCoorXDebSuiv (int x, Moteur m) {

		return (int) ((this.getXDeb() + x * m.getDistanceDeplacement()) / m.getTailleTileX()) ;

	}
	
	private int getCoorYDebSuiv (int y, Moteur m) {

		return (int) ((this.getYDeb() + y * m.getDistanceDeplacement())  / m.getTailleTileY()) ;

	}
	
	private int getCoorXFinSuiv (int x, Moteur m) {

		return (int) ((this.getXFin() + x * m.getDistanceDeplacement())  / m.getTailleTileX()) ;

	}
	
	private int getCoorYFinSuiv (int y, Moteur m) {

		
		return (int) ((this.getYFin() + y * m.getDistanceDeplacement())  / m.getTailleTileY()) ;

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
