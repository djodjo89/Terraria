package objetRessources;

import java.util.ArrayList;

import physique.GameObject;

public class Foreuse extends Outil{
	
	private ArrayList<GameObject> itemNecessaire;

	public Foreuse(String tag) {
		super(tag);
		this.itemNecessaire=new ArrayList<GameObject>();
	}
	
	public void Action() {
		
	}
}
