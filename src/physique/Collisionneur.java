package physique;

import modele.* ;

import java.util.ArrayList;
import java.util.Vector;

import exceptions.* ;
import geometrie.Vecteur;
import javafx.geometry.Point2D;

/**
 * Un Collisionneur est une "boîte" de taille quelconque capable
 * de vérifier qu'il n'entre pas en collision avec un autre.
 */

/*
 * Ma classe prÃ©fÃ©rÃ©e :) J'ai passÃ© un week-end dessus
 * Collisionneur est une "boÃ®te" dÃ©limitÃ©e par quatre
 * cÃ´tÃ©s : xDeb pour le gauche, yDeb pour le haut, 
 * xFin pour le droit et yFin pour celui du bas
 * Il peut dire si un dÃ©placement est possible Ã  partir
 * d'une direction de dÃ©placement, d'un terrain et d'un
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
	
	/**
	 * Vérifie si le déplacement est possible dans la direction du vecteur vitesse
	 * du Collisionneur en fonction de sa position par rapport aux Collisionneurs
	 * du terrain. Le moteur sert à appliquer les forces physiques. Il y a trois 
	 * résultats possibles :
	 * 
	 * - La distance au "prochain Collisionneur touché" est nulle, dans ce cas
	 * la méthode retourne 0
	 * 
	 * - La distance est inférieure à la norme du vecteur, dans ce cas la distance
	 * est retournée
	 * 
	 * - La distance est supérieure à la norme du vecteur, dans ce cas la norme du
	 * vecteur est retournée
	 * 
	 * Le vecteur de vitesse permet d'anticiper la position du Collisionneur à la
	 * fin du déplacement
	 * @param vecteurVitesse
	 * 
	 * Le terrain donne la position des obstacles proches
	 * @param terrain
	 * 
	 * Le moteur applique les forces physiques au déplacement
	 * @param moteur
	 * 
	 * @return La distance au Collisionneur le plus proche de la position où on
	 * se trouvera après le déplacement.
	 * 
	 * @throws VousEtesCoinceException Si le Collisionneur est coincé dans un autre.
	 * 
	 * @author Mathys
	 * 
	 * @version 2.0
	 * 
	 * @since 1.0
	 */
	
	// Renvoie la distance dont peut se déplacer le perso dans la direction donnée

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
