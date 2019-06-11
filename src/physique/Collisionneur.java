package physique;

import modele.* ;
import exceptions.* ;
import geometrie.* ;

/**
 * <h1>Un Collisionneur est un objet possédant un Polygone
 * appelé "boîte".</h1>
 * <p>Il sert à :</>
 * <ul>
 * 		<li>Détecter, à partir d'un Terrain et d'un
 * 		moteur, si la boîte se situe hors des limites de la map</li>
 * 		<li>Vérifier s'il peut se déplacer selon un certain
 * 		vecteur, et renvoyer un vecteur plus petit si nécessaire</li>
 * 		<li>Vérifier s'il chevauche un autre Collisionneur, et renvoyer
 * 		le Point de chevauchement le cas échéant</li>
 * 		<li>Placer dans un tableau d'entier ses coordonnées selon le
 *		 Moteur</li>
 * 		<li>Fournir sa boîte</li>
 * </ul>
 * @see Point
 * @see Polygone
 * @see Vecteur
 * @see Moteur
 * @see Terrain
 * 
 * @author Mathys
 * @version 2.0
 *
 */

public class Collisionneur {

	Polygone boite ;
	
	/**
	 * Crée un Collisionneur vide
	 * 
	 * @version 2.0
	 */

	public Collisionneur () {

		this.boite = new Polygone () ;

	}
	
	/**
	 * Crée un Collisionneur à partir des Points
	 * entrés en paramètres
	 * 
	 * @param points
	 * 
	 * @version 2.0
	 */
	
	public Collisionneur (Point... points) {

		this() ;
		this.boite = new Polygone (points) ;		

	}
	
	/**
	 * 
	 * Retourne vrai si le Collisionneur dépasse les
	 * limites de la map passée en paramètre
	 * 
	 * @param t
	 * @param m
	 * @return
	 * @throws HorsDeLaMapException
	 * 
	 * @version 2.0
	 */

	public boolean depasseLesLimitesDeLaMap (Terrain t, Moteur m) throws HorsDeLaMapException {

		boolean depasse = false ;

		if (!this.boite.estInclusDans(t.getDerniereCase().getCollisionneur().getBoite().get(3).getX(), t.getDerniereCase().getCollisionneur().getBoite().get(3).getY()))
		
			depasse = true ;

		return depasse ;

	}

	/**
	 * 
	 * <h1>Retourne le Vecteur dont peut se déplacer le Collisionneur dans la direction donnée</h1>
	 * <p>A partir du Vecteur, du Terrain et du Moteur passés en paramètre, calcule la position d'un
	 * "Collisionneur virtuel". S'il est en collision, retourne un vecteur réduit, sinon retourne le vecteur tel quel</p>
	 * 
	 * @param vecteur
	 * @param terrain
	 * @param moteur
	 * @return Le Vecteur dont peut se déplacer le Collisionneur
	 * @throws VousEtesCoinceException
	 * @throws HorsDeLaMapException
	 */

	public Vecteur deplacementPossible (Vecteur vecteur, Terrain terrain, Moteur moteur) throws VousEtesCoinceException, HorsDeLaMapException {

		int i ;
		int[] coordonneesDuPoint ;
		boolean deplacementPossible ;
		Collisionneur collisionneurTemporaire ;
		Vecteur nouveauVecteur ;
		Vecteur vecteurDeplacement ;
		
		deplacementPossible = true ;
		nouveauVecteur = new Vecteur (0, 0) ;
		vecteurDeplacement = new Vecteur (vecteur.getX() / 200, vecteur.getY() / 200) ;

		if (!this.depasseLesLimitesDeLaMap(terrain, moteur)) {

			// On crée un collisionneur virtuel qui se déplace sur la case visée
			collisionneurTemporaire = new Collisionneur () ;
			collisionneurTemporaire.getBoite().copie(this.boite) ;

			while (deplacementPossible && (Math.abs(nouveauVecteur.getX()) < Math.abs(vecteur.getX()) || Math.abs(nouveauVecteur.getY()) < Math.abs(vecteur.getY())) && !collisionneurTemporaire.depasseLesLimitesDeLaMap(terrain, moteur)) {

				collisionneurTemporaire.getBoite().ajouterAChaquePoint(vecteurDeplacement) ;
				nouveauVecteur.ajouter(vecteurDeplacement);
				
				/*
				 * i <- 0
				 * Tant Que le d�placement est possible et que le point � l'indice i ne chevauche pas d'obstacle
				 * 	Récupérer ses coordonnées enti�res
				 * 	Récupérer le Collisionneur situé à ces coordonnées
				 * 	Si c'est un obstacle
				 * 		Voir si notre collisionneur le chevauche
				 * 		S'il le chevauche
				 * 			le déplacement est impossible
				 *		Fin Si
				 * 	Fin Si
				 * Fin Tant Que
				 */

				i = 0 ;
				coordonneesDuPoint = new int[2] ;

				while (deplacementPossible && i < collisionneurTemporaire.getBoite().nbSommets()) {

					this.getCoordonneesEntieresSurLaMap(collisionneurTemporaire.getBoite().get(i), moteur, coordonneesDuPoint) ;

					if (collisionneurTemporaire.depasseLesLimitesDeLaMap(terrain, moteur) || (collisionneurTemporaire.pointDeChevauchement(terrain.getCase(coordonneesDuPoint, moteur).getCollisionneur()) != null && terrain.getCase(coordonneesDuPoint, moteur).estUnObstacle())) {

						deplacementPossible = false ;

					}

					i ++ ;

				}
				
			}

		}

//		if (this.depasseLesLimitesDeLaMap(terrain, moteur) || !deplacementPossible) {
//
//			nouveauVecteur.ajouter(-vecteur.getX() / 200, -vecteur.getY() / 200);
//			
//		}
		
		return nouveauVecteur ;
	
	}

	/**
	 * Retourne, le cas échéant, le Point d'intersection entre ce
	 * Collisionneur et un autre. Sinon, retourne null
	 * 
	 * @param collisionneur
	 * @return Le Point d'intersection entre les Collisionneur
	 */
	
	public Point pointDeChevauchement (Collisionneur collisionneur) {

		return this.getBoite().intersection(collisionneur.getBoite()) ;

	}
	
	/**
	 * Place les coordonnées du Collisionneur dans coordonneesAModifier
	 * 
	 * @param point
	 * @param moteur
	 * @param coordonneesAModifier
	 */

	public void getCoordonneesEntieresSurLaMap (Point point, Moteur moteur, int[] coordonneesAModifier) {

		int i, j ;
		
		i = 0;
		j = 0 ;

		while (point.getX() >= moteur.getTailleBoiteX()) {

			point = point.substract(moteur.getTailleBoiteX(), 0) ;

			i ++ ;

		};;

		while (point.getY() > moteur.getTailleBoiteY()) {

			point = point.substract(0, moteur.getTailleBoiteY()) ;

			j ++ ;

		}

		coordonneesAModifier[0] = i ;
		coordonneesAModifier[1] = j ;

	}
	
	/**
	 * Retourne le Polygone du Collisionneur
	 * 
	 * @return La boîte du Collisionneur
	 */
	
	public Polygone getBoite () {

		return this.boite ;

	}

}
