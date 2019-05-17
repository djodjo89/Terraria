package controleur;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import modele.Inventeriable;
import modele.Jeu;
import modele.Personnage;
import javafx.collections.ListChangeListener.Change;
import physique.*;
public class MapControleur {
	
	private Pane p;
	private Jeu jeu;
	private Personnage perso;
	public MapControleur(Pane p, Jeu j) {
		this.p=p;
		this.jeu=j;
	}
	public void ajouterEcouteur () {
		/*
		for (Inventeriable listeObjectsInventaire : this.perso.getInventaire().getListObjet()) {
			
			this.perso.getInventaire().getListObjet().addListener (new ListChangeListener<Inventeriable> () {

				@Override
				public void onChanged(Change<? extends Inventeriable> changement) {


					while (changement.next()) {

					}

				}

			}) ;
			
		}
		*/
	}
}
