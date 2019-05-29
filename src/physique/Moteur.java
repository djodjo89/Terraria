package physique;

public class Moteur {
	
	private double tailleBoiteX ;
	private double tailleBoiteY ;
	private double gravite ;
	
	public Moteur (double tailleBoiteX, double tailleBoiteY, double gravite) {
		
		this.tailleBoiteX = tailleBoiteX ;
		this.tailleBoiteY = tailleBoiteY ;
		this.gravite = gravite ;
		
	}
	
	public void appliquerGravite (GameObject go) {
		
		go.getVecteurVitesse().ajouter(0, this.gravite);
		
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
	
}
