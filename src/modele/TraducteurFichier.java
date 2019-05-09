package modele;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TraducteurFichier {
	
	private File f ;
	
	private BufferedReader bfr ;
	
	private String[][] tableauDeString ;

	public TraducteurFichier (String nomF) {
		
		this.f = new File(nomF) ;
		this.bfr = new BufferedReader(new FileReader(f)) ;
		this.tableauDeString = new String[][] ;
		
	}
	
	public void initTableau () throws IOException {
		
		int i, j ;
		
		String ligne ;
		
		String[] premiereLigne ;
		String[] autresLignes ;

		ligne = bfr.readLine() ;
		
		premiereLigne = ligne.split(",") ;
		
		this.tableauDeString = new String[Integer.parseInt(premiereLigne[0])][Integer.parseInt(premiereLigne[1])] ;
		
		for (i = 0 ; i < this.tableauDeString.length ; i ++) {
			
			ligne = bfr.readLine() ;
			
			autresLignes = ligne.split(",") ;
			
			for (j = 0 ; j < this.tableauDeString[i].length ; j ++) {
				
				this.tableauDeString[i][j] = autresLignes[j] ;
				
				
			}
			
		}
		
	}

}
