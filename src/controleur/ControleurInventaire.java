package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.tk.Toolkit;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.*;
import objetRessources.Inventeriable;
import vue.*;
import vue.Tuile;
import ressources.Images;

public class ControleurInventaire {

	private Jeu j;
	private Images image;
	private ObservableList<Tuple> listeObjets ;
	private InventaireVue invVue;
	

	public ControleurInventaire(Jeu j, Images img, InventaireVue inv) {
		this.j=j;
		this.image=img;
		this.listeObjets=this.j.getPerso().getInventaire().getInventaire();
		this.invVue=inv;
	}
	
	
	public void ajoutListenerInventaire() {
		this.listeObjets.addListener (new ListChangeListener<Tuple> () {

			@Override
			public void onChanged(Change<? extends Tuple> changement) {
				while (changement.next()) {      
					if(changement.wasAdded()) {
						Tuile tile = invVue.ajoutItemInventaire((Inventeriable)listeObjets.get(changement.getFrom()).getKey());
						setClickObjetDonnerAuPerso(tile, (Inventeriable)listeObjets.get(changement.getFrom()).getKey());
					}
					
					if (changement.wasRemoved()) {
						invVue.retireItemInvent(changement.getFrom());
					
					}
				}
			}});
	}
	
	public void setClickObjetDonnerAuPerso(Tuile tile, Inventeriable objet) {
		tile.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
			this.j.getPerso().donner(objet);	
			System.out.println(this.j.getPerso().getMain().getTag());
			event.consume();
		});
	}
	
}
