package formes;

import java.lang.*;

public class Plan{

	private Point premier;
	private Point second;
	private Point ternaire;

	public Plan(Point premier, Point second, Point ternaire){
		this.premier = premier;
		this.second = second;
		this.ternaire = ternaire;
	}
	public Plan(Droite premier, Droite second){
		this.premier = premier.getPrem();
		this.second = second.getPrem();
		this.ternaire = premier.getPrem();
	}

	/**<h1>la methode retrouveT</h1>
	 * <p>
	*Cette méthode se base sur un systeme a quatre équation, l'equation de droite, et la representation parametrique de droite.
	*On résout ce systeme sur papier a la recherche d'une equation pour t. (notre calcul se base sur le vecteur dont la coordonné z vaut 1)
	*@see vectorm
	*Ainsi, on trouve t = (-aj -bk -cl -d)/(a*a' + b*b' + c*c') où:
	*abc sont les coordonnée du vecteur normal au plan.
	*jkl le point premier de la droite rentré en paramètre.
	*a'b'c' un vecteur de la droite entré en paramètre.
	*dval la valeur de d de l'equation du plan.
	*</p>
	*<p>
	*Comme il est impossible de diviser par 0, lorsque (a*a' + b*b' + c*c') vaut 0, 
	*nous renvoyons une valeur de t en appliquant la règle des limite seulon la quelle a/0+ = + infini.
	*On donne donc un grand chiffre a t, ici 100000.
	* </p>
	 */
	public double retrouveT(Droite d){
		Vecteur vectorme = this.vectorme();
		Point premierD = d.getPrem();
		Vecteur vect = d.vecteur();
		double dval = this.valDEquaPlan();

		double tp1 = 0 - (vectorme.getX() * premierD.getX()) - (vectorme.getY() * premierD.getY()) - premierD.getZ() - dval;
		double tp2 = (vect.getX() * vectorme.getX()) + (vect.getY() * vectorme.getY()) + vect.getZ();

		if (tp2 == 0){
			return 100000;
		}

		double t = tp1 / tp2;

		return t;
	}

/**<h1>la methode calculIntersectPlanDroite</h1>
	 * <p>
	*Cette méthode nous permet de récupérer les differente méthode permettant de 
	*calculer le point d'intersection afin d'aleger le code dans les differente forme.
	*On récupère la valeur de T et on l'appel dans la representation parametrique de la 
	*droite ce qui nous permet ainsi de récuperer le point d'intersection de la droite avec le plan.
	 *</p>
	 *<p>
	 *	en cas de t valant une valeur impossible en temps normal, 
	 *	on prend un t valant 1 afin d'avoir un résultat proche de la vérité. 
	 *</p>
	 */
	public Point calculIntersectPlanDroite(Droite d){
		double t = this.retrouveT(d);
		if (t == 100000){
			t = 1;
		}
		return (d.represPram(t));
	}

/**<h1>la methode valDEquaPlan</h1>
	 * <p>
	*Cette méthode calcul la valeur de de l'equation du plan : 
	*ax + by + cz + d = 0
	*d = -ax -by -cz
	*abc sont le vecteur normal.
	*xyz sont les coordonnée du point premier du plan.
	* </p>
	 */
	public double valDEquaPlan(){
		Vecteur vectorme = this.vectorme();
		double d = 0 - (vectorme.getX() * this.premier.getX()) - (vectorme.getY() * this.premier.getY()) - (vectorme.getZ() * this.premier.getZ());

		return d;
	}

/**<h1>la methode vectorme</h1>
	 * <p>
	*Cette méthode cherche les valeur xyz du vecteur normal au plan.
	*Un vecteur normal au plan est ortogonal a deux vecteur different du plan.
	*Ainsi, ici on récupere deux vecteurs chacun d'une droite du plan.
	*Une des droite relis le point premier au point second et l'utre premier a ternaire.
	*Il existe une infinité de vecteur normal et le calcul du vecteur normal nous permet une certaine liberté (d'ou le choix de z=1).
	*Ce calcul et basé sur le fait que, deux vecteur(v et u) orthogonaux donne: ux*vx + uy*vy + uz *vz =0.
	 *</p>
	 */
	public Vecteur vectorme(){
		Droite d1 = new Droite(this.premier, this.second);
		Droite d2 = new Droite(this.premier, this.ternaire);

		Vecteur xyz = d1.vecteur();
		Vecteur ijk = d2.vecteur();
		double i = 0;

		while((0- i * (ijk.getZ()* xyz.getY() - xyz.getZ() *ijk.getY()))/ (ijk.getY()*xyz.getX() - ijk.getX()*xyz.getY()) < 3){
			i+=0.1;
		}
		double x = 3;
		double z = i;
		double y = (xyz.getX()*x - xyz.getZ()*z )/xyz.getY();

		return (new Vecteur(x, y, z));
	}

/**<h1>la methode represPram</h1>
	 * <p>
	*Cette méthode calcul la represenation parametrique du plan en fonction d'un t et d'un t'.
	*une fois que l'on a un t et un t', on peu récuperer un point. 
	*t et t' se calcul de la m^me façon que le parametre t de la representation parametrique d'une droite .
	* @see retrouveT
	 *</p>
	 */
	public Point represPram(double t, double tPrim){
		Droite d1 = new Droite(this.premier, this.second);
		Droite d2 = new Droite(this.premier, this.ternaire);

		Vecteur vect1 = d1.vecteur();
		Vecteur vect2 = d2.vecteur();

		double x = this.premier.getX() + (vect1.getX() * t) + (vect2.getX() * tPrim);
		double y = this.premier.getY() + (vect1.getY() * t) + (vect2.getY() * tPrim);
		double z = this.premier.getZ() + (vect1.getZ() * t) + (vect2.getZ() * tPrim);

		Point obtien = new Point( x,y,z);
		return obtien;
	}

	public Point getPrem(){
		return this.premier;
	}

	public Point getSec(){
		return this.second;
	}

	public Point getTer(){
		return this.ternaire;
	}
}
