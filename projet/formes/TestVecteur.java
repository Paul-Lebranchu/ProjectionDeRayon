package formes;

import java.util.*;
import java.lang.*;
import java.awt.Color;

public class TestVecteur{

  public static void main(String args[]){
    Droite d = new Droite(new Point(0,0,0), new Point(1,1,1));
    Droite c = new Droite(new Point(1,0,0), new Point(1,1,1));
    Vecteur a = d.vecteur();
    Vecteur b = c.vecteur();
    
    double z = a.produitScalair(b);
    double y = a.normeVecteur();
    Vecteur x = a.produitVect(b, d.calculAngle(c));

    

    System.out.println("produitScalair" + z + "");
    System.out.println("normeVecteur" + y + "");
    System.out.println("produitVect" + x.getX() + x.getY() + x.getZ() + "");

  }
}