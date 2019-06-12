package fabriques;

import geometrie.Point;
import objetRessources.Air;
import objetRessources.BlocBiomasse;
import objetRessources.BlocBois;
import objetRessources.BlocDechet;
import objetRessources.BlocElectromagnetique;
import objetRessources.BlocMetalique;
import objetRessources.BlocPlastique;
import objetRessources.Electronique;
import objetRessources.Granite;
import objetRessources.Metal;
import objetRessources.Pierre;
import objetRessources.Plastique;
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
		
		case "BBI": objet = new BlocBiomasse("blocBio"); break;
		
		case "BB": objet = new BlocBois("blocBois"); break;
		
		case "BD": objet = new BlocDechet("blocDechet"); break;
		
		case "BE": objet = new BlocElectromagnetique("blocElectro"); break;
		
		case "BM": objet = new BlocMetalique("blocMetal"); break;
		
		case "BP": objet = new BlocPlastique("blocPlastique"); break;
		
		//case "B": caseMap = new Bois("bois"); break;
		
		
		case "P": objet = new Pierre("pierre"); break;
		
		default : objet = new Air("air") ; break ;
		
		
		}
		
		objet.setCollisionneur(collision) ;
		
		
		return objet;

	}



}
