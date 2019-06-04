package modele;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import modele.Personnage;

public class Scrolling {
	
	private SimpleDoubleProperty xTranslate;
	private SimpleDoubleProperty yTranslate;
	private Pane pane;
	private Pane paneMap;
	private Pane paneInventaire;

	public Scrolling(Pane pane,Pane paneMap,Pane paneInventaire) {
		this.xTranslate = new SimpleDoubleProperty(0);
		this.yTranslate = new SimpleDoubleProperty(0);
		this.paneMap=paneMap;
		this.pane=pane;
		this.paneInventaire=paneInventaire;
	}
	
	public void faireScroll(Personnage Perso) {
		this.xTranslate.setValue(-Perso.getX()+pane.getWidth()/2);
		this.yTranslate.setValue((-Perso.getY()+pane.getHeight()/2));
		this.pane.setTranslateY(this.getY());
		this.pane.setTranslateX(this.getX());
		this.paneInventaire.setTranslateX(-this.getX());
		this.paneInventaire.setTranslateY(-this.getY());
	}
	
	public double getX() {
		return xTranslate.getValue();
	}
	public double getY() {
		return yTranslate.getValue();
	}
	
	public SimpleDoubleProperty getXProperty() {
		return xTranslate;
	}
	public SimpleDoubleProperty getYProperty() {
		return yTranslate;
	}
	public void setX(double v) {
		this.xTranslate.setValue(v);
	}
	public void setY(double v) {
		this.yTranslate.setValue(v);
	}
	
	// Une version du scroll avec les vecteurs
	
	/*public void futurScroll (Vecteur vecteur, Personnage perso) {
		
		if (vecteur.getX() > 0 && perso.getX() > this.paneMap.getWidth() / 2 && 1.5 * this.paneMap.getWidth() > perso.getX())
			
			this.xTranslate -= perso.getVitesseX() ;
		
		else if (vecteur.getX() < 0 && this.xtranslate < 0 && this.paneMap.getWidth() - this.paneMap.getWidth() / 2)
			
			this.xTranslate += perso.getVitesseX() ;
		
		this.pane.setTranslateX(this.xTranslate);
		
		
			
		
	}*/

}
