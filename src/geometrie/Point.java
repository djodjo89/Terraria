package geometrie;

import javafx.geometry.Point2D;

/**
 * <h1>Un Point est un objet disposant de coordonnées x et y.</h1>
 * <p>On peut lui ajouter des x et des y, ou le baser sur un
 * autre point. On peut également le comparer à un autre
 * et l'afficher.</p>
 * 
 * @author Mathys
 * @version 1.0
 */

public class Point extends Point2D implements Comparable<Point> {
	
	/**
	 * Crée un Point aux coordonnées x en abscisse et y
	 * en ordonnée
	 * @param x
	 * @param y
	 * 
	 * @version 1.0
	 */
	
	public Point (double x, double y) {
		
		super (x, y) ;
		
	}
	
	public Point substract (double x, double y) {
		
		return new Point (this.getX() - x, this.getY() - y) ;
		
	}
	/**
	 * Retourne un nouveau Point auquel on a ajouté x et y
	 * 
	 * @param x
	 * @param y
	 * @return Point
	 * 
	 * @since 1.0
	 * @version 1.0
	 */
	
	public Point add (double x, double y) {
		
		return new Point (this.getX() + x, this.getY() + y) ;
		
	}
	
	/**
	 * Retourne un nouveau point égal à celui entré en paramètre
	 * @param point
	 * @return Point
	 * 
	 * @since 1.0
	 * @version 1.0
	 */
	
	public Point set (Point point) {
		
		return this.add(point.getX() - this.getX(), point.getY() - this.getY()) ;
		
	}
	
	/**
	 * Compare deux points entre eux. On regarde d'abord la ligne (y)
	 * sur laquelle ils se trouvent, puis on compare la colonne (x)
	 * Retourne 0 si les deux points sont égaux
	 * 
	 * @since 1.0
	 * @version 1.0
	 */

	@Override
	public int compareTo(Point point) {
		
		int diffY ;
		int diffTotale ;
		
		Point p2 = (Point)point ;
		
		diffY = (int)(this.getY() - p2.getY()) ;
		
		if (diffY == 0)
			
			diffTotale = (int)(this.getX() - p2.getX()) ;
		
		else
			
			diffTotale = diffY ;

		return diffTotale ;
		
	}
	
	/**
	 * Permet d'afficher le point
	 * 
	 * @since 1.0
	 * @version 1.0
	 */
	
	public String toString () {
		
		return "{" + this.getX() + ":" + this.getY() + "}" ;
		
	}

}
