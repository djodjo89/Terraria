package objetRessources;

import java.util.ArrayList;

import modele.Tuple;
import physique.GameObject;

public class Foreuse extends Outil{
	
	private static ArrayList<Tuple> recette;
	
	public Foreuse(String tag) {
		super(tag, recette);
	}
	
}
