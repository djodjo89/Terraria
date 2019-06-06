package modele;

/**
 * <h1>Air est un bloc non Inventeriable traversable</h1>
 * 
 * @version 1.1
 * @author Mathys
 *
 */

public class Air extends Bloc implements Cliquable {
	
	public Air (String tag) {
		
		super(tag, 0,false) ;
		
	}
	
	@Override
	public void interactionClick(int x, int y, Jeu jeu) {
		//System.out.println(this.getListeLignes().get(y).get(x).getPV());
		Terrain terrain = jeu.getTerrain();
		Personnage perso = jeu.getPerso();
		System.out.println(perso.getInventaire().getListObjet());
		perso.donner(perso.getInventaire().getListObjet().get(1));
		int j = perso.getInventaire().chercheObjetDansInventaire(perso.getMain());
		
		if (perso.getMain().estUnObstacle() && perso.getMain() !=null) {
	
			Bloc caseMap = (Bloc) perso.getInventaire().getListObjet().get(j);
			terrain.getListeLignes().get(y).set(x,caseMap);
			perso.getInventaire().retirerObjet(caseMap);
			perso.objetMainExisteEncore(caseMap);
		}

				
		
	}

}
