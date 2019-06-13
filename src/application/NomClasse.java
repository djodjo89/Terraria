package application;

import physique.GameObject;

public class NomClasse {
	
	public static String retrouver (GameObject go) {
		
		int i ;
		String premierChar ;
		String nomComplet = go.getClass().getName() ;
		
		i = nomComplet.length() - 1 ;
		
		while (i > 0 && nomComplet.charAt(i) != '.') {
			
			i -- ;
			
		}
		
		nomComplet = new String(nomComplet.substring(i + 1)) ;
		
		premierChar = nomComplet.substring(0, 1) ;
		
		premierChar = premierChar.toLowerCase() ;
		
		nomComplet = new String (nomComplet.substring(1)) ;
		
		nomComplet = premierChar + nomComplet ;
		
		return nomComplet ;
		
	}

}
