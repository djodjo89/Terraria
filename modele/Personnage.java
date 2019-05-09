package modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Personnage {
	private DoubleProperty x;
	private DoubleProperty y;
	
 
	public Personnage(double x, double y) {
		this.x=new SimpleDoubleProperty(x);
		this.y=new SimpleDoubleProperty(y);
	}
	
	public void deplace() {
		this.x.setValue(this.x.getValue()+0.2);;
	}
	
	public final double getX() {
		return this.x.getValue();
	}
	public final void setX(double x) {
		this.x.setValue(x);
	}
	public final DoubleProperty xProperty() {
		return this.x;
	}
	public final double getY() {
		return this.y.getValue();
	}
	public final void setY(double y) {
		this.y.setValue(y);
	}
	public final DoubleProperty yProperty() {
		return this.y;
	}
}