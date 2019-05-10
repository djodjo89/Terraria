package modele;

import javafx.beans.property.* ;

public class Objet {
	
	private DoubleProperty pv ;
	private StringProperty nom ;
	
	public Objet () {
		
		this.pv = new SimpleDoubleProperty () ;
		this.nom = new SimpleStringProperty () ;
		
	}
	
	public double getPV () {
		
		return this.pv.getValue() ;
		
	}
	
	public String getNom () {
		
		return this.nom.getValue() ;
		
	}
	
	public DoubleProperty getPVProperty () {
		
		return this.pv ;
		
	}
	
	public StringProperty getNomProperty () {
		
		return this.nom ;
		
	}

}
