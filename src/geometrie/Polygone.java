package geometrie;

import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Polygone {
	
	ArrayList<Point2D> listeSommets ;
	
	public Polygone () {
		
		this.listeSommets = new ArrayList<Point2D> () ;
		
	}
	
	public Polygone (Point2D...points) {
		
		this() ;
		for (Point2D point : points) {
			
			this.ajouterSommet(point) ;
			
		}
		
	}
	
	public void copie (Polygone polygone) {
		
		int i ;
		
		for (i = 0 ; i < polygone.nbSommets() ; i ++) {
			
			this.ajouterSommet(new Point2D (polygone.get(i).getX(), polygone.get(i).getY())) ;
			
		}
		
	}
	
	public Point2D get (int i) {
		
		return this.listeSommets.get(i) ;
		
	}
	
	public void ajouterAChaquePoint (Vecteur vecteur) {
		
		for (Point2D point : this.listeSommets) {
			
			point.add(vecteur.getX(), vecteur.getY()) ;
			
		}
		
	}
	
	public void ajouterSommet (Point2D point) {
		
		if (nbSommets () != 0 && !this.contient(point))
			
			this.listeSommets.add(point) ;
		
	}
	
	public boolean contient (Point2D point) {
		
		int i ;
		boolean trouve ;
		
		i = 0 ;
		trouve = false ;
		
		while (!trouve && i < this.nbSommets ()) {
			
			if (this.listeSommets.get(i).getX() == point.getX() && this.listeSommets.get(i).getY() == point.getY())
				
				trouve = true ;
			
			i ++ ;
			
		}
		
		return trouve ;
		
	}
	
	public int nbSommets () {
		
		return this.listeSommets.size() ;
		
	}
	
	/**
	 * @todo Mettre un Polygone en paramètre
	 * @param polygone
	 * @return
	 */
	
	public boolean estInclusDans (double xMax, double yMax) {
		
		int i ;
		boolean depasseLesBornes ;
		
		i = 0 ;
		depasseLesBornes = false ;
		
		while (!depasseLesBornes && i < this.nbSommets()) {
			
			if (this.listeSommets.get(i).getX() < 0 || this.listeSommets.get(i).getY() < 0 || this.listeSommets.get(i).getX() >= xMax || this.listeSommets.get(i).getY() >= yMax) {
				
				depasseLesBornes = true ;
				
			}
			
			i ++ ;
			
		}
		
		return !depasseLesBornes ;
		
	}

}
