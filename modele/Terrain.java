
	package modele;

	import java.util.ArrayList;

	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;

	public class Terrain {
		
		private ArrayList<ObservableList<String>> listeDeLignes ;
		
		

		public Terrain (ArrayList<ObservableList<String>> newlist) {
			
			listeDeLignes = newlist ;
			
			
			
			
			
		}
		
		public Terrain() {
			listeDeLignes=null;
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
		
		public void affTerrain () {
			
			for (ObservableList<String> ligne : this.listeDeLignes) {
				
				for (String casE : ligne) {
					
					System.out.print(casE + " ");
					
				}
				
				System.out.println();
				
			}
			
		}

}
