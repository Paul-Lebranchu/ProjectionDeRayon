/*fonction util:
	-java.lang.Math.cos(double a)
	-java.lang.Math.acos(double a)(les angles doivent être exprimé en radiant)
	-java.lang.Math.abs(float a)(mettre en valeur absolut)
	-java.util.ArrayList
	-java.lang.Math.sqrt
*/

/*source utilisée: Oracle.com, java pour les nuls et le cours*/

/*creation d'un package commun lumiere pour regrouper les feuilles*/
package lumiere;

import formes.Point;
import formes.Droite;
import formes.Vecteur;
import java.lang.*;

public class Lumiere{

	private Point pointIncidence;
	private Point pointLumineux;

	public Lumiere(Point pointIncidence, Point pointLumineux){
		this.pointIncidence = pointIncidence;
		this.pointLumineux = pointLumineux;
	}

 	/*Cette methode renvois un rayon de lumière qui va du point lumineux au
 	point d'impact sur l'objet*/
	public Droite creaRayonIncident(){
		return (new Droite(this.pointIncidence, this.pointLumineux));
	}
/* suite à des nan renvoyé par les fonction a une étude des problème, il est ressorti 
que lorque que l'on multipli deux double surpra long entre eux, il renvois nan donc en
approximativant on pert une infime parti de justesse et on obtient un résultat
cette méthode est aussi dans droite car je n'ai pas trouvé le moyen de changer une classe
Java près existante comme la classe double.*/
   public double approximative(int approx, double d){
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

	/**
	<h1>la methode creaDroiteAPartirDAngle</h1>
	* <p>
	* On recré la formule d'olinde rodrigues de façon a pouvoir l'utiliser en code:
	*	v​⃗​​  = (cos a)u​⃗​​ + (1 - cos a)(u​⃗​​.​n​⃗​)*n​⃗​​ + (sin a) * (u​⃗​​ x n​⃗​​)
	*	v est ici le vecteur optenu par la rotation du vecteur de la droite d selon l'angle a.
	*	u est le vecteur de la droite d
	*	n est un vecteur unitaire (norme de n vaut 1 (dans le cercle trigonometrique, les vecteurs sont unitaire))
	*	a est la valeur en radiant de l'angle entrée en paramètre de la fonction.
	* </p>
	* <p> 
	*	On obtient ainsi dans le cas de la coordonnée de Z par exemple:
	*	v​⃗​​  = (cos a)uZ + (1 - cos a)(u​⃗​​.​n​⃗​)*nZ + (sin a) * (uZ * nZ) 
	*
	*	Dans notre méthode afin de plus de lisibilité, nous avons coupé la formule au niveau des +.
	*
	*	On a refait le produit scalaire sans appeler la methode car, nous avions besoin d'un produit scalaire avec des vecteur. 
	*	Etant donnée que la méthode appel des droites, et que nous n'avons pas besoin du produit scalaire de deux vecteurs ailleur, 
	*	cette maniere de faire était la plus optimisée. Il aurait aussi était possible de renvoyer un point.
	*/
	public double OlindeRodrigues(double a, double ucor, double ncor, Vecteur u, Vecteur n){
		double cosaEtucor = Math.cos(a) * ucor;
		double scal = u.getX()*n.getX() + u.getY()*n.getY() + u.getZ()*n.getZ();
		double moinscosaEtScal = (1 - Math.cos(a)) * scal * ncor;
		double ucorEtncor = Math.sin(a) * (approximative(5, ncor) * approximative(5, ucor));

		return ( cosaEtucor + moinscosaEtScal + ucorEtncor );
	}

	/**
	<h1>la methode creaDroiteAPartirDAngle</h1>
	 * <p>
	 * on applique ici les règles de rotation Vectoriel.
	 * voir: https://www.youtube.com/watch?v=zjMuIxRvygQ&feature=youtu.be
	 * On suit la formule d'Olinde Rodrigues que l'on appelera pour chaque coordonné du vecteur car on ne peux pas multiplier par un vecteur en code.
	 * Il était aussi possible de créer une liste avec XYZ et une boucle qui traversé la liste afin d'éviter trop de répétition.
	 * Ce choix si me semblant plus lisible, je l'ai gardé.
	 * @see OlindeRodrigues
	 * </p>
	*/
	public Droite creaDroiteAPartirDAngle(double a, Droite unitaire, Droite d){
		Vecteur u = d.vecteur();
		Vecteur n = unitaire.vecteurUnitair();

		double vx = OlindeRodrigues(a, u.getX(), n.getX(), u, n);
		double vy = OlindeRodrigues(a, u.getY(), n.getY(), u, n);
		double vz = OlindeRodrigues(a, u.getZ(), n.getZ(), u, n);

    	Point pointProjete = new Point(vx + pointIncidence.getX(), vy + pointIncidence.getX(), vz + pointIncidence.getX());
    	Droite creerDroite = new Droite(this.pointIncidence, pointProjete);

    	return creerDroite;
	}

 	/**
 	 * <h1>la methode reflexion</h1>
 	 * <p>
 	 * Cette methode est utilisé dans le cas où, la lumière atteint
 	 *un objet réflechissant.
 	* </p>
 	* <p>
 	* La methode renvois le rayon réflechit(cette fonction s'applique sur tout objet
 	*réflechissant la lumière(la puissance de ce rayon dépend de la matière,
 	*si cette dernière est opaque, le rayon renvoyé est de même puissance que le
 	*rayon incident (celui qui atteint l'objet et provoquant la reflexion),
 	*ou transparente, ce qui renverra un rayon de puissance plus faible.
 	* </p>
	* <p>
 	*L'angle entre la normal et le rayon reflechit est égale à l'angle
 	*entre la normal et le rayon incident.
	* </p>
	* <p>
	* la fonction calcul l'angle (a) en radiant entre le rayon incident et la droite normal.
	* Elle crée ensuite une droite représentant la rotation du vecteur du rayon incident celon le vecteur unitaire de la droite normal,
	*pour cela on utilise la methode creaDroiteAPartirDAngle avec l'angle a * 2 car 
	*l'angle entre le rayon incidant et le rayon réflechit et le double de celui entre le rayon incident et la droite normal.
	*</p>*/
	public Droite reflexion( Droite normal){
		Droite rayonIncident = creaRayonIncident();
		double a = rayonIncident.calculAngle(normal);
		Droite rayonReflechit = creaDroiteAPartirDAngle(a*2, normal, rayonIncident);
		return rayonReflechit;
	}

	/**
	 * <h1>la methode refraction</h1>
	 * <p>
	 * On calcul ici la droite de refraction.
	 * Quand la lumière traverse un objet "transparent"
	 *(à tous degret de transparence), l'angle du rayon lumineux change et le rayon
	*se divise en deux (tant que l'objet n'est pas 100% transparent).
	* Ainsi, on applique la loi de snell-Descartes dans la quelle,
	*l'indice de refraction du milieu (n):
	* <ul>
	* <li> air : 1,0003 </li>
	* <li> eau : 1,33 </li>
	* <li> verre : 1,7 </li>
	* <li> glace : 1,31 </li>
	* </ul>
	* multiplié par le sinus de l'angle entre le rayon incident et
	*la droite normal est égal à l'indice de réfraction du nouveau milieu(n2)
	*fois le sinus de l'angle entre le rayon refracté et la
	*droite normal ce qui donne : n1 * sin(a1) = n2 * sin(a2)
	* n1: indice de réfraction du milieu d'origine
	* n2: indice de réfraction du nouveau milieur
	* a1: angle entre normal et rayon incident
	* a2: angle entre normal et rayon refracté
	* </p>
	* <p>
	* Pour cette méthode on va, récuperer l'angle a1
	*puis, le passer en sinus, pour ensuite le multiplé a n1.
	*On divisera le tout par n2 et pour finir, on fera un arccosinus du tout.
	*Ainsi, nous récupererons l'angle l'additionneront a l'angle a1 et 
	*appliqueront la méthode creaDroiteAPartirDAngle.
	*Pour enfin renvoyer le rayon refracté.

	*/
 	public Droite refraction( Droite normal, double n1, double n2){
		Droite rayonIncident = creaRayonIncident();
		double a1 = rayonIncident.calculAngle(normal);
		double sina1Etn1 = Math.sin(a1) * n1;
		double arcsinEtn2 = Math.asin(sina1Etn1/n2);

		Droite rayonReflechit = creaDroiteAPartirDAngle(a1 + arcsinEtn2, normal, rayonIncident);

		return rayonReflechit;
	}

	public Point getpointLumineux(){
		return this.pointLumineux;
	}
}
