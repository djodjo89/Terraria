package objetRessources;

import modele.Jeu;
import modele.Tuple;
import physique.Collisionneur;

public class Torche extends Outil{

	public Torche(Collisionneur c) {
		super(c);
		super.initRecette(new Tuple(new BlocElectromagnetique(), 1));
	}

	@Override
	public void utilisation(int x, int y , Jeu jeu) {
		// TODO Auto-generated method stub
		
	}

}
