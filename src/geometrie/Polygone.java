package geometrie;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Polygone {
	
	ArrayList<Point2D> listeSommets ;
	
	public Polygone () {
		
		this.listeSommets = new ArrayList<Point2D> () ;

	}
	
	
	public Point2D get (int index) {
		
		return this.listeSommets.get(index) ;
		
	}
	
	public void ajouterSommet (double x, double y) {
		
		if (dernierPoint() != null && !contient(new Point2D(x, y)))
			
			this.listeSommets.add(new Point2D(x, y)) ;
		
	}
	
	public boolean contient (Point2D p) {
		
		int i ;
		boolean trouve ;
		
		i = 0 ;
		trouve = false ;
		
		while (!trouve && i < this.listeSommets.size()) {
			
			if (this.listeSommets.get(i).distance(p) == 0)
				
				trouve = true ;
			
		}
		
		return trouve ;
		
	}
	
	public int nbPoints () {
		
		return this.listeSommets.size() ;
		
	}
	
	public Point2D dernierPoint () {
		
		Point2D dernierPoint ;
		
		if (this.listeSommets.size() != 0) {
			
			dernierPoint = this.listeSommets.get(this.listeSommets.size() - 1) ;
			
		}
		
		else {
			
			dernierPoint = null ;
			
		}
		
		return dernierPoint ;
		
	}

}
