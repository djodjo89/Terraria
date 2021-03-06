package fabriques;

import java.io.File;

import application.NomClasse;
import javafx.scene.image.Image;
import objetRessources.*;
import physique.Collisionneur;
import ressources.Images;
import modele.* ;

public class FabriqueImages {
	
	public static Images initialiserImages () {
		
		Images images=new Images();
		System.out.println(NomClasse.retrouver(new PersonnagePrincipal()));
		images.ajouterImage(NomClasse.retrouver(new PersonnagePrincipal()), "gif");
		images.ajouterImage(NomClasse.retrouver(new Licorne()), "gif");
		images.ajouterImage(NomClasse.retrouver(new LicorneVolante()), "png");
		images.ajouterImage(NomClasse.retrouver(new Granite()), "png");
		images.ajouterImage(NomClasse.retrouver(new Terre()), "png") ;
		images.ajouterImage(NomClasse.retrouver(new Air()), "png");
		images.ajouterImage("fondInventaire", "png");
		images.ajouterImage(NomClasse.retrouver(new Foreuse()), "png");
		images.ajouterImage(NomClasse.retrouver(new RetroFusee(new Collisionneur())), "gif");
		images.ajouterImage(NomClasse.retrouver(new Tronconneuse(new Collisionneur())), "gif");
		images.ajouterImage(NomClasse.retrouver(new Torche(new Collisionneur())), "png");
		
		images.ajouterImage("fondInventaireSel", "png");
		
		images.ajouterImage("heart", "png");

		images.ajouterImage(NomClasse.retrouver(new BlocBiomasse()), "png");
		images.ajouterImage(NomClasse.retrouver(new BlocBois()), "png");
		images.ajouterImage(NomClasse.retrouver(new BlocDechet()), "png");
		images.ajouterImage(NomClasse.retrouver(new BlocElectromagnetique()), "gif");
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
