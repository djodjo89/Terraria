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

	// Renvoie la distance dont peut se déplacer le perso dans la direction donnée

	public Vecteur deplacementPossible (Vecteur vecteur, Terrain terrain, Moteur moteur) throws VousEtesCoinceException, HorsDeLaMapException {

		int i ;
		int[] coordonneesDuPoint ;
		boolean deplacementPossible ;
		Collisionneur collisionneurTemporaire ;
		Vecteur nouveauVecteur ;
		
		deplacementPossible = true ;
		nouveauVecteur = new Vecteur (0, 0) ;
		
		// On crée une collisionneur virtuel qui se déplace sur la case visée
		collisionneurTemporaire = new Collisionneur () ;
		collisionneurTemporaire.getBoite().copie(this.boite) ;
		collisionneurTemporaire.getBoite().ajouterAChaquePoint(vecteur) ;

		if (!collisionneurTemporaire.depasseLesLimitesDeLaMap(terrain) && !depasseLesLimitesDeLaMap(terrain)) {
			
			while (Math.abs(nouveauVecteur.getX()) != Math.abs(vecteur.getX()) || Math.abs(nouveauVecteur.getY()) != Math.abs(vecteur.getY()) && deplacementPossible) {

				/*
				 * i <- 0
				 * Tant Que le déplacement est possible et que le point à l'indice i ne chevauche pas d'obstacle
				 * 	RÃ©cupÃ©rer ses coordonnÃ©es entières
				 * 	RÃ©cupÃ©rer le Collisionneur situÃ© Ã  ces coordonnÃ©es
				 * 	Si c'est un obstacle
				 * 		Voir si notre collisionneur le chevauche
				 * 		S'il le chevauche
				 * 			le dÃ©placement est impossible
				 *		Fin Si
				 * 	Fin Si
				 * Fin Tant Que
				 */
				
				i = 0 ;
				coordonneesDuPoint = new int[2] ;
				
				while (deplacementPossible && i < collisionneurTemporaire.getBoite().nbSommets()) {
					
					this.getCoordonneesEntieresSurLaMap(collisionneurTemporaire.getBoite().get(i), moteur, coordonneesDuPoint) ;
					System.out.println(coordonneesDuPoint[0] + ":" + coordonneesDuPoint[1]);
					System.out.println(terrain.getCase(coordonneesDuPoint, moteur).getTag());
					if (terrain.getCase(coordonneesDuPoint, moteur).getTag().equals("T")) {
						System.out.println(terrain.getCase(coordonneesDuPoint, moteur).getCollisionneur());
						if (collisionneurTemporaire.chevauche(terrain.getCase(coordonneesDuPoint, moteur).getCollisionneur()) != null) {
							
							deplacementPossible = false ;
							System.out.println("Déplacement impossible");
						
						}
						
					}
					
					i ++ ;
					
				}
				
				if (deplacementPossible) {
					
					nouveauVecteur.ajouter(vecteur.getX() - nouveauVecteur.getX(), vecteur.getY() - nouveauVecteur.getY());
					System.out.println(Math.abs(nouveauVecteur.getX()));
					System.out.println(vecteur.getX());
					
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

	private void getCoordonneesEntieresSurLaMap (Point point, Moteur moteur, int[] coordonneesAModifier) {

		int i, j ;
		
		i = 0;
		j = 0 ;

		while (point.getX() > moteur.getTailleBoiteX()) {

			point = point.substract(moteur.getTailleBoiteX(), 0) ;

			i ++ ;

		}

		while (point.getY() >= moteur.getTailleBoiteY()) {

			point = point.substract(0, moteur.getTailleBoiteY()) ;

			j ++ ;

		}
		
		for (int k = 0 ; k < 10 ;k ++) System.out.println("y : " + j);

		coordonneesAModifier[0] = i ;
		coordonneesAModifier[1] = j ;

	}
	
	public Polygone getBoite () {

		return this.boite ;

	}

}
