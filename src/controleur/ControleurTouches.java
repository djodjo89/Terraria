package controleur;

import modele.* ;
import javafx.scene.input.KeyEvent ;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

/*
 * Le Controleur de touches gère les cas d'entrées
 * clavier et définit ce qui se passe en fonction
 * de la touche :
 * - "ESPACE" fait sauter
 * - "D" fait aller à droite
 * - "S" fait descendre
 * - "Q" fait aller à gauche
 */

public class ControleurTouches {

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
				
				if (event.getCode() == KeyCode.SPACE)
					
					j.getPerso().deplacerVers("haut", j.getMoteur());
				
				if (event.getCode() == KeyCode.D)
					
					j.getPerso().deplacerVers("droite", j.getMoteur());
				
				if (event.getCode() == KeyCode.S)
					
					j.getPerso().deplacerVers("bas", j.getMoteur());
				
				if (event.getCode() == KeyCode.Q)
					
					j.getPerso().deplacerVers("gauche", j.getMoteur());
				
				/*if (event.getCode() == KeyCode.SPACE)
					
					touchesPressees.add("E") ;
				
				if (event.getCode() == KeyCode.D)
					
					touchesPressees.add("D") ;
				
				if (event.getCode() == KeyCode.S)
					
					touchesPressees.add("S") ;
				
				if (event.getCode() == KeyCode.Q)
					
					touchesPressees.add("Q") ;
				
				if (touchesPressees.contains("E") && touchesPressees.contains("D"))
					
					j.getPerso().deplacerVers("hautdroite", j.getMoteur()) ;
				
				else if (touchesPressees.contains("E") && touchesPressees.contains("Q"))
					
					j.getPerso().deplacerVers("hautgauche", j.getMoteur()) ;
				
				else if (touchesPressees.contains("E"))
					
					j.getPerso().deplacerVers("haut", j.getMoteur());
				
				else if (touchesPressees.contains("D"))
					
					j.getPerso().deplacerVers("droite", j.getMoteur()) ;
				
				else if (touchesPressees.contains("Q"))
					
					j.getPerso().deplacerVers("gauche", j.getMoteur()) ;
			
				else if (touchesPressees.contains("S"))
					
					j.getPerso().deplacerVers("bas", j.getMoteur()) ;*/

			}

		});

	}

}
