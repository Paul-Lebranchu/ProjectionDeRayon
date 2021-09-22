package main;

import formes.*;
import java.util.*;
import java.io.*;
import javafx.scene.paint.Color;

/*
Classe test s'assurant que le bon nombre d'objet Sphere soit créé: on met
un fichier ne contenant que des sphères, on les comptes et ensuite on créé des
objets de type sphère, on les stoke dans une liste puis on parcours la liste et
s'assure que les objets créé soit bien des objets du bon type et qu'il y en a le bon nombre
*/
public class TestSphere{

  public static void main(String args[]){

    Interprete nom = new Interprete("./pov/testSphere.pov");
    try{
      nom.lectureCompteur();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }catch(IOException er){
      er.printStackTrace();
    }

    ArrayList<Sphere> list = new ArrayList<>();

    for (int i =0; i < nom.compteurSphere ; i++){
      String[] rayCentre = nom.sphere().get(0).get(i).split(",");

      Point centre = new Point(Double.parseDouble(rayCentre[0]),Double.parseDouble(rayCentre[1]),Double.parseDouble(rayCentre[2]));
      Double rayon = Double.parseDouble(rayCentre[3]);
      Color gris = Color.rgb(125,125,125);

      Sphere sphere = new Sphere(centre,rayon,gris);

      list.add(sphere);
    }

    System.out.println("");
    System.out.println("il y a "+ list.size() +" sphere");
    System.out.println("");

    for (Sphere s: list){
      System.out.println(s);
      System.out.println("");
    }
  }
}
