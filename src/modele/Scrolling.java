package modele;

import javafx.scene.layout.Pane;
import vue.Tuile;

public class Scrolling {
	
	private int xTranslate;
	private Pane pane;
	private Pane paneMap;
	private Pane paneInventaire;

	public Scrolling(Pane pane,Pane paneMap,Pane paneInventaire) {
		this.xTranslate = 0;
		this.paneMap=paneMap;
		this.pane=pane;
		this.paneInventaire=paneInventaire;
	}
	
	public void faireScroll(String direction,Personnage Perso) {
		if(direction=="D" && (Perso.getX()>this.paneMap.getWidth())) 
			this.xTranslate-=Perso.getDistanceDeplacement();
		else if(direction=="Q"&& xTranslate<0  )
			this.xTranslate+=Perso.getDistanceDeplacement();
		this.pane.setTranslateX(xTranslate);
		this.paneInventaire.setTranslateX(-xTranslate);
	}
	
	public int getX() {
		return xTranslate;
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
