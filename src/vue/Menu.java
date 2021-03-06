package vue;

import java.io.File;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Menu {
	private Pane panePrincipal;
	private VBox menuBox;
	private Button resume;
	private Button exit;
	private ImageView fond;
	private Boolean estLa;
	private String fichier = "son/music.mp3" ;
	private Media son ;
	private MediaPlayer mediaPlayer;
	
	public Menu(Pane panePrincipal) {
		this.panePrincipal=panePrincipal;
		System.out.println("coucou");
		fond= new ImageView(new Image(new File("image/backgroundMenu.jpg").toURI().toString()));
		panePrincipal.getChildren().add(fond);
		resume=new Button();
		resume.setText("Jouer");
		resume.setMinSize(500, 100);
		resume.setFont(Font.font("regular",FontWeight.NORMAL,50));
		exit=new Button();
		exit.setText("Exit");
		exit.setMinSize(500, 100);
		exit.setFont(Font.font("regular",FontWeight.NORMAL,50));
		menuBox = new VBox(-5);
		menuBox.getChildren().add(resume);
		menuBox.getChildren().add(exit);
		this.son = new Media (new File(fichier).toURI().toString()) ;
		this.mediaPlayer = new MediaPlayer(son) ;
        resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                disparait();
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
		panePrincipal.getChildren().add(menuBox);
		estLa=true;
		mediaPlayer.play();
	}
	
	public void afficheMenu(double X,double y) throws URISyntaxException {
		fond.setTranslateX(-X);
		menuBox.setTranslateX(-X);
		fond.setTranslateY(-y);
		menuBox.setTranslateY(-y);
		menuBox.setVisible(true);
        fond.setVisible(true);
        estLa=true;
       
		mediaPlayer.play();
	}
	
	public void disparait() {
		menuBox.setVisible(false);
        fond.setVisible(false);
        estLa=false;
        mediaPlayer.pause();
	}
	
	public Boolean estAffiche() {
		return estLa;
	}
}
