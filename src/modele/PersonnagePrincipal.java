
package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import objetRessources.Air;
import objetRessources.Inventeriable;
import objetRessources.Outil;
import physique.Collisionneur;
import physique.GameObject;
import physique.Moteur;

public class PersonnagePrincipal extends Personnage{
	
	private Inventaire i;
	private Inventeriable main;
	
	public PersonnagePrincipal (String nom, double pv, double ptsAtt, double posX, double posY, double masse, double hauteurSaut, double vitesseDeplacement, Collisionneur collisionneur, Jeu jeu) {
		
		super (nom, pv,ptsAtt, posX, posY, masse,hauteurSaut, vitesseDeplacement, collisionneur, jeu) ;
		
		this.i = new Inventaire(20);
		//System.out.println("voici mon attaque :"+this.ptsAttaque.getValue());
		
	}
	
	public void ajouterObjetMain (Inventeriable o) {
		
		this.donner(this.i.getListeObjets().get(0)) ;
		
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
	

	public Inventeriable getMain () {
		
		return this.main ;
		
	}


	


}