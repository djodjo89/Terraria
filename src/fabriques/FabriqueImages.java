package fabriques;

import java.io.File;

import javafx.scene.image.Image;
import ressources.Images;

public class FabriqueImages {
	
	public static Images initialiserImages () {
		
		Images images=new Images();
		images.ajouterImage("perso", new Image(new File("image/wall-by.gif").toURI().toString()));
		images.ajouterImage("ennemi", new Image(new File("image/mechant.gif").toURI().toString()));
		images.ajouterImage("granite", new Image(new File("image/perso.png").toURI().toString()));
		images.ajouterImage("terre", new Image(new File("image/terre.png").toURI().toString()));
		images.ajouterImage("air", new Image(new File("image/air.png").toURI().toString()));
		images.ajouterImage("fondInventaire", new Image(new File("image/fondInventaire.png").toURI().toString()));
		images.ajouterImage("forreuse", new Image(new File("image/forreuse.png").toURI().toString()));

		return images;
	}

}
