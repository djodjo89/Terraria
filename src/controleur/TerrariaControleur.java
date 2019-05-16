
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import modele.* ;
import physique.GameObject;

/*
 * TerrariaControleur génère la map à l'écran, crée une partie
 * et lance une boucle de jeu.
 * Elle possède un jeu, un controleur de touches pour gérer
 * les évènements clavier, un pane pour le personnage et un
 * autre pour la map.
 * Voici ses responsabilités :
 * - Ajouter un écouteur sur la map
 * - Afficher la map à l'écran
 * - Lancer la boucle de jeu
 * 
 */

public class TerrariaControleur implements Initializable {
	
	private Timeline gameLoop;
	
	private Jeu jeu ;
	
	private ControleurTouches controlTouche ;
	
	private Images images ;
	
	@FXML
    private BorderPane borderPanePerso;
	
	@FXML
    private Pane paneCentral;
	@FXML
	private Tuile perso;
    
    @FXML
	private Pane paneMap;
    
    @FXML
    private Pane paneInventaire;

    @FXML
    private Pane paneItemsInventaire;
    
    private int nbTour;
    
    private MapControleur controlMap;
    
    //!\\ MODIFIABLE
    // Lance la boucle de jeu et définit ce qu'y s'y passe (pour l'instant pas grand-chose)
    
	public void initBoucleJeu() {
		nbTour=0;
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		

		KeyFrame kf = new KeyFrame(
				// on définit le FPS (nbre de frame par seconde)
				Duration.seconds(0.017), 
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda
				(ev ->{
					try {
						nbTour=this.jeu.evoluer(nbTour,controlTouche);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}
    
	// Affiche les tuiles du terrain et le personnage à l'écran
	
	public void initMap () {
		   
	    	String nom = new String("test");
	    	String typeBloc;
	    	String valeur;
	    	int yMap=this.jeu.getMap().getDimY();
	    	int xMap=this.jeu.getMap().getDimX();
	    	
	    	Tuile tile;
    		this.paneMap.getChildren().clear();

    		
    		
	    	for(int y=0;y<yMap;y++) {
		    	for(int x=0;x<xMap;x++) {
		    		nom = x+":"+y;
		    		valeur=this.jeu.getMap().getListeLignes().get(y).get(x).getTag();
		    		
		    		switch(valeur) {
		    		case "T":
		    			typeBloc="terre";
		    			break;
		    		case "A":
		    			typeBloc="air";
		    			break;
		    		default:
		    			typeBloc="air";
		    			break;
		    		}
		    		
		    		tile= new Tuile(nom,x*jeu.getMoteur().getTailleTileX(),y*jeu.getMoteur().getTailleTileY(),this.images.getImage(typeBloc));
		    		this.paneMap.getChildren().add(tile);
		    	}
		    	
	    	}
	    	this.perso= new Tuile(nom,0,0,this.images.getImage("perso"));
	    	this.borderPanePerso.getChildren().add(this.perso);
			this.perso.translateXProperty().bind(jeu.getPerso().getXProperty());
			this.perso.translateYProperty().bind(jeu.getPerso().getYProperty());
			
			
	    	Tuile tileItem = new Tuile();
	    	for(int i=0; i<10; i++) {
	    		nom = "" +i;
	    		tile= new Tuile(nom, (i*jeu.getMoteur().getTailleTileX()),0,this.images.getImage("fondInventaire"));
	    		tileItem = new Tuile(nom,(i*jeu.getMoteur().getTailleTileX())+10,15,this.images.getImage("forreuse"));
	    		this.paneInventaire.getChildren().add(tile);
	    		this.paneItemsInventaire.getChildren().add(tileItem);
	    		
	    	}
	    	
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
    	this.images = new Images () ;
    	this.images.ajouterImage("perso", new Image(new File("image/perso.png").toURI().toString()));
    	this.images.ajouterImage("terre", new Image(new File("image/terre.png").toURI().toString()));
    	this.images.ajouterImage("air", new Image(new File("image/air.png").toURI().toString()));
       	this.images.ajouterImage("fondInventaire", new Image(new File("image/fondInventaire.png").toURI().toString()));
    	this.images.ajouterImage("forreuse", new Image(new File("image/forreuse.png").toURI().toString()));
    	
    	
    	try {
			this.jeu = new Jeu("map.csv", this.images.getImage("air").getWidth(), this.images.getImage("air").getHeight(), 10., 10.) ;
			controlMap=new MapControleur(this.paneMap,this.jeu);
			this.controlMap.ajouterEcouteur () ;
			this.initMap() ;
			this.initBoucleJeu();
			this.paneMap.setFocusTraversable(true);
			this.controlTouche = new ControleurTouches(this.borderPanePerso, this.jeu) ;
			this.gameLoop.play();
			this.paneItemsInventaire.toFront();

    	} 
    	
    	catch (HorsDeLaMapException e) {System.out.println(e);}
    	catch (IOException e) {e.printStackTrace();}
		
	}
}	



