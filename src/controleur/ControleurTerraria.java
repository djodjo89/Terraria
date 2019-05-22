
package controleur;

import modele.* ;
import ressources.Images;
import vue.Tuile;
import exceptions.HorsDeLaMapException;
import fabriques.FabriqueControleurs;
import fabriques.FabriqueImages;
import fabriques.FabriqueJeu;
import fabriques.FabriquePanes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

 /**
 * <h1>ControleurTerraria génère la map à l'écran, crée un Jeu
 * et les lie à l'aide de contrôleurs.</h1>
 * 
 * <p>
 * 	Des objets du modèle :
 * 	<ul>
 * 		<li>Un int pour compter le nombre de tours.</li>
 * 		<li>Des Images pour créer les tuiles à utiliser.</li>
 *		<li>Un Jeu pour actualiser le modèle</li> 		
 * 		<li>Une Timeline pour lancer la boucle de jeu</li>
 * 	</ul>
 * 	Des contrôleurs :
 * 	<ul>
 * 		<li>Un ControleurMap pour modifier la vue en fonction du terrain</li>
 * 		<li>Un ControleurSouris pour modifier le terrain en fonction des clics du joueur</li>
 *  	<li>Un ControleurTouches pour actualiser le modèle</li>
 *  </ul>
 *  Des panes et une tuile :
 *  <ul>
 *   	<li>Un Pane pour le personnage.</li>
 *   	<li>Un Pane pour l'inventaire.</li>
 *   	<li>Un Pane pour les objets de l'inventaire.</li>
 *   	<li>Un Pane pour la map.</li>
 *   	<li>Une Tuile pour afficher le personnage.</li>
 *  </ul>
 *  Les données les plus importantes du jeu sont initialisées dans la méthode initialize.
 * </p>
 * 
 * @see Images
 * @see Jeu
 * @see ControleurMap
 * @see ControleurSouris
 * @see ControleurTouches
 * 
 * @author Mathys
 * @author Romain
 * @author Karen
 * @version 3.0
 *
 */

public class ControleurTerraria implements Initializable {
	
	/**
	 * Le nombre de tours du jeu
	 * 
	 * <p>Il sert notamment à compter le temps écoulé depuis
	 * le début d'un saut</p>
	 * 
	 * @see Personnage#sauter(Terrain, physique.Moteur)
	 */
	
    private int nbTour;
    
    /**
     * La banque d'images
     * 
     * <p>Elle permet d'accéder facilement aux images des tuiles
     * à intégrer ou modifier en jeu</p>
     * 
     * @see Images#ajouterImage(String, Image)
     * @see Images#getImage(String)
     */
    
	private Images images ;	
	
	/**
	 * Le jeu principal
	 * 
	 * <p>Il permet d'accéder au modèle par une unique porte d'entrée</p>
	 * 
	 * @see Jeu#evoluer(int, ControleurTouches)
	 * @see Jeu#getMoteur()
	 * @see Jeu#getPerso()
	 * @see Jeu#getTerrain()
	 */
    
	private Jeu jeu ;
	
	/**
	 * La boucle de jeu
	 * 
	 * <p>Elle se charge de faire évoluer le jeu à chaque frame</p>
	 */
	
	private Timeline gameLoop;
	
	/**
	 * Le contrôleur de la map
	 * 
	 * <p>Il transmet les changements du terrain à la vue</p>
	 * 
	 * @see ControleurMap#ajouterEcouteur()
	 */
	
    private ControleurMap controleurMap ;
    
    /**
     * Le contrôleur de la souris
     * 
     * <p>Il transmet les clics de la souris au modèle</p>
     */
    
    private ControleurSouris controleurSouris ;
    
    /**
     * Le contrôleur de touches
     * 
     * <p>Il transmet au modèle la direction dans laquelle le 
     * personnage doit se déplacer</p>
     * 
     * @see ControleurTouches#gererControleur()
     * @see ControleurTouches#setKeyListener()
     * @see ControleurTouches#espaceActive()
     * @see ControleurTouches#setEspaceFalse()
     */
    
	private ControleurTouches controleurTouches ;
	
	/**
	 * Le pane du personnage
	 * 
	 * <p>Il permet d'afficher d'afficher le personnage et de
	 * reçevoir les clics et appuis de touches du joueur</p>
	 */

    @FXML
    private Pane panePerso;

    /**
     * Le pane de l'inventaire
     */
    
    @FXML
    private Pane paneInventaire;

    /**
     * Le pane des objets de l'inventaire
     */
    
    @FXML
    private Pane paneItemsInventaire;

    /**
     * Le pane principal
     * 
     * <p>Il contient tous les autres panes</p>
     */
    
    @FXML
    private Pane panePrincipal;

    /**
     * Le pane de la map
     * 
     * <p>Il affiche le terrain</p>
     */
    
    @FXML
    private Pane paneMap;
	
    /**
     * La tuile du personnage
     * 
     * <p>Elle permet de déplacer le personnage dans la vue</p>
     */
    
	@FXML
	private Tuile perso;

	/**
	 * Lance la boucle de jeu
	 * 
	 * @see ControleurTouches#setKeyListener()
	 * @see Jeu#evoluer(int, ControleurTouches)
	 * 
	 * @author Romain
	 * @since 1.1
	 */
	
	private Personnage personnage;
	private ControleurInventaire controlInvent;
	
