package formes;

import java.util.ArrayList;
import java.awt.Color;
import java.lang.*;

public class Cercle{
   protected Point centre;
   protected double rayon;

   public Cercle(Point centre, double rayon){
      this.centre = centre;
      this.rayon = rayon;
   }

/**<h1>la methode calculDiscriminant</h1>
 *<p> On cherche la valeur de t telquel appartient au cercle.
 *on se base donc sur l'equation cartesienne du cercle:
 *((i+at)-x)² + ((j+bt)-y)² + ((k+ct)-z)² - r²=0
 * ijk le point de la droite
 * abc le vecteur de la droite
 * xyz le point centre du cercle
 * r le rayon du cercle
 * après calcul on trouve une equation du second degré sous la forme at² +bt -c = 0.
 *On cherche sont discriminant.
 *on renvois le point de la droite d.
  *</p>*/
   public Point calculDiscriminant(Droite d, Point incid){
      Point ijk = d.getPrem();
      Vecteur abc = d.vecteur();
      Point xyz = this.centre;
      double r = this.rayon;

      double a = (2*Math.pow(ijk.getX(), 2)*Math.pow(abc.getX(), 2))+ (2*Math.pow(ijk.getY(), 2)*Math.pow(abc.getY(), 2)) +(2*Math.pow(ijk.getZ(), 2)*Math.pow(abc.getZ(), 2)) + Math.pow(abc.getX(), 2) +Math.pow(abc.getY(), 2) +Math.pow(abc.getZ(), 2);
      double b = xyz.getX()*abc.getX() +xyz.getY()*abc.getY() +xyz.getZ()*abc.getZ();
      double c =0 -( Math.pow(r, 2) -Math.pow(ijk.getX(), 2) -Math.pow(ijk.getY(), 2) -Math.pow(ijk.getZ(), 2) -Math.pow(xyz.getX(), 2) -Math.pow(xyz.getY(), 2) -Math.pow(xyz.getZ(), 2) + 2 *xyz.getX()*ijk.getX() + 2 *xyz.getY()*ijk.getY() + 2 *xyz.getZ()*ijk.getZ());

      double discriminant = Math.pow(b, 2) - 4* a* c;
      if (discriminant == 0){
         double t = 0 - b/ (2*a);
         return d.represPram(t);

      }if (discriminant >= 0){
         double t1 = (0-b - Math.sqrt(discriminant))/(2*a);
         double t2 =(0-b + Math.sqrt(discriminant))/(2*a);

         Point x1 = d.represPram(t1);
         Point x2 = d.represPram(t2);

         Droite x1incid = new Droite(x1, incid);
         Droite x2incid = new Droite(x2, incid);
         if(x1incid.calculLongueur() < x2incid.calculLongueur()){
            return x1;
         }return x2;
      }return (new Point());//ce return ne s'effectuera pas pour droite normal car le point appartient au cone.
   }
}
