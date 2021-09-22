package formes;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import java.lang.*;

public class Cone{
 protected Point pointBas;
 protected Point pointHaut;
 protected double rayonB;//rayon du cercle du bas
 protected double rayonH;//rayon du cercle du haut
 protected Color coul;

 public Cone(Point pointBas,Point pointHaut,double rayonB,double rayonH,Color coul){
   this.pointBas = pointBas;
   this.pointHaut = pointHaut;
   this.rayonB = rayonB;
   this.rayonH = rayonH;
   this.coul = coul;
 }

 public Cone(){
   this.pointBas= new Point();
   this.pointHaut = new Point(0,0,2);//messures choisie arbitrairement
   this.rayonB= 1;
   this.rayonH= 0;
   this.coul = Color.rgb(125,125,125);
 }

/**<h1>la methode pointIncidence</h1>
 *<p> On calcul le centre du cone:
   * on a le centre du cercle servant de base et le sommet du cone.
   * Ainsi, pour récuperer le centre du cone il suffit de trouver le point situé au milieu du segment sommet centreCerle
  *</p>
  *<p>Ensuite, on cherche le point commun entre la droite lumiere et la surface du cone.
   * Pour cela, nous faisons (voir: https://homeomath2.imingo.net/cone1.htm)
   * on va chercher a l'angle entre le rayon et la hauteur.
   * On va donc rechercher t puis appeler la fonction represPram permettant en sachant t de retrouver un point d'intersection entre la doroite et le cone.
  *</p>*/
 public Point pointIncidence(Point lum){

   Point pCentre= new Point();

   pCentre.setX((pointBas.getX()+pointHaut.getX())/2);
   pCentre.setY((pointBas.getY()+pointHaut.getY())/2);
   pCentre.setZ((pointBas.getZ()+pointHaut.getZ())/2);

   Droite lumiere = new Droite(lum, pCentre);

   Point pointCercle = new Point(this.pointBas.getX() + this.rayonB , this.pointBas.getX(), this.pointBas.getX());

   Droite rayon = new Droite(pointCercle, pointBas);


   double angle = lumiere.calculAngle(rayon);

   double t = valTSystEquaParam(lumiere, angle);
   Point pt = lumiere.represPram(t);

   return pt;
 }

/**<h1>la methode valTSystEquaParam</h1>
 *<p> resolution d'un systeme a quatre équation entre la representation parametrique de droite
   *et l'equation quartesienne d'un cone de révolution x^2 + y^2 -z^2*tan(a)^2 =0.
   * X Y Z étant les points rechercher. On va donc rechercher t puis le retourner.
  *</p>*/
   public double valTSystEquaParam(Droite d, double a){
      Vecteur vect = d.vecteur();
      Point pt = d.getPrem();
      double p1 = Math.pow(pt.getX(), 2) + Math.pow(pt.getY(), 2) - Math.pow(pt.getZ(), 2)*Math.tan(a);
      double p2 = 2*Math.pow(pt.getX(), 2)*Math.pow(vect.getX(), 2) + 2*Math.pow(pt.getY(), 2)*Math.pow(vect.getY(), 2) + Math.pow(vect.getX(), 2) + Math.pow(vect.getY(), 2) + 2*Math.pow(pt.getZ(), 2)*Math.pow(vect.getZ(), 2)*Math.tan(a) + Math.pow(vect.getZ(), 2)*Math.tan(a);//tout est au carré donc pas de probème de égal à 0
      double p = p1/p2;
      return Math.sqrt(p);
   }

/**<h1>la methode droiteNormal</h1>
 *<p> Une fois le point incidence récuperé, on crée un vecteur orthogonal a deux autres vecteur
   * le vecteur sommet incidence et le vecteur incidence point de la base circulaire appartenant a la droite.
 *</p>*/
  public Droite droiteNormal(Point lum){
   Point incidence = pointIncidence(lum);
   Droite h = new Droite(incidence, this.pointHaut);
   Cercle c = new Cercle(this.pointBas, this.rayonB);
   Point basDroitIncid = c.calculDiscriminant(h, incidence);
   Plan coteCone = new Plan(incidence, this.pointHaut, basDroitIncid);
   Vecteur vect = coteCone.vectorme();
   Point recup = new Point((incidence.getX() - vect.getX()), (incidence.getY() - vect.getY()), (incidence.getZ() - vect.getZ()));
   Droite d = new Droite(incidence,recup);
   return d;
  }

 public Point getBas(){
   return this.pointBas;
 }

 public Point getHaut(){
   return this.pointHaut;
 }

 public double getRayonB(){
   return this.rayonB;
 }

 public double getRayonH(){
  return this.rayonH;
  }
 public Color getCoul(){
  return this.coul;
  }
}
