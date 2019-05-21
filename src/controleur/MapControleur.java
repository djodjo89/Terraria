package controleur;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import modele.Air;
import modele.Inventeriable;
import modele.Jeu;
import vue.Tuile;
import javafx.collections.ListChangeListener.Change;
import physique.*;
import ressources.Images;
public class MapControleur {
	
	private Pane p;
	private Jeu jeu;
	private Images images ;
	public MapControleur(Pane p, Jeu j) {
		this.p=p;
		this.jeu=j;
	}
	public void ajouterEcouteur () {
		
		for (ObservableList<Inventeriable> listeCases : this.jeu.getTerrain().getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<Inventeriable> () {

				@Override
				public void onChanged(Change<? extends Inventeriable> changement) {


					while (changement.next()) {
						if(changement.wasReplaced()) {
							int x = changement.getFrom();
							int y = 0;
							System.out.println(x);
							remplacerImage(x,y);
						}

					}

				}

			}) ;
			
		}
		
	}
	
	public void remplacerImage(int x, int y) {
		//Tuile tile= new Tuile("test",x*jeu.getMoteur().getTailleTileX(),y*jeu.getMoteur().getTailleTileY(),this.images.getImage("air"));
		//System.out.println("ok");
		//p.getChildren().set(x*2, tile);
	}
}
