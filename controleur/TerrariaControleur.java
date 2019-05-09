
package controleur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D ;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.control.Button;
import modele.Personnage;
import modele.Terrain;
import modele.TraducteurFichier;
import modele.Personnage;

public class TerrariaControleur implements Initializable {
	
	private Terrain t ;
	private Point2D p ;
	final static private String[] DIRECTIONS = {"haut", "droite", "bas", "gauche"} ;
	private int direction ;
	private Personnage joueur;
	
	private Timeline gameLoop;
	
	@FXML
    private Pane panePerso;
	
	 @FXML
	 private ImageView perso;
	
	private TraducteurFichier tf ;
	
    @FXML
    private TextArea map;
    
    @FXML
    private Button boutonDroite ;
    
    @FXML
    private Button boutonGauche ;
    
    @FXML
    private Button boutonHaut ;
    
    @FXML
    private Button boutonBas ;
    
    @FXML
	private BorderPane BorderMap;
    
	public void initDeplacement() {
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		

		KeyFrame kf = new KeyFrame(
				// on définit le FPS (nbre de frame par seconde)
				Duration.seconds(0.017), 
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda
				(ev ->{
					joueur.deplace();
				})
				);
		gameLoop.getKeyFrames().add(kf);
	}

	    
	   public void afficherMap () {
		   
	    	 Terrain map;
	    	int tailleImage=50;
	    	try {
				TraducteurFichier mapFichier=new TraducteurFichier("map.csv");
		    	map=new Terrain(mapFichier.getTabMap());

			} catch (IOException e) {
				map=new Terrain();
				e.printStackTrace();
			}
	    	Image perso=new Image("image/perso.png");
	    	Image terre= new Image("image/terre.png");
	    	Image air = new Image("image/air.png");
	    	String nom = new String("test");
	    	ImageView tile=new ImageView();
	    	
	    	
	    	
	    	int yMap=map.getDimY();
	    	int xMap=map.getDimX();
	    	
    		String valeur;
    		this.perso=new ImageView(perso);
    		

	    	for(int y=0;y<yMap;y++) {
		    	for(int x=0;x<xMap;x++) {
		    		nom = "case"+ x+","+y;
		    		valeur=map.getListeLignes().get(y).get(x);
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
	    	BorderMap.getChildren().add(this.perso);
	    	
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.joueur=new Personnage(0,0);
		
		
		this.p = new Point2D(5, 5) ;
		
		try {
			this.tf = new TraducteurFichier("map.csv") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("coucou");
		}
		
		this.t = new Terrain (this.tf.getTabMap()) ;
		
		this.ajouterEcouteur () ;
		
		this.t.getListeLignes().get((int)p.getX()).set((int)p.getY(), "P") ;
		
		this.afficherMap();
		initDeplacement();
		gameLoop.play();
		this.perso.translateXProperty().bind(joueur.xProperty());
		this.perso.translateYProperty().bind(joueur.yProperty());
		
	}
	
	public void allerEnHaut (ActionEvent event) {
		
		this.direction = 0 ;
		this.seDeplacer();
		
	}
	
	public void allerADroite (ActionEvent event) {
		
		this.direction = 1 ;
		this.seDeplacer();
		
	}
	
	public void allerEnBas (ActionEvent event) {
		
		this.direction = 2 ;
		this.seDeplacer();
		
	}
	
	public void allerAGauche (ActionEvent event) {
		
		this.direction = 3 ;
		this.seDeplacer();
		
	}

	
	private void seDeplacer () { // 0 : haut, 1 : droite, 2 : bas, 3 : gauche
		
		this.t.getListeLignes().get((int)p.getX()).set((int)p.getY(), "T") ;
		System.out.println(p.getX() + p.getY());
		System.out.println(this.direction);
		
		switch (this.direction) { 
		
			case 0 : 
				
				if (this.p.add(0., 1.).getX() >= 0) {
					
					this.p = this.p.add(-1., 0.) ; 
					
				}
				
			break ;
			
			case 1 : 
				
				if (this.p.add(0., 1.).getY() < this.t.getDimY()) {
					
					this.p = this.p.add(0., 1.) ; 
					
				}
				
			break ;
			
			case 2 : 
				
				if (this.p.add(1., 0.).getX() < this.t.getDimX()) {
				
					this.p = this.p.add(1., 0.) ; 
					
				}
				
			break ;
			
			case 3 : 
				
				if (this.p.add(0., -1.).getY() >= 0) {
					
					this.p = this.p.add(0., -1.) ; 
					
				}
				
			break ;
			
		}
		
		this.t.getListeLignes().get((int)p.getX()).set((int)p.getY(), "P") ;
		System.out.println(p.getX() + p.getY());
		this.afficherMap();
		
	}
	
	public void ajouterEcouteur () {
		
		for (ObservableList<String> listeCases : this.t.getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<String> () {

				@Override
				public void onChanged(Change<? extends String> changement) {


					while (changement.next()) {

						if (changement.wasReplaced()) {

							if (listeCases.get((int)p.getY()) == "P") {

								System.out.print("Déplacement ");

								switch (direction) {

								case 1 :
								case 3 :

									System.out.print("à ");

									break ;

								case 0 :
								case 2 :

									System.out.print("en ");

									break ;

								}

								System.out.println(DIRECTIONS[direction]);

							}

						}

					}

				}

			}) ;
			
		}
		
	}

}	



