package main;

import java.util.*;
import formes.*;
import java.io.*;
import javafx.scene.paint.Color;
/*
Classe test s'assurant que le bon nombre d'objet Parallepipede soit créé: on met
un fichier ne contenant que des box(Parallepipede), on les comptes et ensuite on créé des
objets de type Parallepipede, on les stocke dans une liste puis on parcours la liste et
s'assure que les objets créé soit bien des objets du bon type et qu'il y en a le bon nombre
*/

public class TestBox{

  public static void main(String args[]){
    Interprete nom = new Interprete("./pov/testBox.pov");
    try{
      nom.lectureCompteur();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }catch(IOException er){
      er.printStackTrace();
    }

    ArrayList<Parallepipede> list = new ArrayList<>();

    for (int i =0; i < nom.compteurBox ; i++){
      String[] ptn = nom.boite().get(0).get(i).split(",");

      Point un = new Point(Double.parseDouble(ptn[0]),Double.parseDouble(ptn[1]),Double.parseDouble(ptn[2]));
      Point deux = new Point(Double.parseDouble(ptn[3]),Double.parseDouble(ptn[4]),Double.parseDouble(ptn[5]));
      Color gris = Color.rgb(125,125,125);
      Parallepipede para = new Parallepipede(un,deux,gris);

      list.add(para);
    }
    System.out.println("");
    System.out.println("il y a "+ list.size() +" Parallepipede");
    System.out.println("");
    for (Parallepipede p: list){
      System.out.println(p);
      System.out.println("");
    }
  }
}
