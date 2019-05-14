
	package modele;

import physique.* ;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
		private ArrayList<ObservableList<GameObject>> listeDeLignes ;

		public Terrain (ArrayList<ObservableList<String>> newlist, double tailleCaseX, double tailleCaseY) {
			
			this.tailleCaseX = tailleCaseX ;
			this.tailleCaseY = tailleCaseY ;
			this.listeDeLignes = new ArrayList<ObservableList<GameObject>> () ;
			this.initTerrain(newlist) ;
			
		}
		
		private void initTerrain (ArrayList<ObservableList<String>> newlist) {
			
			int i, j, unPixel, unAutrePixel ;
			
			unAutrePixel = 1 ;
			unPixel = 1;
			
			for (i = 0 ; i < newlist.size() ; i ++) {
				
				this.listeDeLignes.add(FXCollections.observableArrayList()) ;
				
				for (j = 0 ; j < newlist.get(i).size() ; j ++) {
					
					this.listeDeLignes.get(i).add(
					new GameObject(newlist.get(i).get(j),
					new Collisionneur (j * this.tailleCaseX,
									   i * this.tailleCaseY,
									 ((j * this.tailleCaseX) + this.tailleCaseX - unAutrePixel),
									 ((i * this.tailleCaseY) + this.tailleCaseY - unPixel)))) ;
					
				}
				
			}
			
		}
		
		public int getDimY () {
			
			return this.listeDeLignes.size() ;
			
		}
		
		public int getDimX () {
			
			return this.listeDeLignes.get(0).size() ;
			
		}
		
		public double getTailleY () {
			
			return this.getDerniereCase().getCollisionneur().getYFin() ;
			
		}
		
		public double getTailleX () {
			
			return this.getDerniereCase().getCollisionneur().getXFin() ;
			
		}
		
		private GameObject getDerniereCase () {
			
			return this.listeDeLignes.get(this.getDimY() - 1).get(this.getDimX() - 1) ;
			
		}
		
		public ArrayList<ObservableList<GameObject>> getListeLignes () {
			
			return this.listeDeLignes ;
			
		}

	}


