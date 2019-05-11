package physique;

import modele.Personnage;
import modele.Terrain;
import java.util.ArrayList;

public class Collisionneur {
	
	public static boolean deplacementPossible (String direction, Terrain t,Personnage p, Moteur m) {
		
		boolean deplacementOK ;
		
		deplacementOK = false ;
		
		switch (direction) {
		
			case "haut" : deplacementOK = !tombeSurUnObstacle(0, -1, t,p, m) ; break ;
			case "droite" : deplacementOK = !tombeSurUnObstacle(1, 0, t,p, m) ; break ;
			case "bas" : deplacementOK = !tombeSurUnObstacle(0, 1, t,p, m) ; break ;
			case "gauche" : deplacementOK = !tombeSurUnObstacle(-1, 0, t,p, m) ; break ;
		
		}
		
		return deplacementOK ;
		
	}
	
	public static boolean tombeSurUnObstacle (int x, int y, Terrain t, Personnage p, Moteur m) {
		
		double xPos=p.getX()/m.getTailleTileX() ;
		double yPos=p.getY()/m.getTailleTileY() ;
		
		// Par défaut la case adjacente est un obstacle
		String caseAdjacente = m.getObstacles().get(0) ;
		System.out.println(yPos);
		System.out.println(xPos);
		
		boolean depasseMurGauche, depassePlafond, depasseFond, depasseMurDroite, rentreDansUnObstacle ;
		
		depasseMurGauche = (int)xPos + x < 0 && (xPos < m.getDistanceDeplacementPersos()) ;
		depasseMurDroite = (int)xPos + x >= t.getDimX() ;
		depassePlafond = (int)yPos + y < 0 ;
		depasseFond = (int)yPos + y >= t.getDimY() ;
		
		if (!(depasseMurGauche || depasseMurDroite || depassePlafond || depasseFond))
			caseAdjacente = t.getListeLignes().get((int)yPos + y).get((int)xPos + x) ;
		rentreDansUnObstacle = estUnObstacle(m.getObstacles(), caseAdjacente) ;
		
		return depasseMurGauche || depasseMurDroite || depassePlafond || depasseFond || rentreDansUnObstacle ;
		
	}
	
	public static boolean estUnObstacle (ArrayList<String> obstacles, String casE) {
		
		return obstacles.contains(casE) ;
		
	}

}
