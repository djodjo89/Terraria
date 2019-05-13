package modele;

import javafx.beans.property.* ;
import physique.* ;

public class Objet extends GameObject {
	
	private DoubleProperty pv ;
	private StringProperty nom ;
	
	public Objet () {
		
		super("", new Collisionneur (0, 0, 0, 0)) ;
		this.pv = new SimpleDoubleProperty () ;
		this.nom = new SimpleStringProperty () ;
		
	}
	
	public Objet (String nom, String tag, Collisionneur c) {
		
		super (tag, c) ;
		this.pv = new SimpleDoubleProperty () ;
		this.nom = new SimpleStringProperty (nom) ;
		
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
	
	public void perdrePV (double pv) {
		
		this.pv.set(this.pv.getValue() - pv);
		
	}

}
