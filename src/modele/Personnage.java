package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import physique.Collisionneur;
import physique.GameObject;

public class Personnage extends GameObject{
	
	private DoubleProperty ptsAttaque ;
	private double hauteurSaut ;
	private Inventaire i;
	private Inventeriable main;
	
	public Personnage (String nom, double pv, double ptsAtt, double posX, double posY, double masse, double hauteurSaut, double vitesseDeplacement, Collisionneur collisionneur, Jeu jeu) {
		
		super (nom, pv, posX, posY, masse, vitesseDeplacement, collisionneur, jeu) ;
		this.hauteurSaut = hauteurSaut ; // ((51.9 * this.hauteurSaut + 48.9 * this.masse - 2007) / m.getTailleBoiteY()*650)
		this.ptsAttaque = new SimpleDoubleProperty (ptsAtt) ;
		this.i = new Inventaire(20);
		//System.out.println("voici mon attaque :"+this.ptsAttaque.getValue());
		
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
		this.donner(this.getInventaire().getListeObjets().get(2));
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
	
	public double getPuissanceSaut () {

		return ((51.9 * this.hauteurSaut + 48.9 * super.getMasse() - 2007) / super.getJeu().getMoteur().getTailleBoiteY()*650) ;
		
	} 
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue () ;
		
	}
	
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