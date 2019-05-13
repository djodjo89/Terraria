package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
	
	public ObservableList<Objet> listeObjets ;
	public ObservableList<Integer> listeQtes ;
	
	public Inventaire (int taille) {
		
		this.listeObjets = FXCollections.observableArrayList() ;
		this.listeQtes = FXCollections.observableArrayList() ;
		this.initInventaire(taille);
		
	}
	
	public void initInventaire (int taille) {
		
		int i ;
		
		for (i = 0 ; i < taille ; i ++) {
			
			this.listeObjets.add(null) ;
			this.listeQtes.add(0) ;
			
		}
		
	}
	
	public void ajouterObjet (Objet o) {
		
		int i ;
		
		i = 0 ;
		
		if (!this.listeObjets.contains(o)) {
			
			while (this.listeObjets.get(i) != null) {
				
				i ++ ;
				
			}
			
			this.listeObjets.set(i, o) ;
			this.listeQtes.set(i, 1) ;
			
		}
		
		else {
			
			this.listeQtes.set(this.listeObjets.indexOf(o), this.listeQtes.get(this.listeObjets.indexOf(o)) + 1) ;
			
		}
		
	}
	
	public void retirerObjet (Objet o) {
		
		if (this.listeObjets.contains(o)) {
			
			this.listeQtes.set(this.listeObjets.indexOf(o), this.listeObjets.indexOf(o) - 1) ;
			
		}
		
	}
	
	public void supprimerObjet (Objet o) {
		
		if (this.listeObjets.contains(o)) {
			
			this.listeQtes.set(this.listeObjets.indexOf(o), 0) ;
			this.listeObjets.remove(o) ;
			
		}
		
	}
	
	public boolean estDansLInventaire (Objet o) {
		
		return this.listeObjets.contains(o) ;
		
		
	}
	
	public ObservableList<Objet> getInventaire () {
		
		return this.listeObjets ;
		
	}

}
