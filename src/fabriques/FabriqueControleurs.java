package fabriques;

import controleur.* ;
import javafx.scene.layout.Pane ;
import modele.Jeu ;
import ressources.Images;
import vue.Tuile ;

public class FabriqueControleurs {
	
	public static ControleurMap initialiserControleursMap (Jeu jeu, Pane paneMap, Images image) {
		
		ControleurMap controleurMap = new ControleurMap(paneMap,jeu,image);
		controleurMap.ajouterEcouteur () ;
		return controleurMap;
		
	}
	
	public static ControleurTouches initialiserControleurTouches( Pane panePrincipal,Jeu jeu, Tuile perso,Pane paneMap,Pane paneInventaire, ControleurTerraria control) {
		
		ControleurTouches controleurTouches = new ControleurTouches(panePrincipal, jeu, perso,paneMap, paneInventaire,control) ;
		controleurTouches.gererControleur();
		return controleurTouches;
	}
	
	public static ControleurSouris initialiserControleurSouris(Pane paneTerrain,Jeu jeu) {
		ControleurSouris controleurSouris = new ControleurSouris(paneTerrain,jeu);
		return controleurSouris;
	}

}
