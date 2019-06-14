package objetRessources;

import modele.Jeu;
import modele.Tuple;
import physique.Collisionneur;

public class Tronconneuse extends Outil{

	public Tronconneuse(Collisionneur c) {
		super(c);
		super.initRecette(new Tuple(new BlocMetalique(), 5), new Tuple(new BlocElectromagnetique(), 5), new Tuple(new BlocPlastique(), 5)) ;
	}

	@Override
	public void utilisation(int x, int y, Jeu jeu) {
		// TODO Auto-generated method stub
		
	}

}
