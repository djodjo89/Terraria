package vue;

import java.util.ArrayList;

import controleur.ControleurInventaire;
import controleur.ControleurSouris;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.Jeu;
import modele.PersonnagePrincipal;
import objetRessources.Foreuse;
import objetRessources.Inventeriable;
import ressources.Images;

public class InventaireVue {

	private Pane paneFondInvent;
	private Pane paneItems;
	private Jeu jeu;
	private Images images;
	private ArrayList<Tuile> listItemsInvent;
	private static int nbItemAffiche;

	
	
	public InventaireVue(Pane paneInventaire, Pane paneItems, Jeu j, Images img) {
		this.paneFondInvent=paneInventaire;
		this.paneItems=paneItems;
		this.jeu=j;
		this.images=img;
		this.listItemsInvent=new ArrayList<>();
		this.nbItemAffiche=0;
	}
	
	
	//Fonction appelé dans le controleurTouche lorqu'on actionne la touche E.
	//Va afficher un plus grand inventaire;
	public void derouleInventaire() {
		String nom;
		Tuile tile;
		for(int i=0; i<5 ; i++) {
			for(int j=0; j<3; j++) {
				nom = ""+i +j;
	    		tile= new Tuile(nom, (i*this.jeu.getMoteur().getTailleBoiteX()),55+(j*this.jeu.getMoteur().getTailleBoiteY()),this.images.getImage("fondInventaire"));
	    		this.paneFondInvent.getChildren().add(tile);
	    		this.listItemsInvent.add(tile);
			}
		}
	}
	
	public void reduitInventaire() {
		
		for(int i=0; i<this.listItemsInvent.size(); i++) {
			this.paneFondInvent.getChildren().remove(this.listItemsInvent.get(i));
		}
		
	}
	
	public Tuile ajoutItemInventaire(Inventeriable obj) {
		nbItemAffiche++;
		Tuile tileItem = new Tuile(obj.getTag(), nbItemAffiche*this.jeu.getMoteur().getTailleBoiteX(),0, this.images.getImage(obj.getTag()));
		this.paneItems.getChildren().add(tileItem);
		return tileItem;
		
	}
	
	
	public void initFondInventaire() {
		Foreuse foreuse = new Foreuse("forreuse");
		this.jeu.getPerso().getInventaire().ajouterObjet(foreuse);
		
		String nom = ""; 
		
    	Tuile tile = new Tuile();
    	for(int i=0; i<5; i++) {
    		nom = "1" +i;
    		tile= new Tuile(nom, (i*jeu.getMoteur().getTailleBoiteX()),0,this.images.getImage("fondInventaire"));
    		this.paneFondInvent.getChildren().add(tile);
    	}
	}
}
