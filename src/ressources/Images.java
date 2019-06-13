package ressources;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import application.NomClasse;
import javafx.scene.image.Image;
import objetRessources.Terre;

public class Images {
	
	private Map<String, Image> images ;
	
	public Images () {
		
		images = new HashMap<>() ;
		
	}
	
	public void ajouterImage (String nomImg, String typeImg) {
		
		this.images.put(nomImg, new Image(new File("image/" + nomImg + "." + typeImg).toURI().toString())) ;
		
	}
	
	public Image getImage (String nomImg) {
		
		return this.images.get(nomImg) ;
		
	}

}
