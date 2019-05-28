package controleur;

import modele.* ;
import vue.Menu;
import vue.Tuile;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent ;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import exceptions.VousEtesCoinceException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;


/*
 * Le Controleur de touches gère les cas d'entrées
 * clavier et définit ce qui se passe en fonction
 * de la touche :
 * - "ESPACE" fait monter/sauter
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
	 * La touche espace
	 * 
	 * <p>Dit si la touche espace a été pressée. Si c'est le case, espace est à mis à true</p>
	 * 
	 * @see ControleurTouches#espaceActive()
	 * @see ControleurTouches#setEspaceFalse()
	 */

	private boolean espace;
	
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
	
	private ControleurTerraria controlIvent;

	public ControleurTouches (Pane pane, Jeu jeu,Tuile perso, Pane paneMap,Pane paneInventaire, ControleurTerraria controlInvent) {
		this.scroll=new Scrolling(pane,paneMap,paneInventaire);
		this.jeu = jeu ;
		this.pane = pane ;
		this.ToucheAppuyer = new ArrayList<String>();
		derniereDirection=new String("droite");
		this.perso=perso;
		menu=new Menu(pane);
		this.controlIvent=controlInvent;
	}
	
	
	public ControleurTouches() {
		// TODO Auto-generated constructor stub
	}


	public void gererControleur() {
		this.pane.setOnKeyPressed(
				new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code=e.getCode().toString();

					if(!ToucheAppuyer.contains(code) && code!="ESCAPE"&& code!="E" && !menu.estAffiche())
						ToucheAppuyer.add(code);
				}
				});
		this.pane.setOnKeyReleased(
				new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code=e.getCode().toString();
						ToucheAppuyer.remove(code);
						if(code=="ESCAPE"||code=="E")
							ToucheAppuyer.add(code);
				}
				});
				
	}
	
	public void setKeyListener () throws VousEtesCoinceException, URISyntaxException {

		for(String touche : this.ToucheAppuyer) {

			switch(touche) {

				case "Q":		
					if(jeu.getPerso().jePeuxMeDeplacerLa("gauche"))
						scroll.faireScroll("Q", jeu.getPerso());
					jeu.getPerso().deplacementColision("gauche");
					if(derniereDirection.equals("droite")) {

						perso.setRotate(180);

					}

					derniereDirection="gauche";						
				break;

				case "S":
					jeu.getPerso().deplacementColision("bas") ;
				break;

				case "D":
					if(jeu.getPerso().jePeuxMeDeplacerLa("droite"))
						scroll.faireScroll("D",jeu.getPerso());
					jeu.getPerso().deplacementColision("droite") ;
					
					if(derniereDirection.equals("gauche")) {
						perso.setRotate(0);
					}

					derniereDirection="droite";
				break;
				

				case "E":
					if(nbE%2==0) {
						this.controlIvent.derouleInventaire();
						this.nbE++;
					}
					else {
						this.controlIvent.reduitInventaire();
						this.nbE++;
					}		
				break;

				case "SPACE":
					espace=true;
				break;
				
				case "ESCAPE":
					if(!menu.estAffiche())
						menu.afficheMenu(scroll.getX());
						
					else
						menu.disparait();
					break;

			}

		}
		this.ToucheAppuyer.remove("ESCAPE");
		this.ToucheAppuyer.remove("E");

	}
	public boolean espaceActive() { 
		return espace;
	}
	public void setEspaceFalse() {
		espace=false;
	}

}
