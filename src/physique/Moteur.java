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
