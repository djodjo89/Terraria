package objetRessources;

import application.NomClasse;
import modele.Jeu;

public class Main extends Outil{
	
	
	public Main() {
		super(10);
	}

	@Override
	public void Utilisation(int x, int y, Jeu jeu) {
		if (!(NomClasse.retrouver(jeu.getTerrain().getListeLignes().get(y).get(x)).equals(NomClasse.retrouver(new Air()))) && !(NomClasse.retrouver(jeu.getTerrain().getListeLignes().get(y).get(x)).equals(NomClasse.retrouver(new Granite())))) {
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
					System.out.println(jeu.getPerso().getInventaire().getInventaire());
				
			}
		}
	}
	


	
	

}
