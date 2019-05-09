package modele;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
		
		int i, j ;
		
		String ligne ;
		
		String[] premiereLigne ;
		String[] autresLignes ;

		ligne = bfr.readLine() ;
		
		premiereLigne = ligne.split(",") ;
		
		for (i = 0 ; i < this.tableauDeString.size() ; i ++) {
			
			ligne = bfr.readLine() ;
			
			autresLignes = ligne.split(",") ;
			
			for (j = 0 ; j < this.tableauDeString.get(i).size() ; j ++) {
				
				this.tableauDeString.get(i).set(j, autresLignes[j])  ;
				
				
			}
			
		}
		
	}

}
