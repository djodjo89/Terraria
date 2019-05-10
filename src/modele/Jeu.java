package modele;

import java.io.IOException;

public class Jeu {
	
	private Collisionneur c ;
	private Personnage p ;
	private Terrain t ;
	private TraducteurFichier tf ;
	
	public Jeu (String nomF) throws IOException {
		
		this.p = new Personnage () ;
		this.c = new Collisionneur (this.p) ;
		this.tf = new TraducteurFichier(nomF) ;
		this.t = new Terrain (this.tf.getTabMap()) ;
		
	}
	
	public void setPerso (Personnage p) {
		
		this.p =p ;
		
	}
	
	public Terrain getMap () {
		
		return this.t ;
		
	}
	
	public Personnage getPerso () {
		
		return this.p ;
		
	}
	
	public void deplacementPersoPrinc (String direction) {
		
		switch (direction) {
		
			case "haut" : if (this.c.deplacementPossible ("haut", this.t)) this.p.deplace("haut") ; break ;
			
			case "droite" : if (this.c.deplacementPossible ("droite", this.t)) this.p.deplace("droite") ; break ;
			
			case "bas" : if (this.c.deplacementPossible ("bas", this.t)) this.p.deplace("bas") ; break ;
			
			case "gauche" : if (this.c.deplacementPossible ("gauche", this.t)) this.p.deplace("gauche") ; break ;
		
		}
		
	}

}
