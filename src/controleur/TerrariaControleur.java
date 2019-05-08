package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modele.Terrain;

public class TerrariaControleur implements Initializable {
	
	private Terrain t ;
	
	@FXML
	private TextField txt ;

    @FXML
    private TextArea map;
    
    public void afficherMap () {
    	
    	for (ObservableList<Character> ligne : t.getListeLignes()) {
    		
    		for (Character c : ligne) {
    			
    			map.appendText(c + " ");
    			
    		}
    		
    		map.appendText("\n");
    		
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		txt.setText("coucou");
		t = new Terrain () ;
		
		t.initTerrain();
		t.affTerrain();
		
		this.ajouterEcouter () ;
		
		this.t.getListeLignes().get(5).remove(5) ;
		this.t.getListeLignes().get(5).add(5, 'C') ;
		System.out.println(this.t.getListeLignes().get(5).get(5));
		this.afficherMap();
		t.affTerrain();
		
	}
	
	public void ajouterEcouter () {
		
		for (ObservableList<Character> listeCases : this.t.getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<Character> () {

				@Override
				public void onChanged(Change<? extends Character> changement) {


					while (changement.next()) {
						
						if (changement.wasAdded()) {
							
							System.out.println("ok");
							
						}
						
					}
					
				}
			
			}) ;
			
		}
		
	}

}
