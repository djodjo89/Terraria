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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modele.Inventeriable;
import modele.Jeu;
import modele.Personnage;
import vue.Tuile;
import ressources.Images;

public class ControleurInventaire {

	private Jeu j;
	private Images image;
	private Pane pane;
	private Personnage perso;
	

	public ControleurInventaire(Jeu j, Images img, Pane pane, Personnage perso) {
		this.j=j;
		this.image=img;
		this.pane=pane;
		this.perso=perso;
	}
	
	public Personnage getPerso() {
		return this.perso;
	}
	
	public Images getImg() {
		return this.image;
	}
	
	public void afficheItem(Inventeriable item) {
		for(int i=0; i<this.perso.getInventaire().getListObjet().size(); i++) {
			Tuile itemes = new Tuile((""+i), this.image.getImage(this.perso.getInventaire().getListObjet().get(i).getTag()));
			
			this.pane.getChildren().add(itemes);
		}
	}
}
