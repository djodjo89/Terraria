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
	
	// Retire un seul objet de la liste
	
	public void retirerObjet (GameObject o) {
		
		if (this.estVide()) if (this.listeObjets.contains(o)) this.listeQtes.set(this.listeObjets.indexOf(o), this.listeObjets.indexOf(o) - 1) ;
		
	}

	// Supprime complètement un type d'objet de la liste
	
	public void supprimerObjet (GameObject o) {
		
		if (this.estVide())
		
			if (this.listeObjets.contains(o)) {
			
				this.listeQtes.set(this.listeObjets.indexOf(o), 0) ;
				this.listeObjets.remove(o) ;
			
			}
		
	}
	
	public boolean estDansLInventaire (GameObject o) {
		
		return this.listeObjets.contains(o) ;
		
		
	}
	
	public boolean estVide () {
		
		return this.listeObjets.size() == 0 ;
		
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
