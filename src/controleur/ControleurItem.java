package controleur;

import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.Inventeriable;
import modele.Jeu;
import modele.Personnage;
import ressources.Images;
import vue.Tuile;

public class ControleurItem extends ControleurInventaire{
	private Tuile t;
	private Inventeriable objet;
	
	public ControleurItem(Jeu j, Images img, Pane paneItems, Tuile tuileItem, Personnage perso, Inventeriable obj) {
		super(j,img,paneItems,perso);
		this.t=tuileItem;
		this.objet=obj;
		this.setClickListener();
	}
	
	public void setClickListener() {
		this.t.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
			this.getPerso().donner(this.objet);	
			System.out.println(this.getPerso().getMain().getTag());
			event.consume();
		});
	}
}
