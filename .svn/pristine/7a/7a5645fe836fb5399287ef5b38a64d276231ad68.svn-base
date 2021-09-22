package lumiere;

import formes.Point;
import formes.Droite;
import java.lang.*;
/**
	 * <h1>la classe Shadow</h1>
	 * <p>
	 * Cette classe se basesur le modèle de Phong, 
	 * ce modèle consiste en la création de trois types de lumière sur un objet.
	 * On créer une lumière diffuse qui va representé la partie de l'objet couvert par la lumière.
	 * On aaussi une lumière ambiente qui anglobe tout l'objet et représente sa couleur de base.
	 * On a en dernier lieu lalumière speculaire qui represente lepoint ou lalumière a le plus fort impact.
	* </p>
	*/
public class Shadow{

	private double intensiteLumiere;
	private Droite normale;
	private Lumiere lumiere;

	public Shadow(double intensiteLumiere, Droite normale, Lumiere lumiere){
		this.intensiteLumiere = intensiteLumiere;
		this.normale = normale;
		this.lumiere = lumiere;
	}

/**
	 * <h1>la méthode diffuse</h1>
	 * <p>
	 * Cette méthode reprend la formule de composante diffuse de phong:
	 *I = Ip * Kd * cos(a)
	 *I donnant la composante diffuse en un point
	 *Ip étant l'intensité de la lumière, 
	 *a l'angle entre la normal et la lumière,
	 *kd la constante de la composante diffuse (comprise entre 0 et 1) qui dépend de l'aspect de l'objet.
	 *Ainsi, plus l'objet est "doux", plus la lumière se diffuse bien donc, plus la constante est proche de 1.
	 *</p>
	 *<p>
	 * la composante diffuse de phong se base sur le principe que,
	 * quand la lumière frappe un objet, les rayons lumineux sont projeté dans toutes les direction.
	 * ainsi, la quantité de lumière sur la surface va dépendre de l'angle a,
	 * plus il est petit, plus la luminosité est forte.
	 *</p>
	*/
	public double diffuse(double constCompoDiff){
		Droite rayonIncident = lumiere.creaRayonIncident();
		double a = rayonIncident.calculAngle( this.normale);
		return (this.intensiteLumiere * constCompoDiff * Math.cos(a));
	}

/**
	 * <h1>la méthode speculaire</h1>
	 * <p>
	 * Cette méthode reprend la formule de composante spéculaire de phong:
	 *I = Ip * Ks * (cos(a))^n
	 *I donnant la composante spéculaire en un point
	 *Ip étant l'intensité de la lumière, 
	 *a l'angle entre le rayon reflechit et la camera,
	 *kd la constante de la composante spéculaire (comprise entre 0 et 1) qui dépend de l'aspect de l'objet.
	 *n l'intensité spéculaire, plus elle est élevé plus le point est blanc et petit.
	 *Ainsi, plus l'objet est "reflechissant"(exemple le plastic, un planché verni..), plus le point lumineux spéculaire est intense donc, plus la constante est proche de 1.
	 *</p>
	 *<p>
	 * la composante spéculaire de phong se base sur le principe que,
	 quand la lumière frappe un objet lisse, elle crée un reflet sur l'objet.
	 *</p>
	*/
	public double speculaire(double constCompoSpec, Point camera, double intenSpec){
		Droite rayonReflechit = lumiere.reflexion( this.normale);
		Droite rayonVisu = new Droite(camera, lumiere.getpointLumineux());
		double a = rayonReflechit.calculAngle( rayonVisu);
		return (this.intensiteLumiere * constCompoSpec * Math.pow(Math.cos(a),intenSpec));
	}
}