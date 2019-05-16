package controleur;

import modele.* ;
import javafx.scene.input.KeyEvent ;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import exceptions.VousEtesCoinceException;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;


/*
 * Le Controleur de touches gère les cas d'entrées
 * clavier et définit ce qui se passe en fonction
 * de la touche :
 * - "ESPACE" fait monter/sauter
 * - "D" fait aller à droite
 * - "S" fait descendre
 * - "Q" fait aller à gauche
 */

public class ControleurTouches {

	private Jeu j ;
	private Pane p ;
	private boolean espace;
	private ArrayList<String> ToucheAppuyer;
	private String dernierDirection;
	private Tuile perso;

	public ControleurTouches (Pane p, Jeu j,Tuile perso) {

		this.j = j ;
		this.p = p ;
		this.ToucheAppuyer = new ArrayList<String>();
		dernierDirection=new String("droite");
		this.perso=perso;

	}
	
	
	public void gererControleur() {
		this.p.setOnKeyPressed(
				new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code=e.getCode().toString();
					if(!ToucheAppuyer.contains(code))
						ToucheAppuyer.add(code);
				}
				});
		this.p.setOnKeyReleased(
				new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {
					String code=e.getCode().toString();
						ToucheAppuyer.remove(code);
				}
				});
				
	}
	public void setKeyListener () throws VousEtesCoinceException {

					for(String touche: this.ToucheAppuyer) {
						switch(touche) {
						case "Q":
							j.getPerso().deplacementColision("gauche") ;
							if(dernierDirection.equals("droite")) {
								perso.setRotate(180);
							}
							
							dernierDirection="gauche";
							break;
						case "S":
							j.getPerso().deplacementColision("bas") ;
							break;
						case "D":
							j.getPerso().deplacementColision("droite") ;
							if(dernierDirection.equals("gauche")) {
								perso.setRotate(0);
							}
							
							dernierDirection="droite";
							break;
						case "SPACE":
							espace=true;
							break;
						}
						System.out.println(dernierDirection);
					}

	}
	public boolean espaceActive() { 
		return espace;
	}
	public void setEspaceFalse() {
		espace=false;
	}

}
