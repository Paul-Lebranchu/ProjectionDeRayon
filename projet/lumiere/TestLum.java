package lumiere;

import formes.Point;
import formes.Droite;
import formes.Vecteur;
import java.lang.*;

public class TestLum{

  public static void main(String args[]){
    Lumiere l = new Lumiere(new Point(0,0,0), new Point(1,1,1));
    Droite i = l.creaRayonIncident();
    Droite k = new Droite(new Point(2,0,0), new Point(0,1,1));
    Droite j = l.creaDroiteAPartirDAngle(1, i, k);
    Droite m = new Droite(new Point(1,1,1), new Point(0,1,1));
    Droite r =  l.reflexion( m);
    Droite f =  l.refraction(m, 1.0003, 1.7);

    double a = 1;
    Vecteur u = k.vecteur();
    Vecteur n = i.vecteurUnitair();

    double cosaEtucorx = Math.cos(a) * u.getX();
    double cosaEtucory = Math.cos(a) * u.getY();
    double cosaEtucorz = Math.cos(a) * u.getZ();

    Droite rayonIncident = l.creaRayonIncident();
    double b = rayonIncident.calculAngle(m);
    Droite rayonReflechit = l.creaDroiteAPartirDAngle(b*2, m, rayonIncident);

    Vecteur vecta = rayonIncident.vecteur();
    Vecteur vectb = m.vecteur();
    double scalai = vecta.produitScalair(vectb);
    double normeVectIncidence = vecta.normeVecteur();
    double normeVectNormal = vectb.normeVecteur();
    double angleNormalIncidence = Math.acos(scalai/(Math.sqrt(2.5)));
    double pois = Math.sqrt(2.5);


    System.out.println("creaRayonIncident " + i.getPrem() + i.getSec()  + "");
    System.out.println("OlindeRodrigues " + l.OlindeRodrigues(1, 0.25, 0.3, new Vecteur(1,1,1), new Vecteur(0,0,0)) + "");
    System.out.println("creaDroiteAPartirDAngle " + j.getPrem() + j.getSec()  + "");
    System.out.println("reflexion " + r.getPrem() + r.getSec()  + "");
    System.out.println("refraction " + f.getPrem() + f.getSec()  + "");

    System.out.println("rayonIncident " + rayonIncident.getPrem() + rayonIncident.getSec()  + "");
    System.out.println("angle " + b  + "");
    System.out.println("rayonReflechit " + rayonReflechit.getPrem() + rayonReflechit.getSec()  + "");
    
    System.out.println("scalei " + scalai  + pois+ "");
    System.out.println("normevectmulti " + l.approximative(5,normeVectIncidence) * l.approximative(5,normeVectNormal) + vectb.getX() + vectb.getY() + vectb.getZ() + normeVectIncidence + "");
    System.out.println("angleNormalIncidence " + angleNormalIncidence  + "");

    System.out.println("approxdetail " + l.approximative(5,normeVectIncidence)+ "");
  }
}