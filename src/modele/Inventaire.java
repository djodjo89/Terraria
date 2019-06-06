package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import physique.* ;

/*
 * Inventaire gère l'inventaire du personnage.
 * Il possède une liste d'objets ainsi qu'une
 * liste d'entiers pour les quantités des objets
 * Voici ses responsabilités :
 * - ajouter un objet
 * - supprimer définitivement un objet de la liste
 * - retirer un seul objet de la liste
 * - dire si un objet est dans l'inventaire
 * - renvoyer une liste observable d'objets
 */

/**
 * <h1>Inventaire gère l'inventaire du Personnage
 * 
 * 
 * @author Mathys
 *
 */

public class Inventaire {

	public ObservableList<Inventeriable> listeObjets ;
	public ObservableList<Integer> listeQtes ;

	public Inventaire (int taille) {

		this.listeObjets = FXCollections.observableArrayList() ;
		this.listeQtes = FXCollections.observableArrayList() ;
		this.initInventaire(taille);

	}

	private void initInventaire (int taille) {


		for (int i = 0 ; i < taille ; i ++) {
			this.listeObjets.add(null) ;
			this.listeQtes.add(0) ;
		}

	}

	// Si l'objet n'existe pas dans la liste d'objets,
	// l'y ajoute, sinon augmente sa quantité de 1

	public void ajouterObjet (Inventeriable o) {
		
		int i =0 ;
		int j = chercheObjetDansInventaire(o);
		System.out.println(o.getPV());
			
		

			if (j == -1) {
				while (this.listeObjets.get(i) != null) {
					i ++ ;
				}
				this.listeObjets.set(i, o) ;
				this.listeQtes.set(i, 1) ;
			}
			else {

				this.listeQtes.set(j, this.listeQtes.get(j)+1);
				System.out.println(this.listeQtes);
			}
			


	}
	
	// Retire un seul objet de la liste
	
	public void retirerObjet (GameObject o) {
		
		int j = chercheObjetDansInventaire(o);
		
		if (this.estVide()) if (this.listeObjets.contains(o)) this.listeQtes.set(this.listeObjets.indexOf(o), this.listeObjets.indexOf(o) - 1) ;
		this.listeQtes.set(j, this.listeQtes.get(j)-1);
		System.out.println(this.listeQtes);
		if(this.listeQtes.get(j) == 0) {
			supprimerObjet(o);
			System.out.println("yes");
		}
	}

	// Supprime complètement un type d'objet de la liste
	
	public void supprimerObjet (GameObject o) {
		
		if (!this.estVide())
			
			if (this.listeObjets.contains(o)) {
				
				this.listeQtes.set(this.listeObjets.indexOf(o), 0) ;
				this.listeObjets.remove(o) ;
				System.out.println(this.listeObjets);
			}
		
	}
	
	//public boolean estDansLInventaire (GameObject o) {
		
		//boolean estDansInventaire = false;
		//int i ;
		//while (i<this.listeObjets.size() && !estDansInventaire) {
			//if(this.listeObjets.get(i).getTag().equals(o.getTag())) {
				//estDansInventaire = true;
			//}
			///i++;
		//}
		//return estDansInventaire;
		
		
	//}
	
	public boolean estVide () {
		
		return this.listeObjets.size() == 0 ;
		
	}
	
	public int chercheObjetDansInventaire(GameObject o) {
		
		boolean objetExistant = false;
		int j = 0;
			
			while (this.listeObjets.get(j)!=null && (j < this.listeObjets.size() && objetExistant == false)) {
				//System.out.println(o.getTag());
				
				if (this.listeObjets.get(j).getTag().equals(o.getTag())) {
					
					objetExistant  = true;
					
				}
				else {
					j++;
				}
			}
			if (objetExistant) {
			return j;
			}
			else {
				return -1;
			}
	}
	
	public ObservableList<Inventeriable> getInventaire () {
		
		return this.listeObjets ;
		
	}
	
	
	public ObservableList<Inventeriable> getListObjet(){
		return this.listeObjets;
	}
	
	public ObservableList<Integer> getQuantiteObjets(){
		return this.listeQtes;
	}
	

}
