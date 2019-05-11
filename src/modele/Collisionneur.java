package modele;

public class Collisionneur {
	
	
	public static boolean deplacementPossible (String direction, Terrain t,Personnage p) {
		
		boolean deplacementOK ;
		
		deplacementOK = false ;
		
		switch (direction) {
		
			case "haut" : deplacementOK = !tombeSurUnObstacle(0, -1, t,p) ; break ;
			case "droite" : deplacementOK = !tombeSurUnObstacle(1, 0, t,p) ; break ;
			case "bas" : deplacementOK = !tombeSurUnObstacle(0, 1, t,p) ; break ;
			case "gauche" : deplacementOK = !tombeSurUnObstacle(-1, 0, t,p) ; break ;
		
		}
		
		return deplacementOK ;
		
	}
	
	public static boolean tombeSurUnObstacle (int x, int y, Terrain t, Personnage p) {
		double xPos=p.getX()/50;
		double yPos=p.getY()/50;
		System.out.println(yPos);
		System.out.println(xPos);
		
			if((int)yPos >= t.getDimY() ||(int)xPos >= t.getDimX())
				return true;
			else if(yPos<0 ||xPos<0)
				return true;
			else if((t.getListeLignes().get((int)yPos).get((int)xPos).equals("T")))
				return true;
			return false;
		
	}

}
