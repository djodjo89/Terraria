package controleur;

import modele.* ;
import javafx.scene.input.KeyEvent ;
import javafx.scene.layout.Pane;
import exceptions.VousEtesCoinceException;
import geometrie.Vecteur;
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
	private Vecteur vHaut, vDroite, vBas, vGauche ;

	public ControleurTouches (Pane p, Jeu j) {

		this.j = j ;
		this.p = p ;
		this.vHaut = new Vecteur (0, -this.j.getPerso().getVitesseDeplacement()) ;
		this.vDroite = new Vecteur (this.j.getPerso().getVitesseDeplacement(), 0) ;
		this.vBas = new Vecteur (0, this.j.getPerso().getVitesseDeplacement()) ;
		this.vGauche = new Vecteur (-this.j.getPerso().getVitesseDeplacement(), 0) ;
		this.setKeyListener () ;

	}

	public void setKeyListener () {

		this.p.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE) 
					//espace=true;
					j.getPerso().setVitesse(vHaut);

				if (event.getCode() == KeyCode.D) 
					//j.getPerso().changerVitesse (1, 0) ;
					j.getPerso().setVitesse(vDroite);

				if (event.getCode() == KeyCode.S) 
					//j.getPerso().changerVitesse (0, 1) ;
					j.getPerso().setVitesse(vBas);

				if (event.getCode() == KeyCode.Q) 
					//j.getPerso().changerVitesse (-1, 0) ; 
					j.getPerso().setVitesse(vGauche);

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
