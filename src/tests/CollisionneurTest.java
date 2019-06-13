package tests;
import geometrie.* ;
import javafx.scene.image.Image;
import modele.*;
import objetRessources.*;
import physique.* ;
import ressources.Images;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import application.NomClasse;
import exceptions.HorsDeLaMapException;

class CollisionneurTest {

	@Test
	void testDepasseLesLimitesDeLaMap () {
		
		Images images ;
		Jeu jeu ;
       	
		try {
			images = new Images () ;
			images.ajouterImage(NomClasse.retrouver(new Terre()), "png");
	    	images.ajouterImage(NomClasse.retrouver(new Air()), "png");
			jeu = new Jeu("map.csv", images.getImage("air").getWidth(), images.getImage("air").getHeight(), 10., 10.) ;
			Collisionneur colli ;
			
			Point p1, p2, p3, p4 ;
			
			p1 = new Point (10, 10) ;
			p2 = new Point (20, 10) ;
			p3 = new Point (10, 20) ;
			p4 = new Point (20, 20) ;
			
			//colli = new Collisionneur (p1, p2, p3, p4) ;
			
			//assertFalse (colli.depasseLesLimitesDeLaMap(jeu.getMap())) ;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
