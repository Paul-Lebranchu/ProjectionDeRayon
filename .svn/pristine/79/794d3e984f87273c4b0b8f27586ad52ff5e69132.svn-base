package lumiere;

import formes.Point;
import formes.Droite;
import java.lang.*;

public class TestSha{

  public static void main(String args[]){
    Lumiere l = new Lumiere(new Point(0,0,0), new Point(1,1,1));
    Droite n = new Droite(new Point(1,1,1), new Point(0,1,1));
    Shadow s = new Shadow(1, n, l);


    System.out.println("diffuse" + s.diffuse(0.6) + "");
    System.out.println("speculaire" + s.speculaire(0.4, new Point(2,2,2), 1) + "");

  }
}