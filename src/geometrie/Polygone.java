package geometrie;

import java.util.ArrayList;

public class Polygone {
	
	ArrayList<Point> listeSommets ;
	
	public Polygone () {
		
		this.listeSommets = new ArrayList<Point> () ;

	}
	
	public Polygone (int nbSommets) {
		
		
		
	}
	
	public Polygone (Point...points) {
		
		this() ;
		
		for (Point point : points) {
			
			this.ajouterSommet(point) ;
			
		}
		
	}
	
	public void copie (Polygone polygone) {
		
		int i ;
		
		for (i = 0 ; i < polygone.nbSommets() ; i ++) {
			
			this.ajouterSommet(new Point (polygone.get(i).getX(), polygone.get(i).getY())) ;
			
		}
		
	}
	
	public Point get (int i) {
		
		return this.listeSommets.get(i) ;
		
	}
	
	public void ajouterAChaquePoint (Vecteur vecteur) {
		
		int i ;
		
		for (i = 0 ; i < this.nbSommets() ; i ++) {
			
			this.listeSommets.set(i, this.get(i).add(vecteur.getX(), vecteur.getY())) ;
			
		}
		
	}
	
	public void ajouterSommet (Point point) {
		
		if (!this.contient(point))
			
			this.listeSommets.add(point) ;
		
	}
	
	public boolean contient (Point point) {
		
		int i ;
		boolean trouve ;
		
		i = 0 ;
		trouve = false ;
		
		while (!trouve && i < this.nbSommets ()) {
			
			if (this.listeSommets.get(i).estEgalA(point))
				
				trouve = true ;
			
			i ++ ;
			
		}
		
		return trouve ;
		
	}
	
	public int nbSommets () {
		
		return this.listeSommets.size() ;
		
	}
	
	public boolean estInclusDans (double xMax, double yMax) {
		
		int i ;
		boolean depasseLesBornes ;
		
		i = 0 ;
		depasseLesBornes = false ;
		
		while (!depasseLesBornes && i < this.nbSommets()) {
			
			if (this.get(i).getX() < 0 || this.get(i).getY() < 0 || this.get(i).getX() >= xMax || this.get(i).getY() >= yMax) {
				
				depasseLesBornes = true ;
				
			}
			
			i ++ ;
			
		}
		
		return !depasseLesBornes ;
		
	}
	
	public boolean estEgalA (Polygone poly2) {
		
		int i ;
		boolean estEgal ;
		
		i = 0 ;
		estEgal = true ;
		
		if (this.nbSommets() == poly2.nbSommets()) 
		
			while (estEgal && i < this.nbSommets() && i < poly2.nbSommets()) {
				
				if (!this.get(i).estEgalA(poly2.get(i)))
					
					estEgal = false ;
				
				i ++ ;
				
			}
		
		else
			
			estEgal = false ;
		
		return estEgal ;
		
	}
	
	/*public ArrayList<ArrayList<Point2D>> listeOrdonneeParY2 () {
		
		ArrayList<ArrayList<Point2D>> nvListe ;
		
		nvListe = new ArrayList<> () ;
		
		
		
	}
	
	public ArrayList<Point2D> listeOrdonneeParY () {
		
		ArrayList<Point2D> nvListe ;
		
		nvListe = new ArrayList<> () ;
		
		for (Point2D)
		
	}*/
	
	public String toString () {
		
		int i ;
		String aff ;
		
		aff = "" ;
		
		for (i = 0 ; i < this.nbSommets() ; i ++) {
			
			aff += "Point n°" + i + " : {" + this.get(i).getX() + ":" + this.get(i).getY() + "} ;\n" ;
			
		}
		
		return aff ;
		
	}

}
