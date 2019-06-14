package application;
	
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(); 
			URL url = new File("vue/VueTerraria.fxml").toURI().toURL();
			loader.setLocation(url);
			Pane root = new Pane();
			root = loader.load();
			Scene scene = new Scene(root,1920,1080);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Terraria");
			primaryStage.setFullScreen(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
