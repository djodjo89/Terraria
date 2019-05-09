package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D ;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import modele.Terrain;

public class TerrariaControleur implements Initializable {
	
	private Terrain t ;
	private Point2D p ;
	final static private String[] DIRECTIONS = {"haut", "droite", "bas", "gauche"} ;
	private int direction ;
	
    @FXML
    private TextArea map;
    
    @FXML
    private Button boutonDroite ;
    
    @FXML
    private Button boutonGauche ;
    
    @FXML
    private Button boutonHaut ;
    
    @FXML
    private Button boutonBas ;
    
    public void afficherMap () {
    	
    	this.map.clear();
    	
    	for (ObservableList<String> ligne : t.getListeLignes()) {
    		
    		for (String c : ligne) {
    			
    			map.appendText(c + " ");
    			
    		}
    		
    		map.appendText("\n");
    		
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.p = new Point2D(5, 5) ;
		this.t = new Terrain () ;
		
		this.t.initTerrain();
		
		this.ajouterEcouteur () ;
		
		this.t.getListeLignes().get((int)p.getX()).set((int)p.getY(), "P") ;
		
		this.afficherMap();
		
	}
	
	public void allerEnHaut (ActionEvent event) {
		
		this.direction = 0 ;
		this.seDeplacer();
		
	}
	
	public void allerADroite (ActionEvent event) {
		
		this.direction = 1 ;
		this.seDeplacer();
		
	}
	
	public void allerEnBas (ActionEvent event) {
		
		this.direction = 2 ;
		this.seDeplacer();
		
	}
	
	public void allerAGauche (ActionEvent event) {
		
		this.direction = 3 ;
		this.seDeplacer();
		
	}

	
	private void seDeplacer () { // 0 : haut, 1 : droite, 2 : bas, 3 : gauche
		
		this.t.getListeLignes().get((int)p.getX()).set((int)p.getY(), "T") ;
		System.out.println(p.getX() + p.getY());
		System.out.println(this.direction);
		
		switch (this.direction) { 
		
			case 0 : 
				
				if (this.p.add(0., 1.).getX() >= 0) {
					
					this.p = this.p.add(-1., 0.) ; 
					
				}
				
			break ;
			
			case 1 : 
				
				if (this.p.add(0., 1.).getY() < this.t.getDim()) {
					
					this.p = this.p.add(0., 1.) ; 
					
				}
				
			break ;
			
			case 2 : 
				
				if (this.p.add(1., 0.).getX() < this.t.getDim()) {
				
					this.p = this.p.add(1., 0.) ; 
					
				}
				
			break ;
			
			case 3 : 
				
				if (this.p.add(0., -1.).getY() >= 0) {
					
					this.p = this.p.add(0., -1.) ; 
					
				}
				
			break ;
			
		}
		
		this.t.getListeLignes().get((int)p.getX()).set((int)p.getY(), "P") ;
		System.out.println(p.getX() + p.getY());
		this.afficherMap();
		
	}
	
	public void ajouterEcouteur () {
		
		for (ObservableList<String> listeCases : this.t.getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<String> () {

				@Override
				public void onChanged(Change<? extends String> changement) {


					while (changement.next()) {

						if (changement.wasReplaced()) {

							if (listeCases.get((int)p.getY()) == "P") {

								System.out.print("Déplacement ");

								switch (direction) {

								case 1 :
								case 3 :

									System.out.print("à ");

									break ;

								case 0 :
								case 2 :

									System.out.print("en ");

									break ;

								}

								System.out.println(DIRECTIONS[direction]);

							}

						}

					}

				}

			}) ;
			
		}
		
	}

}
