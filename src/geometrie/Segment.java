package geometrie;

public class Segment {
	
	private Point p0 ;
	private Point p1 ;
	
	public Segment (Point p0, Point p1) {
		
		this.p0 = p0 ;
		this.p1 = p1 ;
		
	}
	
	public Point intersection (Segment ligne) {
		
		double a1, a2, b1, b2, c1, c2 ;
		double denominateur ;
		double intersectX, intersectY ;
		double ratioIntersectY0, ratioIntersectX0 ;
		double ratioIntersectY1, ratioIntersectX1 ;
		Point coordonneesIntersection ;
				
		a1 = this.p1.getY() - this.p0.getY() ;
		b1 = this.p0.getX() - this.p1.getX() ;
		c1 = a1 * this.p0.getX() + b1 * this.p0.getY() ;
		
		a2 = ligne.getPoint2().getY() - ligne.getPoint1().getY() ;
		b2 = getPoint1().getX() - ligne.getPoint2().getX() ;
		c2 = a2 * getPoint1().getX() + b2 * getPoint1().getY() ;
		
		denominateur = a1 * b2 - a2 * b1 ;
		
		intersectX = (b2 * c1 - b1 * c2) / denominateur ;
		intersectY = (a1 * c2 - a2 * c1) / denominateur ;
		ratioIntersectX0 = (intersectX - this.p0.getX()) / (this.p1.getX() - this.p0.getX()) ;
		ratioIntersectY0 = (intersectY - this.p0.getY()) / (this.p1.getY() - this.p0.getY()) ;
		ratioIntersectX1 = (intersectX - ligne.getPoint1().getX()) / (ligne.getPoint2().getX() - ligne.getPoint1().getX()) ;
		ratioIntersectY1 = (intersectY - ligne.getPoint1().getY()) / (ligne.getPoint2().getY() - ligne.getPoint1().getY()) ;
		
		if ((ratioIntersectX0 >= 0 && ratioIntersectX0 <= 1) || (ratioIntersectY0 >= 0 && ratioIntersectY0 <= 1)
		&& (ratioIntersectX1 >= 0 && ratioIntersectX1 <= 1) || (ratioIntersectY1 >= 0 && ratioIntersectY1 <= 1)) {
			
			coordonneesIntersection = new Point (intersectX, intersectY) ;
			
		}
		
		else {
			
			coordonneesIntersection = null ;
			
		}
		
		return coordonneesIntersection ;
		
	}
	
	public Point getPoint1 () {
		
		return this.p0 ;
		
	}

	public Point getPoint2 () {
		
		return this.p1 ;
		
	}
	
}
