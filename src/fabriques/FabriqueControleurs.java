package fabriques;

import controleur.* ;
import javafx.scene.layout.Pane ;
import modele.Jeu ;
import vue.Tuile ;

public class FabriqueControleurs {
	
	public static ControleurMap initialiserControleursMap (Jeu jeu, Pane paneMap) {
		
		ControleurMap controleurMap = new ControleurMap(paneMap,jeu);
		controleurMap.ajouterEcouteur () ;
		return controleurMap;
		
	}
	
	public static ControleurTouches initialiserControleurTouches( Pane panePrincipal,Jeu jeu, Tuile perso) {
		
		ControleurTouches controleurTouches = new ControleurTouches(panePrincipal, jeu, perso) ;
		controleurTouches.gererControleur();
		return controleurTouches;
	}

}