	public void initBoucleJeu() {
		
		this.nbTour = 0 ;
		this.gameLoop = new Timeline() ;
		this.gameLoop.setCycleCount(Timeline.INDEFINITE) ;


		KeyFrame kf = new KeyFrame(
				// on définit le FPS (nbre de frame par seconde)
				Duration.seconds(0.017), 
				// on définit ce qui se passe à chaque frame 
				// c'est un eventHandler d'ou le lambda
				(ev ->{
					
					try {
						
						this.controleurTouches.setKeyListener() ;
						this.nbTour = this.jeu.evoluer(this.nbTour, this.controleurTouches) ;

					} catch (Exception e) {
						
						e.printStackTrace() ;
						
					}

				})
				
				);
		
		this.gameLoop.getKeyFrames().add(kf);
		this.gameLoop.play();
		
	}
	
	/**
	 * Met en place tous les éléments graphiques du jeu
	 * 
	 * @see Images#getImage(String)
	 * @see Jeu#getMoteur()
	 * @see Jeu#getTerrain()
	 * @see Terrain#getListeLignes()
	 * @see Tuile#Tuile(String, double, double, Image)
	 * 
	 * @author Mathys
	 * @author Romain
	 * @author Karen
	 */

	public void initMap () {

		int yMap = this.jeu.getTerrain().getDimY() ;
		int xMap = this.jeu.getTerrain().getDimX() ;

		String nom = new String("test") ;
		String typeBloc ;
		String valeur ;

		Tuile tile ;
		Tuile tileItem ;
		
		this.paneMap.getChildren().clear();

		for (int y = 0 ; y < yMap ; y++) {
			
			for (int x = 0 ; x < xMap ; x++) {
				
				nom = x + ":" + y ;
				valeur = this.jeu.getTerrain().getListeLignes().get(y).get(x).getTag();

				switch(valeur) {
				
					case "T":
						typeBloc="terre" ;
					break;
					
					case "A":
						typeBloc="air" ;
					break;
					
					default:
						typeBloc="air" ;
					break;
					
				}

				tile= new Tuile(nom, x * this.jeu.getMoteur().getTailleTileX(), y * this.jeu.getMoteur().getTailleTileY(), this.images.getImage(typeBloc)) ;
				this.paneMap.getChildren().add(tile) ;

			}

		}
		
		this.perso= new Tuile(nom,0,0,this.images.getImage("perso")) ;
		this.panePerso.getChildren().add(this.perso) ;
		this.panePerso.toFront();
		this.panePerso.setFocusTraversable(true);
		this.paneInventaire.toFront();
		
	
		Foreuse foreuse = new Foreuse("forreuse");
		this.personnage.getInventaire().ajouterObjet(foreuse);
		this.personnage.getInventaire().ajouterObjet(foreuse);
		this.personnage.getInventaire().ajouterObjet(foreuse);
		this.personnage.getInventaire().ajouterObjet(foreuse);
		
		

    	Tuile tileItem1 = new Tuile();
    	for(int i=0; i<10; i++) {
    		nom = "" +i;
    		tile= new Tuile(nom, (i*jeu.getMoteur().getTailleTileX()),0,this.images.getImage("fondInventaire"));
    		this.paneInventaire.getChildren().add(tile);
    		
    		if(personnage.getInventaire().getListObjet().get(i) != null) {
    			for(int z=0; z<personnage.getInventaire().getQuantiteObjets().get(i); z++) {
		    		tileItem1 = new Tuile(nom,((i+z)*jeu.getMoteur().getTailleTileX())+10,15,this.images.getImage(personnage.getInventaire().getListObjet().get(i).getTag()));
		    		this.paneItemsInventaire.getChildren().add(tileItem1);
		    		this.controlInvent = new ControleurInventaire(tileItem1, jeu, this.personnage, personnage.getInventaire().getListObjet().get(i));
    			}
    		}
    	}
	}

	/**
	 * Initialise tous les composants nécessaires au lancement du jeu
	 * 
	 * <p>Il commmence par créer la banque d'images, puis il crée un
	 * Jeu, des Controleurs, initialise les Pane, la map, la tuile
	 * du personnage et finit par lancer la boucle de jeu</p>
	 * 
	 * @see FabriqueImages#initialiserImages(Images)
	 * @see FabriqueJeu#initialiserJeu(Jeu, Images)
	 * @see FabriqueControleurs#initialiserControleurs(Jeu, Pane, Pane, Tuile, ControleurMap, ControleurSouris, ControleurTouches)
	 * @see FabriquePanes#initPanes(Pane, Pane)
	 * 
	 * @author Mathys
	 * @author Romain
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		

		try {
			this.personnage = new Personnage();
			images=FabriqueImages.initialiserImages();
			jeu=FabriqueJeu.initialiserJeu(this.jeu, this.images) ;
			FabriquePanes.initPanes(this.paneMap, this.paneInventaire) ;
			this.initMap() ;
			this.initPositionPerso() ;
			controleurMap=FabriqueControleurs.initialiserControleursMap(this.jeu, this.paneMap);
			controleurTouches=FabriqueControleurs.initialiserControleurTouches(this.panePrincipal, this.jeu, this.perso,this.paneMap);
			this.initBoucleJeu();
			paneMap.setFocusTraversable(true);
			paneItemsInventaire.toFront();
		
		} 

		catch (HorsDeLaMapException e) {System.out.println(e);}
		catch (IOException e) {e.printStackTrace();}

	}
	
	/**
	 * Place le personnage sur la map
	 * 
	 * @see Jeu#getPerso()
	 * @see Personnage#getXProperty()
	 * @see Personnage#getYProperty()
	 * 
	 * @author Mathys
	 * @author Romain
	 * 
	 */
	
	public void initPositionPerso () {
		
		this.perso.translateXProperty().bind(jeu.getPerso().getXProperty());
		this.perso.translateYProperty().bind(jeu.getPerso().getYProperty());
		this.perso.setRotationAxis(new Point3D(0,1,0));
		
	}
	

}	



