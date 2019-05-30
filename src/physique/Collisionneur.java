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

		/*if (this.boite.minMaxY()[0] < 0 || this.boite.minMaxX()[0] < 0 || this.boite.minMaxY()[1] > t.getTailleY() || this.boite.minMaxY()[1] > t.getTailleX())

			throw new HorsDeLaMapException (t) ;*/

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
		nouveauVecteur = new Vecteur (0, 0) ;

		if (!this.depasseLesLimitesDeLaMap(terrain)) {

			// On crée un collisionneur virtuel qui se déplace sur la case visée
			collisionneurTemporaire = new Collisionneur () ;
			collisionneurTemporaire.getBoite().copie(this.boite) ;

			while (deplacementPossible && (Math.abs(nouveauVecteur.getX()) < Math.abs(vecteur.getX()) || Math.abs(nouveauVecteur.getY()) < Math.abs(vecteur.getY())) && !collisionneurTemporaire.depasseLesLimitesDeLaMap(terrain)) {

				System.out.println("nv:"+Math.abs(nouveauVecteur.getY()));
				System.out.println("vecteur:"+Math.abs(vecteur.getY()));
				collisionneurTemporaire.getBoite().ajouterAChaquePoint(new Vecteur (vecteur.getX() / 100, vecteur.getY() / 100)) ;
				nouveauVecteur.ajouter(vecteur.getX() / 100, vecteur.getY() / 100);
				System.out.println(collisionneurTemporaire.getBoite().get(0));
				
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
					
					if (collisionneurTemporaire.chevauche(terrain.getCase(coordonneesDuPoint, moteur).getCollisionneur()) != null && terrain.getCase(coordonneesDuPoint, moteur).getTag().equals("T")) {

						deplacementPossible = false ;
						System.out.println(coordonneesDuPoint[0]+":"+coordonneesDuPoint[1]);
						System.out.println(terrain.getCase(coordonneesDuPoint, moteur).getCollisionneur().getBoite().get(0));
						System.out.println("Déplacement impossible");

					}

					i ++ ;

				}
				
				if (!deplacementPossible) {
					
					collisionneurTemporaire.getBoite().ajouterAChaquePoint(new Vecteur (-vecteur.getX() / 100, -vecteur.getY() / 100)) ;
					nouveauVecteur.ajouter(-vecteur.getX() / 100, -vecteur.getY() / 100);
					
				}

			}

		}

		else {

			throw new VousEtesCoinceException (this, terrain, moteur) ;

		}
		
		return nouveauVecteur ;
	
	}

	public Point chevauche (Collisionneur collisionneur) {

		return this.getBoite().intersection(collisionneur.getBoite()) ;

	}

	public void getCoordonneesEntieresSurLaMap (Point point, Moteur moteur, int[] coordonneesAModifier) {

		int i, j ;
		
		i = 0;
		j = 0 ;

		while (point.getX() >= moteur.getTailleBoiteX()) {

			point = point.substract(moteur.getTailleBoiteX(), 0) ;

			i ++ ;

		}

		while (point.getY() > moteur.getTailleBoiteY()) {

			point = point.substract(0, moteur.getTailleBoiteY()) ;

			j ++ ;

		}

		coordonneesAModifier[0] = i ;
		coordonneesAModifier[1] = j ;

	}
	
	public Polygone getBoite () {

		return this.boite ;

	}

}
