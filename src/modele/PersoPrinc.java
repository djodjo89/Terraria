package modele;

public class PersoPrinc extends Personnage {
	
	private Inventaire i ;
	
	public PersoPrinc () {
		
		super ("joueur",1,1) ;
		
	}
	
	public PersoPrinc (String nom, int x, int y) {
		
		super(nom, x, y) ;
		
	}
	
	public void ajouterObjetMain (Outil o) {
		
		if (this.i.getInventaire().get(this.i.getInventaire().indexOf(o)).getClass().equals("Outil"))
		
		super.donner((Outil)this.i.getInventaire().get(this.i.getInventaire().indexOf(o))) ;
		
	}

}
