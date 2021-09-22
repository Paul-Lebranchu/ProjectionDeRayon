package main;

import java.io.*;

public class Translate{

  /*classe qui traduit les données récupéré dans le fichier interprete.java
  n'est pour le moment qu'une classe test permettant de vérifier que le
  fichier interprete.java renvoie les bonnes informations en les affichant dans
  la console*/

  public static void main(String args[]){

    // récupère le fichier test.pov
    Interprete nom = new Interprete("./pov/test.pov");
    try{
      nom.lectureCompteur();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }catch(IOException er){
      er.printStackTrace();
    }

    //récupère les informations concernant la caméra en prenant les informations
    //renvoyé par l'ArrayList de la méthode camera: la position et l'endroit ou regarde la caméra
    String[] posCam = nom.camera().get(0).split(",");
    String[] regCam = nom.camera().get(1).split(",");

    System.out.println();
    System.out.println("caméra : ");
    System.out.println("position de la camera : x = " + posCam[0] + " ,y = " + posCam[1] + " ,z = " + posCam[2]);
    System.out.println("regard de la camera : x = " + regCam[0] + " ,y = " + regCam[1] + " ,z = " + regCam[2]);
    System.out.println();

    //récupère les informations liés à la lumière dans le document povray
    // parcours de l'arraylist: premier élément = position source lumineuse
    //deuxième élément = couleur de la lumière

    String[] source = nom.lumiere().get(0).split(",");

    System.out.println("lumière : ");
    System.out.println("source : x = " + source[0] + " ,y = " + source[1] + " ,z = " + source[2]);
    System.out.println("couleur : " + nom.lumiere().get(1));
    System.out.println();

    //on parcours l'ensemble des objets de types boites renvoyé par l'interpréteur
    for (int i =0; i < nom.compteurBox ; i++){
      //récupère les deux points nécessaire à la création de la boite
      String[] ptn = nom.boite().get(0).get(i).split(",");
      System.out.println("boite " + (i+1)  + ": " );
      System.out.println("coordonnées points des deux extrèmes : ");
      System.out.println("premier point : x = " + ptn[0] + " ,y = " + ptn[1] + " ,z = " + ptn[2]);
      System.out.println("deuxième point : x = " + ptn[3] + " ,y = " + ptn[4] + " ,z = " + ptn[5]);

      //récupère les infos lié au paramètres optionnels et les affiche en console
      //informations concernant la rotation de l'objet
      if (nom.boite().get(1).get(i) != "None"){
        String[] rot = nom.boite().get(1).get(i).split(",");
        System.out.println("rotation : x = " + rot[0] + " ,y = " + rot[1] + " ,z = " + rot[2]);
      }
      else{
        System.out.println("rotation : pas de rotation");
      }
      //informations concernant la couleur de l'objet
      if (nom.boite().get(2).get(i) != "None"){
        System.out.println("couleur : " + nom.boite().get(2).get(i));
      }
      else{
        System.out.println("couleur : couleur de base");
      }
      //informations concernant la texture de l'objet
      if (nom.boite().get(3).get(i) != "None"){
        System.out.println("texture : " + nom.boite().get(3).get(i));
      }
      else{
        System.out.println("texture : aucune texture");
      }
      //informations concernant la taille de l'objet par rapport aux axe x,y,z
      if (nom.boite().get(4).get(i) != "None"){
        String[] taille = nom.boite().get(4).get(i).split(",");
        System.out.println("taille : x = " + taille[0] + " ,y = " + taille[1] + " ,z = " + taille[2]);
      }
      else{
        System.out.println("taille : pas de modification de taille");
      }
      //informations concernant le décalage de l'objet par rapport aux axe x,y,z
      if (nom.boite().get(5).get(i) != "None"){
        String[] decal = nom.boite().get(5).get(i).split(",");
        System.out.println("décalage : x = " + decal[0] + " ,y = " + decal[1] + " ,z = " + decal[2]);
      }
      else{
        System.out.println("décalage : pas de décalage");
      }

      System.out.println();
    }

    //même chose que pour box mais pour les objets de types sphère
    for (int i =0; i < nom.compteurSphere ; i++){
      //récupère les info concernant la coordonnées du centre de la sphère et de son rayon
      String[] ptnRay = nom.sphere().get(0).get(i).split(",");
      System.out.println("sphère "+ (i+1) + " : ");
      System.out.println("coordonnées du centre : x = " + ptnRay[0] + " ,y = " + ptnRay[1] + " ,z = " + ptnRay[2]);
      System.out.println("rayon : " + ptnRay[3]);

      //paramètres optionnels(cf box)
      if (nom.sphere().get(1).get(i) != "None"){
        String[] rot = nom.sphere().get(1).get(i).split(",");
        System.out.println("rotation : x = " + rot[0] + " ,y = " + rot[1] + " ,z = " + rot[2]);
      }
      else{
        System.out.println("rotation : pas de rotation");
      }

      if (nom.sphere().get(2).get(i) != "None"){
        System.out.println("couleur : " + nom.sphere().get(2).get(i));
      }
      else{
        System.out.println("couleur : couleur de base");
      }

      if (nom.sphere().get(3).get(i) != "None"){
        System.out.println("texture : " + nom.sphere().get(3).get(i));
      }
      else{
        System.out.println("texture : aucune texture");
      }

      if (nom.sphere().get(4).get(i) != "None"){
        String[] taille = nom.sphere().get(4).get(i).split(",");
        System.out.println("taille : x = " + taille[0] + " ,y = " + taille[1] + " ,z = " + taille[2]);
      }
      else{
        System.out.println("taille : pas de modification de taille");
      }

      if (nom.sphere().get(5).get(i) != "None"){
        String[] decal = nom.sphere().get(5).get(i).split(",");
        System.out.println("décalage : x = " + decal[0] + " ,y = " + decal[1] + " ,z = " + decal[2]);
      }
      else{
        System.out.println("décalage : pas de décalage");
      }

      System.out.println();
    }

    //même chose que pour box mais pour les objets de types cylindre
    for (int i =0; i < nom.compteurCylindre ; i++){
      //récupère les coordonnées du centre de la base et du sommet du cylindre ainsi que le rayon commun
      //au deux cercles
      String[] cylPtnRay = nom.cylindre().get(0).get(i).split(",");
      System.out.println("cylindre "+ (i+1) + " : ");
      System.out.println("coordonnées centre 1 : x = " + cylPtnRay[0] + " ,y = " + cylPtnRay[1] + " ,z = " + cylPtnRay[2]
      + " ; coordonnées centre 2 : x = " + cylPtnRay[3] + " ,y = " + cylPtnRay[4] + " ,z = " + cylPtnRay[5]);
      System.out.println("rayon des cercles : " + cylPtnRay[6]);

      //paramètre optionnels, cf box
      if (nom.boite().get(1).get(i) != "None"){
        String[] rot = nom.boite().get(1).get(i).split(",");
        System.out.println("rotation : x = " + rot[0] + " ,y = " + rot[1] + " ,z = " + rot[2]);
      }
      else{
        System.out.println("rotation : pas de rotation");
      }

      if (nom.cylindre().get(2).get(i) != "None"){
        System.out.println("couleur : " + nom.cylindre().get(2).get(i));
      }
      else{
        System.out.println("couleur : couleur de base");
      }

      if (nom.cylindre().get(3).get(i) != "None"){
        System.out.println("texture : " + nom.cylindre().get(3).get(i));
      }
      else{
        System.out.println("texture : aucune texture");
      }

      if (nom.cylindre().get(4).get(i) != "None"){
        String[] taille = nom.cylindre().get(4).get(i).split(",");
        System.out.println("taille : x = " + taille[0] + " ,y = " + taille[1] + " ,z = " + taille[2]);
      }
      else{
        System.out.println("taille : pas de modification de taille");
      }

      if (nom.cylindre().get(5).get(i) != "None"){
        String[] decal = nom.cylindre().get(5).get(i).split(",");
        System.out.println("décalage : x = " + decal[0] + " ,y = " + decal[1] + " ,z = " + decal[2]);
      }
      else{
        System.out.println("décalage : pas de décalage");
      }

      System.out.println();
    }

    for (int i =0; i < nom.compteurCone ; i++){
      //récupère les coordonnées du centre de la base et du sommet du cone ainsi que le rayon de chacun
      //des cercles
      String[] conPtnRay = nom.cone().get(0).get(i).split(",");
      System.out.println("cone " + (i+1) +" : ");
      System.out.println("coordonnées centre 1 : x = " + conPtnRay[0] + " ,y = " + conPtnRay[1] + " ,z = " + conPtnRay[2]
      + " ; rayon 1 : x = " + conPtnRay[3]);
      System.out.println("coordonnées centre 2 : x = " + conPtnRay[4] + " ,y = " + conPtnRay[5] + " ,z = " + conPtnRay[6]
      + " ; rayon 2 : x = " + conPtnRay[7]);

      //pramaètre optionnels: cf box
      if (nom.cone().get(1).get(i) != "None"){
        String[] rot = nom.cone().get(1).get(i).split(",");
        System.out.println("rotation : x = " + rot[0] + " ,y = " + rot[1] + " ,z = " + rot[2]);
      }
      else{
        System.out.println("rotation : pas de rotation");
      }

      if (nom.cone().get(2).get(i) != "None"){
        System.out.println("couleur : = " + nom.cone().get(2).get(i));
      }
      else{
        System.out.println("couleur : couleur de base");
      }

      if (nom.cone().get(3).get(i) != "None"){
        System.out.println("texture : " + nom.cone().get(3).get(i));
      }
      else{
        System.out.println("texture : aucune texture");
      }

      if (nom.cone().get(4).get(i) != "None"){
        String[] taille = nom.cone().get(4).get(i).split(",");
        System.out.println("taille : x = " + taille[0] + " ,y = " + taille[1] + " ,z = " + taille[2]);
      }
      else{
        System.out.println("taille : pas de modification de taille");
      }

      if (nom.cone().get(5).get(i) != "None"){
        String[] decal = nom.cone().get(5).get(i).split(",");
        System.out.println("décalage : x = " + decal[0] + " ,y = " + decal[1] + " ,z = " + decal[2]);
      }
      else{
        System.out.println("décalage : pas de décalage");
      }

      System.out.println();
    }
  }
}
