package fabriques;

import java.io.IOException;

import exceptions.HorsDeLaMapException;
import modele.Jeu;
import ressources.Images;

public class FabriqueJeu {
	
	public static Jeu initialiserJeu (Jeu jeu, Images images) throws IOException, HorsDeLaMapException {
		
		return new Jeu ("map.csv", images.getImage("air").getWidth(), images.getImage("air").getHeight(), 600., 10.) ;
		
	}

}
