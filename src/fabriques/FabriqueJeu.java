package fabriques;

import java.io.IOException;

import exceptions.HorsDeLaMapException;
import modele.Jeu;
import ressources.Images;

public class FabriqueJeu {
	
	public static void initialiserJeu (Jeu jeu, Images images) throws IOException, HorsDeLaMapException {
		
		jeu = new Jeu ("map.csv", images.getImage("air").getWidth(), images.getImage("air").getHeight(), 10., 10.) ;
		
	}

}
