package objetRessources;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import modele.Tuple;
import objetRessources.Inventeriable;
import physique.Collisionneur;
import physique.GameObject;

/*
 * Un Outil est un Objet qui a des points d'attaque
 * Voici ses responsabilités :
 * - donner ses points d'attaque
 * - donner la propriété de ses points d'attaque
 */

public abstract class Outil extends Inventeriable {
	
	private DoubleProperty ptsAttaque ;
	private ArrayList<Tuple> recette;
	
	public Outil() {
		super();
		this.ptsAttaque=new SimpleDoubleProperty(50);
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
	

	public ArrayList<Tuple> getRecette(){
		return this.recette;
	}

	public abstract void utilisation(int x, int y);
	


}
