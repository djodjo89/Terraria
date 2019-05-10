
	package modele;

	import java.util.ArrayList;
	import javafx.collections.ObservableList;

	public class Terrain {
		
		private ArrayList<ObservableList<String>> listeDeLignes ;

		public Terrain (ArrayList<ObservableList<String>> newlist) {
			
			listeDeLignes = newlist ;
			
		}
		
		public Terrain () {
			
			new Terrain(null) ;
			
		}
		
		public int getDimY() {
			
			return this.listeDeLignes.size();
			
		}
		
		public int getDimX () {
			
			return this.listeDeLignes.get(0).size() ;
			
		}
		
		public ArrayList<ObservableList<String>> getListeLignes () {
			
			return this.listeDeLignes ;
			
		}
		
		public void setListe (ArrayList<ObservableList<String>> l) {
			
			this.listeDeLignes = l ;
			
		}

	}


