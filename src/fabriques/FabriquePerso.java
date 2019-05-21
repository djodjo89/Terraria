package fabriques;

import modele.Personnage;
import physique.Collisionneur;

public class FabriquePerso {
	
	private Personnage personnage ;
	
	public FabriquePerso (double posXJoueur, double posYJoueur) {
		
		this.personnage = new Personnage ("Wall-E", 100., 10., posXJoueur, posYJoueur, 1., 1., 1., new Collisionneur (posXJoueur, posYJoueur, m.getTailleBoiteY() + posXJoueur - 1, m.getTailleBoiteX() + posYJoueur - 1),this, 10) ;
		
	}

}
