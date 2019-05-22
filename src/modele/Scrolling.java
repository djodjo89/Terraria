package modele;

import javafx.scene.layout.Pane;
import vue.Tuile;

public class Scrolling {
	
	private int xTranslate;
	private Pane pane;
	private Pane paneMap;

	public Scrolling(Pane pane,Pane paneMap) {
		this.xTranslate = 0;
		this.paneMap=paneMap;
		this.pane=pane;
	}
	
	public void faireScroll(String direction,double xPerso) {
		if(direction=="D" && xPerso>600 && paneMap.getWidth()>xPerso-600 ) 
			this.xTranslate-=10;
		else if(direction=="Q"&& xTranslate<0 && paneMap.getWidth()>xPerso-600)
			this.xTranslate+=10;
		pane.setTranslateX(xTranslate);
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
