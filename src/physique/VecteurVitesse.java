package physique;

public class VecteurVitesse {
	
	private double vitesseX ;
	private double vitesseY ;
	
	public VecteurVitesse (double vitesseX, double vitesseY) {
		
		this.vitesseX = vitesseX ;
		this.vitesseY = vitesseY ;
		
	}
	
	public void ajouter (double x, double y) {
		
		this.vitesseX += x ;
		this.vitesseY += y ;
		
	}
	
	public double getVitesseX () {
		
		return this.vitesseX ;
		
	}
	
	public double getVitesseY () {
		
		return this.vitesseY ;
		
	}
	
	public void setVitesseX (double vitesseX) {
		
		this.vitesseX = vitesseX ;
		
	}
	
	public void setVitesseY (double vitesseY) {
		
		this.vitesseY = vitesseY ;
		
	}

}
