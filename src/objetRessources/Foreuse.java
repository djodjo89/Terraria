package objetRessources;

import java.util.ArrayList;

import application.NomClasse;
import modele.Jeu;

import modele.Tuple;



import physique.GameObject;

public class Foreuse extends Outil{
	



	public Foreuse() {
		super(50);
		super.initRecette(new Tuple(new BlocElectromagnetique(),1),new Tuple(new BlocMetalique(),1));

	}

	@Override
	public void Utilisation(int x, int y, Jeu jeu) {
		
		super.destructionBloc(jeu, x,y);
	}
	


}
