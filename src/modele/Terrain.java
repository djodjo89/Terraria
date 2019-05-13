
	package modele;

import physique.* ;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

	public class Terrain {
		
		private double tailleCaseX ;
		private double tailleCaseY ;
		private ArrayList<ObservableList<Objet>> listeDeLignes ;

		public Terrain (ArrayList<ObservableList<String>> newlist, double tailleCaseX, double tailleCaseY) {
			
			this.tailleCaseX = tailleCaseX ;
			this.tailleCaseY = tailleCaseY ;
			this.listeDeLignes = new ArrayList<ObservableList<Objet>> () ;
			this.initTerrain(newlist) ;
			
		}
		
		public void initTerrain (ArrayList<ObservableList<String>> newlist) {
			
			int i, j, unPixel, unAutrePixel ;
			
			unAutrePixel = 1 ;
			unPixel = 1;
			
			for (i = 0 ; i < newlist.size() ; i ++) {
				
				this.listeDeLignes.add(FXCollections.observableArrayList()) ;
				
				for (j = 0 ; j < newlist.get(i).size() ; j ++) {
					
					this.listeDeLignes.get(i).add(
					new Objet(newlist.get(i).get(j),
					new String(j + ":" + i),
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
		
		private Objet getDerniereCase () {
			
			return this.listeDeLignes.get(this.getDimY() - 1).get(this.getDimX() - 1) ;
			
		}
		
		public ArrayList<ObservableList<Objet>> getListeLignes () {
			
			return this.listeDeLignes ;
			
		}

	}


