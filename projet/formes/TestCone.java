package formes;

import java.util.*;
import java.lang.*;
import javafx.scene.paint.Color;

public class TestCone{

  public static void main(String args[]){
    Cone cone = new Cone(new Point(0,0,0), new Point(1,1,1), 1, 0, Color.rgb(125,125,125));

    Point incid = cone.pointIncidence(new Point(1,1,1));
    Droite d = new Droite(new Point(1,1,1), new Point(1,0,1));
    Droite norm = cone.droiteNormal(new Point(1,1,1));


   Droite lumiere = new Droite(new Point(1,1,1), new Point(0.5,0.5,0.5));
   System.out.println("lumiere" + lumiere.getPrem() + lumiere.getSec() + "");

   Point pointCercle = new Point(1 , 0, 0);

   Droite rayon = new Droite(pointCercle, new Point(0,0,0));
   System.out.println("lumiere" + rayon.getPrem() + rayon.getSec() + "");

   double angle = lumiere.calculAngle(rayon);
   System.out.println("lumiere" + angle + "");

    System.out.println("L'impact de la lumiere de source lumineuse 1,1,1 sur un cone de hauteurs 1 et de base circulaire centre 0,0,0 et rayon 1 est de" + incid + "");
    System.out.println("Valeur de t pour intersection entre cone et droite" + cone.valTSystEquaParam(d, 0.6) + "");

    System.out.println("La droite normal au point d'incidence passe par " + norm.getPrem() + ", " +norm.getSec() +"");
  }
}
