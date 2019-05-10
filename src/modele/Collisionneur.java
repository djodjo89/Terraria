package modele;

public class Collisionneur {
	
	private Personnage p ;
	
	public Collisionneur (Personnage p) {
		
		this.p = p ;
		
	}
	
	public boolean deplacementPossible (String direction, Terrain t) {
		
		boolean deplacementOK ;
		
		deplacementOK = false ;
		
		switch (direction) {
		
			case "haut" : deplacementOK = !tombeSurUnObstacle(0, -1, t) ; break ;
			case "droite" : deplacementOK = !tombeSurUnObstacle(1, 0, t) ; break ;
			case "bas" : deplacementOK = !tombeSurUnObstacle(0, 1, t) ; break ;
			case "gauche" : deplacementOK = !tombeSurUnObstacle(-1, 0, t) ; break ;
		
		}
		
		return deplacementOK ;
		
	}
	
	public boolean tombeSurUnObstacle (int x, int y, Terrain t) {
		
		return (int)this.p.getCoordonnees().getY() + y >= t.getDimY() || (int)this.p.getCoordonnees().getY() + y < 0 || (int)this.p.getCoordonnees().getX() + x >= t.getDimX() || (int)this.p.getCoordonnees().getX() + x < 0 || t.getListeLignes().get((int)this.p.getCoordonnees().getY() + y).get((int)this.p.getCoordonnees().getX() + x).equals("T") ;
		
	}

}
