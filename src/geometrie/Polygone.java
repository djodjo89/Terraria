package geometrie;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <h1>Un Polygone est une liste ordonnée de Points appelés sommets.</h1>
 * <p>Un Polygone peut :
 * 	<ul>
 * 		<li>En copier un autre</li>
 * 		<li>Retourne un point à une position donnée</li>
 * 		<li>Ajouter un vecteur à chaque point</li>
 * 		<li>Ajouter un sommet</li>
 * 		<li>Dire s'il contient un point</li>
 * 		<li>Donner son nombre de sommets</li>
 * 		<li>Dire s'il est inclus dans un rectangle</li>
 * 		<li>S'ordonner de façon à être affichable en console</li>
 * 		<li>Donner les extrêmes de ses points</li>
 * 		<li>S'afficher</li>
 * </ul>
 * La classe Polygone permet de manipuler facilement des ensembles de points
 * utiles notamment lors des opérations de collisions.
 * </p>
 * @see Point
 * @see Vecteur
 * 
 * @author Mathys
 * @version 2.0
 */

public class Polygone {
	
	ArrayList<Point> listeSommets ;
	
	/**
	 * Constructeur permettant d'initialiser rapidement un polygone vide
	 * 
	 * @since 1.0
	 */
	
	public Polygone () {
		
		this.listeSommets = new ArrayList<Point> () ;

	}
	
	/**
	 * Crée un Polygone de nbSommets initialisés à 0:0
	 * 
	 * @param nbSommets
	 * @since 2.0
	 */
	
	public Polygone (int nbSommets) {
		
		this () ;
		int i ;
		
		for (i = 0 ; i < nbSommets ; i ++)
			
			this.ajouterSommet(new Point (0, 0)) ;
		
	}
	
	/**
	 * Crée un polygone de nbSommets initialisés à x:y
	 * 
	 * @param nbSommet
	 * @param x
	 * @param y
	 * @since 2.0
	 */
	
	public Polygone (int nbSommet, double x, double y) {
		
		this () ;
		int i ;
		
		for (i = 0 ; i < nbSommet ; i ++) {
			
			this.ajouterSommet(new Point (x, y)) ;
			
		}
		
	}
	
	/**
	 * Crée un Polygone composé des points entrés en paramètre
	 * dans l'ordre
	 * 
	 * @param points
	 * @since 2.0
	 */
	
	public Polygone (Point...points) {
		
		this() ;
		
		for (Point point : points) {
			
			this.ajouterSommet(point) ;
			
		}
		
	}
	
	/**
	 * Retourne le point d'intersection des deux polygones
	 * 
	 * @param polygone0
	 * @return Le premier Point rencontré
	 * @since 2.1
	 */
	
	public Point intersection (Polygone polygone) {
		
		int i, j ;
		Point intersection ;
		Segment s1, s2 ;
		
		i = 0 ;
		j = 0 ;
		intersection = null ;
		
		while (intersection == null && i < this.listeSommets.size()) {

				s1 = new Segment(this.get(i), this.get((i + 1) % this.nbSommets())) ;
			
			while (intersection == null && j < polygone.nbSommets()) {
				
				s2 = new Segment(polygone.get(j), polygone.get((j + 1) % this.nbSommets())) ;
				intersection = s1.intersection(s2) ;
				
				j ++ ;
				
			}
			
			i++ ;
			
		}
		
		return intersection ;
		
	}
	
	public void dezoom () {
		
		for (Point p : this.listeSommets) {
			
			p = p.add(p.getX()/100 - p.getX(), p.getY()/100 - p.getY()) ;
			System.out.println(p);
		}
		
	}
	
	/**
	 * Copie le contenu d'un autre polygone dans celui-ci
	 * 
	 * @param polygone
	 * @since 2.0
	 */
	
	public void copie (Polygone polygone) {
		
		int i ;
		
		this.listeSommets = new ArrayList<Point> () ;
		
		for (i = 0 ; i < polygone.nbSommets() ; i ++) {
			
			this.ajouterSommet(new Point (polygone.get(i).getX(), polygone.get(i).getY())) ;
			
		}
		
	}
	
	/**
	 * Retourne le point situ� � l'indice i dans la liste
	 * 
	 * @param i
	 * @return Le Point à l'indice i
	 * @since 1.0
	 */
	
	public Point get (int i) {
		
		return this.listeSommets.get(i) ;
		
	}
	
	/**
	 * Ajoute les x et les y du vecteur à chaque point
	 * du polygone
	 * 
	 * @param vecteur
	 * @since 2.0
	 */
	
