package formes;

import java.util.*;
import java.lang.*;
import java.awt.Color;

public class TestPlan{

  public static void main(String args[]){
    Droite d = new Droite(new Point(0,0,0), new Point(1,1,1));
    Droite b = new Droite(new Point(0,1,0), new Point(1,1,1));
    Plan a3 = new Plan(d, b);
    Plan q = new Plan(new Point(2,2,2), new Point(1,1,1), new Point(0,0,0));

    double t = q.retrouveT(d);
    Point p = q.calculIntersectPlanDroite(d);
    double val = q.valDEquaPlan();
    Vecteur v = q.vectorme();
    Point pq = q.represPram(1, 0);


    System.out.println("retrouveT" + t + "");
    System.out.println("calculIntersectPlanDroite" + p.getX() + p.getY() + p.getZ() + "");
    System.out.println("represPram" + pq.getX() + pq.getY() + pq.getZ() + "");
    System.out.println("vectorme" + v.getX() + v.getY() + v.getZ() + "");
    System.out.println("valDEquaPlan" + val + "");
    
    System.out.println("Plan a3" + a3.getPrem() + a3.getSec() + a3.getTer() + "");

  }
}