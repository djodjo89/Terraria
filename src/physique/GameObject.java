 package physique;
import geometrie.*;

import modele.* ;
import javafx.beans.property.* ;

/**
 * <h1>Un GameObject représente un objet qui interagirera avec d'autres objets d'un Jeu</h1>
 * <p>Un GameObject peut :</p>
 * <ul>
 * 		<li>Donner sa masse</li>
 * 		<li>Donner la puissance de son saut</li>
 * 		<li>Donner la hauteur à laquelle il se trouve</li>
 * 		<li>Se déplacer dans une certaine direction</li>
 * 		<li>Changer son nombre de pv</li>
 * 		<li>Dire si c'est un obstacle</li>
 * 		<li>Devenir un obstacle</li>
 * 		<li>Changer de tag</li>
 * 		<li>Donner son tag</li>
 * 		<li>Donner son nombre de pv</li>
 * 		<li>Perdre des pv</li>
 * 		<li>Donner sa position x</li>
 * 		<li>Donner sa position y</li>
 * 		<li>Donner sa propriété x</li>
 * 		<li>Donner sa propriété y</li>
 * 		<li>Changer sa position x</li>
 * 		<li>Changer sa position y</li>
 * 		<li>Changer sa position y</li>
 * 		<li>Changer sa vitesse</li>
 * 		<li>Donner sa vitesse de déplacement</li>
 * 		<li>Changer sa direction</li>
 * 		<li>Se déplacer à partir de sa position et de sa vitesse</li>
 * 		<li>Donner sa vitesse</li>
 * 		<li>Changer de Collisionneur</li>
 * 		<li>Donner son Collisionneur</li>
 * 		<li>Changer son coefficient de frottements</li>
 * </ul>
 * 
 * @see Collisionneur
 * @see Moteur
 * @see Vecteur
 * @author Mathys
 *
 */

public class GameObject {
	
	String tag ;
	private DoubleProperty pv ;
	private DoubleProperty posX ;
	private DoubleProperty posY ;
	private Vecteur vecteurVitesse ; 
	public double vitesseDeplacement ;
	private double masse ;
	private double hauteurSaut ;
	private boolean estUnObstacle ;
	private double coeffFrottement ;
	public boolean peutSauter ;
	private Collisionneur collisionneur ;
	
	/**
	 * Un Constructeur donnant un tag au GameObject
	 * 
	 * @param tag
	 */
	
	public GameObject (String tag) {
		
		this.tag = tag ;
		
	}
	
	/**
	 * Un Constructeur permettant d'entièrement initialiser un GameObject
	 * 
	 * @param tag
	 * @param pv
	 * @param posX
	 * @param posY
	 * @param masse
	 * @param collisionneur
	 */
	
	public GameObject (String tag, double pv, double posX, double posY, double masse, Collisionneur collisionneur) {
		
		this.vitesseDeplacement = 1 ;
		this.hauteurSaut = 10 ; // ((51.9 * this.hauteurSaut + 48.9 * this.masse - 2007) / m.getTailleBoiteY()*650)
		this.tag = tag ;
		this.posX = new SimpleDoubleProperty(pv) ;
		this.posX = new SimpleDoubleProperty(posX) ;
		this.posY = new SimpleDoubleProperty(posY) ;
		this.vecteurVitesse = new Vecteur(0, 0) ;
		this.masse = masse ;
		this.collisionneur = collisionneur ;
		this.estUnObstacle = false ;
		this.coeffFrottement = 0 ;
		this.peutSauter = false ;
		
	}
	
	public void setSautPossible () {
		
		this.peutSauter = true ;
		
	}
	
	public void setSautImpossible () {
		
		this.peutSauter = false ;
		
	}
	
	public void verifSiPeutSauter (Vecteur v) {
		
		if (v.getY() == 0.0)
			
			this.setSautPossible() ;
		
		else
			
			this.setSautImpossible() ;
		
	}
	
	/**
	 * Retourne la masse du GameObject
	 */
	
	public double getMasse () {
		
		return this.masse ;
		
	}
	
	/**
	 * Calcule et renvoie la puissance du saut du GameObject selon le Moteur
	 * 
	 * @param m
	 */
	
	private double getPuissanceSaut (Moteur m) {

		return this.hauteurSaut ;
		
	}
	
	/**
	 * Retourne la hauteur à laquelle se trouve le GameObject selon le Moteur et le
	 * Terrain
	 * 
	 * @param m
	 * @param t
	 */
	
	public double getHauteur (Moteur m, Terrain t) {
		
		int i ;
		int[] coordonnees ;
		
		i = 0 ;
		coordonnees = new int[2] ;
		
		this.collisionneur.getCoordonneesEntieresSurLaMap (new Point (this.getX(), this.getY()), m, coordonnees) ;
		
		while (!t.getCase(coordonnees, m).getTag().equals("T") && i < t.getDimY()) {
			
			coordonnees[1] += 1 ;
			i ++ ;
			
		}
		
		return i * m.getTailleBoiteY() ;
		
	}
	
	/**
	 * Ajoute au Vecteur vitesse du GameObject un nouveau Vecteur
	 * en fonction de la direction entrée en paramètre
	 * 
	 * @param direction
	 * @param m
	 */
	
