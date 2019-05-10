package modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {
	
	public ObservableList<Objet> listeObjets ;
	
	public Inventaire () {
		
		this.listeObjets = FXCollections.observableArrayList() ;
		
	}

}
