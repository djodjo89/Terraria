package controleur;

import modele.*;
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
	
	public ControleurMap(Pane pane, Jeu jeu) {
		
		this.pane =pane ;
		this.jeu=jeu ;
		
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
		
		for (ObservableList<Inventeriable> listeCases : this.jeu.getTerrain().getListeLignes()) {
			
			listeCases.addListener (new ListChangeListener<Inventeriable> () {

				@Override
				public void onChanged(Change<? extends Inventeriable> changement) {

					while (changement.next()) {

					}

				}

			}) ;
			
		}
		
	}
	
}
