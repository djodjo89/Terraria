package modele;

public class PersoPrinc extends Personnage {
	
	private Inventaire i ;
	
	public PersoPrinc () {
		
		super () ;
		
	}
	
	public PersoPrinc (String nom, double x, double y, double ptsAtt) {
		
		super(nom, x, y, ptsAtt) ;
		
	}
	
	public void ajouterObjetMain (Outil o) {
		
		if (this.i.getInventaire().get(this.i.getInventaire().indexOf(o)).getClass().equals("Outil"))
		
		super.donner((Outil)this.i.getInventaire().get(this.i.getInventaire().indexOf(o))) ;
		
	}

}
