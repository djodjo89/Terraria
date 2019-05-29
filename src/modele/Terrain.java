
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
		private ObservableList<ObservableList<Inventeriable>> listeDeLignes ;
		
		private int positionBlockY;
		

		public Terrain (ArrayList<ObservableList<String>> newlist, double tailleCaseX, double tailleCaseY) {
			
			this.tailleCaseX = tailleCaseX ;
			this.tailleCaseY = tailleCaseY ;
			this.listeDeLignes = FXCollections.observableArrayList() ;
			this.initTerrain(newlist) ;
			this.positionBlockY = 0;
			
		}
		
		private void initTerrain (ArrayList<ObservableList<String>> newlist) {
			
			int unPixel=1, unAutrePixel=1 ;
			String nomCase ;
			Inventeriable caseMap = null ;

			
			for (int i = 0 ; i < newlist.size() ; i ++) {
				
				this.listeDeLignes.add(FXCollections.observableArrayList()) ;
				
				for (int j = 0 ; j < newlist.get(i).size() ; j ++) {
					
					nomCase = newlist.get(i).get(j) ;
					
					switch (nomCase) {
					
					case "T" : caseMap = new Terre(nomCase) ; break ;
					
					case "A" : caseMap = new Air(nomCase) ; break ;
					
					case "G": caseMap = new Granite(nomCase); break;
					
					}
					
					caseMap.setCollisionneur(new Collisionneur (j * this.tailleCaseX,
									   i * this.tailleCaseY,
									 ((j * this.tailleCaseX) + this.tailleCaseX - unAutrePixel),
									 ((i * this.tailleCaseY) + this.tailleCaseY - unPixel)));
					
					this.listeDeLignes.get(i).add(caseMap) ;
					
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
		
		public ObservableList<ObservableList<Inventeriable>> getListeLignes () {
			
			return this.listeDeLignes ;
			
		}
		
		public void destructionTerrain(int x, int y) {
			//System.out.println(this.getListeLignes().get(y).get(x).getPV());
			if(this.listeDeLignes.get(y).get(x).estUnObstacle() && this.listeDeLignes.get(y).get(x).getPV()>0) {
				
				this.listeDeLignes.get(y).get(x).perdrePV(10);
				System.out.println(this.listeDeLignes.get(y).get(x).getPV());
					
				if(this.listeDeLignes.get(y).get(x).getPV() <= 0) {
					Inventeriable caseMap = new Air("A");
					
					System.out.println(x);
					System.out.println(y);
					this.listeDeLignes.get(y).set(x,caseMap);
				}
			}
		}
		
		public void setPositionBlockY(int y) {
			this.positionBlockY = y;
		}
		
		public int getPositionBlockY() {
			return this.positionBlockY;
		}
		
		

	}


