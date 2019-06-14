package objetRessources;

import java.util.ArrayList;

import modele.Jeu;
import physique.GameObject;

public class Foreuse extends Outil{
	
	private ArrayList<GameObject> itemNecessaire;

	public Foreuse(String tag) {
		super(tag);
		this.itemNecessaire=new ArrayList<GameObject>();
	}

	@Override
	public void Utilisation(int x, int y, Jeu jeu) {
		if (jeu.getTerrain().getListeLignes().get(y).get(x).getTag() !="air" && jeu.getTerrain().getListeLignes().get(y).get(x).getTag() !="granite") {
			jeu.getTerrain().getListeLignes().get(y).get(x).perdrePV(this.getPtsAttaque());
			//creer une methode 
			
			if(jeu.getTerrain().getListeLignes().get(y).get(x).getPV() <= 0) {
				Inventeriable blocCible = null;
				Air caseMap = new Air("air");
				
				caseMap.setCollisionneur(jeu.getTerrain().getListeLignes().get(y).get(x).getCollisionneur()) ;
				blocCible = jeu.getTerrain().getListeLignes().get(y).get(x);
				blocCible.setPv(100) ;
				jeu.getTerrain().getListeLignes().get(y).set(x,caseMap);
					
					jeu.getPerso().getInventaire().ajouterObjet(blocCible);
					System.out.println(jeu.getPerso().getInventaire().getInventaire());
				
			}
		}
	}
	

}
