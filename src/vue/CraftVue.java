package vue;

import application.NomClasse;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import modele.* ;
import objetRessources.BlocBiomasse;
import objetRessources.Outil;
import ressources.Images;

public class CraftVue {

	private boolean paneAffiche ;
	private Jeu jeu ;
	private Pane paneCraft ;
	private Images images ;
	
	public CraftVue(Jeu jeu, Pane paneCraft, Images images) {
		this.jeu = jeu ;
		this.paneCraft = paneCraft ;
		this.images = images ;
	}
	
	public void affichePaneCraft () {
		
		this.paneCraft.setVisible(true);
		this.paneCraft.setFocusTraversable(true);
		this.paneAffiche = true ;
	}
	
	public void faireDisparaitrePaneCraft() {
		
		this.paneCraft.setVisible(false) ;
		this.paneAffiche = false ;
		
	}
	
	public void afficheOutilsCraftable() {
		
		int i ;
		
		i = 0 ;
		
		this.paneCraft.getChildren().clear() ;
		
		for (i = 0 ; i < this.jeu.getCraft().getListeObjetsCraftables().size() ; i ++)
			
			this.paneCraft.getChildren().add(new Tuile (i + "", new Image (NomClasse.retrouver(new BlocBiomasse())))) ;
		
	}
	
	public boolean paneAffiche () {
		
		return this.paneAffiche ;
		
	}
	
	public Tuile ajouterOutilCraftable (Outil o) {
		
		Tuile tuileOutil = new Tuile (NomClasse.retrouver(o), this.jeu.getMoteur().getTailleBoiteX(), 0, this.images.getImage(NomClasse.retrouver(o))) ;
		tuileOutil.setLayoutX(this.paneCraft.getChildren().size() * this.jeu.getMoteur().getTailleBoiteX());
		tuileOutil.setLayoutY(this.jeu.getMoteur().getTailleBoiteY());
		this.paneCraft.getChildren().add(tuileOutil) ;
		return tuileOutil ;
		
	}
	
	public void retirerOutilCraftable (int i) {
		
		this.paneCraft.getChildren().remove(i) ;
		
	}
	
	public Pane getPane () {
		
		return this.paneCraft ;
		
	}

}
