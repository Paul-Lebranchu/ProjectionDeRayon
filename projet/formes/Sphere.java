 package formes;
 
import javafx.scene.paint.Color;

public class Sphere{
	protected Point centre;
	protected double rayon;
  protected Color coul;

	public Sphere(Point centre, double rayon,Color coul){
		this.centre=centre;
		this.rayon=rayon;
    this.coul = coul;
	}
/*on a besoin  du point lumineux en argument*/
	public Point pointIncidence(Point lum){

		double vectX = lum.getX() - this.centre.getX();
		double vectY = lum.getY() - this.centre.getY();
		double vectZ = lum.getZ() - this.centre.getZ();

		double x=vectX/rayon;
		double y=vectY/rayon;
		double z=vectZ/rayon;

		/*on evite de diviser par 0*/

		if(x == 0){
			x = 1;
		}

		if(y == 0){
				y = 1;
		}

		if(z == 0){
			z = 1;
		}

		vectX= vectX/x;
		vectY= vectY/y;
		vectZ= vectZ/z;

		Point pi= new Point();
		pi.setX(centre.getX() * vectX );
		pi.setY(centre.getY() * vectY ) ;
		pi.setZ(centre.getZ() * vectZ ) ;
		return pi;

	}
//on a besoin du point lumineux pour appeler la methode point d'incidence
	public Droite droiteNormal(Point lum){
		Droite d = new Droite(centre,pointIncidence(lum));
		return d;
	}

  public double getRayon(){
    return this.rayon;
  }

  public Point getCentre(){
    return this.centre;
  }
  public Color getCoul(){
		return this.coul;
	}
}
