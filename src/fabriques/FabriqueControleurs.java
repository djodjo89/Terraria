package fabriques;

import controleur.* ;
import javafx.scene.layout.Pane ;
import modele.Jeu ;
import vue.Tuile ;

public class FabriqueControleurs {
	
	public static void initialiserControleurs (Jeu jeu, Pane panePrincipal, Pane paneMap, Tuile perso, ControleurMap controleurMap, ControleurSouris controleurSouris, ControleurTouches controleurTouches) {
		
		controleurMap = new ControleurMap(paneMap,jeu);
		controleurMap.ajouterEcouteur () ;
		controleurTouches = new ControleurTouches(panePrincipal, jeu, perso) ;
		controleurTouches.gererControleur();
		
	}

}
