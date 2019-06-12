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
		images.ajouterImage("fondInventaireSel", new Image(new File("image/fondInventaireSel.png").toURI().toString()));

		images.ajouterImage("1", new Image(new File("image/1.png").toURI().toString()));
		images.ajouterImage("2", new Image(new File("image/2.png").toURI().toString()));
		images.ajouterImage("3", new Image(new File("image/3.png").toURI().toString()));
		images.ajouterImage("4", new Image(new File("image/4.png").toURI().toString()));
		images.ajouterImage("5", new Image(new File("image/5.png").toURI().toString()));
		images.ajouterImage("6", new Image(new File("image/6.png").toURI().toString()));
		
		return images;
	}

}
