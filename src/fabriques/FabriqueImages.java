package fabriques;

import java.io.File;

import application.NomClasse;
import javafx.scene.image.Image;
import objetRessources.*;
import ressources.Images;
import modele.* ;

public class FabriqueImages {
	
	public static Images initialiserImages () {
		
		Images images=new Images();
		System.out.println(NomClasse.retrouver(new PersonnagePrincipal()));
		images.ajouterImage(NomClasse.retrouver(new PersonnagePrincipal()), "gif");
		images.ajouterImage(NomClasse.retrouver(new Licorne()), "gif");
		images.ajouterImage("granite", "png");
		images.ajouterImage(NomClasse.retrouver(new Terre()), "png") ;
		images.ajouterImage(NomClasse.retrouver(new Air()), "png");
		images.ajouterImage("fondInventaire", "png");
		images.ajouterImage(NomClasse.retrouver(new Foreuse()), "png");

		images.ajouterImage("fondInventaireSel", "png");

		images.ajouterImage("1", "png");
		images.ajouterImage("2", "png");
		images.ajouterImage("3", "png");
		images.ajouterImage("4", "png");
		images.ajouterImage("5", "png");
		images.ajouterImage("6", "png");

		images.ajouterImage(NomClasse.retrouver(new BlocBiomasse()), "png");
		images.ajouterImage(NomClasse.retrouver(new BlocBois()), "png");
		images.ajouterImage(NomClasse.retrouver(new BlocDechet()), "png");
		images.ajouterImage(NomClasse.retrouver(new BlocElectromagnetique()), "png");
		images.ajouterImage(NomClasse.retrouver(new BlocMetalique()), "png");
		images.ajouterImage(NomClasse.retrouver(new BlocPlastique()), "png");
		images.ajouterImage(NomClasse.retrouver(new BlocBois()), "png");
		images.ajouterImage(NomClasse.retrouver(new Electronique()), "png");
		images.ajouterImage(NomClasse.retrouver(new Metal()), "png");
		images.ajouterImage(NomClasse.retrouver(new Pierre()), "png");
		images.ajouterImage(NomClasse.retrouver(new Plastique()), "png");
		images.ajouterImage(NomClasse.retrouver(new TableCraft()), "png");

		
		return images;
	}

}
