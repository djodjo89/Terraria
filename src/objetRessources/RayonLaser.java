package objetRessources;

import modele.Jeu;

import java.util.ArrayList;

import modele.Tuple;


public class RayonLaser extends Outil{

	public RayonLaser(String tag, ArrayList<Tuple> recette) {
		super(tag);
		super.initRecette(new Tuple(new BlocPlastique(), 1), new Tuple(new BlocElectromagnetique(), 1));
	}

	@Override
	public void utilisation(int x, int y , Jeu jeu) {
		// TODO Auto-generated method stub
		
	}


}
