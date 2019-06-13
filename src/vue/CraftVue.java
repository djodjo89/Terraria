package vue;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modele.Craft;

public class CraftVue {

	private Craft craft;
	private Pane paneCraft;
	
	public CraftVue(Craft craft, Pane paneCraft) {
		this.craft=craft;
		this.paneCraft=paneCraft;
	}
	
	public void initCraftVue () {
		
		this.paneCraft.setVisible(true);
		this.paneCraft.toFront();
		this.paneCraft.setFocusTraversable(true);
		ImageView fond= new ImageView(new Image(new File("image/backgroundMenu.jpg").toURI().toString()));
		this.paneCraft.getChildren().add(fond);
	}
	
	public void affichePaneCraft() {
		this.paneCraft.setVisible(true);
		this.paneCraft.setFocusTraversable(true);
		this.paneCraft.toFront();
	}
	
	public void afficheOutilsCraftable() {
		
	}
	
	public Pane getPane () {
		
		return this.paneCraft ;
		
	}

}
