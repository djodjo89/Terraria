package modele;

import physique.*;
import exceptions.VousEtesCoinceException;
import javafx.beans.property.* ;

/*
 * Un Personnage dispose de coordonnées modifiables et observables
 * Voici ses responsabilités :
 * - prendre un objet dans sa main
 * - renvoyer l'objet qu'il tient
 * - donner ses points d'attaque
 * - attaquer un objet
 */

public class Personnage extends NonInventeriable {
	
	
	private Inventeriable main ;

	private Inventaire i ;
	
	public Personnage () {
		
		super () ;
		this.main = null;
		this.i = new Inventaire(10);
		
		
	}
	
	public Personnage (String nom, double pv, double ptsAtt, double x, double y, double vitesseX, double vitesseY, double poids, Collisionneur c, Jeu jeu, double distanceDeplacement) {
		
		super (nom, pv, x, y, vitesseX, vitesseY, poids, c, distanceDeplacement,jeu,ptsAtt) ;
		
		this.i = new Inventaire (20) ;
		this.setObstacle() ;
		
	}
	
	public void donner (Inventeriable o) {
		
		this.main = o ;
		
	}
	

	
	public GameObject getMain () {
		
		return this.main ;
		
	}
	
	public void attaque (GameObject o) {
		
		if(this.main instanceof Outil ) 
			o.perdrePV (((Outil) this.main).getPtsAttaque()) ;
		
		else 
			o.perdrePV(this.getPtsAttaque());
		
	}
	public void ajouterObjetMain (Inventeriable o) {
		
		this.donner(this.i.getInventaire().get(0)) ;
		
	}
	
	public Inventeriable destructionTerrain(int x, int y) {
		//System.out.println(this.getListeLignes().get(y).get(x).getPV());
		Terrain terrain = this.getJeu().getTerrain();
		Inventeriable blocCible = null;
		if(terrain.getListeLignes().get(y).get(x).estUnObstacle() && terrain.getListeLignes().get(y).get(x).getPV()>0) {
			
			terrain.getListeLignes().get(y).get(x).perdrePV(10);
			System.out.println(terrain.getListeLignes().get(y).get(x).getPV());
				
			if(terrain.getListeLignes().get(y).get(x).getPV() <= 0) {
				Air caseMap = new Air("air");
				blocCible = terrain.getListeLignes().get(y).get(x);
				System.out.println(x);
				System.out.println(y);
				terrain.getListeLignes().get(y).set(x,caseMap);
				
			}
		}
		return blocCible;
	}
	
	public void poserBlockTerrain(int x, int y) {
		Terrain terrain = this.getJeu().getTerrain();
		if (this.getInventaire().getListObjet().get(2).getTag().equals("terre")) {
	
			Terre caseMap = new Terre("terre");
			caseMap.estUnObstacle();
			terrain.getListeLignes().get(y).set(x,caseMap);
			this.getInventaire().retirerObjet(caseMap);
			System.out.println("etape2");
		}
	}
	
	//public void poserBlock() {
		//if (this.getInventaire().getListObjet().get(2).getTag().equals("T")) {
		//	this.getJeu().getTerrain().
		//}
	//}
	

	
	public Inventaire getInventaire (){
		
		return this.i ;
	}
	


}
