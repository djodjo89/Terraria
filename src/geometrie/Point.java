package geometrie;

import javafx.geometry.Point2D;

public class Point extends Point2D {
	
	public Point (double x, double y) {
		
		super (x, y) ;
		
	}
	
	public Point add (double x, double y) {
		
		return new Point (this.getX() + x, this.getY() + y) ;
		
	}
	
	public Point set (Point point) {
		
		return this.add(point.getX() - this.getX(), point.getY() - this.getY()) ;
		
	}
	
	public boolean estEgalA (Point p2) {
		
		return this.getX() == p2.getX() && this.getY() == p2.getY() ;
		
	}

}
