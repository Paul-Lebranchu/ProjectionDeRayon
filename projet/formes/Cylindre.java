package formes;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import java.lang.*;

public class Cylindre{
 protected Point pointBas;
 protected Point pointHaut;
 protected double rayon;
 protected Color coul;

 public Cylindre(Point pointBas,Point pointHaut,double rayon, Color coul){
   this.pointBas = pointBas;
   this.pointHaut = pointHaut;
   this.rayon = rayon;
   this.coul = coul;
 }
/**<h1>la methode recupDroiteLum</h1>
 *<p> On calcul le centre du cylindre:
   * on a le centre du cercle servant de base et le centre du cercle superieur.
   * Ainsi, pour récuperer le centre du cylindre il suffit de trouver le point situé au milieu du segment centrecerleSup centreCerle.
   * Une fois le centre récupérer, on créer la droite entre le point lumineux et le centre.
  *</p>*/
  public Droite recupDroiteLum(Point lum){
    Point centre = new Point(((pointBas.getX()+pointHaut.getX())/2), ((pointBas.getY()+pointHaut.getY())/2), ((pointBas.getZ()+pointHaut.getZ())/2));
    Droite lumiere = new Droite(lum, centre);
    return lumiere;
  }

/**<h1>la methode pointIncidence</h1>
 *<p> Cette méthode permet de récupèrer le point d'impact de la lumière ou point d'incidence sur un cylindre.
  *</p>*/
  public Point pointIncidence(Point lum){
    Droite lumiere = recupDroiteLum(lum);
    Point incid = pointCommunAvecCyl(lumiere, lum);
    return incid;
  }
/**<h1>la methode pointCommunAvecCyl</h1>
 *<p> on verrifit ici que le t renvoyé par calculDiscriminantCylindreEtDroite est seul ou non,
 *si il n'est pas seul c'est que l'on a plusieur possibilité de point.
 * Si l'on a plusieur t, on cherche quelle t donne le point le plus proche du point lumineux.
  *</p>*/
  public Point pointCommunAvecCyl(Droite lumiere, Point lum){
    double t = 0;
    Point pt;

    ArrayList<Double> tliste = calculDiscriminantCylindreEtDroite(lumiere);

    if (tliste.size() == 1){
      t = tliste.get(0);
      pt = lumiere.represPram(t);
    }else{
      t = tliste.get(0);
      double t1 = tliste.get(1);
      Point pt1 = lumiere.represPram(t1);
      pt = lumiere.represPram(t);

      Droite tp1 = new Droite(pt, lum);
      Droite tp2 = new Droite(pt1, lum);
         if(tp1.calculLongueur() > tp2.calculLongueur()){
          pt = lumiere.represPram(t1);
         }
    }
    return pt;
  }

/**<h1>la methode calculDiscriminantCylindreEtDroite</h1>
 *<p> On cherche la valeur de t telquel appartient au cercle.
 *on se base donc sur l'equation cartesienne du cercle:
 *((i+at)-x)² + ((j+bt)-y)² + ((k+ct)-z)² - r²=0
 * ijk le point de la droite
 * abc le vecteur de la droite
 * xyz le point centre du cercle
 * r le rayon du cercle
 * après calcul on trouve une equation du second degré sous la forme at² +bt -c = 0.
 *On cherche sont discriminant.
 *on renvois le point de la droite d.
  *</p>*/
   public ArrayList<Double> calculDiscriminantCylindreEtDroite(Droite d){
      Point ijk = d.getPrem();
      Vecteur abc = d.vecteur();
      double r = this.rayon;
      ArrayList<Double> lesT = new ArrayList<Double>();

      double a = Math.pow(abc.getX(), 2) + Math.pow(abc.getZ(), 2);
      double b = 2 *abc.getX()*ijk.getX() + 2 *abc.getZ()*ijk.getZ();
      double c = 0 - (Math.pow(r, 2) - Math.pow(ijk.getX(), 2) -Math.pow(ijk.getZ(), 2));

      double discriminant = Math.pow(b, 2) - 4* a* c;
      if (discriminant == 0){
         lesT.add(0 - b/ (2*a));
         return lesT;

      }if (discriminant >= 0){
         lesT.add((0-b - Math.sqrt(discriminant))/(2*a));
         lesT.add((0-b + Math.sqrt(discriminant))/(2*a));
         return lesT;
      }
      lesT.add(0.0);
      return lesT;//ce return ne s'effectuera pas pour droite entré par les fonction si jointe car le point appartient au cylindre.
   }

/**<h1>la methode droiteNormal</h1>
  *<p> On a creer le point d'incidence.
    * Pour récupere la droite normal, on cherche une droite orthogonal a deux vecteurs.
    * La fonction pour creer un vecteur normal se nomme vectorme() et se trouve dans la classe Plan.java.
    * on cherche pour utiliser cette méthode deux points,
    * le point entre le cercle du haut et la droite point d'incidence et un autre point M.
    * le point entre le cercle du bas et la droite point d'incidence et un autre point M.
    * pour trouver cest point on fait appel a calculDiscriminant(Droite d, Point incid) de la classe Cercle.
    * Il nous manque maintenant le point M.
    * Ce point peut se retrouver en cherchant l'intersection entre le cylindre et la droite reliant le point lumineux au centre d'un des deux cercles (haut ou bas).
    * Pour cela on utilise pointCommunAvecCyl(Droite lumiere, Point lum).
  *</p>*/
  public Droite droiteNormal(Point lumineux){
    Point incident = pointIncidence(lumineux);
    Droite incidCentreBas = new Droite(incident, this.pointBas);
    Point m = pointCommunAvecCyl(incidCentreBas, lumineux);

    Droite mIncid = new Droite(m, incident);

    Cercle bas = new Cercle(this.pointBas, this.rayon);
    Cercle haut = new Cercle(this.pointHaut, this.rayon);
    Point enBas = bas.calculDiscriminant(mIncid, incident);
    Point enHaut = haut.calculDiscriminant(mIncid, incident);

    Plan p = new Plan(incident, enBas, enHaut);
    Vecteur vect = p.vectorme();
    Point recup = new Point((incident.getX() - vect.getX()), (incident.getY() - vect.getY()), (incident.getZ() - vect.getZ()));
    Droite normale = new Droite(incident,recup);

    return normale;
  }

  public Point getBas(){
    return this.pointBas;
  }

  public Point getHaut(){
    return this.pointHaut;
  }

  public double getRayon(){
    return this.rayon;
  }

  public Color getCoul(){
		return this.coul;
	}
}
