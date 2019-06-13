package controleur;

import modele.*;
import objetRessources.Bloc;
import objetRessources.Inventeriable;
import ressources.Images;
import vue.Tuile;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;


/**
 * <h1>ControleurMap est la classe chargé de transmettre à la vue les changements du terrain</h1>
 * 
 * <p>Un ControleurMap possède :
 * 	<ul>
 * 		<li>Un Jeu permettant d'accéder au terrain.</li>
 * 		<li>Un Pane auquel seront appliqués les modifications.</li>
 * 	</ul>
 * 	La méthode ajouterEcouteur ajoute un ListChangeListener sur la liste observable d'objets
 * 	inventeriables du terrain.
 * </p>
 *  
 * @see Inventeriable
 * @see Jeu
 * @see Terrain
 * 
 * @author Karen
 * @version 1.1
 */

public class ControleurMap {
	
	/**
	 * Le Jeu du contrôleur
	 * 
	 * <p>Il fournit le terrain observé par le contrôleur</p>
	 * 
	 * @see ControleurMap#ControleurMap(Pane, Jeu)
	 * 
	 */
	
	private Jeu jeu ;
	
	/**
	 * Le Pane du contrôleur.
	 * 
	 * <p>C'est lui que modifiera le contrôleur en cas
	 * de changement</p>
	 * 
	 * @see ControleurMap#ControleurMap(Pane, Jeu)
	 */
	
	private Pane pane ;
	
	private Images images;
	
	private int x;
	
	private int y;
	
	public ControleurMap(Pane pane, Jeu jeu, Images image) {
		
		this.pane =pane ;
		this.jeu=jeu ;
		this.images = image;
		this.x = 0;
		this.y = 0;
		
	}
	
	

	/**
	 * Ajoute un ChangeListener sur chaque ligne du terrain
	 * 
	 * @author Karen
	 * @see Inventeriable
	 * @see Jeu#getTerrain()
	 * @see Terrain#getListeLignes()
	 * @since 1.0
	 */
	
	public void ajouterEcouteur () {
		
	
		
		for (ObservableList<Bloc> listeCases : this.jeu.getTerrain().getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<Inventeriable> () {

				@Override
				public void onChanged(Change<? extends Inventeriable> changement) {

					while (changement.next()) {
						if(changement.wasReplaced()) {
							x = changement.getFrom();
							
							//get(x).getFrom;//changement.getAddedSubList();
							System.out.println(x);
							remplacerImage();
						}
					}

				}

			}) ;
			
		}
		
	}
	public void remplacerImage() {
		
		int y = this.jeu.getTerrain().getPositionBlockY();
		
		String nom = x + ":" + y;
		for (int i = 0; i < pane.getChildren().size(); i++) {
			if(pane.getChildren().get(i).getId().equals(nom)) {

				//System.out.println("cool");
				//ImageView b = (ImageView) pane.getChildren().get(i);
			
				Tuile a = new Tuile(nom, x * this.jeu.getMoteur().getTailleBoiteX(), y * this.jeu.getMoteur().getTailleBoiteY(), this.images.getImage(this.jeu.getTerrain().getListeLignes().get(y).get(x).getClass().getName()));
				pane.getChildren().set(i, a);
			}
		}
	}
	
}
