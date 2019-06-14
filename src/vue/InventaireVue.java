package vue;

import java.util.ArrayList;

import application.NomClasse;
import controleur.ControleurInventaire;
import controleur.ControleurSouris;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import modele.*;
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
	
	public Tuile ajoutItemInventaire(Tuple tuple, int i) {
		Label nbItems ;
		StackPane pane = new StackPane() ;
		Tuile tileItem ;
		nbItems = new Label() ;
		nbItems.setTextFill(Color.web("#ffffff", 0.8));
		nbItems.setFont(Font.font ("Verdana", 20));
		tileItem = new Tuile(NomClasse.retrouver((Inventeriable)tuple.getKey()), 0, 0, this.images.getImage(NomClasse.retrouver((Inventeriable)tuple.getKey()))) ;
		tileItem.setFitWidth(this.jeu.getMoteur().getTailleBoiteX() * 1.4);
		tileItem.setFitHeight(this.jeu.getMoteur().getTailleBoiteY() * 1.4);
		nbItems.setTranslateX(this.jeu.getMoteur().getTailleBoiteX() / 2);
		nbItems.setTranslateY(this.jeu.getMoteur().getTailleBoiteY() / 2);
		pane.getChildren().add(tileItem) ;
		pane.getChildren().add(nbItems) ;
		pane.setTranslateX((i * this.jeu.getMoteur().getTailleBoiteX() * 2)+this.jeu.getMoteur().getTailleBoiteX() / 10);
		pane.setTranslateY(5);
		nbItems.textProperty().bind(tuple.getValueProperty().asString());
		this.paneItems.getChildren().add(pane) ;
		return tileItem;
		
	}
	
	public void retireItemInvent(int i) {
		
		this.paneItems.getChildren().remove(i - nbCasesANullJusquACetIndice(i ));
		
	}
	
	public Tuile afficheNbItem(int i) {
		Tuile tileNb = new Tuile(i+"", i*this.jeu.getMoteur().getTailleBoiteX(),0, this.images.getImage(this.jeu.getPerso().getInventaire().getInventaire().get(i).getValue()+""));
		this.paneIteration.getChildren().set(i,tileNb);
		return tileNb;
	}
	
	public int nbCasesANullJusquACetIndice (int i) {
		
		int j ;
		int nbNull ;
		
		j = 0 ;
		nbNull = 0 ;
		
		while (j < i) {
			
			if (this.jeu.getPerso().getInventaire().getListeObjets().get(j) == null)
				
				nbNull ++ ;
			
			j ++ ;
			
		}
		
		return nbNull ;
		
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
    		tile= new Tuile(nom, (i*jeu.getMoteur().getTailleBoiteX()*2),0,this.images.getImage("fondInventaire"));
    		this.paneFondInvent.getChildren().add(tile);
    		tile.setFitWidth(this.jeu.getMoteur().getTailleBoiteX() * 2);
    		tile.setFitHeight(this.jeu.getMoteur().getTailleBoiteX() * 2);
    		//this.paneIteration.getChildren().add(new TextField());
    	}
    	//System.out.println("taile pane iter :" + this.paneIteration.getChildren().size());
    	
    	
	}
}
