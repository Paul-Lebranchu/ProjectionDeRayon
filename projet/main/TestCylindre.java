package main;

import formes.*;
import java.util.*;
import java.io.*;
import javafx.scene.paint.Color;

/*
Classe test s'assurant que le bon nombre d'objet Cylindre soit créé: on met
un fichier ne contenant que des cylindres, on les comptes et ensuite on créé des
objets de type Cylindre, on les stoke dans une liste puis on parcours la liste et
s'assure que les objets créé soit bien des objets du bon type et qu'il y en a le bon nombre
*/

public class TestCylindre{

  public static void main(String args[]){
    Interprete nom = new Interprete("./pov/testCylindre.pov");
    try{
      nom.lectureCompteur();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }catch(IOException er){
      er.printStackTrace();
    }

    ArrayList<Cylindre> list = new ArrayList<>();

    for (int i =0; i < nom.compteurCylindre ; i++){
      String[] rayCentre = nom.cylindre().get(0).get(i).split(",");

      Point centreUn = new Point(Double.parseDouble(rayCentre[0]),Double.parseDouble(rayCentre[1]),Double.parseDouble(rayCentre[2]));
      Point centredeux = new Point(Double.parseDouble(rayCentre[3]),Double.parseDouble(rayCentre[4]),Double.parseDouble(rayCentre[5]));
      Double rayon= Double.parseDouble(rayCentre[6]);
      Color gris = Color.rgb(125,125,125);
      Cylindre cyl = new Cylindre(centreUn,centredeux,rayon,gris);

      list.add(cyl);
    }

    System.out.println("");
    System.out.println("il y a "+ list.size() +" Cylindre");
    System.out.println("");

    for (Cylindre c: list){
      System.out.println(c);
      System.out.println("");
    }
  }
}
