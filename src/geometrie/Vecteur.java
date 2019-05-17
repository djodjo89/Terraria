package geometrie;

public class Vecteur {
	
	private double x ;
	private double y ;
	
	public Vecteur (double x, double y) {
		
		this.x = x ;
		this.y = y ;
		
	}
	
	public void ajouter (double x, double y) {
		
		this.x += x ;
		this.y += y ;
		
	}
	
	public double getVitesseX () {
		
		return this.x ;
		
	}
	
	public double getVitesseY () {
		
		return this.y ;
		
	}
	
	public void setVitesseX (double x) {
		
		this.x = x ;
		
	}
	
	public void setVitesseY (double y) {
		
		this.y = y ;
		
	}

}
