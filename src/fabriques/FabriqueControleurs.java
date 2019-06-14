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
	
	public static ControleurTouches initialiserControleurTouches( Pane panePrincipal, Jeu jeu, Tuile perso, Pane paneMap, Pane paneInventaire, Pane paneCraft, InventaireVue inv, CraftVue craftV) {
		
		ControleurTouches controleurTouches = new ControleurTouches(panePrincipal, jeu, perso, paneMap, paneInventaire, paneCraft, inv , craftV) ;
		controleurTouches.gererControleur();
		return controleurTouches;
		
	}
	
	public static ControleurSouris initialiserControleurSouris(Pane paneTerrain, Jeu jeu) {
		ControleurSouris controleurSouris = new ControleurSouris(paneTerrain,jeu);
		return controleurSouris;
	}
	
	public static ControleurInventaire initialiserControleurInventaire(Jeu j, Images img, InventaireVue inv) {
		ControleurInventaire controleurInventaire = new ControleurInventaire(j, img, inv);
		controleurInventaire.ajoutListenerInventaire();
		return controleurInventaire;
	}
	
	public static ControleurCraft initialiserControleurCraft (Jeu j, Images images, CraftVue craftVue) {
		
		ControleurCraft controleurCraft = new ControleurCraft(j, images, craftVue) ;
		controleurCraft.ajouterListenerCraft() ;
		return controleurCraft ;
		
	}

}
