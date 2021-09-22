package formes;

import java.util.*;
import java.lang.*;
import javafx.scene.paint.Color;

public class TestCylindre{

  public static void main(String args[]){
    Cylindre cylindre= new Cylindre(new Point(0,0,0), new Point(1,1,1), 1, Color.rgb(125,125,125));

    Point incid = cylindre.pointIncidence(new Point(0.5,2,0.5));
    Droite norm = cylindre.droiteNormal(new Point(1,1,1));
    System.out.println("Pointincidence sur cylindre" + incid + "");
    System.out.println("La droite normal au point d'incidence passe par " + norm.getPrem() + ", " +norm.getSec() +"");

    Point incident = cylindre.pointIncidence(new Point(1,1,1));
    Droite incidCentreBas = new Droite(incident, new Point(0,0,0));
    Point m = cylindre.pointCommunAvecCyl(incidCentreBas, new Point(1,1,1));

    Droite mIncid = new Droite(m, incident);

    Cercle bas = new Cercle(new Point(0,0,0), 1);
    Cercle haut = new Cercle(new Point(1,1,1), 1);
    Point enBas = bas.calculDiscriminant(mIncid, incident);
    Point enHaut = haut.calculDiscriminant(mIncid, incident);

    Plan p = new Plan(incident, enBas, enHaut);
    Vecteur vect = p.vectorme();
    Point recup = new Point((incident.getX() - vect.getX()), (incident.getY() - vect.getY()), (incident.getZ() - vect.getZ()));

    System.out.println("vectorme" + vect.getX() + ", " +vect.getY()+ vect.getZ() +"");

  }
}
