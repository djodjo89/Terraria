package modele;

import java.io.IOException;

public class Jeu {
	
	private Collisionneur c ;
	private Personnage p ;
	private Terrain t ;
	private TraducteurFichier tf ;
	
	public Jeu (String nomF) throws IOException {
		
		this.p = new Personnage ("joueur",1,1) ;
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
		
			case "haut" : if (Collisionneur.deplacementPossible ("haut", this.t,this.p)) this.p.deplace("haut") ; break ;
			
			case "droite" : if (Collisionneur.deplacementPossible ("droite", this.t,this.p)) this.p.deplace("droite") ; break ;
			
			case "bas" : if (Collisionneur.deplacementPossible ("bas", this.t,this.p)) this.p.deplace("bas") ; break ;
			
			case "gauche" : if (Collisionneur.deplacementPossible ("gauche", this.t,this.p)) this.p.deplace("gauche") ; break ;
		
		}
		
	}

}