	public void deplacerVers (String direction, Moteur m) {
		
		Vecteur vecteurDeplacement ;

		switch (direction) {

			case "haut" : if (this.peutSauter)
				
							vecteurDeplacement = new Vecteur (0, -this.getPuissanceSaut(m)) ;
			
						  else
							  
							vecteurDeplacement = new Vecteur (0, 0) ;
	
			break ;
	
			case "droite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, 0) ;
	
			break ;
	
			case "bas" : vecteurDeplacement = new Vecteur (0, this.vitesseDeplacement) ;
	
			break ;
	
			case "gauche" : vecteurDeplacement = new Vecteur (-vitesseDeplacement, 0) ;
	
			break ;
			
			case "hautdroite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, -this.getPuissanceSaut(m)) ;
	
			break ;
			
			case "basdroite" : vecteurDeplacement = new Vecteur (this.vitesseDeplacement, this.vitesseDeplacement) ;
	
			break ;
			
			case "basgauche" : vecteurDeplacement = new Vecteur (-vitesseDeplacement, this.vitesseDeplacement) ;
	
			break ;
			
			case "hautgauche" : vecteurDeplacement = new Vecteur (-this.vitesseDeplacement, -this.getPuissanceSaut(m)) ;
	
			break ;
			
			default : vecteurDeplacement = new Vecteur (0, 0) ;
			
			break ;

		}
		
		this.ajouter(vecteurDeplacement) ;
		
	}
	
	/**
	 * Change les pv du GameObject
	 * 
	 * @param pv
	 */
	
	public void setPv (double pv) {
		
		this.pv.set(pv) ;
		
	}
	
	/**
	 * Retourne vrai si le GameObject est un obstacle
	 */
	
	public boolean estUnObstacle () {
		
		return this.estUnObstacle ;
		
	}
	
	/**
	 * Met estUnObstacle à vrai
	 */
	
	public void setObstacle () {
		
		this.estUnObstacle = true ;
		
	}
	
	/**
	 * Change le tag du GameObject
	 * @param tag
	 */
	
	public void changerTag (String tag) {
		
		this.tag = tag ;
		
	}
	
	/**
	 * Retourne le tag du GameObject
	 */
	
	public String getTag () {
		
		return this.tag ;
		
	}
	
	/**
	 * Retourne la quantité de PV restants du GameObject
	 */
	
	public double getPV () {
		
		return this.pv.getValue() ;
		
	}
	
	/**
	 * Fait perdre des pv au GameObject
	 * 
	 * @param pv
	 */
	
	public void perdrePV (double pv) {
		
		this.pv.set(this.pv.getValue() - pv);
		
	}
	
	/**
	 * Retourne la position x du GameObject
	 */
	
	public double getX() {
		return this.posX.getValue();
	}
	
	/**
	 * Retourne la position y du GameObject
	 */
	
	public double getY() {
		return this.posY.getValue();
	}
	
	/**
	 * Retourne la propriété x du GameObject
	 */
	
	public DoubleProperty getXProperty() {
		return this.posX;
	}
	
	/**
	 * Retourne la propriété y du GameObject
	 */
	
	public DoubleProperty getYProperty() {
		return this.posY;
	}
	
	/**
	 * Change la position x du GameObject
	 * @param x
	 */
	
	private void setX(double x) {
		this.posX.setValue(x);
	}
	
	/**
	 * Change la position y du GameObject
	 * @param y
	 */
	
	private void setY(double y) {
		this.posY.setValue(y);
	}
	
	/**
	 * Change la vitesse du GameObject
	 * @param x
	 * @param y
	 */
	
	public void changerVitesse (double x, double y) {
		
		this.vecteurVitesse.ajouter(x, y) ;
		
	}
	
	/**
	 * Change la vitesse du GameObject
	 * @param v
	 */
	
	public void setVitesse (Vecteur v) {
		
		this.vecteurVitesse = v ;
		
	}
	
	/**
	 * Renvoie la vitesse de déplacement du GameObject
	 */
	
	public double getVitesseDeplacement () {
		
		return this.vitesseDeplacement ;
		
	}
	
	/**
	 * Ajoute le Vecteur entré en paramètre au GameObject
	 * @param vecteur
	 */
	
	public void ajouter (Vecteur vecteur) {
		
		this.vecteurVitesse.ajouter(vecteur);
		
	}
	
	/**
	 * Déplace le GameObject et son Collisionneur du Vecteur
	 * vitesse
	 */
	
	public void deplacer () {
		
		this.setX(this.getX() + this.vecteurVitesse.getX()) ;
		this.setY(this.getY() + this.vecteurVitesse.getY()) ;
		this.collisionneur.getBoite().ajouterAChaquePoint(this.vecteurVitesse) ;
		
	}
	
	/**
	 * Retourne le Vecteur vitesse du GameObject
	 */
	
	public Vecteur getVecteurVitesse () {
		
		return this.vecteurVitesse ;
		
	}
	
	/**
	 * Change le Collisionneur du GameObject
	 * @param collisionneur
	 */
	
	public void setCollisionneur (Collisionneur collisionneur) {
		
		this.collisionneur = collisionneur ;
		
	}
	
	/**
	 * Retourne le Collisionneur du GameObject
	 */
	
	public Collisionneur getCollisionneur () {
		
		return this.collisionneur ;
		
	}
	
	/**
	 * Change le coefficient du GameObject
	 * @param coeff
	 */
	
	public void setCoeffFrottement (double coeff) {
		
		this.coeffFrottement = coeff ;
		
	}

}
