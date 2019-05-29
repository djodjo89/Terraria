package geometrie;

public class Segment {
	
	private Point p0 ;
	private Point p1 ;
	
	public Segment (Point p0, Point p1) {
		
		this.p0 = p0 ;
		this.p1 = p1 ;
		
	}
	
	public double coteDuSegment (Point p) {

		return (this.getPoint2().getX() - this.getPoint1().getX())*(p.getY() - this.getPoint1().getY())
			 -(this.getPoint2().getY() - this.getPoint1().getY())*(p.getX() - this.getPoint1().getX()) ;
		
	}
	
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
	
	public boolean seSuperposeA (Segment ligne) {
		
		return ((this.getPoint1().getX() <= ligne.getPoint1().getX() && ligne.getPoint1().getX() <= this.getPoint2().getX() &&
			this.getPoint1().getY() <= ligne.getPoint1().getY() && this.getPoint2().getY() >= ligne.getPoint1().getY()) ||
			(this.getPoint1().getX() >= ligne.getPoint1().getX() && this.getPoint2().getX() <= ligne.getPoint1().getX() &&
			this.getPoint1().getY() >= ligne.getPoint1().getY() && this.getPoint2().getY() <= ligne.getPoint1().getY())) ;
		
	}
	
	public String toString () {
		
		return this.p0 + "->" + this.p1 ;
		
	}
	
	public Point getPoint1 () {
		
		return this.p0 ;
		
	}

	public Point getPoint2 () {
		
		return this.p1 ;
		
	}
	
}
