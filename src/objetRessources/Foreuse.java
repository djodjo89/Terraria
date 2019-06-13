package objetRessources;

import java.util.ArrayList;

import modele.Tuple;
import physique.GameObject;

public class Foreuse extends Outil{
	
	
	public Foreuse(String tag) {
		super(tag);
		super.initRecette(new Tuple(new BlocMetalique(), 1), new Tuple(new BlocElectromagnetique(), 1));
	}

	@Override
	public void utilisation(int x, int y) {
		
		
	}
}
