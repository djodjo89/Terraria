package vue;

import java.util.ArrayList;
import modele.*;
import ressources.Images;
import javafx.scene.layout.*;

public class PVVue {
	
	private Pane panePV ;
	private Jeu jeu ;
	private Images images ;
	private ArrayList<Tuile> listeCoeurs ;
	
	public PVVue (Pane panePV, Jeu jeu, Images images) {
		
		this.panePV = panePV ;
		this.jeu = jeu ;
		this.images = images ;
		
	}
	
	public void ajouterCoeur () {
		
		Tuile tuileCoeur ;
		
		tuileCoeur = new Tuile ("heart", 0, 0, this.images.getImage("heart")) ;
		panePV.getChildren().add(tuileCoeur) ;
		
	}

}
