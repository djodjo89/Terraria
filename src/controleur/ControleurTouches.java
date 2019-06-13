package controleur;

import modele.* ;
import vue.InventaireVue;
import vue.Menu;
//import modele.Scrolling;
import vue.Scrolling;
import vue.Tuile;
import javafx.scene.input.KeyEvent ;
import javafx.scene.layout.Pane;

import java.net.URISyntaxException;
import java.util.ArrayList;

import exceptions.VousEtesCoinceException;
import geometrie.Vecteur;
import javafx.event.EventHandler;


/*
 * Le Controleur de touches gère les cas d'entrées
 * clavier et définit ce qui se passe en fonction
 * de la touche :
 * - "ESPACE" fait sauter
 * - "D" fait aller à droite
 * - "S" fait descendre
 * - "Q" fait aller à gauche
 */

/**
 * <h1>ControleurTouches est la classe chargée d'enregister les touches
 * pressées par le joueur et de modifier le modèle en conséquence</h1>
 * 
 * <p>Un ControleurTouches possède :
 * 	<ul>
 * 		<li>Un booléen espace permettant de déclencher un saut</li>
 * 		<li>Une String derniereDirection permettant de connaître la dernière direction du personnage</li>
 * 		<li>Un Jeu permettant de faire bouger le personnage dans le modèle</li>
 * 		<li>Une ArrayList<String> permettant de connaître les touches pressées lors d'une frame</li>
 * 		<li>Un Pane chargé de transmettre les touches pressées et relâchées</li>
 * 		<li>Une Tuile à faire pivoter sur elle-même lors d'un chagement de direction</li>
 * 	<ul>
 * 	Lorsque le joueur appuie sur plusieurs touches, le contrôleur le prend en compte et les active les unes
 * 	à la suite des autres.
 * <p>
 * 
 * @see Jeu
 * @see Tuile
 * 
 * @author Mathys
 * @version 2.0
 */

public class ControleurTouches {
	
	/**
	 * La dernière direction
	 * 
	 * <p>Donne la dernière direction dans laquelle est allé le personnage</p>
	 * 
	 * @see ControleurTouches#gererControleur()
	 * @see ControleurTouches#setKeyListener()
	 */
	
	private String derniereDirection;
	
	/**
	 * La Jeu du contrôleur
	 * 
	 * <p>Permet de déplacer le personnage du jeu lors de l'appui sur une touche</p>
	 * 
	 * @see ControleurTouches#ControleurTouches(Pane, Jeu, Tuile)
	 * @see Jeu#getPerso()
	 */
	
	private Jeu jeu ;
	
	/**
	 * La liste des touches pressées par le joueur
	 * 
	 * <p>Permet de déplacer le personnage du jeu lors de l'appui sur une touche</p>
	 * 
	 * @see Jeu#getPerso()
	 */
	
	private ArrayList<String> ToucheAppuyer;
	
	/**
	 * Le Pane principal
	 * 
	 * <p>Permet de connaître les touches pressées par le joueur</p>
	 * 
	 * @see ControleurTouches#setKeyListener()
	 */
	
	private Pane pane ;
	
	/**
	 * La Tuile du personnage
	 * 
	 * <p>Tourne sur son axe y en cas de changement de direction</p>
	 * 
	 * @see ControleurTouches#setKeyListener()
	 */
	
	private Tuile perso;

	private Scrolling scroll;
	
	private Menu menu;
	
	private int nbE=0;
	
	private InventaireVue invVue;

	public ControleurTouches (Pane pane, Jeu jeu,Tuile perso, Pane paneMap,Pane paneInventaire, InventaireVue vueInvent) {
		this.scroll=new Scrolling(pane,paneMap,paneInventaire);
		this.jeu = jeu ;
		this.pane = pane ;
		this.ToucheAppuyer = new ArrayList<String>();
		derniereDirection=new String("droite");
		this.perso=perso;
		menu=new Menu(pane);
		this.jeu.getPerso().getXProperty().addListener((x)->{scroll.faireScroll(this.jeu.getPerso());});
		this.jeu.getPerso().getYProperty().addListener((y)->{scroll.faireScroll(this.jeu.getPerso());});
		this.invVue=vueInvent;

	}

	public void gererControleur() {
		this.pane.setOnKeyPressed(
				new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				String code=event.getCode().toString();
				if(!ToucheAppuyer.contains(code) && code!="ESCAPE"&& code!="E" && !menu.estAffiche())
					ToucheAppuyer.add(code);
			}
			
		});
		
		this.pane.setOnKeyReleased(
				new EventHandler<KeyEvent>() {
					
			@Override
			public void handle(KeyEvent event) {
				jeu.getPerso().ajouter(new Vecteur(-jeu.getPerso().getVecteurVitesse().getX(),0));
				String code = event.getCode().toString() ;
				ToucheAppuyer.remove(code) ;
				if (code =="ESCAPE" || code == "E")
					ToucheAppuyer.add(code) ;
				
			}
					
					
		}) ;
				
	}
	
	public void setKeyListener () throws VousEtesCoinceException, URISyntaxException {
		
		for(String touche : this.ToucheAppuyer) {

			switch(touche) {

				case "Q":

					jeu.getPerso().deplacerVers("gauche", jeu.getMoteur());
					if(derniereDirection.equals("droite")) {

						perso.setRotate(180);

					}

					derniereDirection="gauche";						
				break;

				case "S":
					jeu.getPerso().deplacerVers("bas", jeu.getMoteur());
				break;

				case "D":

					jeu.getPerso().deplacerVers("droite", jeu.getMoteur());
					
					if(derniereDirection.equals("gauche")) {
						perso.setRotate(0);
					}

					derniereDirection="droite";
				break;
				
				case "SPACE" :
					
					jeu.getPerso().deplacerVers("haut", jeu.getMoteur());
					
				break ;

				case "E":
					if(nbE%2==0) {
						this.invVue.derouleInventaire();
						this.nbE++;
					}
					else {
						this.invVue.reduitInventaire();
						this.nbE++;
					}		
				break;
				
				case "ESCAPE":
					if(!menu.estAffiche()) {
						this.ToucheAppuyer.removeAll(ToucheAppuyer);
						menu.afficheMenu(scroll.getX(),scroll.getY());}
						
					else 
						menu.disparait();
					break;

			}

		}
		this.ToucheAppuyer.remove("ESCAPE");
		this.ToucheAppuyer.remove("E");

	}
		
	public Menu getMenu() {
		return this.menu;
	}

}
