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

	public boolean depasseLesLimitesDeLaMap (Terrain t, Moteur m) throws HorsDeLaMapException {

		boolean depasse = false ;
		System.out.println(this.getBoite().get(3));
		System.out.println(t.getDerniereCase().getCollisionneur().getBoite().get(3));
		System.out.println("maxX : " + Math.ceil(this.boite.minMaxX()[1]));
		if (this.boite.minMaxY()[0] < 0 || this.boite.minMaxX()[0] < 0 || Math.ceil(this.boite.minMaxY()[1]) >= t.getDerniereCase().getCollisionneur().getBoite().get(3).getY() || Math.ceil(this.boite.minMaxX()[1]) >= t.getDerniereCase().getCollisionneur().getBoite().get(3).getX())
		{
			depasse = true ;System.out.println("hors de la map");}

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

		if (!this.depasseLesLimitesDeLaMap(terrain, moteur)) {

			// On crée un collisionneur virtuel qui se déplace sur la case visée
			collisionneurTemporaire = new Collisionneur () ;
			collisionneurTemporaire.getBoite().copie(this.boite) ;

			while (deplacementPossible && (Math.abs(nouveauVecteur.getX()) < Math.abs(vecteur.getX()) || Math.abs(nouveauVecteur.getY()) < Math.abs(vecteur.getY())) && !collisionneurTemporaire.depasseLesLimitesDeLaMap(terrain, moteur)) {
				System.out.println("oighrzeeoughzouh");
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
					
					if (collisionneurTemporaire.depasseLesLimitesDeLaMap(terrain, moteur) || (collisionneurTemporaire.chevauche(terrain.getCase(coordonneesDuPoint, moteur).getCollisionneur()) != null && terrain.getCase(coordonneesDuPoint, moteur).getTag().equals("T"))) {

						deplacementPossible = false ;
						System.out.println(coordonneesDuPoint[0]+":"+coordonneesDuPoint[1]);
						System.out.println(terrain.getCase(coordonneesDuPoint, moteur).getCollisionneur().getBoite().get(0));
						System.out.println("Déplacement impossible");

					}

					i ++ ;

				}
				


			}
			
			if (!deplacementPossible) {
				
				collisionneurTemporaire.getBoite().ajouterAChaquePoint(new Vecteur (-vecteur.getX() / 100, -vecteur.getY() / 100)) ;
				nouveauVecteur.ajouter(-vecteur.getX() / 100, -vecteur.getY() / 100);
				
			}

		}
		
		else
			
			nouveauVecteur.ajouter(-vecteur.getX() / 100, -vecteur.getY() / 100);
		
		System.out.println("nouveau vecteur renvoyé : " + nouveauVecteur);
		
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
