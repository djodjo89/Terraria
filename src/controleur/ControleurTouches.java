package controleur;

import modele.* ;
import javafx.scene.input.KeyEvent ;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import exceptions.VousEtesCoinceException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

public class ControleurTouches extends Parent {

	private Jeu j ;
	private Pane p ;

	public ControleurTouches (Pane p, Jeu j) {

		this.j = j ;
		this.p = p ;
		this.setKeyListener () ;

	}

	public void setKeyListener () {

		this.p.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				try {

					if (event.getCode() == KeyCode.SPACE) j.deplacementPersoPrinc("haut") ;

					if (event.getCode() == KeyCode.D) j.deplacementPersoPrinc("droite") ;

					if (event.getCode() == KeyCode.S) j.deplacementPersoPrinc("bas") ;

					if (event.getCode() == KeyCode.Q) j.deplacementPersoPrinc("gauche") ;

				} catch (VousEtesCoinceException e) {System.out.println(e);} ;

			}

		});

	}

}
