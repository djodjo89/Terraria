package geometrie;

public class Vecteur {
	
	private double x ;
	private double y ;
	
	public Vecteur (double x, double y) {
		
		this.x = x ;
		this.y = y ;
		
	}
	
	public void ajouter (Vecteur v) {
		
		this.x += v.getX() ;
		this.y += v.getY() ;
		
	}
	
	public void ajouter (double x, double y) {
		
		this.x += x ;
		this.y += y ;
		
	}
	
	public double getNorme () {
		
		return (Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2))) ;
		
	}
	
	public double getX () {
		
		return this.x ;
		
	}
	
	public double getY () {
		
		return this.y ;
		
	}
	
	public boolean equals (Vecteur v) {
		
		boolean egaux ;
		
		if (this.x == v.getX() && this.y == v.getY())
	
			egaux = true ;
		
		else
			
			egaux = false ;
		
		return egaux ;
	}
	
	public String toString () {
		
		return this.x + ":" + this.y ;
		
	}

}
