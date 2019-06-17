package controleur;

import modele.* ;

import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

import geometrie.Point;
import javafx.event.EventHandler;

/**
 * <h1>ControleurSouris est la classe chargé de transmettre
 * au modèle les clics de souris dans la vue</h1>
 * 
 * <p>Un ControleurSouris possède :
 * 	<ul>
 * 		<li>Un Jeu permettant d'accéder au terrain.</li>
 * 		<li>Un Pane chargé de transmettre les clics.</li>
 * 	</ul>
 * 	Chaque fois que le joueur cliquera sur un Inventeriable
 * 	visible à l'écran le modèle sera modifié. * 
 * </p>
 * 
 * @see Jeu
 * @see Terrain
 * 
 * @author Ludovic
 * @version 1.0
 *
 */

public class ControleurSouris extends Parent {
	
	/**
	 * Le Jeu du contrôleur
	 * 
	 * <p>Il fournit le terrain modifié par le contrôleur</p>
	 * 
	 * @see ControleurMap#ControleurMap(Pane, Jeu)
	 * 
	 */
	
	private Jeu jeu ;
	
	/**
	 * Le Pane du contrôleur.
	 * 
	 * <p>C'est lui que avertira le contrôleur en cas
	 * de clic</p>
	 * 
	 * @see ControleurMap#ControleurMap(Pane, Jeu)
	 * 
	 */
	
	private Pane pane ;
	
	/**
	 * Ajoute un EventHandler sur le pane
	 * 
	 * @see Jeu#getTerrain()
	 * @see Terrain#getListeLignes()
	 * 
	 * @since 1.0
	 */
	


	public ControleurSouris (Pane p, Jeu j) {

		this.jeu = j ;
		this.pane = p ;

		this.setMouseListener () ;

	}

	public void setMouseListener () {

		this.pane.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				WhatIDoWhithThisBlockPointDInterrogation(event.getX(),event.getY());

			}

		});

	}
	public void WhatIDoWhithThisBlockPointDInterrogation(double x, double y) {
		int blockX = (int) (x/50);
		int blockY = (int) (y/50);

		int i = 0;
		Personnage persoClic = null;
		Point zoneClic = new Point(x, y);
		while (i < this.jeu.getEnnemi().size() && persoClic == null) {
			if (this.jeu.getEnnemi().get(i).getX()<x && this.jeu.getEnnemi().get(i).getX()+50>x && this.jeu.getEnnemi().get(i).getY()<y && this.jeu.getEnnemi().get(i).getY() + 50 > y) {
				persoClic = this.jeu.getEnnemi().get(i);
				}
			i++;
		}
		
		if (persoClic != null) {
			persoClic.perdrePV(20);
			System.out.println("ennemie pv: " + persoClic.getPV());
			if (persoClic.tMort()) {
				System.exit(0);
			}
		}
		else {
			this.jeu.getTerrain().setPositionBlockY(blockY);
			this.jeu.getPerso().getMain().Utilisation(blockX,blockY,jeu);
		}

	}
}
	
	
