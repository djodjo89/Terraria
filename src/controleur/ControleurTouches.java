package controleur;

import modele.* ;
import javafx.scene.input.KeyEvent ;
import javafx.scene.layout.Pane;
import exceptions.VousEtesCoinceException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

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

	public ControleurTouches (Pane p, Jeu j) {

		this.j = j ;
		this.p = p ;
		this.setKeyListener () ;

	}

	public void setKeyListener () {

		this.p.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE) 
					espace=true;

				if (event.getCode() == KeyCode.D) 
					j.getPerso().changerVitesse (1, 0) ;

				if (event.getCode() == KeyCode.S) 
					j.getPerso().changerVitesse (0, 1) ;

				if (event.getCode() == KeyCode.Q) 
					j.getPerso().changerVitesse (-1, 0) ; ;

			}

		});

	}
	public boolean espaceActive() { 
		return espace;
	}
	public void setEspaceFalse() {
		espace=false;
	}

}
