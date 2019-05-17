package modele;

import physique.Collisionneur;

public class Bloc extends Inventeriable{
	
	public Bloc (String tag) {
		super(tag);
	}
	public Bloc(String tag, double pv, Collisionneur c) {
		super (tag,pv,c);
	}
}
