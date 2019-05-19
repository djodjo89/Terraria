package fabriques;

import javafx.scene.layout.Pane;

public class FabriquePanes {
	
	public static void initPanes (Pane paneMap, Pane paneItemsInventaire) {
		
		paneMap.setFocusTraversable(true);
		paneItemsInventaire.toFront();
		
	}

}
