package formes;

import java.util.*;
import java.lang.*;

public class TestCercle{

  public static void main(String args[]){

    Cercle cercle = new Cercle(new Point(0,0,0), 1);
    Droite d = new Droite(new Point(0,0,1), new Point(1,1,1));
    Point incid = new Point(1, 1, 0);

    Point point = cercle.calculDiscriminant(d, incid);//on a 0,0,1
    System.out.println("Les coordonnées du point entre la droite passant par 1,1,1 et 0,0,1 et le cercle de centre 0,0,0 et de rayon 1 est " + point + "");

    Droite b = new Droite(new Point(0,0,2), new Point(2,2,2));
    Point incidi = new Point(2, 2, 0);

    Point p = cercle.calculDiscriminant(b, incidi);//0n a 0,0,0
    System.out.println("Les coordonnées du point entre la droite passant par 1,1,1 et 0,0,1 et le cercle de centre 0,0,0 et de rayon 1 est " + p + "");

    Droite bi = new Droite(new Point(0,0,1), new Point(0,1,0));
    Point inciidi = new Point(0, 0-1, 1);

    Point pi = cercle.calculDiscriminant(bi, inciidi);//on a 0,0,1
    System.out.println("Les coordonnées du point entre la droite passant par 1,1,1 et 0,0,1 et le cercle de centre 0,0,0 et de rayon 1 est " + pi + "");

  }
}
