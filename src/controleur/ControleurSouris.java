package controleur;

import modele.* ;
import javafx.scene.input.KeyEvent ;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import exceptions.VousEtesCoinceException;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;

public class ControleurSouris extends Parent {
	private Jeu j ;
	private Pane p ;
	
	public ControleurSouris (Pane p, Jeu j) {

		this.j = j ;
		this.p = p ;
		this.setMouseListener () ;

	}

	public void setMouseListener () {

		this.p.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("yeah!");
				System.out.println(event.getScreenX());
		        System.out.println(event.getScreenY());
	//			try {

					

			//	} catch (VousEtesCoinceException e) {System.out.println(e);} ;

			}

		});

	}
	
	
}
