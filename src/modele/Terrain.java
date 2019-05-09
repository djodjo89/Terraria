package modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Terrain {
	
	private ArrayList<ObservableList<String>> listeDeLignes ;

	public Terrain (ArrayList<ObservableList<String>> mapFichier) {
		
		listeDeLignes = new ArrayList<> () ;
		
	}
	
	public Terrain () {
		
		listeDeLignes = new ArrayList<> () ;
		
	}
	
	public void initTerrain () {
		
		int i, j ;
		
		for (i = 0 ; i < 10 ; i ++) {
			
			listeDeLignes.add(FXCollections.observableArrayList()) ;
			
			for (j = 0 ; j < 10 ; j ++) {
				
				listeDeLignes.get(i).add("T") ;
				
			}
			
		}
		
	}
	
	public int getDim () {
		
		return this.listeDeLignes.get(0).size() ;
		
	}
	
	public ArrayList<ObservableList<String>> getListeLignes () {
		
		return this.listeDeLignes ;
		
	}
	
	public void affTerrain () {
		
		for (ObservableList<String> ligne : this.listeDeLignes) {
			
			for (String casE : ligne) {
				
				System.out.print(casE + " ");
				
			}
			
			System.out.println();
			
		}
		
	}

}
