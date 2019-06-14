package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CaseInventaire {
	
	private Label qte ;
	private StackPane pane ;
	private Tuile objet ;
	
	public CaseInventaire (Tuile t, double xLabel, double yLabel, double xTuile, double yTuile, double xPane, double yPane) {

		this.setLabel(xLabel, yLabel);			
		this.setTuile(t, xTuile, yTuile) ;
		this.setPane(xPane, yPane) ;
		
	}
	
	public CaseInventaire (double xPane, double yPane) {
		
		this.setPane(xPane, yPane) ;
		
	}
	
	public void setTuile (Tuile t, double xTuile, double yTuile) {
		
		this.objet = t ;
		this.objet.setFitWidth(xTuile);
		this.objet.setFitHeight(yTuile);
		
	}
	
	public void setLabel (double xLabel, double yLabel) {
		
		this.qte = new Label() ;
		this.qte.setTextFill(Color.web("#ffffff", 0.8));
		this.qte.setFont(Font.font ("Verdana", 20));
		this.qte.setTranslateX(xLabel);
		this.qte.setTranslateY(yLabel);		
		
	}
	
	public void setPane (double xPane, double yPane) {
		
		this.pane = new StackPane() ;
		this.pane.getChildren().add(this.objet) ;
		this.pane.getChildren().add(this.qte) ;
		this.pane.setTranslateX(xPane);
		this.pane.setTranslateY(yPane);
		
	}
	
	public StackPane getPane () {
		
		return this.pane ;
		
	}
	
	public Label getQte () {
		
		return this.qte ;
		
	}
	
	public Tuile getTuile () {
		
		return this.objet ;
		
	}

}
