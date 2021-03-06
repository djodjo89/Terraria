package modele;



import application.NomClasse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetRessources.Inventeriable;

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

	private ObservableList<Tuple> listeObjets;

	public Inventaire (int taille) {
		this.listeObjets=FXCollections.observableArrayList();
		this.initInventaire(taille);

	}

	private void initInventaire (int taille) {
		
		for (int i = 0 ; i < taille ; i ++) 
			this.listeObjets.add(new Tuple(null,0)) ;
	}

	// Si l'objet n'existe pas dans la liste d'objets,
	// l'y ajoute, sinon augmente sa quantité de 1

	public void ajouterObjet (Inventeriable o) {
		
		int i =0 ;
		int j = chercheObjetDansInventaire(o);
			
			if (j == -1) {
				while (this.listeObjets.get(i).getKey() != null && i<19) {
					i ++ ;
				}
				this.listeObjets.set(i,new Tuple(o, 1));
				
			}
			else {
				this.listeObjets.get(j).increment();
				
			}
			
			
			
	}
	
	// Retire un seul objet de la liste
	
	public boolean retirerObjet (Inventeriable o) {
		
		int pos = chercheObjetDansInventaire(o);
		boolean objSupprime ;
		
		objSupprime = false ;
		
		if (!this.estVide()) {

			if(pos != -1) {
				
				this.listeObjets.get(pos).decrement();
				
				if(this.listeObjets.get(pos).getValue() == 0) {
					
					supprimerObjet(o);
					objSupprime = true ;
					
				}
				
			}
			
		}
		
		return objSupprime ;
		
	}

	// Supprime complètement un type d'objet de la liste
	
	public void supprimerObjet (Inventeriable o) {
		
		int pos = chercheObjetDansInventaire(o);
		
		if (!this.estVide())	
			if (pos != -1) {		
				this.listeObjets.set(pos, new Tuple());
			}
		
	}
	
	
	public boolean estVide () {
		
		return this.listeObjets.size() == 0 ;
		
	}
	
	public int chercheObjetDansInventaire(Inventeriable o) {

		boolean objetExistant = false;
		int j = 0;

		while (j < this.listeObjets.size() && objetExistant == false && this.listeObjets.get(j)!=null) {
			
			Inventeriable inv = (Inventeriable)this.listeObjets.get(j).getKey();
			if (inv != null) {
			if (NomClasse.retrouver(inv).equals(NomClasse.retrouver(o)))

				objetExistant  = true;
			else
				
				j ++ ;}
			else
				
				j++;
			
		}
		
		
		if (objetExistant) {
			
			return j;
			
		}
		
		else {
			
			return -1;
			
		}
		
	}
	
	public ObservableList<Tuple> getInventaire () {
		
		return this.listeObjets ;
		
	}
	
	public ObservableList<Inventeriable> getListeObjets(){
		ObservableList<Inventeriable> listObjets = FXCollections.observableArrayList();
		for(int i=0; i<this.listeObjets.size();i++) {
			listObjets.add((Inventeriable)this.listeObjets.get(i).getKey());
		}
		return listObjets;
	}
	
	public int getQtiteObjet(Inventeriable obj) {
		
		int pos = this.chercheObjetDansInventaire(obj);
		
		return this.listeObjets.get(pos).getValue();
	}
	

}
