package modele;

import physique.Collisionneur;


public class Granite extends Bloc {
		
		public Granite (String tag) {
			
			super(tag) ;
			this.setObstacle() ;
			
		}
		
		public Granite (String tag,  Collisionneur collisionneur) {
			
			super(tag, 0, collisionneur,true) ;
			this.setObstacle() ;
			
		}
}
