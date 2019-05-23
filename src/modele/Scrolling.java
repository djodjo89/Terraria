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

}
