package main;

import java.util.*;
import formes.*;
import lumiere.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.stage.Stage;


  /**
    *<h1> class Render </h1>
    * la class render est la classe executable de notre application.
    * Elle demande à l'utilisateur de rentrer le nom d'un document POV-Ray
    *(extension .pov) et génère une image au format png à partir de ce fichier.
    *l'image est ensuite stocké dans un fichier png stocké dans le dossier
    *image
  */
  public class Render extends Application {

      //création de l'application javafx
      public void start(Stage stage){

        /*création d'un scanner demandant à l'utilisateur de rentrer le nom du fichier
        povray qu'il souhaite transformer en png*/
        System.out.println();
        System.out.println("veuillez rentrer le chemin vers le fichier .pov s'il vous plait (exemple ./pov/nomDuFichier.pov ):");
        System.out.println();
        Scanner pov = new Scanner(System.in);
        String fichier = pov.nextLine();

        //boolean utiliser dans la boucle while définissant si un non de fichier est valide ou non
        Boolean fichierValide = false;

        // création de l'interprète
        Interprete nom = new Interprete(fichier);

        //tant que le fichier n'est pas valide, on redemande à l'utilisateur de rentrer un nom de fichier.
        while(!fichierValide){
          try{
            nom.lectureCompteur();
            /*si le fichier contient des formes à dessiner et qu'il finit par .pov,
             alors le fichier est considéré comme valide*/
            if(fichier.endsWith(".pov") && nom.compteurBox != 0 || nom.compteurSphere != 0 || nom.compteurCone != 0 || nom.compteurCylindre != 0){
              fichierValide = true;
            }
            //sinon, on affiche ce message d'erreur.
            else{
              System.out.println("");
              System.out.println("votre fichier ne contient aucune forme à dessiner.");
              System.out.println("");
            }
          }catch(FileNotFoundException e){
            e.printStackTrace();
          }catch(IOException er){
            er.printStackTrace();
          }

          //redemande à l'utilisateur de rentrer un nouveau nom de fichier
          if(fichierValide == false){
            System.out.println("");
            System.out.println("veuillez rentrer le chemin vers le fichier .pov s'il vous plait (exemple ./pov/nomDuFichier.pov ):");
            System.out.println("");
            pov = new Scanner(System.in);
            fichier = pov.nextLine();
            nom = new Interprete(fichier);
          }
        }

        //création des différentes formes qui seront mise dans l'ArrayList de formes:

        //création des Box
        ArrayList<Parallepipede> para = new ArrayList<>();

        for (int i =0; i < nom.compteurBox ; i++){
          /*extraction des données du povray: nom.boite.get(0) renvoi le premier Arraylist de l'ArrayList
          généré par la méthode boite de l'interprète: il contient les infos nécessaire à la création d'un objets
          le get(i) nous permet de récupérer les données concernant la ième boite.
          split permet de transformé un string en tableau de string: ici on converti un String de type
          x,y,z en un tableau de string {x,y,z} ce qui facilite l'accès à ces données.*/
          String[] ptn = nom.boite().get(0).get(i).split(",");
          //création des variables nécessaire à la création d'une forme de type Parallepipede depuis le fichier pov +création forme
          Point un = new Point(Double.parseDouble(ptn[0]),Double.parseDouble(ptn[1]),Double.parseDouble(ptn[2]));
          Point deux = new Point(Double.parseDouble(ptn[3]),Double.parseDouble(ptn[4]),Double.parseDouble(ptn[5]));

          //récupération de la couleur de l'objet:
          String[] coul = nom.boite().get(2).get(i).split(",");
          Color couleurPara = Color.rgb(Integer.parseInt(coul[0]),Integer.parseInt(coul[1]),Integer.parseInt(coul[2]));

          Parallepipede parall = new Parallepipede(un,deux,couleurPara);

          para.add(parall);
        }

        //création des Sphères
        ArrayList<Sphere> sphere = new ArrayList<>();

        for (int i =0; i < nom.compteurSphere ; i++){
          /*comme pour box, on récupère les données essentielles à la création de l'objet, ici,
          il s'agit d'un point et d'un rayon. la fonction split nous renvoyé ici un tableau
          sous la forme {x,y,z,rayon}*/
          String[] rayCentre = nom.sphere().get(0).get(i).split(",");
          //création des variables nécessaire à la création d'une forme de type sphere depuis le fichier pov + création forme
          Point centre = new Point(Double.parseDouble(rayCentre[0]),Double.parseDouble(rayCentre[1]),Double.parseDouble(rayCentre[2]));
          Double rayon = Double.parseDouble(rayCentre[3]);

          //récupération de la couleur de l'objet:
          String[] coul = nom.sphere().get(2).get(i).split(",");
          Color couleurSphe = Color.rgb(Integer.parseInt(coul[0]),Integer.parseInt(coul[1]),Integer.parseInt(coul[2]));

          Sphere sph = new Sphere(centre,rayon,couleurSphe);

          sphere.add(sph);
        }


        //création des Cones
        ArrayList<Cone> cone = new ArrayList<>();

        for (int i =0; i < nom.compteurCone ; i++){
          /*comme pour box, on récupère les données essentielles à la création de l'objet,
          ici, il s'agit de deux points et deux rayon (pour créé les deux cercles du cones).
           la fonction split nous renvoyé ici un tableau sous la forme {x1,y1,z1,rayon1,x2,y2,rayon2}*/
          String[] rayCentre = nom.cone().get(0).get(i).split(",");
          //création des variables nécessaire à la création d'une forme de type cone depuis le fichier pov + création forme
          Point centreUn = new Point(Double.parseDouble(rayCentre[0]),Double.parseDouble(rayCentre[1]),Double.parseDouble(rayCentre[2]));
          Double rayonUn = Double.parseDouble(rayCentre[3]);
          Point centredeux = new Point(Double.parseDouble(rayCentre[4]),Double.parseDouble(rayCentre[5]),Double.parseDouble(rayCentre[6]));
          Double rayonDeux = Double.parseDouble(rayCentre[7]);

          //récupération de la couleur de l'objet:
          String[] coul = nom.cone().get(2).get(i).split(",");
          Color couleurCone = Color.rgb(Integer.parseInt(coul[0]),Integer.parseInt(coul[1]),Integer.parseInt(coul[2]));

          Cone co = new Cone(centreUn,centredeux,rayonUn,rayonDeux,couleurCone);

          cone.add(co);
        }

        //création des cylindres
        ArrayList<Cylindre> cyl = new ArrayList<>();

        for (int i =0; i < nom.compteurCylindre ; i++){
          /*comme pour box, on récupère les données essentielles à la création de l'objet, ici,
          il s'agit de deux points et un rayon (pour créé les deux cercles du cylindre).
          la fonction split nous renvoyé ici un tableau sous la forme {x1,y1,z1,x2,y2,rayon}*/
          String[] rayCentre = nom.cylindre().get(0).get(i).split(",");
          //création des variables nécessaire à la création d'une forme de type cylindre depuis le fichier pov + création forme
          Point centreUn = new Point(Double.parseDouble(rayCentre[0]),Double.parseDouble(rayCentre[1]),Double.parseDouble(rayCentre[2]));
          Point centredeux = new Point(Double.parseDouble(rayCentre[3]),Double.parseDouble(rayCentre[4]),Double.parseDouble(rayCentre[5]));
          Double rayon= Double.parseDouble(rayCentre[6]);

          //récupération de la couleur de l'objet:
          String[] coul = nom.cylindre().get(2).get(i).split(",");
          Color couleurCyl = Color.rgb(Integer.parseInt(coul[0]),Integer.parseInt(coul[1]),Integer.parseInt(coul[2]));

          Cylindre cylindre = new Cylindre(centreUn,centredeux,rayon,couleurCyl);

          cyl.add(cylindre);
        }

      //Création des point lumière et camera
      //split de lum et de cam : renvoi un tableau de coordonnées{x,y,z}
      String[] coordLum = nom.lumiere().get(0).split(",");
      Point lum = new Point(Double.parseDouble(coordLum[0]),Double.parseDouble(coordLum[1]),Double.parseDouble(coordLum[2]));

      String[] coordCam = nom.camera().get(0).split(",");
      Point cam = new Point(Double.parseDouble(coordCam[0]),Double.parseDouble(coordCam[1]),Double.parseDouble(coordCam[2]));

      //création d'une instance de room: c'est depuis cette instance qu'on créra l'image à afficher
      Room piece = new Room(para,sphere,cone,cyl,lum,cam);


      //création d'une variable qui contiendra le nom du fichier
      System.out.println("");
      System.out.println("Rentrez le nom de votre futur fichier (sans l'extension):");
      System.out.println("");
      Scanner nomFichier = new Scanner(System.in);
      String nomFich = pov.nextLine();

      //création d'une image valide à partir de la class Room: la méthode getRes() de Room renvoi l'image généré par cette classe
      Image image = piece.createForme();

      //création du booléan qui validera la création du fichier quand le nom rentré sera un nom disponible
      Boolean nomValide = false;

      /*tant que le nom du fichier ne sera pas valide, on demandera à
      l'utilisateur d'en rentrer un nouveau */
      while(!nomValide){
        try{
          //création du fichier qui ser placé dans le répertoire image
          File png = new File("../images/"+nomFich+".png");
          //si le fichier est créé affiché un message de succès
          if (png.createNewFile()){
            System.out.println("");
            System.out.println("votre fichier a été créé avec succés!");
            //ajout de l'image au fichier grace à la méthode write
            //SwingFXUtils.fromFXImage permet la conversion de la WritableImage en
            //BufferedImage
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", png);
            nomValide = true;

          }
          //sinon demandé à l'utilisateur de recommencer.
          else{
            System.out.println("");
            System.out.println("le nom de votre fichier est déjà pris, veuillez en choisir un autre:");
            System.out.println("");
            nomFichier = new Scanner(System.in);
            nomFich = pov.nextLine();
          }

        }catch(Exception e){
          System.err.println(e);
        }
      }

      }

      public static void main(String args[]){
        //lancement de l'application javafx
        launch(args);
    }

    /*https://www.geeksforgeeks.org/javafx-box-with-examples/: 
    lien expliquant comment gérer une application javafx*/
  }
