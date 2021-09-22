package main;

import java.util.Map;
import java.util.ArrayList;
import java.io.*; //gère la lecture du fichier.pov

/**
  * <h1> classe Interprete </h1>
  * <p>
  * La classe Interprete est la classe qui va extraire toutes les informations
  * importantes du fichier povray et qui les renverra sous forme d'ArrayList
  * d'ArrayList d'objet de type String.
  * </p>
*/

public class Interprete{
/**
  * <p>
  * variable de type string contenant le fichier povray
  * </p>
*/

  protected String fichier = "";
  /**
  * <p>
  * variable de type int comptant le nombre d'objet box du povray
  * </p>
  */

  protected int compteurBox = 0;
  /**
  * <p>
  * variable de type int comptant le nombre d'objet sphère du povray
  * </p>
  */

  protected int compteurSphere = 0;
  /**
  * <p>
  * variable de type int comptant le nombre d'objet cylindre du povray
  * </p>
  */

  protected int compteurCylindre = 0;
  /**
  * <p>
  * variable de type int comptant le nombre d'objet cone du povray
  * </p>
  */

  protected int compteurCone = 0;
  /**
  * <p>
  * variable de type int comptant le nombre d'objet Prism du povray
  * </p>
  */

  protected int compteurPrism = 0;

  /**
   * <p>
   * Le constructeur prend en attribut un fichier de type povray .
   *</p>
  */

  public Interprete(String fichier){
    this.fichier = fichier;
  }

  /**
    * <p>
    * cette méthode ne retourne rien mais elle permet de compter le
    * nombre de chaque objet présent dans le povray et d'afficher la valeur
    * des compteurs.
    * <p>
  */
  protected void lectureCompteur() throws FileNotFoundException,IOException{
    try{
      String ligne = "";
      BufferedReader fich = new BufferedReader(new FileReader(this.fichier));

      while ((ligne= fich.readLine()) != null){
        if (ligne.startsWith("box",0)){ // si la ligne commence par box alors je fais tel action!
          compteurBox +=1;
        }
        if (ligne.startsWith("sphere",0)){
          compteurSphere +=1;
        }
        if (ligne.startsWith("cylinder",0)){
          compteurCylindre +=1;
        }
        if (ligne.startsWith("cone",0)){
          compteurCone +=1;
        }
      }
      fich.close(); //ferme le fichier
      System.out.println("il y a " + compteurBox + " objet box");
      System.out.println("il y a " + compteurSphere + " objet sphère");
      System.out.println("il y a " + compteurCylindre + " objet cylindre");
      System.out.println("il y a " + compteurCone + " objet cone");

    }
    catch (FileNotFoundException e){
      System.err.print("votre fichier n'existe pas.");
    }
    catch(IOException er){
      er.printStackTrace();
    }


  }

  /**
  *<p>
  *Méthode permettant la gestion des données de type rotate du document povray
  * c'est à dire la rotation d'un objet par rapport à un ou plusieurs axes.
  *@param ligne: chaine de caractère à analiser
  *@param rotate: tableau contenant les différentes valeurs de rotate
  *</p>
  */
  public void rotate(String ligne, ArrayList<String> rotate){
    if(ligne.startsWith("rotate",4)){ // indentation
      if (ligne.contains("<") && ligne.contains(">")){
        //création d'un nouvel objet String conteant le String qui se trouve entre "<" et ">"
        ligne = ligne.substring(ligne.indexOf("<")+1 ,ligne.indexOf(">"));
        //supression des espaces inutiles
        rotate.add(ligne.trim());
      }
    }
  }

  /**
  *<p>
  *Méthode permettant la gestion des données de type pigment du document povray
  *c'est à dire la coleur de base de l'objet
  *@param ligne: chaine de caractère à analiser
  *@param pigment: tableau contenant les différentes valeurs de pigment
  *</p>
  */
  public void pigment(String ligne, ArrayList<String> pigment){
    if(ligne.startsWith("pigment",4)){
      if (ligne.contains("{") && ligne.contains("}")){
        ligne = ligne.substring(ligne.indexOf("<")+1 ,ligne.indexOf(">"));
        pigment.add(ligne.trim());
      }
    }
  }

  /**
  *<p>
  *Méthode permettant la gestion des données de type texture du document povray
  *c'est à dire la texture de l'objet
  *@param ligne: chaine de caractère à analiser
  *@param texture: tableau contenant les différentes valeurs de texture
  *</p>
  */
  public void texture(String ligne, ArrayList<String> texture){
    if(ligne.startsWith("texture",4)){
      if (ligne.contains("{") && ligne.contains("}")){
        ligne = ligne.substring(ligne.indexOf("{")+1 ,ligne.indexOf("}"));
        texture.add(ligne.trim());
      }
    }
  }

