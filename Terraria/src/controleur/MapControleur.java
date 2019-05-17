package controleur;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import modele.Inventeriable;
import modele.Jeu;
import javafx.collections.ListChangeListener.Change;
import physique.*;
public class MapControleur {
	
	private Pane p;
	private Jeu jeu;
	public MapControleur(Pane p, Jeu j) {
		this.p=p;
		this.jeu=j;
	}
	public void ajouterEcouteur () {
		
		for (ObservableList<Inventeriable> listeCases : this.jeu.getMap().getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<Inventeriable> () {

				@Override
				public void onChanged(Change<? extends Inventeriable> changement) {


					while (changement.next()) {
						if(changement.wasReplaced()) {
							
						}

					}

				}

			}) ;
			
		}
		
	}
}
