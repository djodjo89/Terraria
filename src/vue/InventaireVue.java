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
	    		tile= new Tuile(nom, i*jeu.getMoteur().getTailleBoiteX()*2,(this.jeu.getMoteur().getTailleBoiteY() * 2 + (this.jeu.getMoteur().getTailleBoiteY() / 10))+(j*this.jeu.getMoteur().getTailleBoiteY() * 2),this.images.getImage("fondInventaire"));
	    		this.paneFondInvent.getChildren().add(tile);
	    		tile.setFitWidth(this.jeu.getMoteur().getTailleBoiteX() * 2);
	    		tile.setFitHeight(this.jeu.getMoteur().getTailleBoiteX() * 2);
	    		this.listItemsInvent.add(tile);
			}
		}
	}
	
	public void reduitInventaire() {
		
		for(int i=0; i<this.listItemsInvent.size(); i++) {
			this.paneFondInvent.getChildren().remove(this.listItemsInvent.get(i));
		}
		
	}
	


//	public Tuile ajoutItemInventaire(Inventeriable obj, int i) {
//		
//		Tuile tileItem = new Tuile(NomClasse.retrouver(obj), i*this.jeu.getMoteur().getTailleBoiteX(),0, this.images.getImage(NomClasse.retrouver(obj)));
//		this.paneItems.getChildren().add(tileItem);
//		System.out.println("nb bind : " + this.jeu.getPerso().getInventaire().getInventaire().get(i).getValue());
//
//		return tileItem;

	public Tuile ajoutItemInventaire(Tuple tuple, int i) {
		/*Label nbItems ;
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
		this.paneItems.getChildren().add(pane) ;*/
		CaseInventaire ci = new CaseInventaire (new Tuile(NomClasse.retrouver((Inventeriable)tuple.getKey()), 0, 0, this.images.getImage(NomClasse.retrouver((Inventeriable)tuple.getKey()))), this.jeu.getMoteur().getTailleBoiteX() / 2, this.jeu.getMoteur().getTailleBoiteY() / 2, this.jeu.getMoteur().getTailleBoiteX() * 1.4, this.jeu.getMoteur().getTailleBoiteY() * 1.4, (i * this.jeu.getMoteur().getTailleBoiteX() * 2)+this.jeu.getMoteur().getTailleBoiteX() / 10, this.jeu.getMoteur().getTailleBoiteY() / 10) ;
		ci.getQte().textProperty().bind(tuple.getValueProperty().asString());
		this.paneItems.getChildren().add(ci.getPane()) ;
		return ci.getTuile () ;

		
	}
	
	public void retireItemInvent(int i) {
		
		this.paneItems.getChildren().remove(i - nbCasesANullJusquACetIndice(i));
		
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
		
		String nom = ""; 
		
    	Tuile tile = new Tuile();
    	for(int i=0; i<5; i++) {
    		nom = "1" +i;
    		tile= new Tuile(nom, i*jeu.getMoteur().getTailleBoiteX()*2,0,this.images.getImage("fondInventaire"));
    		this.paneFondInvent.getChildren().add(tile);
    		tile.setFitWidth(this.jeu.getMoteur().getTailleBoiteX() * 2);
    		tile.setFitHeight(this.jeu.getMoteur().getTailleBoiteX() * 2);
    		//this.paneIteration.getChildren().add(new TextField());
    	}
    	//System.out.println("taile pane iter :" + this.paneIteration.getChildren().size());
    	
    	
	}
}
