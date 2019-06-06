package controleur;

import com.sun.javafx.tk.Toolkit;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import modele.Inventeriable;
import modele.Jeu;
import modele.PersonnagePrincipal;
import vue.Tuile;

public class ControleurInventaire {

	private Tuile t;
	private Jeu j;
	private Inventeriable objet;
	private PersonnagePrincipal perso;

	public ControleurInventaire(Tuile t, Jeu j, PersonnagePrincipal perso , Inventeriable obj) {
		this.t=t;
		this.j=j;
		this.perso = perso;
		this.objet = obj;
		this.setClickListener();
		
	}
	
	public void setClickListener() {
		this.t.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
			this.perso.donner(this.objet);	
			System.out.println(this.perso.getMain().getTag());
			event.consume();
		});
	}
	
	public void setObjet(Inventeriable ob) {
		this.objet=ob;
	}
}
