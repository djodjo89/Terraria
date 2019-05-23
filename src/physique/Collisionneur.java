package physique;

import modele.* ;
import exceptions.* ;
import geometrie.* ;

public class Collisionneur {

	Polygone boite ;

	public Collisionneur () {

		this.boite = new Polygone () ;

	}
	
	public Collisionneur (Point... points) {

		this() ;
		this.boite = new Polygone (points) ;		

	}

	public boolean depasseLesLimitesDeLaMap (Terrain t) throws HorsDeLaMapException {

		boolean depasse = false ;

		if (this.boite.minMaxY()[0] < 0 || this.boite.minMaxX()[0] < 0 || this.boite.minMaxY()[1] > t.getTailleY() || this.boite.minMaxY()[1] > t.getTailleX())

			throw new HorsDeLaMapException (t) ;

		return depasse ;

	}

	// Renvoie la distance dont peut se d�placer le perso dans la direction donn�e

	public Vecteur deplacementPossible (Vecteur vecteur, Terrain terrain, Moteur moteur) throws VousEtesCoinceException, HorsDeLaMapException {

		int i ;
		int[] coordonneesDuPoint ;
		boolean deplacementPossible ;
		Collisionneur collisionneurTemporaire ;
		Vecteur nouveauVecteur ;
		
		deplacementPossible = true ;
		nouveauVecteur = vecteur ;

		if (!depasseLesLimitesDeLaMap(terrain)) {
			
			while (nouveauVecteur.getX() > 0 && nouveauVecteur.getY() > 0 && deplacementPossible) {
				
				// On cr�e une collisionneur virtuel qui se d�place sur la case vis�e
				collisionneurTemporaire = new Collisionneur () ;
				collisionneurTemporaire.getBoite().copie(this.boite) ;
				collisionneurTemporaire.getBoite().ajouterAChaquePoint(vecteur) ;

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
					
					coordonneesDuPoint = this.getCoordonneesEntieresSurLaMap(collisionneurTemporaire.getBoite().get(i), moteur) ;
					
					if (terrain.getCase(coordonneesDuPoint, moteur).estUnObstacle()) {
						
						if (collisionneurTemporaire.chevauche(this.getCase(coordonneesDuPoint, terrain, moteur).getCollisionneur()) != null) {
							
							deplacementPossible = false ;
							System.out.println("D�placement impossible");
							
						}
						
					}
					
					i ++ ;
					
				}
				
				if (!deplacementPossible) {
					
					nouveauVecteur = new Vecteur (vecteur.getX() - 1, vecteur.getY() - 1) ;
					
				}
				
			}

		}

		else {

			throw new VousEtesCoinceException (this, terrain, moteur) ;

		}
		
		return nouveauVecteur ;
	
	}

	private Point chevauche (Collisionneur collisionneur) {

		int i ;
		int j ;
		boolean chevauche ;
		
		Point coordonneesIntersection ;
		Segment segmentDeMonCollisionneur, segmentDeLAutreCollisionneur ;

		i = 0 ;
		j = 0 ;
		chevauche = false ;
		
		coordonneesIntersection = null ;

		while (!chevauche && i < this.boite.nbSommets()) {

			segmentDeMonCollisionneur = new Segment (this.boite.get(i), this.boite.get((i + 1) % this.boite.nbSommets())) ;

			while (!chevauche && j < collisionneur.getBoite().nbSommets()) {

				segmentDeLAutreCollisionneur = new Segment (collisionneur.getBoite().get(j), collisionneur.getBoite().get((j + 1) % collisionneur.getBoite().nbSommets())) ;

				coordonneesIntersection = segmentDeMonCollisionneur.intersection(segmentDeLAutreCollisionneur) ;

				if (coordonneesIntersection != null) {

					chevauche = true ;

				}

				j ++ ;

			}

			i ++ ;

		}

		return coordonneesIntersection ;

	}

	private int[] getCoordonneesEntieresSurLaMap (Point point, Moteur moteur) {

		int i, j ;
		int[] coordonnees ;

		i = 0;
		j = 0 ;
		coordonnees = new int[2] ;

		while (point.getX() > moteur.getTailleBoiteX()) {

			point.subtract(moteur.getTailleBoiteX(), 0) ;

			i ++ ;

		}

		while (point.getY() > moteur.getTailleBoiteY()) {

			point.subtract(0, moteur.getTailleBoiteY()) ;

			j ++ ;

		}

		coordonnees[0] = i ;
		coordonnees[1] = j ;

		return coordonnees ;

	}
	
	public Polygone getBoite () {

		return this.boite ;

	}

}
