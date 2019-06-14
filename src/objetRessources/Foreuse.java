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
		
		if ((NomClasse.retrouver(jeu.getTerrain().getListeLignes().get(y).get(x))) !=NomClasse.retrouver(new Air())&& (NomClasse.retrouver(jeu.getTerrain().getListeLignes().get(y).get(x))) !=NomClasse.retrouver(new Granite())) {
			jeu.getTerrain().getListeLignes().get(y).get(x).perdrePV(this.getPtsAttaque());
			//creer une methode 
			
			if(jeu.getTerrain().getListeLignes().get(y).get(x).getPV() <= 0) {
				Inventeriable blocCible = null;
				Air caseMap = new Air();
				
				caseMap.setCollisionneur(jeu.getTerrain().getListeLignes().get(y).get(x).getCollisionneur()) ;
				blocCible = jeu.getTerrain().getListeLignes().get(y).get(x);
				blocCible.setPv(100) ;
				jeu.getTerrain().getListeLignes().get(y).set(x,caseMap);
					
					jeu.getPerso().getInventaire().ajouterObjet(blocCible);
				
			}
		}
		jeu.getCraft().actualisation();
	}
	


}