	public void ajouterAChaquePoint (Vecteur vecteur) {
		
		int i ;
		
		for (i = 0 ; i < this.nbSommets() ; i ++) {
			
			this.listeSommets.set(i, this.get(i).add(vecteur.getX(), vecteur.getY())) ;
			
		}
		
	}
	
	/**
	 * Ajoute le Point au Polygone
	 * 
	 * @param vecteur
	 * @since 2.0
	 */
	
	public void ajouterSommet (Point point) {
		
		if (!this.contient(point))
			
			this.listeSommets.add(point) ;
		
	}
	
	/**
	 * Ajoute un nouveau Point crée à partir
	 * des coordonnées fournies
	 * 
	 * @param x
	 * @param y
	 */
	
	public void ajouterSommet (double x, double y) {
		
		this.ajouterSommet(new Point (x, y)) ;
		
	}
	
	/**
	 * Retourne vrai si le Polygone contient le Point
	 * passé en paramètre
	 * 
	 * @param point
	 * @return
	 */
	
	public boolean contient (Point point) {
		
		int i ;
		boolean trouve ;
		
		i = 0 ;
		trouve = false ;
		
		while (!trouve && i < this.nbSommets ()) {
			
			if (this.listeSommets.get(i).compareTo(point) == 0)
				
				trouve = true ;
			
			i ++ ;
			
		}
		
		return trouve ;
		
	}
	
	/**
	 * Retourne le nombre de Points que contient
	 * le Polygone
	 * 
	 * @return La taille de la liste de Point
	 */
	
	public int nbSommets () {
		
		return this.listeSommets.size() ;
		
	}
	
	/**
	 * Retourne vrai si tous les sommets du Polygone
	 * sont compris entre 0 et xMax et 0 et yMax
	 * 
	 * @param xMax
	 * @param yMax
	 * @return Vrai si le Polygone est situé dans les bornes données
	 */
	
	public boolean estInclusDans (double xMax, double yMax) {
		
		int i ;
		boolean depasseLesBornes ;
		
		i = 0 ;
		depasseLesBornes = false ;
		
		while (!depasseLesBornes && i < this.nbSommets()) {
			
			if (this.get(i).getX() < 0 || this.get(i).getY() < 0 || (int)this.get(i).getX() - 1 >= xMax || (int)this.get(i).getY() - 1 >= yMax) {
				
				depasseLesBornes = true ;
				
			}
			
			i ++ ;
			
		}
		
		return !depasseLesBornes ;
		
	}
	
	/**
	 * Renvoie vrai si les deux figures sont identiques
	 * 
	 * @param poly2
	 */
	
	public boolean estEgalA (Polygone poly2) {
		
		int i ;
		boolean estEgal ;
		
		i = 0 ;
		estEgal = true ;
		
		if (this.nbSommets() == poly2.nbSommets()) 
		
			while (estEgal && i < this.nbSommets() && i < poly2.nbSommets()) {
				
				if (!(this.get(i).compareTo(poly2.get(i)) == 0))
					
					estEgal = false ;
				
				i ++ ;
				
			}
		
		else
			
			estEgal = false ;
		
		return estEgal ;
		
	}
	
	/**
	 * Classe les Point du Polygone dans un ordre permettant de les afficher
	 * séquentiellement
	 * 
	 * @return Un tableau permettant d'afficher le Polygone
	 */
	
	public ArrayList<ArrayList<Point>> tableauOrdonne () {
		
		int i ;
		double y ;
		ArrayList<Point> listeOrdonneeParY ;
		ArrayList<ArrayList<Point>> nvListe ;
		
		listeOrdonneeParY = new ArrayList<Point>() ;
		listeOrdonneeParY.addAll(this.listeSommets) ;
		Collections.sort(listeOrdonneeParY);
		
		i = 0 ;
		y = listeOrdonneeParY.get(0).getY() ;
		
		nvListe = new ArrayList<> () ;
		nvListe.add(new ArrayList<Point> ()) ;
		
		for (i = 0 ; i < listeOrdonneeParY.size() ; i ++) {

			if (y != listeOrdonneeParY.get(i).getY()) {
				
				nvListe.add(new ArrayList<Point> ()) ;
				y = listeOrdonneeParY.get(i).getY() ;
				
			}

			nvListe.get(nvListe.size() - 1).add(listeOrdonneeParY.get(i)) ;
			
		}
		
		return nvListe ;
		
	}
	
	/**
	 * Retourne la plus petite et la plus grande coordonnées x du Polygone
	 * 
	 * @return Les extrêmités x du Polygone
	 */
	
