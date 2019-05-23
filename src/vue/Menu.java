package vue;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Menu {
	private Pane panePrincipal;
	private VBox menuBox;
	private Button resume;
	private ImageView fond;
	private Boolean estLa;
	
	public Menu(Pane panePrincipal) {
		this.panePrincipal=panePrincipal;
		System.out.println("coucou");
		fond= new ImageView(new Image(new File("image/backgroundMenu.jpg").toURI().toString()));
		panePrincipal.getChildren().add(fond);
		resume=new Button();
		resume.setText("Jouer");
		resume.setMinSize(500, 100);
		resume.setFont(Font.font("regular",FontWeight.NORMAL,50));
		menuBox = new VBox(-5);
		menuBox.getChildren().add(resume);
        resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                disparait();
            }
        });
		panePrincipal.getChildren().add(menuBox);
		estLa=true;
	}
	
	public void afficheMenu() {
		menuBox.setVisible(true);
        fond.setVisible(true);
        estLa=true;
	}
	
	public void disparait() {
		menuBox.setVisible(false);
        fond.setVisible(false);
        estLa=false;
	}
	
	public Boolean estAffiche() {
		return estLa;
	}
}
