
	package modele;

import physique.* ;
import java.util.ArrayList;

import fabriques.FabriqueGameObject;
import geometrie.Point;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objetRessources.Bloc;

/*
 * Le Terrain est le modèle de la map.
 * C'est lui qui définit, sous la forme d'un tableau
 * observable de chaînes de caractères, les différents
 * blocs qui constituent la map
 * Voici ses responsabilités :
 * - donner ses dimensions
 * - fournir la dernière case
 * - fournir son tableau observable
 */

	public class Terrain {
		
		private double tailleCaseX ;
		private double tailleCaseY ;
		private ArrayList<ObservableList<Bloc>> listeDeLignes;
		
		private int positionBlockY;
		

		public Terrain (ArrayList<ObservableList<String>> newlist, double tailleCaseX, double tailleCaseY) {
			
			this.tailleCaseX = tailleCaseX ;
			this.tailleCaseY = tailleCaseY ;
			this.listeDeLignes = new ArrayList<>() ;
			this.initTerrain(newlist) ;
			this.positionBlockY = 0;
			
		}
		
		private void initTerrain (ArrayList<ObservableList<String>> newlist) {
			
			String nomCase ;
			Bloc caseMap = null ;

			
			for (int i = 0 ; i < newlist.size() ; i ++) {
				
				this.listeDeLignes.add(FXCollections.observableArrayList()) ;
				
				for (int j = 0 ; j < newlist.get(i).size() ; j ++) {
					
					nomCase = newlist.get(i).get(j) ;
					
					

					Collisionneur colisionobjet = new Collisionneur (new Point (j * this.tailleCaseX, i * this.tailleCaseY),

							new Point (j * this.tailleCaseX + 49, i * this.tailleCaseY),
							new Point (j * this.tailleCaseX, i * this.tailleCaseY + 49),
							new Point (j * this.tailleCaseX + 49, i * this.tailleCaseY + 49)) ;
					
					caseMap = (Bloc)FabriqueGameObject.creerGameObjectDemander(nomCase, colisionobjet);

					this.listeDeLignes.get(i).add(caseMap) ;
					
				}
				
			}
			
		}
		
		public GameObject getCase (int[] coordonneesDeLaCase, Moteur moteur) {

			return this.getListeLignes().get(coordonneesDeLaCase[1]).get(coordonneesDeLaCase[0]) ;

		}
		
		public int getDimY () {
			
			return this.listeDeLignes.size() ;
			
		}
		
		public int getDimX () {
			
			return this.listeDeLignes.get(0).size() ;
			
		}
		
		public double getTailleY () {
			
			return Math.abs(this.getDerniereCase().getCollisionneur().getBoite().minMaxY()[0] - this.getDerniereCase().getCollisionneur().getBoite().minMaxY()[1]) ;
			
		}
		
		public double getTailleX () {
			
			return Math.abs(this.getDerniereCase().getCollisionneur().getBoite().minMaxX()[0] - this.getDerniereCase().getCollisionneur().getBoite().minMaxX()[1]);
			
		}
		
		public GameObject getDerniereCase () {
			
			return this.listeDeLignes.get(this.getDimY() - 1).get(this.getDimX() - 1) ;
			
		}
		
		public ArrayList<ObservableList<Bloc>> getListeLignes () {
			
			return this.listeDeLignes ;
			
		}

		public void setPositionBlockY(int y) {
			this.positionBlockY = y;
		}
		
		public int getPositionBlockY() {
			return this.positionBlockY;
		}
		
		

	}