	public double[] minMaxX () {
		
		int i ;
		double min ;
		double max ;
		double[] minMax ;
		ArrayList<ArrayList<Point>> tableauOrdonne ;
		
		tableauOrdonne = new ArrayList<> () ;
		
		tableauOrdonne = this.tableauOrdonne() ;
		
		i = 0 ;
		min = tableauOrdonne.get(0).get(0).getX() ;
		max = tableauOrdonne.get(0).get(tableauOrdonne.get(i).size() - 1).getX() ;
		minMax = new double[2] ;	
		
		while (i < tableauOrdonne.size()) {
			
			if (tableauOrdonne.get(i).get(0).getX() < min)
				
				min = tableauOrdonne.get(i).get(0).getX() ;
			
			if (tableauOrdonne.get(i).get(tableauOrdonne.get(i).size() - 1).getX() > max)
				
				max = tableauOrdonne.get(i).get(tableauOrdonne.get(i).size() - 1).getX() ;
			
			i ++ ;
			
		}
		
		minMax[0] = min ;
		minMax[1] = max ;
		
		return minMax ;
		
	}
	
	/**
	 * Retourne la plus petite et la plus grande coordonnées y du Polygone
	 * 
	 * @return Les extrêmités y du Polygone
	 */
	
	public double[] minMaxY () {
		
		int i ;
		double min ;
		double max ;
		double[] minMax ;
		
		ArrayList<ArrayList<Point>> tableauOrdonne ;
		
		tableauOrdonne = new ArrayList<> () ;
		
		tableauOrdonne = this.tableauOrdonne() ;
		
		i = 0 ;
		min = tableauOrdonne.get(0).get(0).getY() ;
		max = tableauOrdonne.get(0).get(tableauOrdonne.get(i).size() - 1).getY() ;
		minMax = new double[2] ;	
		
		while (i < tableauOrdonne.size()) {
			
			if (tableauOrdonne.get(i).get(0).getY() < min)
				
				min = tableauOrdonne.get(i).get(0).getY() ;
			
			if (tableauOrdonne.get(i).get(tableauOrdonne.get(i).size() - 1).getY() > max)
				
				max = tableauOrdonne.get(i).get(tableauOrdonne.get(i).size() - 1).getY() ;
			
			i ++ ;
			
		}
		
		minMax[0] = min ;
		minMax[1] = max ;
		
		return minMax ;
		
	}
	
	/**
	 * Affiche le Polygone à l'aide d'étoiles
	 */
	
	public String toString () {
		
		int i ;
		int j ;
		int k ;
		int l ;
		double[] minMaxX ;
		double[] minMaxY ;
		boolean trouve ;
		String affichage ;
		String espacesX ;
		String espacesY ;
		ArrayList<ArrayList<Point>> listeAffichage ;

		affichage = "" ;
		espacesX = "" ;
		espacesY = "" ;
		listeAffichage = new ArrayList<> () ;
		listeAffichage = this.tableauOrdonne() ;
		minMaxX = this.minMaxX() ;
		minMaxY = this.minMaxY() ;		
		
		for (i = 0 ; i < Math.abs(minMaxY[0]) ; i ++) {
			
			espacesY += '\n' ;
			
		}
		
		for (j = 0 ; j < Math.abs(minMaxX[1]) ; j ++) {
			
			espacesX += ' ' ;
			
		}
		
		affichage += espacesY ;

		for (i = 0 ; i <= Math.abs(minMaxY[1] - minMaxY[0]) ; i ++) {

			l = 0 ;
			trouve = false ;
			affichage += espacesX ;

			while (!trouve && l < listeAffichage.size()) {
				
				if (i + minMaxY[0] == listeAffichage.get(l).get(0).getY())

					trouve = true ;
				
				l ++ ;

			}
			
			if (!trouve)

				affichage += '\n' ;

			else {					
				
				l -- ;

				for (j = 0 ; j <= Math.abs(minMaxX[1] - minMaxX[0]) ; j ++) {

					k = 0 ;
					trouve = false ;

					while (!trouve && k < listeAffichage.get(l).size()) {

						if (j + minMaxX[0] == listeAffichage.get(l).get(k).getX())

							trouve = true ;

						k ++ ;

					}

					if (!trouve)

						affichage += ' ' ;

					else

						affichage += '*' ;
					
					if (j == Math.abs(minMaxX[1] - minMaxX[0]))
						
						affichage += '\n' ;

				}
				
			}

		}
		
		return affichage ;
		
	}

}
