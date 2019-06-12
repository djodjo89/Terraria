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
		images.ajouterImage("blocBio", new Image(new File("image/blocBio.png").toURI().toString()));
		images.ajouterImage("blocBois", new Image(new File("image/blocBois.png").toURI().toString()));
		images.ajouterImage("blocDechet", new Image(new File("image/blocDechet.png").toURI().toString()));
		images.ajouterImage("blocElectro", new Image(new File("image/blocElectro.png").toURI().toString()));
		images.ajouterImage("blocMetal", new Image(new File("image/blocMetal.png").toURI().toString()));
		images.ajouterImage("blocPlastique", new Image(new File("image/blocPlastique.png").toURI().toString()));
		images.ajouterImage("bois", new Image(new File("image/bois.png").toURI().toString()));
		images.ajouterImage("electronique", new Image(new File("image/electronique.png").toURI().toString()));
		images.ajouterImage("metal", new Image(new File("image/metal.png").toURI().toString()));
		images.ajouterImage("pierre", new Image(new File("image/pierre.png").toURI().toString()));
		images.ajouterImage("plastique", new Image(new File("image/plastique.png").toURI().toString()));
		images.ajouterImage("tableCraft", new Image(new File("image/table.png").toURI().toString()));
		
		return images;
	}

}
