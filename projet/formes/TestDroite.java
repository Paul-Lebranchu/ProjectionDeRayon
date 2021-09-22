package formes;

import java.util.*;
import java.lang.*;
import java.awt.Color;

public class TestDroite{

  public static void main(String args[]){
    Droite d = new Droite(new Point(0,0,0), new Point(1,1,1));
    Droite b = new Droite(new Point(1,0,0), new Point(1,1,1));
    Vecteur u = d.vecteurUnitair();
    Vecteur v = d.vecteur();
    Vecteur w=d.vectCoordoA1();

    

    System.out.println("Vecteur unitaire de droite" + u.getX() + u.getY()  + u.getZ() + "");
    System.out.println("Longueur de droite " + d.calculLongueur() +"");
    System.out.println("Vecteur de droite " + v.getX() + v.getY()  + v.getZ() +"");
    System.out.println("Vecteur avec coordonnée a 1 de droite " + w.getX() + w.getY()+ w.getZ()+"");
    System.out.println("Valeur de t en croisant deux droites " + d.trouveT(b) +"");
    System.out.println("Le point de croisement des droites " + d.represPram(d.trouveT(b)) +"");
    System.out.println("Angle entre deux droite " + d.calculAngle(b) +"");
  }
}