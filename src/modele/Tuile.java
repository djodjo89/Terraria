package modele;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ressources.Images;

public class Tuile extends ImageView{
	private Images image;
	
	public Tuile(String id, double x, double y, Image img) {
		this.setId(id);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setImage(img);
		
	}
	
	
	//Constructeur qui initialise les coordonnée à 0
	public Tuile(String id, Image img) {
		this.setId(id);
		this.setLayoutX(0);
		this.setLayoutY(0);
		this.setImage(img);
	}
	
	//Constructeur qui initialise un objet vide
	public Tuile() {
		this.setId(null);
		this.setImage(null);
	}
	
	public void setCoordonneeEtId(String id, double x, double y) {
		this.setId(id);
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
	
	public void setImg(String img) {
		this.setImage(this.image.getImage(img));
	}
	
	
	
}
