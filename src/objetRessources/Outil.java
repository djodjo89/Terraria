package objetRessources;

import java.util.ArrayList;

import application.NomClasse;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;




import modele.Tuple;


import modele.Jeu;

import objetRessources.Inventeriable;
import physique.Collisionneur;


/*
 * Un Outil est un Objet qui a des points d'attaque
 * Voici ses responsabilités :
 * - donner ses points d'attaque
 * - donner la propriété de ses points d'attaque
 */

public abstract class Outil extends Inventeriable {
	
	private DoubleProperty ptsAttaque ;
	private ArrayList<Tuple> recette;
	
	public Outil(int ptsDegat) {
		super();
		this.ptsAttaque=new SimpleDoubleProperty(ptsDegat);
	}
	public void initRecette (Tuple... ingredients) {
		this.recette=new ArrayList<>();
		for(Tuple ingredient : ingredients) {
			
			this.recette.add(ingredient) ;
			
		}
		
	}
	
	public Outil (Collisionneur c) {
		
		super (500, c,false) ;
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue() ;
		
	}
	
	public DoubleProperty getPtsAttaqueProperty () {
		
		return this.ptsAttaque ;
		
	}
	
	
	public abstract void Utilisation(int x, int y, Jeu jeu) ;
	
	public ArrayList<Tuple> getRecette(){
		return this.recette;
	}


	public void destructionBloc(Jeu jeu, int x, int y) {
		
		if (!(NomClasse.retrouver(jeu.getTerrain().getListeLignes().get(y).get(x))).equals(NomClasse.retrouver(new Air()))&& !(NomClasse.retrouver(jeu.getTerrain().getListeLignes().get(y).get(x))).equals(NomClasse.retrouver(new Granite()))) {
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
