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

public abstract class Bloc extends Inventeriable implements Cliquable {
	
	public static String tag;
	
	public Bloc( double pv,boolean estUnObstacle) {
		
		super (tag,pv,null,estUnObstacle);
		
	}
	
	public void initTag (String tag) {
		
		this.tag = tag ;
		
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
					System.out.println(jeu.getPerso().getInventaire().getInventaire());
				
			}
		}
		
		
		
	}
	
	public void utilisation(int x, int y,Jeu jeu) {
		System.out.println(this);
		if (this != null) {
			this.setCollisionneur(jeu.getTerrain().getListeLignes().get(y).get(x).getCollisionneur());
			jeu.getTerrain().getListeLignes().get(y).set(x,this);
			jeu.getPerso().getInventaire().retirerObjet(this);
			jeu.getPerso().objetMainExisteEncore(this);
		}
		
	}
	
	
	
}
