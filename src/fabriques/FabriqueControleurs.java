package fabriques;

import controleur.* ;
import javafx.scene.layout.Pane ;
import modele.Jeu ;
import modele.PersonnagePrincipal;
import ressources.Images;
import vue.CraftVue;
import vue.InventaireVue;
import vue.Tuile ;

public class FabriqueControleurs {
	
	public static ControleurMap initialiserControleursMap (Jeu jeu, Pane paneMap, Images image) {
		
		ControleurMap controleurMap = new ControleurMap(paneMap,jeu,image);
		controleurMap.ajouterEcouteur () ;
		return controleurMap;
		
	}
	
	public static ControleurTouches initialiserControleurTouches( Pane panePrincipal, Jeu jeu, Tuile perso, Pane paneMap, Pane paneInventaire, InventaireVue inv, CraftVue craftV) {
		
		ControleurTouches controleurTouches = new ControleurTouches(panePrincipal, jeu, perso, paneMap, paneInventaire, inv , craftV) ;
		controleurTouches.gererControleur();
		return controleurTouches;
		
	}
	
	public static ControleurSouris initialiserControleurSouris(Pane paneTerrain, Jeu jeu) {
		ControleurSouris controleurSouris = new ControleurSouris(paneTerrain,jeu);
		return controleurSouris;
	}
	
	public static ControleurInventaire initialiserControleurInventaire(Jeu j, Images img, InventaireVue inv) {
		ControleurInventaire controlInventaire = new ControleurInventaire(j, img, inv);
		controlInventaire.ajoutListenerInventaire();
		return controlInventaire;
	}

}
