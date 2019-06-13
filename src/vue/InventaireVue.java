package vue;

import java.util.ArrayList;

import application.NomClasse;
import controleur.ControleurInventaire;
import controleur.ControleurSouris;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private Pane paneIteration;
	private Jeu jeu;
	private Images images;
	private ArrayList<Tuile> listItemsInvent;


	
	
	public InventaireVue(Pane paneInventaire, Pane paneItems, Pane paneIteration, Jeu j, Images img) {
		this.paneFondInvent=paneInventaire;
		this.paneItems=paneItems;
		this.paneIteration=paneIteration;
		this.jeu=j;
		this.images=img;
		this.listItemsInvent=new ArrayList<>();
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
	
	public Tuile ajoutItemInventaire(Inventeriable obj, int i) {
		
		Tuile tileItem = new Tuile(NomClasse.retrouver(obj), i*this.jeu.getMoteur().getTailleBoiteX(),0, this.images.getImage(NomClasse.retrouver(obj)));
		this.paneItems.getChildren().add(tileItem);
		System.out.println("nb bind : " + this.jeu.getPerso().getInventaire().getInventaire().get(i).getValue());

		return tileItem;
		
	}
	
	public void retireItemInvent(int i) {
		
		this.paneItems.getChildren().remove(i);
		
	}
	
	public Tuile afficheNbItem(int i) {
		//System.out.println("i" + i);
		Tuile tileNb = new Tuile(i+"", i*this.jeu.getMoteur().getTailleBoiteX(),0, this.images.getImage(this.jeu.getPerso().getInventaire().getInventaire().get(i).getValue()+""));
		this.paneIteration.getChildren().set(i,tileNb);
		return tileNb;
	}
	
	
	public void initFondInventaire() {
		Foreuse foreuse = new Foreuse();
		this.jeu.getPerso().getInventaire().ajouterObjet(foreuse);
		
		this.jeu.getPerso().getInventaire().supprimerObjet(foreuse);
		//this.jeu.getPerso().getInventaire().ajouterObjet(foreuse);
		//System.out.println("taille invent "+this.jeu.getPerso().getInventaire().getInventaire().size());
		String nom = ""; 
		
    	Tuile tile = new Tuile();
    	for(int i=0; i<5; i++) {
    		nom = "1" +i;
    		tile= new Tuile(nom, (i*jeu.getMoteur().getTailleBoiteX()),0,this.images.getImage("fondInventaire"));
    		this.paneFondInvent.getChildren().add(tile);
    		//this.paneIteration.getChildren().add(new TextField());
    	}
    	//System.out.println("taile pane iter :" + this.paneIteration.getChildren().size());
    	
    	
	}
}
