package objetRessources;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap;

import modele.Cliquable;
import modele.Jeu;
import modele.Personnage;
import modele.PersonnagePrincipal;
import modele.Terrain;

/**
 * <h1>Air est un bloc non Inventeriable traversable</h1>
 * 
 * @version 1.1
 * @author Mathys
 *
 */

public class Air extends Bloc implements Cliquable {
	
	public Air () {
		
		super(0,false) ;
		super.initTag("air");
		
	}
	
	@Override
	public void interactionClick(int x, int y, Jeu jeu) {
		/*
		//System.out.println(this.getListeLignes().get(y).get(x).getPV());
		Terrain terrain = jeu.getTerrain();
		PersonnagePrincipal perso = jeu.getPerso();
		System.out.println(perso.getInventaire().getInventaire());
		//rechercher si il y a un ennemie sur ce bloc
		*/
	
	
			
		}

				
		
	}


  