package controleur;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.layout.Pane;
import modele.Inventeriable;
import modele.Jeu;
import modele.Personnage;
import ressources.Images;
import vue.Tuile;

public class ControleurZoneInventaire extends ControleurInventaire {
	
	private ObservableList<Inventeriable> listeObjets ;

	public ControleurZoneInventaire(Jeu j, Images img, Pane paneFondInventaire, Personnage perso) {
		super(j,img,paneFondInventaire, perso);
		this.listeObjets = this.getPerso().getInventaire().getListObjet();
	}
	/*
	public void ajoutListenerInventaire() {
		this.listeObjets.addListener (new ListChangeListener<Inventeriable> () {

			@Override
			public void onChanged(Change<? extends Inventeriable> changement) {

				while (changement.next()) {      
					if(changement.wasAdded()) {
						Tuile tuile = new Tuile((""+changement.getFrom()), this.getImg().changement.getFrom());
					}
				}
			}});
	}*/
}
