
package controleur;

import modele.* ;
import fabriques.*;
import application.*;
import objetRessources.BlocBiomasse;
import objetRessources.Terre;
import ressources.Images;
import vue.InventaireVue;
import vue.Tuile;
import exceptions.HorsDeLaMapException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	@FXML
	private Pane paneIteration;

	private ControleurInventaire controlInvent;

	private ArrayList<Tuile> listItemsInvent;
	private ImageView ennemi;
	private InventaireVue inv;

	
	public void initBoucleJeu() {
		
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
						this.jeu.evoluer(this.controleurTouches) ;

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
		String valeur ;

		Tuile tile ;
		
		this.paneMap.getChildren().clear();

		for (int y = 0 ; y < yMap ; y++) {
			
			for (int x = 0 ; x < xMap ; x++) {
				
				nom = x + ":" + y ;
				valeur = NomClasse.retrouver(this.jeu.getTerrain().getListeLignes().get(y).get(x)) ;

				tile= new Tuile(nom, x * this.jeu.getMoteur().getTailleBoiteX(), y * this.jeu.getMoteur().getTailleBoiteY(), this.images.getImage(valeur)) ;
				this.paneMap.getChildren().add(tile) ;

			}

		}
		
		this.perso= new Tuile(nom,0,0,this.images.getImage(NomClasse.retrouver(new PersonnagePrincipal()))) ;
		this.panePerso.getChildren().add(this.perso) ;


		this.panePerso.toFront();
		this.panePerso.setFocusTraversable(true);
		this.paneInventaire.toFront();
		
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

		BlocBiomasse t = new BlocBiomasse() ;
		System.out.println(NomClasse.retrouver(t));

		try {
		
			images=FabriqueImages.initialiserImages();
			jeu=FabriqueJeu.initialiserJeu(this.jeu, this.images) ;
			FabriquePanes.initPanes(this.paneMap, this.paneInventaire) ;
			this.initMap() ;
			this.initPositionPerso() ;
			this.initEnnemi();
			
			this.inv=FabriqueVue.initialiserUnInventaireVue(paneInventaire, paneItemsInventaire, paneIteration, this.jeu, this.images);
			controlInvent=FabriqueControleurs.initialiserControleurInventaire(this.jeu, this.images, inv);
			controleurSouris=FabriqueControleurs.initialiserControleurSouris(this.paneMap,this.jeu);
			controleurMap=FabriqueControleurs.initialiserControleursMap(this.jeu, this.paneMap,this.images);
			controleurTouches=FabriqueControleurs.initialiserControleurTouches(this.panePrincipal, this.jeu, this.perso,this.paneMap,this.paneInventaire, this.inv);

		
			this.initBoucleJeu();
			paneMap.setFocusTraversable(true);
			paneItemsInventaire.toFront();
			paneIteration.toFront();
		
		} 

		catch (HorsDeLaMapException e) {System.out.println(e);}
		catch (IOException e) {e.printStackTrace();}

	}
	
	/**
	 * Place le personnage sur la map
	 * 
	 * @see Jeu#getPerso()
	 * @see PersonnagePrincipal#getXProperty()
	 * @see PersonnagePrincipal#getYProperty()
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
	public void initEnnemi() {
		Tuile ennemi;
		for(Personnage ennemiJeu: jeu.getEnnemi()) {
			if(!ennemiJeu.getNom().equals("Wall-E")) {
				ennemi= new Tuile(ennemiJeu.getClass().getName(),0,0,this.images.getImage("ennemi")) ;
				paneMap.getChildren().add(ennemi);
				ennemi.setFocusTraversable(false);
				ennemi.toFront();
				ennemi.translateXProperty().bind(ennemiJeu.getXProperty());
				ennemi.translateYProperty().bind(ennemiJeu.getYProperty());
			}
		}
		
	}
	
}	



