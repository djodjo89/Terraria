package objetRessources;

import application.NomClasse;
import modele.Jeu;

public class Main extends Outil{
	
	
	public Main() {
		super(10);
	}

	@Override
	public void Utilisation(int x, int y, Jeu jeu) {

		super.destructionBloc(jeu, x,y);
		
	}
	


	
	

}
