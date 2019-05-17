package physique;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Polygone {
	
	ArrayList<Point2D> listeSommets ;
	
	public Polygone () {
		
		this.listeSommets = new ArrayList<Point2D> () ;
		
	}
	
	public Point2D get (int i) {
		
		return this.listeSommets.get(i) ;
		
	}
	
	public void ajouterSommet (double x, double y) {
		
		if (nbSommets () != 0 && !this.contient(x, y))
			
			this.listeSommets.add(new Point2D(x, y)) ;
		
	}
	
	public boolean contient (double x, double y) {
		
		int i ;
		boolean trouve ;
		
		i = 0 ;
		trouve = false ;
		
		while (!trouve && i < this.nbSommets ()) {
			
			if (this.listeSommets.get(i).getX() == x && this.listeSommets.get(i).getY() == y)
				
				trouve = true ;
			
			i ++ ;
			
		}
		
		return trouve ;
		
	}
	
	public int nbSommets () {
		
		return this.listeSommets.size() ;
		
	}

}
