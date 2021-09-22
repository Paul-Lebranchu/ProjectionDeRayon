package formes;

import java.lang.*;

public class Droite{
	
	private Point premier;
	private Point second;
	
	public Droite(Point premier, Point second){
		this.premier = premier;
		this.second = second;
	}
	
	public Droite(){
		this.premier = new Point(1, 1, 1);
		this.second = new Point (0, 0, 0);
	}
 
 	/**<h1>la methode vecteurUnitair </h1>
	 * <p>
	 *	Un vecteur unitaire est un vecteur dont la norme vaut 1. 
	 *	Il nous sera util ici pour la rotation vectorielle,
	 *	cette rotation dans un plan 3D étant défini par un angle et un vecteur unitaire
	 *	donnant ainsi un sens de rotation et un angle de rotation le sens n'est pas nécessaire dans un plan 2D.
	 *</p>
	 *<p>
	 *	Dans ctte méthode, on verifie que le vecteur ne soit pas unitaire, 
	 *	si il ne l'est pas, on divise chaqune de ces valeurs (x, y, z) par sa norme et 
	 *	on obtient ainsi le vecteur unitaire de la droite avec la quelle on a appelé la méthode.
	 *</p>
	 * */
	public Vecteur vecteurUnitair(){
		Vecteur vect = this.vecteur();
		if (vect.normeVecteur() == 1){
			return vect;
		}
		else{
			Vecteur vecte = new Vecteur(vect.getX()/vect.normeVecteur(), vect.getY()/vect.normeVecteur(), vect.getZ()/vect.normeVecteur());// on peux ce le permettre car, la norme d'un vecteur est toujours positive
			return vecte;
		}
	}

	/**<h1>la methode calculLongueur</h1>
	 * <p>
	 *Afin de calculer la longueur d'un segment, on applique la formule:
	 * √((Xb - Xa)² + (Yb - Ya)² + (Zb - Za)²)
	 *</p>
	 */
	public double calculLongueur(){
		double longueur = Math.sqrt( Math.pow((second.getX() - premier.getX()), 2) + Math.pow((second.getY() - premier.getY()), 2) + Math.pow((second.getZ() - premier.getZ()),2));
		return longueur;
	}
	
	/**<h1>la methode vecteur</h1>
	 * <p>
	 *Cette methode permet de renvoyer un vecteur. 
	 * Un vecteur se calcule selon la règle suivante: 
	 * u​⃗​​(Xb - Xa, Yb - Ya, Zb - Za)
	 *</p>
	 * */
	public Vecteur vecteur(){
		Vecteur vect = new Vecteur(second.getX() - premier.getX(), second.getY() - premier.getY(), second.getZ() - premier.getZ());
		return vect;
	}

	/**<h1>la methode vectCoordoA1</h1>
	 * <p>
	 *Cette methode permet de renvoyer un vecteur dont la coordonné première est un pour facilité les calcul liée au calcul d'intersection. 
	 *Problème lié a cette manière de faire: les calculs effectué sont sur des base de valeurs approximative. 
	 *Ainsi le résultat final sera une approximation d'approximation.
	 *Je me suis permise de faire ainsi car un ordinateur peu récupèrer un grand nombre de chiffre après la virgule et évité de trop gros équart entre la réalité et le calcul.
	 *</p>
	 * */
	public Vecteur vectCoordoA1(){
		Vecteur vect = new Vecteur(1, (second.getY() - premier.getY())/(second.getX() - premier.getX()), (second.getZ() - premier.getZ())/(second.getX() - premier.getX()));
		return vect;
	}
/*Cette méthode est la même que dans lumière car je n'ai pas trouvé le moyen de changer une classe près existante*/
   public double approximativeDroit(int approx, double d){
   		int nbZero = 1;
		for(int i =0; i<approx; i++){
			nbZero = nbZero*10;
		} 
		double nouv = d*nbZero;
		int recup = (int)nouv;
		double recupd = recup;
		double renvoi = recupd/nbZero;
		return renvoi;
   }
/**<h1>la methode trouveT </h1>
	 * <p>
	 *	Cette méthode permet de retrouver la valeur de t d'une representation parametrique de droite.
	 * on cherche donc a exprimer t en fonction de t' puis t' en foction des valeurs(comme dans un systeme).
	 * De plus, on verifie que l'on divise pas par un zero, si tel est le cas, 
	 * on applique la règle des limites comme quoi, a/0+ = +infini, on donne donc a t' la valeur de 100000.
	 *</p>
	 * */
	public double trouveT(Droite d){
		Point prem = d.getPrem();
		double tPrim = 2;
		Point thisp = this.getPrem();
		Vecteur vectd = d.vecteur();
		Vecteur vect = this.vecteur();
		double tPrimp1 = (thisp.getY() - prem.getY()) * vect.getX();
		double tPrimp2 = (prem.getX() - thisp.getX()) / vect.getY();
		double tPrimp3 = (vectd.getY() * vect.getX() ) - (vectd.getX() * vect.getY());
		if (tPrimp3 == 0){
			tPrim = 100000;
		}else{
			tPrim = (tPrimp1 + tPrimp2)/ tPrimp3;
		}
		double t = (prem.getX() + vectd.getX()* tPrim - thisp.getX()) / vect.getX();
		return t;
	}

	/**<h1>la methode represPram </h1>
	 * <p>
	 *	Cette méthode permet de créer la representation parametrique d'une droite.
	 *	Cette representation nous est utile pour calculer le point d'intersection d'une droite et d'un plan. 
	 *	Une fois le t rentrée, on récupère un point appartenant au plan dont l'equation a été utilisé pour retrouver t.
	 *</p>
	 * */
	public Point represPram(double t){
		Vecteur vect = this.vecteur();

		double x = this.premier.getX() + (vect.getX() * t);
		double y = this.premier.getY() + (vect.getY() * t);
		double z = this.premier.getZ() + (vect.getZ() * t);

		Point obtien = new Point( x,y,z);
		return obtien;
	}

/* les "/**" permettent de créer une documentation java*/
	/**
	 * <h1>la methode calculAngle</h1>
	 * <p>
	 * cette méthode se base sur des calculs de trigoometrie. 
	 *on sait que le produit scalaire de deux vecteur vaut:
	 *u​⃗​​.​v​⃗​​ = ∣∣​u​⃗​​∣∣ × ∣∣​v​⃗​​∣∣ × cosinus(​u​⃗​​,​v​⃗​​) mais aussi  u​⃗​​.​v​⃗​​ = xx' + yy' + zz'
	 * Ainsi, on calcul le scalaire des vecteurs des droites dont on veux récuperer l'angle.
	 * On le divise par la norme du premier vecteur fois celle du second.
	 * @see normeVecteur
	 * Puis, on effectue un argcosinus du tout se qui nous permet de récuperer un angle en radiant.
	* </p>
	*/
	public double calculAngle(Droite normal){
		Vecteur vecta = this.vecteur();
		Vecteur vectb = normal.vecteur();
		double scal = vecta.produitScalair(vectb);
		double normeVectIncidence = vecta.normeVecteur();
		double normeVectNormal = vectb.normeVecteur();

		double angleNormalIncidence = Math.acos(scal/(approximativeDroit(5, normeVectIncidence) * approximativeDroit(5, normeVectNormal)));

		return angleNormalIncidence;
	}

	public Point getPrem(){
		return this.premier;
	}
	
	public Point getSec(){
		return this.second;
	}
 }