  /**
  *<p>
  *Méthode permettant la gestion des données de type translate du document povray
  *c'est à dire le déclage de l'objet pa rapport à un ou plusieurs axes
  *@param ligne: chaine de caractère à analiser
  *@param translate: tableau contenant les différentes valeurs de décalage
  *</p>
  */
  public void translate(String ligne, ArrayList<String> translate){
    if(ligne.startsWith("translate",4)){
      if (ligne.contains("<") && ligne.contains(">")){
        ligne = ligne.substring(ligne.indexOf("<")+1 ,ligne.indexOf(">"));
        translate.add(ligne.trim());
      }
    }
  }

  /**
  *<p>
  *Méthode permettant la gestion des données de type scale du document povray
  *c'est à dire la taille des objets en fonction des axes x,y,z
  *@param ligne: chaine de caractère à analiser
  *@param scale: tableau contenant les différentes valeurs de scale
  *</p>
  */
  public void scale(String ligne, ArrayList<String> scale){
    if(ligne.startsWith("scale",4)){
      if (ligne.contains("<") && ligne.contains(">")){
        ligne = ligne.substring(ligne.indexOf("<")+1 ,ligne.indexOf(">"));
        scale.add(ligne.trim());
      }
    }
  }

  /**
  * <p>
  *   Cette méthode permet de récupérer les informations concernant la caméra:
  *   sa position, le point qu'elle regarde et son angle
  *   @return un ArrayList < String > contenant les informations
  *   mentionner au dessus.
  * </p>
  */
  public ArrayList<String> camera(){
    String ligne  = "";
    ArrayList<String> cam = new ArrayList<>();

    try{
      BufferedReader fich = new BufferedReader(new FileReader(this.fichier));
      while ((ligne = fich.readLine()) != null){
        if (ligne.startsWith("camera")){
          while ((ligne = fich.readLine()).startsWith("}") != true){
            if (ligne.startsWith("location",4)){
              if (ligne.contains("<") && ligne.contains(">")){
                ligne = ligne.substring(ligne.indexOf("<")+1 ,ligne.indexOf(">"));
                cam.add(ligne.trim());
              }
            }
            if (ligne.startsWith("look_at",4)){
              if (ligne.contains("<") && ligne.contains(">")){
                ligne = ligne.substring(ligne.indexOf("<")+1 ,ligne.indexOf(">"));
                cam.add(ligne.trim());
              }
            }
          }
        }
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return cam;
  }

  /**
  * <p>
  *   Cette méthode permet de récupérer les informations concernant la lumière:
  *   la position de la source lumineuse et la couleur
  *   @return un ArrayList < String > contenant les informations
  *   mentionner au dessus.
  * </p>
  */
  public ArrayList<String> lumiere(){
    String ligne  = "";
    ArrayList<String> lumos = new ArrayList<>();

    try{
      BufferedReader fich = new BufferedReader(new FileReader(this.fichier));

      while ((ligne = fich.readLine()) != null){
        if (ligne.startsWith("light_source")){
          while ((ligne = fich.readLine()).startsWith("}") != true){
            if (ligne.startsWith("<",4)){
              if (ligne.contains("<") && ligne.contains(">")){
                ligne = ligne.substring(ligne.indexOf("<")+1 ,ligne.indexOf(">"));
                lumos.add(ligne.trim());
              }
            }
            if (ligne.startsWith("color",4)){
              ligne = ligne.substring(ligne.indexOf("color")+6) ;
              lumos.add(ligne.trim());
            }
          }
        }
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return lumos;
  }


  /**
  * <p>
  *   Cette méthode permet de récupérer les informations importantes concernant
  *   les box: les points de deux coins de la boites et les informations
  *   spécifiques tel l'orientation, la couleur, la texture ou la taille de la
  *   boite.
  *   @return un ArrayList < ArrayList < String > > contenant les informations
  *   mentionner au dessus.
  * </p>
  */
  public ArrayList<ArrayList<String>> boite(){
    String ligne = "";
    ArrayList<ArrayList<String>> box = new ArrayList<>();
    ArrayList<String> points = new ArrayList<>();
    ArrayList<String> rotate = new ArrayList<>();
    ArrayList<String> pigment = new ArrayList<>();
    ArrayList<String> texture = new ArrayList<>();
    ArrayList<String> scale = new ArrayList<>();
    ArrayList<String> translate = new ArrayList<>();

    try{
      BufferedReader fich = new BufferedReader(new FileReader(this.fichier));

      while ((ligne= fich.readLine()) != null){
        if(ligne.startsWith("box")){
            while ((ligne = fich.readLine()).startsWith("}") != true){
              /* récupère la ligne contenant les coordonnées des points des
              extrémités de la boites */
              if(ligne.startsWith("<",4)){
                ligne = ligne.replace("<","");
                ligne = ligne.replace(">","");
                points.add(ligne.trim());
              }
              rotate(ligne,rotate);
              pigment(ligne,pigment);
              texture(ligne,texture);
              //gestion du scale
              scale(ligne,scale);

              translate(ligne,translate);
            }
            if(rotate.size() < points.size()){
              rotate.add("None");
            }
            //ajout couleur gris par défaut
            if(pigment.size() < points.size()){
              pigment.add("125,125,125");
            }
            if(texture.size() < points.size()){
              texture.add("None");
            }
            if(scale.size() < points.size()){
              scale.add("None");
            }
            if(translate.size() < points.size()){
              translate.add("None");
            }
        }
      }
      box.add(points);
      box.add(rotate);
      box.add(pigment);
      box.add(texture);
      box.add(scale);
      box.add(translate);

      fich.close();
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return box;
  }

  /**
  * <p>
  *   Cette méthode permet de récupérer les informations importantes concernant
  *   les box: le centre et le rayon de la sphère et les informations
  *   spécifiques tel l'orientation, la couleur, la texture ou la taille de la
  *   sphère.
  *   @return un ArrayList < ArrayList < String > > contenant les informations
  *   mentionner au dessus.
  * </p>
  */
  public ArrayList<ArrayList<String>> sphere(){
    String ligne = "";
    ArrayList<ArrayList<String>> sphere = new ArrayList<>();
    ArrayList<String> ptnRay = new ArrayList<>();
    ArrayList<String> rotate = new ArrayList<>();
    ArrayList<String> pigment = new ArrayList<>();
    ArrayList<String> texture = new ArrayList<>();
    ArrayList<String> scale = new ArrayList<>();
    ArrayList<String> translate = new ArrayList<>();

    try{
      BufferedReader fich = new BufferedReader(new FileReader(this.fichier));

      while ((ligne= fich.readLine()) != null){
        if(ligne.startsWith("sphere")){
            while ((ligne = fich.readLine()).startsWith("}") != true){
              /*récupère les informations de la ligne contenant les coordonnées
              du centre de la sphère et son rayon */
              if(ligne.startsWith("<",4)){
                ligne = ligne.replace("<","");
                ligne = ligne.replace(">","");
                ptnRay.add(ligne.trim());
              }
              rotate(ligne,rotate);
              pigment(ligne,pigment);
              texture(ligne,texture);
              scale(ligne,scale);
              translate(ligne,translate);
            }
            if(rotate.size() < ptnRay.size()){
              rotate.add("None");
            }
            if(pigment.size() < ptnRay.size()){
              pigment.add("125,125,125");
            }
            if(texture.size() < ptnRay.size()){
              texture.add("None");
            }
            if(scale.size() < ptnRay.size()){
              scale.add("None");
            }
            if(translate.size() < ptnRay.size()){
              translate.add("None");
            }
        }
      }
      sphere.add(ptnRay);
      sphere.add(rotate);
      sphere.add(pigment);
      sphere.add(texture);
      sphere.add(scale);
      sphere.add(translate);

      fich.close();
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return sphere;
  }

  /**
  * <p>
  *   Cette méthode permet de récupérer les informations importantes concernant
  *   les cylindre: les centres et le rayon des cercles du cylindres et les informations
  *   spécifiques tel l'orientation, la couleur, la texture ou la taille du
  *   cylindre.
  *   @return un ArrayList < ArrayList < String > > contenant les informations
  *   mentionner au dessus.
  * </p>
  */
  public ArrayList<ArrayList<String>> cylindre(){
    String ligne = "";
    ArrayList<ArrayList<String>> cylindre = new ArrayList<>();
    ArrayList<String> ptnRay = new ArrayList<>();
    ArrayList<String> rotate = new ArrayList<>();
    ArrayList<String> pigment = new ArrayList<>();
    ArrayList<String> texture = new ArrayList<>();
    ArrayList<String> scale = new ArrayList<>();
    ArrayList<String> translate = new ArrayList<>();

    try{
      BufferedReader fich = new BufferedReader(new FileReader(this.fichier));

      while ((ligne= fich.readLine()) != null){
        if(ligne.startsWith("cylinder")){
            while ((ligne = fich.readLine()).startsWith("}") != true){
              /* Récupère les informations de la ligne contenant les coordonnées
              des deux centres des bases du cylindre et le rayon des deux cercles*/
              if(ligne.startsWith("<",4)){
                ligne = ligne.replace("<","");
                ligne = ligne.replace(">","");
                ptnRay.add(ligne.trim());
              }
              rotate(ligne,rotate);
              pigment(ligne,pigment);
              texture(ligne,texture);
              scale(ligne,scale);
              translate(ligne,translate);
            }
            if(rotate.size() < ptnRay.size()){
              rotate.add("None");
            }
            if(pigment.size() < ptnRay.size()){
              pigment.add("125,125,125");
            }
            if(texture.size() < ptnRay.size()){
              texture.add("None");
            }
            if(scale.size() < ptnRay.size()){
              scale.add("None");
            }
            if(translate.size() < ptnRay.size()){
              translate.add("None");
            }
        }
      }
      cylindre.add(ptnRay);
      cylindre.add(rotate);
      cylindre.add(pigment);
      cylindre.add(texture);
      cylindre.add(scale);
      cylindre.add(translate);

      fich.close();
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return cylindre;
  }

  /**
  * <p>
  *   Cette méthode permet de récupérer les informations importantes concernant
  *   les cylindre: les centres et le rayons des cercles du cone et les informations
  *   spécifiques tel l'orientation, la couleur, la texture ou la taille du
  *   cone.
  *   @return un ArrayList < ArrayList < String > > contenant les informations
  *   mentionner au dessus.
  * </p>
  */
  public ArrayList<ArrayList<String>> cone(){
    String ligne = "";
    ArrayList<ArrayList<String>> cone = new ArrayList<>();
    ArrayList<String> ptnRay = new ArrayList<>();
    ArrayList<String> rotate = new ArrayList<>();
    ArrayList<String> pigment = new ArrayList<>();
    ArrayList<String> texture = new ArrayList<>();
    ArrayList<String> scale = new ArrayList<>();
    ArrayList<String> translate = new ArrayList<>();

    try{
      BufferedReader fich = new BufferedReader(new FileReader(this.fichier));

      while ((ligne= fich.readLine()) != null){
        if(ligne.startsWith("cone")){
            while ((ligne = fich.readLine()).startsWith("}") != true){
              /* Récupère les informations de la ligne contenant les coordonnées
              des deux centres des bases du cone et leur rayon respectif */
              if(ligne.startsWith("<",4)){
                ligne = ligne.replace("<","");
                ligne = ligne.replace(">","");
                ptnRay.add(ligne.trim());
              }
              rotate(ligne,rotate);
              pigment(ligne,pigment);
              texture(ligne,texture);
              scale(ligne,scale);
              translate(ligne,translate);
            }
            if(rotate.size() < ptnRay.size()){
              rotate.add("None");
            }
            if(pigment.size() < ptnRay.size()){
              pigment.add("125,125,125");
            }
            if(texture.size() < ptnRay.size()){
              texture.add("None");
            }
            if(scale.size() < ptnRay.size()){
              scale.add("None");
            }
            if(translate.size() < ptnRay.size()){
              translate.add("None");
            }
        }
      }
      cone.add(ptnRay);
      cone.add(rotate);
      cone.add(pigment);
      cone.add(texture);
      cone.add(scale);
      cone.add(translate);

      fich.close();
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return cone;
  }
}

//récupérer les différentes données du fichier.pov

  //récupérer la caméra

    //cam: camera{location<x,y,z> (position viewer )point
                  //look_at<x,y,z> (point de vuebox vecteur
                  //)}


  // source de lumière

    //point à l'origine de la lumière qui va éclairer dans toutes les directions, coordonnées <x,y,z>
    // couleur de la lumière color red 1.0 green 1.0 blue

  //récupérer les formes

    //sphère:  sphere{ (prend la coordonnées centre format) <x,y,z>, (puis le rayon) x(float})

    //cylindre: cylinder{(centre du cercle en bas )<x,y,z>,( centre du cercle haut) <x,y,z>, (rayon) x (float)}

    //cone: cone{(centre du bas)<x,y,z>, (rayon cercle bas) x (float),(centre cercle haut)<x,y,z>, (rayon cercle haut) x (float)}

    //box: box{(coordonné point 1)<x,y,z>,(coordonnées point opposé)<x,y,z>}

    //rotate : <x,y,z>en degré

    //pigment: gère la couleur: color rgb <x,x,x> ou nom de la couleur

    //scale : gère la taille de l'objet: faire un agrandissement par rapport à un axe x,y,z

    //texture : gère la texture

    //translate : gère la position par rapport à la position définit intialement = déplacement parallè
    //par rapport à un des trois axes , permet de gérer la dilatation,
