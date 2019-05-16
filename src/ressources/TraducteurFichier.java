package ressources;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TraducteurFichier {
	
	private File f ;
	
	private BufferedReader bfr ;
	
	private ArrayList<ObservableList<String>> tableauDeString ;

	public TraducteurFichier (String nomF) throws IOException {
		
		this.f = new File(nomF) ;
		this.bfr = new BufferedReader(new FileReader(f)) ;
		this.tableauDeString = new ArrayList<ObservableList<String>> () ;
		this.initTableau();
		
	}
	
	public ArrayList<ObservableList<String>> getTabMap () {
		
		return this.tableauDeString ;
		
	}
	
	public void initTableau () throws IOException {
		
		int i ;
		
		String ligne ;
		
		String[] lignes ;
		
		i = 0 ;
		
		// Tant qu'il reste des lignes à lire
		while ((ligne = bfr.readLine()) != null) {
			
			// On découpe la ligne dans un tableau de String
			lignes = ligne.split(",") ;
			// On ajoute une nouvelle liste à notre map
			this.tableauDeString.add(FXCollections.observableArrayList()) ;
			
			for (int j = 0 ; j < lignes.length ; j ++) {
				// On ajoute une nouvelle case à notre map
				this.tableauDeString.get(i).add(lignes[j])  ;
								
			}
			
			i ++ ;
			
		}
		
	}

}
