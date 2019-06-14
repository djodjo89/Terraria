package modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetRessources.* ;
import physique.Collisionneur;

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
		this.listeOutils.add(new RetroFusee(new Collisionneur())) ;
		this.listeOutils.add(new Foreuse()) ;
		this.listeOutils.add(new RayonLaser()) ;
		this.listeOutils.add(new Torche(new Collisionneur())) ;
		this.listeOutils.add(new Tronconneuse(new Collisionneur())) ;
		
	}
	
	public void actualisation () {
		
		for (Outil o : this.listeOutils) {
			
			if (this.peuxCrafter(o) && !this.objetsCraftables.contains(o))
				
				this.objetsCraftables.add(o) ;
			
			else if (!this.peuxCrafter(o))
				
				this.objetsCraftables.remove(o) ;
			
		}
		
	}
	
//	public boolean peuxCrafter(Outil obj) {
//		
//		if(this.jeu.getPerso().getInventaire().getInventaire().size() < obj.getRecette().size())
//			return false;
//		
//		for(int i=0; i<obj.getRecette().size(); i++) {
//			if(this.jeu.getPerso().getInventaire().chercheObjetDansInventaire((Inventeriable)obj.getRecette().get(i).getKey()) == -1) {
//				return false;
//			}
//			if(this.jeu.getPerso().getInventaire().getInventaire().get(i).getValue() < obj.getRecette().get(i).getValue()) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	public boolean peuxCrafter(Outil obj) {
		int j ;
		if(this.jeu.getPerso().getInventaire().getInventaire().size() < obj.getRecette().size())
			return false;
		for(int i=0; i<obj.getRecette().size(); i++) {
			j = this.jeu.getPerso().getInventaire().chercheObjetDansInventaire((Inventeriable)obj.getRecette().get(i).getKey());
			if(j == -1) {
				return false;
			}
			if(this.jeu.getPerso().getInventaire().getInventaire().get(j).getValue() < obj.getRecette().get(i).getValue()) {
				return false;
			}
		}
		return true;
	}
	
	public void craft(Outil obj) {

		if(this.peuxCrafter(obj)) {
			for(int i=0; i<obj.getRecette().size(); i++) {
				for(int j=0; j<obj.getRecette().get(i).getValue(); j++)
					this.jeu.getPerso().getInventaire().retirerObjet((Bloc)obj.getRecette().get(i).getKey());
				this.actualisation();
			}
			this.jeu.getPerso().getInventaire().ajouterObjet(obj);
			this.actualisation();
		}
		System.out.println("value"+this.jeu.getPerso().getInventaire().getInventaire().get(0).getValue());
	}
	
	public ObservableList<Outil> getListeObjetsCraftables () {
		
		return this.objetsCraftables ;
		
	}
	
	public ArrayList<Outil> getListeOutils () {
		
		return this.listeOutils ;
		
	}

}
