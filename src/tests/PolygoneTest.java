package tests;
import geometrie.* ;
import javafx.geometry.Point2D;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolygoneTest {

	@Test
	void testCopie () {

		Polygone poly = new Polygone () ;
		Polygone poly2 = new Polygone () ;
		
		for (int i = 0 ; i < 10 ; i++)
			
			poly.ajouterSommet(new Point(i, 0));
		
		poly2.copie(poly) ;
		
		assertTrue (poly.estEgalA(poly2)) ;
		
	}
	
	@Test
	void testAjouterAChaquePoint () {
		
		Polygone poly ;
		Polygone poly2 ;
		Polygone poly3 ;
		Vecteur vecteur ;
		
		poly = new Polygone () ;
		poly2 = new Polygone () ;
		poly3 = new Polygone () ;
		vecteur = new Vecteur (1, 1) ;
		
		for (int i = 0 ; i < 10 ; i ++)
			
			poly.ajouterSommet(new Point(i, 0)) ;
		
		poly2.copie(poly) ;
		poly2.ajouterAChaquePoint(vecteur) ;
		
		for (int j = 0 ; j < 10 ; j ++)
			
			poly3.ajouterSommet(new Point (j + 1, 1)) ;
		
		assertTrue (poly2.estEgalA(poly3)) ;
		
	}
	
	@Test
	void testContient () {
		
		Polygone poly ;
		Point point ;
		
		poly = new Polygone () ;
		
		point = new Point (2, 3) ;
		
		for (int i = 0 ; i < 10 ; i ++) {
			
			poly.ajouterSommet(new Point (i, i));
			
		}
		
		assertFalse (poly.contient(point)) ;
		
	}

}
