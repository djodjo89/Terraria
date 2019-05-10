package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Outil extends Objet {
	
	private DoubleProperty ptsAttaque ;
	
	public Outil () {
		
		super () ;
		
		this.ptsAttaque = new SimpleDoubleProperty () ;
		
	}
	
	public double getPtsAttaque () {
		
		return this.ptsAttaque.getValue() ;
		
	}
	
	public DoubleProperty getPtsAttaqueProperty () {
		
		return this.ptsAttaque ;
		
	}

}
