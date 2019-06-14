package objetRessources;

import modele.Cliquable;
import modele.Jeu;
import modele.PersonnagePrincipal;
import modele.Terrain;
import physique.Collisionneur;

/**
 * <h1>Bloc est un bloc Inventeriable</h1>
 * 
 * @version 1.0
 * @author Romain
 *
 */

public class Bloc extends Inventeriable {
	
	
	public Bloc(String tag, double pv,boolean estUnObstacle) {
		
		super (tag,pv,null,estUnObstacle);
		
	}

	@Override

	public void Utilisation(int x, int y, Jeu jeu) {
		
		if (jeu.getTerrain().getListeLignes().get(y).get(x).getTag() == "air") {
			
		
		int j = jeu.getPerso().getInventaire().chercheObjetDansInventaire(jeu.getPerso().getMain());
		Bloc caseMap = (Bloc) jeu.getPerso().getInventaire().getInventaire().get(j).getKey();
		caseMap.setCollisionneur(jeu.getTerrain().getListeLignes().get(y).get(x).getCollisionneur());
		jeu.getTerrain().getListeLignes().get(y).set(x,caseMap);
		jeu.getPerso().getInventaire().retirerObjet(caseMap);
		jeu.getPerso().objetMainExisteEncore(caseMap);
		}
	}


	
	
}
