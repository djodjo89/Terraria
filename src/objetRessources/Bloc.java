package objetRessources;

import modele.Cliquable;
import modele.Jeu;
import modele.PersonnagePrincipal;
import modele.Terrain;

/**
 * <h1>Bloc est un bloc Inventeriable</h1>
 * 
 * @version 1.0
 * @author Romain
 *
 */

public abstract class Bloc extends Inventeriable implements Cliquable {
	
	public Bloc( double pv,boolean estUnObstacle) {
		
		super (pv,null,estUnObstacle);
		
	}

	@Override
	public void interactionClick(int x, int y, Jeu jeu) {
		
		Terrain terrain = jeu.getTerrain();
		Inventeriable blocCible = null;
		
		if(terrain.getListeLignes().get(y).get(x).getPV()>0) {
			
			jeu.getPerso().attaque(terrain.getListeLignes().get(y).get(x));
				
			if(terrain.getListeLignes().get(y).get(x).getPV() <= 0) {
				
				Air caseMap = new Air();
				
				caseMap.setCollisionneur(terrain.getListeLignes().get(y).get(x).getCollisionneur()) ;
				blocCible = terrain.getListeLignes().get(y).get(x);
				blocCible.setPv(100) ;
				terrain.getListeLignes().get(y).set(x,caseMap);
					
				jeu.getPerso().getInventaire().ajouterObjet(this);
				jeu.getCraft().actualisation();
				
			}
		}
		
		
		
	}
	
	public void utilisation(int x, int y,Jeu jeu) {

		if (this != null) {
			this.setCollisionneur(jeu.getTerrain().getListeLignes().get(y).get(x).getCollisionneur());
			jeu.getTerrain().getListeLignes().get(y).set(x,this);
			jeu.getPerso().getInventaire().retirerObjet(this);
			jeu.getCraft().actualisation();
			jeu.getPerso().objetMainExisteEncore(this);
		}
		
	}
	
	
	
}
