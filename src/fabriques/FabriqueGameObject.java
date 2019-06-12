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
		
		case "T" : objet = new Terre() ; break ;
		
		case "A" : objet = new Air() ; break ;
		
		case "G": objet = new Granite(); break;
		
		case "BBI": objet = new BlocBiomasse(); break;
		
		case "BB": objet = new BlocBois(); break;
		
		case "BD": objet = new BlocDechet(); break;
		
		case "BE": objet = new BlocElectromagnetique(); break;
		
		case "BM": objet = new BlocMetalique(); break;
		
		case "BP": objet = new BlocPlastique(); break;
		
		//case "B": caseMap = new Bois("bois"); break;
		
		
		case "P": objet = new Pierre(); break;
		
		default : objet = new Air() ; break ;
		
		
		}
		
		objet.setCollisionneur(collision) ;
		
		
		return objet;

	}



}
