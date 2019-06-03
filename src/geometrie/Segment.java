package geometrie;

/**
 * <h1>Un Segment est un ensemble de deux Point</h1>
 * <p>Un Segment peut :</p>
 * <ul>
 * 		<li>Dire si de quel côté du segment se 
 * 		situe un Point</li>
 * 		<li>Donner le Point d'intersection entre
 * 		deux Segment</li>
 * 		<li>Dire si le Segment se superpose à un autre</li>
 * 		<li>Afficher les deux Point du Segment</li>
 * 		<li>Fournir ses deux Point séparément</li>
 * 
 * @author Mathys
 *
 */

public class Segment {
	
	private Point p0 ;
	private Point p1 ;
	
	/**
	 * Crée un Segment à partir des deux Point entrés
	 * en paramètres
	 * 
	 * @param p0
	 * @param p1
	 */
	
	public Segment (Point p0, Point p1) {
		
		this.p0 = p0 ;
		this.p1 = p1 ;
		
	}
	
	/**
	 * Retourne de façon relative de quel côté du segment se situe un
	 * Point (il faut comparer le résultat avec un autre)
	 * 
	 * @param p
	 * @return un double dont le signe varie selon le côté du Point
	 */
	
	public double coteDuSegment (Point p) {

		return (this.getPoint2().getX() - this.getPoint1().getX())*(p.getY() - this.getPoint1().getY())
			 -(this.getPoint2().getY() - this.getPoint1().getY())*(p.getX() - this.getPoint1().getX()) ;
		
	}
	
	/**
	 * Retourne le Point d'intersection entre deux Segments
	 * 
	 * @param ligne
	 * @return Le Point d'intersection
	 */
	
	public Point intersection (Segment ligne) {
		
		double intersectX, intersectY ;
		Point coordonneesIntersection ;
		
		intersectX=((this.p0.getX() + ligne.getPoint1().getX()) + (this.p1.getX() + ligne.getPoint2().getX())) / 4 ;
		intersectY=((this.p0.getY() + ligne.getPoint1().getY()) + (this.p1.getY() + ligne.getPoint2().getY())) / 4 ;
		
		if ((this.coteDuSegment(ligne.getPoint1()) == 0 || this.coteDuSegment(ligne.getPoint2()) == 0) && (this.seSuperposeA(ligne)) || ((this.coteDuSegment(ligne.getPoint1()) != 0 && this.coteDuSegment(ligne.getPoint2()) != 0)) && (this.coteDuSegment(ligne.getPoint1()) != this.coteDuSegment(ligne.getPoint2())))
			
			coordonneesIntersection = new Point ((int)intersectX, (int)intersectY) ;
		
		else
			
			coordonneesIntersection = null ;
		
		return coordonneesIntersection ;
		
	}
	
	/**
	 * Retourne vrai si les deux Segment se superposent
	 * 
	 * @param ligne
	 */
	
	public boolean seSuperposeA (Segment ligne) {
		
		return ((this.getPoint1().getX() <= ligne.getPoint1().getX() && ligne.getPoint1().getX() <= this.getPoint2().getX() &&
			this.getPoint1().getY() <= ligne.getPoint1().getY() && this.getPoint2().getY() >= ligne.getPoint1().getY()) ||
			(this.getPoint1().getX() >= ligne.getPoint1().getX() && this.getPoint2().getX() <= ligne.getPoint1().getX() &&
			this.getPoint1().getY() >= ligne.getPoint1().getY() && this.getPoint2().getY() <= ligne.getPoint1().getY())) ;
		
	}
	
	/**
	 * Affiche les deux points du Segment
	 */
	
	public String toString () {
		
		return this.p0 + "->" + this.p1 ;
		
	}
	
	/**
	 * Retourne l'un des Point du Segment
	 * 
	 * @return le premier Point rentré dans le constructeur
	 */
	
	public Point getPoint1 () {
		
		return this.p0 ;
		
	}
	
	/**
	 * Retourne l'autre Point
	 * 
	 * @return le second Point
	 */

	public Point getPoint2 () {
		
		return this.p1 ;
		
	}
	
}
