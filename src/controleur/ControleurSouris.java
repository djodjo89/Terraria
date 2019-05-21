package controleur;

import modele.* ;
import ressources.Images;
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

		this.p.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				destructionBlock(event.getSceneX(),event.getSceneY());
				//System.out.println(event.getSceneX());
		        //System.out.println(event.getSceneY());
	//			try {

					

			//	} catch (VousEtesCoinceException e) {System.out.println(e);} ;

			}

		});

	}
	public void destructionBlock(double x, double y) {
		int blockX = (int) (x/50);
		int blockY = (int) (y/50);
		this.j.getMap().destructionTerrain(blockX, blockY);
		
	}
	
	
}
