package fabriques;

import controleur.ControleurSouris;
import javafx.scene.layout.Pane;
import modele.Jeu;
import ressources.Images;
import vue.InventaireVue;

public class FabriqueVue {

	public static InventaireVue initialiserUnInventaireVue(Pane paneInventaire, Pane paneItems, Jeu j, Images img) {
		InventaireVue inv = new InventaireVue(paneInventaire, paneItems, j, img);
		return inv;
	}
}
