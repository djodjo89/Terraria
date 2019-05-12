package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import physique.Collisionneur;

public class Outil extends Objet {
	
	private DoubleProperty ptsAttaque ;
	
	public Outil () {
		
		super () ;
		
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public Outil (String nom, String tag, Collisionneur c) {
		
		super (nom, tag, c) ;
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue() ;
		
	}
	
	public DoubleProperty getPtsAttaqueProperty () {
		
		return this.ptsAttaque ;
		
	}

}
