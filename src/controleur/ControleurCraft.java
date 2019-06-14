package controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.input.MouseEvent;
import modele.*;
import objetRessources.Outil;
import vue.*;
import ressources.*;

public class ControleurCraft {
	
	private Jeu jeu ;
	private CraftVue craftVue ;
	
	public ControleurCraft (Jeu jeu, Images images, CraftVue craftVue) {
		
		this.jeu = jeu ;
		this.craftVue = craftVue ;
		
	}
	
	public void ajouterListenerCraft () {
		
		this.jeu.getCraft().getListeObjetsCraftables().addListener(new ListChangeListener<Outil> () {
			
			@Override
			public void onChanged (ListChangeListener.Change<? extends Outil> changement) {
				
				while (changement.next()) {

					if (changement.wasAdded()) {
						
						Tuile tuile = craftVue.ajouterOutilCraftable(jeu.getCraft().getListeObjetsCraftables().get(changement.getFrom())) ;
						setClicOutilAAjouterALInventaire(tuile, jeu.getCraft().getListeObjetsCraftables().get(changement.getFrom())) ;
						
					}
						
					if (changement.wasRemoved())
						
						craftVue.retirerOutilCraftable(changement.getFrom()) ;
					
				}
				
			}
			
		});
		
	}
	
	public void setClicOutilAAjouterALInventaire (Tuile tuile, Outil o) {
		
		tuile.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			this.jeu.getPerso().getInventaire().ajouterObjet(o) ;
			event.consume() ;
		});
		
	}

}
