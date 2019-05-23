package modele;

import physique.Collisionneur;
import physique.GameObject;

public abstract class Inventeriable extends GameObject {
	
	public Inventeriable(String tag) {
		super(tag);
	}
	public Inventeriable(String tag, double pv, Collisionneur c) {
		super (tag,pv,c);
	}
	
	public Inventeriable() {
		super("vide");
	}
}
