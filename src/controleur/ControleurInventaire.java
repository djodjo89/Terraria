package controleur;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import modele.*;
import objetRessources.Inventeriable;
import vue.*;
import vue.Tuile;
import ressources.Images;

public class ControleurInventaire {

	private Jeu j;
	private ObservableList<Tuple> listeObjets ;
	private InventaireVue invVue;
	

	public ControleurInventaire(Jeu j, Images img, InventaireVue inv) {
		this.j=j;
		this.listeObjets=this.j.getPerso().getInventaire().getInventaire();
		this.invVue=inv;
	}
	
	
	public void ajoutListenerInventaire() {
		this.listeObjets.addListener (new ListChangeListener<Tuple> () {

			@Override
			public void onChanged(Change<? extends Tuple> changement) {
				while (changement.next()) {
					
					if(changement.wasReplaced()) {
						
						if ((listeObjets.get(changement.getFrom()).getValue()) == 0) {
							
							invVue.retireItemInvent(changement.getFrom());
							
						}
						
						else {
							
							Tuile tile = invVue.ajoutItemInventaire(listeObjets.get(changement.getFrom()), changement.getFrom());
							setClickObjetDonnerAuPerso(tile, (Inventeriable)listeObjets.get(changement.getFrom()).getKey());
							
						}

					}
				}
			}});
	}
	
	public void setClickObjetDonnerAuPerso(Tuile tile, Inventeriable objet) {
		tile.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
			
			this.j.getPerso().donner(objet);	
			event.consume();
			
		});
		
	}
	
}
