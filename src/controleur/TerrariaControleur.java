
package controleur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
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
					BorderMap.getChildren().remove(BorderMap.getChildren().size() - 1);
					afficherMap ();
					
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}
    
	public void afficherMap () {
		   
	    	int tailleImage=50;
	    	Image perso=new Image(new File("image/perso.png").toURI().toString());
	    	Image terre= new Image(new File("image/terre.png").toURI().toString());
	    	Image air = new Image(new File("image/air.png").toURI().toString());
	    	String nom = new String("test");
	    	ImageView tile=new ImageView();
	    	
	    	int yMap=this.jeu.getMap().getDimY();
	    	int xMap=this.jeu.getMap().getDimX();
	    	
    		String valeur;
    		this.perso=new ImageView(perso);
    		
    		this.panePerso.getChildren().clear();

	    	for(int y=0;y<yMap;y++) {
		    	for(int x=0;x<xMap;x++) {
		    		nom = "case"+ x+","+y;
		    		valeur=this.jeu.getMap().getListeLignes().get(y).get(x);
		    		if(valeur.equals("T"))
		    			tile =new ImageView(terre);
		    		else if(valeur.equals("A"))
		    			tile =new ImageView(air);
		    		tile.setId(nom);
		    		tile.setLayoutX(x*tailleImage);
		    		tile.setLayoutY(y*tailleImage);
		    		panePerso.getChildren().add(tile);
		    	}
		    	
	    	}
	    	tile = this.perso ;
	    	//BorderMap.getChildren().remove(tile);
	    	tile.setId("joueur");
	    	tile.setLayoutX(this.jeu.getPerso().getCoordonnees().getX() * tailleImage);
	    	tile.setLayoutY(this.jeu.getPerso().getCoordonnees().getY() * tailleImage);
	    	BorderMap.getChildren().add(tile);
	    	
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			this.jeu = new Jeu("map.csv") ;
		} catch (IOException e) {			
			e.printStackTrace();			
		}

		this.ajouterEcouteur () ;
		this.afficherMap() ;
		this.initBoucleJeu();
		this.panePerso.setFocusTraversable(true);
		this.ct = new ControleurTouches(this.BorderMap, this.jeu) ;
		this.gameLoop.play();
		
	}

	public void ajouterEcouteur () {
		
		for (ObservableList<String> listeCases : this.jeu.getMap().getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<String> () {

				@Override
				public void onChanged(Change<? extends String> changement) {


					while (changement.next()) {

					}

				}

			}) ;
			
		}
		
	}

}	



