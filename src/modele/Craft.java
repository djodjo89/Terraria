package modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetRessources.Foreuse;
import objetRessources.Inventeriable;
import objetRessources.Outil;

public class Craft {
	
	private Jeu jeu;
	private ArrayList<Outil> listeOutils ;
	private ObservableList<Outil> objetsCraftables ;
	

	public Craft(Jeu jeu) {
		this.jeu = jeu;
		this.objetsCraftables = FXCollections.observableArrayList() ;
		this.initListe() ;
	}
	
	public void initListe () {
		
		this.listeOutils = new ArrayList<>() ;
		this.listeOutils.add(new Foreuse()) ;
		
	}
	
	public void actualisation () {
		
		for (Outil o : this.listeOutils) {
			
			if (this.peuxCrafter(o))
				
				this.objetsCraftables.add(o) ;
			
			else
				
				this.objetsCraftables.remove(o) ;
			
		}
		
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
