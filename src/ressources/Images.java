package ressources;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Images {
	
	private Map<String, Image> images ;
	
	public Images () {
		
		images = new HashMap<>() ;
		
	}
	
	public void ajouterImage (String nomImg, Image img) {
		
		this.images.put(nomImg, img) ;
		
	}
	
	public Image getImage (String nomImg) {
		
		return this.images.get(nomImg) ;
		
	}

}
