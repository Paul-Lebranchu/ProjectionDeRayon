package formes;

import java.lang.*;

public class Vecteur{
	
	private double premier;
	private double second;
	private double ternaire;
	
	public Vecteur(double premier, double second, double ternaire){
		this.premier = premier;
		this.second = second;
		this.ternaire = ternaire;
	}

	/** <h1>la methode produitScalair</h1>
	 * <p>
	 *Des vecteurs orhogonaux sont des vecteur dont le produit scalaire est nul, on calcul un produit scalaire en utilisant cette règle:
	 * u​⃗​​.​v​⃗​​ = xx' + yy' + zz'
	 * 
	 * ou celle si:
	 * u​⃗​​.​v​⃗​​ = ∣∣​u​⃗​​∣∣ × ∣∣​v​⃗​​∣∣ × cosinus(​u​⃗​​,​v​⃗​​)
	 * 
	 * de plus si les vecteurs sont othogonaux, les droites sont perpendiculaire.
	 *</p>
	 * */
	public double produitScalair(Vecteur vectb){
		double prodScal = this.getX()*vectb.getX() + this.getY()*vectb.getY() + this.getZ()*vectb.getZ();
		return prodScal;
	}

	/** <h1>la methode normeVecteur </h1>
	 * <p>
	 *	La norme d'un vecteur est sa longueur et sa direction.
	 *	Elle se calcul en faisant:
	 *		√(vX + vY + vZ)
	 *</p>
	 * */
	public double normeVecteur(){
		if((this.getX() + this.getY() + this.getZ()) >0){
		double normeVect = Math.sqrt(this.getX() + this.getY() + this.getZ());
		return normeVect;}
		return Math.sqrt(0- (this.getX() + this.getY() + this.getZ()));
	}

/**<h1>la methode produitVect </h1>
	 * <p>
	 *	Cette méthode renvois le vecteur representant le produit vectoriel de this et du vecteur vectd 
	 * angle est l'angle entre this et le vecteur vectd.
	 *</p>
	 * */
	public Vecteur produitVect(Vecteur vectd, double angle){
		return (new Vecteur( (Math.sin(angle) * (this.getX() * vectd.getX()) ), (Math.sin(angle) * (this.getY() * vectd.getY()) ), (Math.sin(angle) * (this.getZ() * vectd.getZ()) ) ));
	}

	public double getX(){
		return this.premier;
	}
	
	public double getY(){
		return this.second;
	}
	
	public double getZ(){
		return this.ternaire;
	}

 }
