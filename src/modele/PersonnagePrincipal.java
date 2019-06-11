
package modele;

import objetRessources.Air;
import objetRessources.Inventeriable;
import objetRessources.Outil;
import physique.Collisionneur;
import physique.GameObject;

public class PersonnagePrincipal extends Personnage{
	
	private Inventaire i;
	private Inventeriable main;
	
	public PersonnagePrincipal (String nom, double pv, double ptsAtt, double posX, double posY, double masse, double hauteurSaut, double vitesseDeplacement, Collisionneur collisionneur, Jeu jeu) {
		
		super (nom, pv,ptsAtt, posX, posY, masse,hauteurSaut, vitesseDeplacement, collisionneur, jeu) ;
		
		this.i = new Inventaire(20);
		
	}
	
	public void ajouterObjetMain (Inventeriable o) {
		
		this.donner(this.i.getListeObjets().get(0)) ;
		
	}
	
	public Inventeriable destructionTerrain(int x, int y) {
		
		Terrain terrain = this.getJeu().getTerrain();
		Inventeriable blocCible = null;
		
		if(terrain.getListeLignes().get(y).get(x).estUnObstacle() && terrain.getListeLignes().get(y).get(x).getPV()>0) {
			
			this.attaque(terrain.getListeLignes().get(y).get(x));
				
			if(terrain.getListeLignes().get(y).get(x).getPV() <= 0) {
				
				Air caseMap = new Air("air");
				
				caseMap.setCollisionneur(terrain.getListeLignes().get(y).get(x).getCollisionneur()) ;
				blocCible = terrain.getListeLignes().get(y).get(x);
				blocCible.setPv(100) ;
				terrain.getListeLignes().get(y).set(x,caseMap);
				
			}
		}
		return blocCible;
	}
	
	public void poserBlockTerrain(int x, int y) {
		
		Terrain terrain = this.getJeu().getTerrain();
		int j = this.i.chercheObjetDansInventaire(this.main);
		
		if (this.main.estUnObstacle() && this.main !=null) {
	
			Inventeriable caseMap = this.getInventaire().getListeObjets().get(j);
			terrain.getListeLignes().get(y).set(x,caseMap);
			this.getInventaire().retirerObjet(caseMap);
			objetMainExisteEncore(caseMap);
		}
	}
	

	public Inventaire getInventaire (){
		
		return this.i ;
	}

	/**
	 * Calcule et renvoie la puissance du saut du GameObject selon le Moteur
	 * 
	 * @param moteur
	 */
	

	

	
	public void objetMainExisteEncore(Inventeriable o) {
		if (this.i.chercheObjetDansInventaire(o) == -1) {
			this.main = null;
			
		}
	}
	
	public void donner (Inventeriable o) {
		
		this.main = o ;
		
	}
	
	public void attaque (GameObject o) {
		
		if(this.main instanceof Outil ) 
			o.perdrePV (((Outil) this.main).getPtsAttaque()) ;
		
		
		else {
			o.perdrePV(this.getPtsAttaque());
		}
		
	}
	

	public GameObject getMain () {
		
		return this.main ;
		
	}


	


}