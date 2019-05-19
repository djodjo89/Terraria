package physique;

//!\\ MODIFIABLE
/*
 * Moteur gère la physique du jeu, pour l'instant il
 * y en a assez peu mais le reste viendra plus tard.
 * Il a une liste d'obstacles à laquelle il peut en
 * ajouter de nouveau. Il a également une distance de
 * déplacement qui sera partagée par tous les objets
 * utilisant le moteur. Enfin il peut envoyer une exception
 * si un personnage apparaît bien dans un Terrain à partir
 * de ses coordonnées.
 */

public class Moteur {
	
	private double tailleBoiteX ;
	private double tailleBoiteY ;
	private double gravite ;
	
	public Moteur (double tailleBoiteX, double tailleBoiteY, double gravite) {
		
		this.tailleBoiteX = tailleBoiteX ;
		this.tailleBoiteY = tailleBoiteY ;
		this.gravite = gravite ;
		
	}
	
	public double getGravite () {
		
		return this.gravite ;
		
	}
	
	public double getTailleBoiteX () {
		
		return this.tailleBoiteX ;
		
	}
	
	public double getTailleBoiteY () {
		
		return this.tailleBoiteY ;
		
	}
	
	public void appliquerGraviter(GameObject perso) {
		
		perso.changerVitesse(0, -1) ;
		
	}
	
}
