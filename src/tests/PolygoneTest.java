package tests;
import geometrie.* ;
<<<<<<< HEAD

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolygoneTest {

	@Test
	void testCopie () {

		Polygone poly ;
		Polygone poly2 ;
		
		
		poly = new Polygone (10) ;
		poly2 = new Polygone () ;
		
		poly2.copie(poly) ;		
		assertTrue (poly.estEgalA(poly2)) ;
		
	}
	
	@Test
	void testAjouterAChaquePoint () {
		
		int i ;
		Polygone poly ;
		Polygone poly2 ;
		Polygone poly3 ;
		Vecteur vecteur ;
		
		poly = new Polygone (10) ;
		poly2 = new Polygone () ;
		poly3 = new Polygone () ;
		vecteur = new Vecteur (1, 1) ;
		
		poly2.copie(poly) ;
		poly2.ajouterAChaquePoint(vecteur) ;
		
		for (i = 0 ; i < 10 ; i ++)
			
			poly3.ajouterSommet(new Point (1, 1)) ;
		
		assertTrue (poly2.estEgalA(poly3)) ;
		
	}
	
	@Test
	void testContient () {
		
		Point point ;
		Polygone poly ;
		
		point = new Point (2, 3) ;
		poly = new Polygone (10) ;
		
		assertFalse (poly.contient(point)) ;
		
	}
	
	@Test
	void testEstInclusDans () {
		
		Polygone poly ;
		Polygone poly2 ;
		
		poly = new Polygone (4, 2, 2) ;
		poly2 = new Polygone () ;
		
		poly2.ajouterSommet(0, 0);
		poly2.ajouterSommet(3, 0);
		poly2.ajouterSommet(0, 3);
		poly2.ajouterSommet(3, 3);
		
		assertTrue (poly.estInclusDans(3, 3)) ;
		
	}
	
	@Test
	void testIntersection () {
		
		Polygone poly1, poly2, poly3 ;
		
		poly1 = new Polygone() ;
		poly2 = new Polygone() ;
		poly3 = new Polygone() ;
		
		
		poly1.ajouterSommet(1, 1) ;
		poly1.ajouterSommet(3, 1) ;
		poly1.ajouterSommet(3, 2) ;
		poly1.ajouterSommet(1,2) ;
		/*
		poly2.ajouterSommet(2, 1) ;
		poly2.ajouterSommet(4, 1) ;
		poly2.ajouterSommet(2, 2) ;
		poly2.ajouterSommet(4, 2) ;
		*/
		System.out.print(poly1);
		poly2.ajouterSommet(4, 1) ;
		poly2.ajouterSommet(6, 1) ;
		poly2.ajouterSommet(6, 2) ;
		poly2.ajouterSommet(4, 2) ;
		System.out.print(poly2);
		assertEquals(null, poly1.intersection(poly2)) ;
		
		poly3.ajouterSommet(2, 1) ;
		poly3.ajouterSommet(4, 1) ;
		poly3.ajouterSommet(2, 2) ;
		poly3.ajouterSommet(4, 2) ;
		
		System.out.print(poly1);
		System.out.print(poly3);
		assertEquals(new Point(2, 1), poly1.intersection(poly3)) ;

		poly1 = new Polygone () ;
		
		poly1.ajouterSommet(0,0) ;
		poly1.ajouterSommet(6,0) ;
		poly1.ajouterSommet(0,6) ;
		poly1.ajouterSommet(6,6) ;
		System.out.println(poly1) ;
		
		poly2 = new Polygone () ;
		
		poly2.ajouterSommet(2, 3) ;
		poly2.ajouterSommet(6, 6) ;
		poly2.ajouterSommet(8, 0) ;
		
		System.out.print(poly2) ;
		
		assertEquals(new Point(3, 2), poly1.intersection(poly2)) ;
		
	}
	
	@Test
	void testToString () {
		
		/*ArrayList<ArrayList<Point>> liste ;
		Polygone poly ;
		
		poly = new Polygone () ;
		
		poly.ajouterSommet(0, -1);		
		poly.ajouterSommet(-5, 1);
		poly.ajouterSommet(5, 1);
		poly.ajouterSommet(-2, 4);
		poly.ajouterSommet(2, 4);
		poly.ajouterSommet(0, 10);
		poly.ajouterSommet(0, 4);
		
		liste = new ArrayList<>() ;
		liste = poly.tableauOrdonne() ;
		
		System.out.print(poly);
		
		poly.ajouterAChaquePoint(new Vecteur (2, -1));
		
		System.out.print(poly);*/
=======
import javafx.geometry.Point2D;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PolygoneTest {

	@Test
	void testCopie () {

		int i ;
		Polygone poly ;
		Polygone poly2 ;
		
		poly = new Polygone (10) ;
		poly2 = new Polygone () ;
		
		poly2.copie(poly) ;		
		assertTrue (poly.estEgalA(poly2)) ;
		
	}
	
	@Test
	void testAjouterAChaquePoint () {
		
		int i ;
		int j ;
		Polygone poly ;
		Polygone poly2 ;
		Polygone poly3 ;
		Vecteur vecteur ;
		
		poly = new Polygone (10) ;
		poly2 = new Polygone () ;
		poly3 = new Polygone () ;
		vecteur = new Vecteur (1, 1) ;
		
		poly2.copie(poly) ;
		poly2.ajouterAChaquePoint(vecteur) ;
		
		for (j = 0 ; j < 10 ; j ++)
			
			poly3.ajouterSommet(new Point (1, 1)) ;
		
		assertTrue (poly2.estEgalA(poly3)) ;
		
	}
	
	@Test
	void testContient () {
		
		int i ;
		Point point ;
		Polygone poly ;
		
		point = new Point (2, 3) ;
		poly = new Polygone (10) ;
		
		assertFalse (poly.contient(point)) ;
		
	}
	
	@Test
	void testEstInclusDans () {
		
		int i ;
		int j ;
		Polygone poly ;
		Polygone poly2 ;
		
		poly = new Polygone (4, 2, 2) ;
		poly2 = new Polygone () ;
		
		poly2.ajouterSommet(0, 0);
		poly2.ajouterSommet(3, 0);
		poly2.ajouterSommet(0, 3);
		poly2.ajouterSommet(3, 3);
		
		assertTrue (poly.estInclusDans(3, 3)) ;
		
	}
	
	@Test
	void testToString () {
		
		ArrayList<ArrayList<Point>> liste ;
		Polygone poly ;
		
		poly = new Polygone () ;
		
		poly.ajouterSommet(0, -1);		
		poly.ajouterSommet(-5, 1);
		poly.ajouterSommet(5, 1);
		poly.ajouterSommet(-2, 4);
		poly.ajouterSommet(2, 4);
		poly.ajouterSommet(0, 10);
		poly.ajouterSommet(0, 4);
		
		liste = new ArrayList<>() ;
		liste = poly.tableauOrdonne() ;
		
		System.out.print(poly);
		
		poly.ajouterAChaquePoint(new Vecteur (2, -1));
		
		System.out.print(poly);
>>>>>>> refs/heads/master
		
	}

}
