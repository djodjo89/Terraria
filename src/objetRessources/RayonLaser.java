package objetRessources;

import modele.Jeu;
import modele.Tuple;


public class RayonLaser extends Outil{

	public RayonLaser() {
		super(25);
		super.initRecette(new Tuple(new BlocPlastique(), 1), new Tuple(new BlocElectromagnetique(), 1));
	}


	public void Utilisation(int x, int y, Jeu jeu) {

		// TODO Auto-generated method stub
		
	}


}
