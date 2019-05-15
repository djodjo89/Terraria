package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import physique.Collisionneur;
import physique.GameObject;

/*
 * Un Outil est un Objet qui a des points d'attaque
 * Voici ses responsabilités :
 * - donner ses points d'attaque
 * - donner la propriété de ses points d'attaque
 */

public class Outil extends GameObject {
	
	private DoubleProperty ptsAttaque ;
	
	public Outil (String tag, Collisionneur c) {
		
		super (tag, 500, c) ;
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue() ;
		
	}
	
	public DoubleProperty getPtsAttaqueProperty () {
		
		return this.ptsAttaque ;
		
	}

}
