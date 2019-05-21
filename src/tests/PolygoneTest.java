package tests;
import geometrie.* ;
import javafx.geometry.Point2D;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolygoneTest {

	@Test
	void testCopie () {

		Polygone poly = new Polygone () ;
		
		for (int i = 0 ; i < 10 ; i++) {
			
			poly.ajouterSommet(new Point2D(1, 0));
			
		}
		
	}

}
