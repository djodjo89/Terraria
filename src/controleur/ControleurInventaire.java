package controleur;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.Inventeriable;
import modele.Jeu;
import modele.Personnage;
import ressources.Images;
import vue.Tuile;

public class ControleurInventaire {

	private Tuile t;
	private Jeu j;
	private Inventeriable objet;
	private Personnage perso;
	private Pane pane;
	private Images img;
	
	public ControleurInventaire(Tuile t, Jeu j, Personnage perso , Inventeriable obj, Pane pane) {
		this.t=t;
		this.j=j;
		this.perso = perso;
		this.objet = obj;
		this.pane=pane;
		this.img= new Images();
		this.setClickListener();
	}
	
	public void setClickListener() {
		this.t.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
			this.perso.donner(this.objet);	
			System.out.println(this.perso.getMain().getTag());
			event.consume();
		});
	}
	
	
	public void setObjet(Inventeriable ob) {
		this.objet=ob;
	}
	
}
