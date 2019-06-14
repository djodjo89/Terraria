package fabriques;

import controleur.ControleurSouris;
import javafx.scene.layout.Pane;
import modele.Jeu;
import ressources.Images;
import vue.*;

public class FabriqueVue {

	public static InventaireVue initialiserUnInventaireVue(Pane paneInventaire, Pane paneItems, Pane paneIteration, Jeu j, Images img) {
		InventaireVue inv = new InventaireVue(paneInventaire, paneItems, paneIteration, j, img);
		inv.initFondInventaire();
		return inv;
	}
	
	public static CraftVue initialiserCraftVue (Pane paneCraft, Jeu j, Images images) {
		
		CraftVue craftVue = new CraftVue(j, paneCraft, images) ;
		craftVue.faireDisparaitrePaneCraft() ;
		return craftVue ;
		
	}
	
}
