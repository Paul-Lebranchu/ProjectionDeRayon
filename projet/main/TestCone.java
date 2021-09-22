package main;

import formes.*;
import java.util.*;
import java.io.*;
import javafx.scene.paint.Color;
/*
Classe test s'assurant que le bon nombre d'objet Cone soit créé: on met
un fichier ne contenant que des cones, on les comptes et ensuite on créé des
objets de type Cone, on les stoke dans une liste puis on parcours la liste et
s'assure que les objets créé soit bien des objets du bon type et qu'il y en a le bon nombre
*/

public class TestCone{

  public static void main(String args[]){
    Interprete nom = new Interprete("./pov/testCone.pov");
    try{
      nom.lectureCompteur();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }catch(IOException er){
      er.printStackTrace();
    }

    ArrayList<Cone> list = new ArrayList<>();

    for (int i =0; i < nom.compteurCone ; i++){
      String[] rayCentre = nom.cone().get(0).get(i).split(",");

      Point centreUn = new Point(Double.parseDouble(rayCentre[0]),Double.parseDouble(rayCentre[1]),Double.parseDouble(rayCentre[2]));
      Double rayonUn = Double.parseDouble(rayCentre[3]);
      Point centredeux = new Point(Double.parseDouble(rayCentre[4]),Double.parseDouble(rayCentre[5]),Double.parseDouble(rayCentre[6]));
      Double rayonDeux = Double.parseDouble(rayCentre[4]);
      Color gris = Color.rgb(125,125,125);

      Cone cone = new Cone(centreUn,centredeux,rayonUn,rayonDeux,gris);

      list.add(cone);
    }

    System.out.println("");
    System.out.println("il y a "+ list.size() +" Cone");
    System.out.println("");

    for (Cone c: list){
      System.out.println(c);
      System.out.println("");
    }
  }
}
