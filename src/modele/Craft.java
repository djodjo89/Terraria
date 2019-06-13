package modele;

import objetRessources.Inventeriable;
import objetRessources.Outil;

public class Craft {
	
	private Jeu jeu;
	
	

	public Craft(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public boolean peuxCrafter(Outil obj) {
		
		if(this.jeu.getPerso().getInventaire().getInventaire().size() < obj.getRecette().size())
			return false;
		
		for(int i=0; i<obj.getRecette().size(); i++) {
			if(this.jeu.getPerso().getInventaire().chercheObjetDansInventaire((Inventeriable)obj.getRecette().get(i).getKey()) == -1) {
				return false;
			}
			if(this.jeu.getPerso().getInventaire().getInventaire().get(i).getValue() < obj.getRecette().get(i).getValue()) {
				return false;
			}
		}
		return true;
	}
	
	public void craft(Outil obj) {
		if(this.peuxCrafter(obj)) {
			for(int i=0; i<obj.getRecette().size(); i++) {
				for(int j=0; j<obj.getRecette().get(i).getValue(); j++)
					this.jeu.getPerso().getInventaire().retirerObjet((Outil)obj.getRecette().get(i).getKey());
			}
			this.jeu.getPerso().getInventaire().ajouterObjet(obj);
		}
	}
	

}
