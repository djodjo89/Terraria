package fabriques;

import geometrie.Point;

import modele.Jeu;
import objetRessources.Air;
import objetRessources.Granite;
import objetRessources.Terre;
import physique.Collisionneur;
import physique.GameObject;

public class FabriqueGameObject {
	
	public static GameObject creerGameObjectDemander(String tag,Collisionneur collision) {
		
		GameObject objet = null;
		
		switch (tag) {
		
		case "T" : objet = new Terre("terre") ; break ;
		
		case "A" : objet = new Air("air") ; break ;
		
		case "G": objet = new Granite("granite"); break;
		
		
		}
		
		objet.setCollisionneur(collision) ;
		
		
		return objet;

	}



}
