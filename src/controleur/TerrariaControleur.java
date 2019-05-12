
package controleur;

import ressources.Images;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.HorsDeLaMapException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modele.* ;

public class TerrariaControleur implements Initializable {
	
	private Timeline gameLoop;
	
	private Jeu jeu ;
	
	private ControleurTouches ct ;
	
	private Images images ;
	
	@FXML
    private Pane panePerso;
	
	@FXML
	private ImageView perso;
	
    @FXML
    private TextArea map;
    
    @FXML
	private BorderPane BorderMap;
    
	public void initBoucleJeu() {
		
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		

		KeyFrame kf = new KeyFrame(
				// on définit le FPS (nbre de frame par seconde)
				Duration.seconds(0.017), 
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda
				(ev ->{
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}
    
	public void afficherMap () {
		   
	    	String nom = new String("test");
	    	ImageView tile=new ImageView();
	    	
	    	int yMap=this.jeu.getMap().getDimY();
	    	int xMap=this.jeu.getMap().getDimX();
	    	
    		String valeur;
    		this.perso=new ImageView(this.images.getImage("perso"));
    		
    		this.panePerso.getChildren().clear();

	    	for(int y=0;y<yMap;y++) {
		    	for(int x=0;x<xMap;x++) {
		    		nom = x+":"+y;
		    		valeur=this.jeu.getMap().getListeLignes().get(y).get(x).getNom();
		    		if(valeur.equals("T"))
		    			tile =new ImageView(this.images.getImage("terre"));
		    		else if(valeur.equals("A"))
		    			tile =new ImageView(this.images.getImage("air"));
		    		tile.setId(nom);
		    		tile.setLayoutX(x*jeu.getMoteur().getTailleTileX());
		    		tile.setLayoutY(y*jeu.getMoteur().getTailleTileY());
		    		panePerso.getChildren().add(tile);
		    	}
		    	
	    	}
	    	tile = this.perso ;
	    	tile.setId("joueur");
	    	tile.setLayoutX(0);
	    	tile.setLayoutY(0);
	    	BorderMap.getChildren().add(tile);
	    	
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

    	this.images = new Images () ;
    	this.images.ajouterImage("perso", new Image(new File("image/perso.png").toURI().toString()));
    	this.images.ajouterImage("terre", new Image(new File("image/terre.png").toURI().toString()));
    	this.images.ajouterImage("air", new Image(new File("image/air.png").toURI().toString()));
		
    	try {
			this.jeu = new Jeu("map.csv", this.images.getImage("air").getWidth(), this.images.getImage("air").getHeight(), -5., -5.) ;

		this.ajouterEcouteur () ;
		this.afficherMap() ;
		this.initBoucleJeu();
		this.panePerso.setFocusTraversable(true);
		this.ct = new ControleurTouches(this.BorderMap, this.jeu) ;
		this.gameLoop.play();
		this.perso.translateXProperty().bind(jeu.getPerso().getXProperty());
		this.perso.translateYProperty().bind(jeu.getPerso().getYProperty());
		
    	} 
    	catch (HorsDeLaMapException e) {System.out.println(e);}
    	catch (IOException e) {e.printStackTrace();}
		
	}

	public void ajouterEcouteur () {
		
		for (ObservableList<Objet> listeCases : this.jeu.getMap().getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<Objet> () {

				@Override
				public void onChanged(Change<? extends Objet> changement) {


					while (changement.next()) {

					}

				}

			}) ;
			
		}
		
	}

}	



