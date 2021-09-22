package formes;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Parallepipede{
	protected Point point_debut;
	protected Point point_fin;
	protected Color coul;

	public Parallepipede(Point point_debut,Point point_fin, Color coul){
		this.point_debut = point_debut;
		this.point_fin = point_fin;
		this.coul = coul;
	}
  //on fait une liste contenant les 8 points du parallepipede
	public ArrayList<Point> Sommet(){
		ArrayList<Point> listePoint = new ArrayList<Point>();
    	listePoint.add(point_debut);
    	Point p1 = new Point(point_fin.getX(), point_debut.getY(), point_debut.getZ());
    	listePoint.add(p1);
    	Point p2 = new Point(point_fin.getX(), point_fin.getY(), point_debut.getZ());
    	listePoint.add(p2);
    	Point p3 = new Point(point_debut.getX(), point_fin.getY(), point_debut.getZ());
    	listePoint.add(p3);
    	Point p4 = new Point(point_debut.getX(), point_fin.getY(), point_fin.getZ());
    	listePoint.add(p4);
    	Point p5 = new Point(point_debut.getX(), point_debut.getY(), point_fin.getZ());
    	listePoint.add(p5);
    	Point p6 = new Point(point_fin.getX(), point_debut.getY(), point_fin.getZ());
    	listePoint.add(p6);
    	listePoint.add(point_fin);

    return listePoint;
    }

/**<h1>la methode rayonIncidant</h1>
 *<p> On recherche le point centrale de la forme,
 	*Ici en cherchant le point de contact des diagonal interne du Parallelepipede rectangle.
 	*Pour cela on va avoir besoin de la representation parametrique des droites.
  *</p>*/
    public Droite rayonIncidant(Point lum){
    	ArrayList<Point> liste = this.Sommet();
		Droite d = new Droite(liste.get(0), liste.get(7));
		Droite b = new Droite(liste.get(2), liste.get(5));

		double t = b.trouveT(d);
		Point dEtb = b.represPram(t);

		Droite lumiere = new Droite(lum, dEtb);
		return lumiere;
    }

/**<h1>la methode estDansMiFace</h1>
 *<p> Premiere idée : Pour vérifier la présence d'un point sur une face, on prend un point qui sera l'origine et deux autre point.
 	* le segment allant du point d'origine jusqu'au point second est l'axe des x et
 	*celui du point d'origine au point ternaire est l'axe des y.
 	* Seconde idée : utiliser l'algorithme de Möller Trumbore, on divise la face carré en deux face triangulaires.
 	* et on calcul l'intersection d'un rayon avec un triangle,
 	*si l'intersection est dans le triangle alors, on retourne vrai sinon, on retourne faux.
 	* Cette algorithme est utilisé dans le cas du lancé de rayon en infographie.(d'après wikipedia).
 	* J'ai donc ici repris l'algorithme de Möller Trumbore présent a cette adresse :
 	*https://fr.wikipedia.org/wiki/Algorithme_d%27intersection_de_Möller–Trumbore et adapté a mon code(voir les commentaires).
  *</p>*/
    public boolean estDansMiFace(Point lumineux, Plan morceauFace){

    	double epsi = 0.0000001;

    	Droite incidente = rayonIncidant(lumineux);
    	Droite coteTriangle1 = new Droite(morceauFace.getSec(), morceauFace.getPrem());//edge1 vertion droite
    	Droite coteTriangle2 = new Droite(morceauFace.getTer(), morceauFace.getPrem());//edge2 vesrtion droite

    	Vecteur vectCT1 = coteTriangle1.vecteur();//edge1
    	Vecteur vectCT2 = coteTriangle2.vecteur();//edge2

    	Vecteur vectRay = incidente.vecteur();//rayVector

    	double angle1 = incidente.calculAngle(coteTriangle2);//recuperation de l'angle pour le produit scalair
    	Vecteur h = vectRay.produitVect(vectCT2, angle1);//h.cross(rayVector, edge2) ou produit vectoriel de edge2 et rayVector

    	double a = vectCT1.produitScalair(h);//a = edge1.dot(h) ou le produit scalaire de a

    	if ((a > (0 - epsi)) && (a < epsi)){//on verifie ici que le produit scalaire est nul
    		return false;//si produit scalaire nul alors, rayon parallèle au triangle donc pas de point d'intersection.
    	}

    	double f = 1/a;

    	Droite lumPrem= new Droite(lumineux, morceauFace.getPrem());
    	Vecteur s = lumPrem.vecteur();//s.sub(rayOrigin, vertex0) creation du vecteur rayOrigin et vertex0

    	double u = f * s.produitScalair(h);//u = f * (s.dot(h));

    	if(( u < 0 )||( u > 1)){
    		return false;
    	}

    	double angle2 = lumPrem.calculAngle(coteTriangle1);
    	Vecteur q = s.produitVect(vectCT1, angle2);//q.cross(s, edge1);

    	double v = f * vectRay.produitScalair(q);//v = f * rayVector.dot(q);

    	if(( v < 0 )||( (u+v) > 1)){
    		return false;
    	}

    	double t = f * vectCT2.produitScalair(q);

        if(t > epsi){

            return true;

        } else{

            return false;
        }
    }

/**<h1>la methode estDansFace</h1>
 *<p>Comme le théoreme de Möller prend en compte que des triangles,
 *cette fonction permet de prendre en compte des faces entiers (une face étant composée de deux triangle).
  *</p>*/
    public Plan estDansFace(Point lumineux, Plan face, Point quatrieme){
    	boolean bool1 = estDansMiFace(lumineux, face);
    	Plan faciesse = new Plan(quatrieme, face.getSec(), face.getTer());
    	boolean bool2 = estDansMiFace(lumineux, faciesse);
    	if (bool1 == true){
    		return face;
    	}
    	if (bool2 == true){
    		return faciesse;
    	}
      Point p = new Point();
    	return (new Plan(p,p,p));
    }

/**<h1>la methode planIncid</h1>
 *<p> On verifie si telle ou telle face du parallelepipede continent un point d'impact avec la droite lumiere
 	* si oui, on verifie la distance entre la source lumineuse et le point d'impact trouvé.
 	* On récupère le plan contenant le point avec la distance la plus petite et on le retourne.
  *</p>*/
    public Plan planIncid(Point pointLumiere){

    	double dist = 0;
    	ArrayList<Point> liste = this.Sommet();
    	Point def = liste.get(0);
    	Plan p = new Plan(liste.get(0), liste.get(1), liste.get(2));
    	ArrayList<Plan> parallelepipede = new ArrayList<Plan>();
    	parallelepipede.add(estDansFace(pointLumiere, new Plan(liste.get(0), liste.get(1), liste.get(2)) , liste.get(3)));
    	parallelepipede.add(estDansFace(pointLumiere, new Plan(liste.get(0), liste.get(1), liste.get(5)) , liste.get(6)));
    	parallelepipede.add(estDansFace(pointLumiere, new Plan(liste.get(0), liste.get(3), liste.get(4)) , liste.get(5)));
    	parallelepipede.add(estDansFace(pointLumiere, new Plan(liste.get(7), liste.get(4), liste.get(5)) , liste.get(6)));
    	parallelepipede.add(estDansFace(pointLumiere, new Plan(liste.get(7), liste.get(2), liste.get(3)) , liste.get(4)));
    	parallelepipede.add(estDansFace(pointLumiere, new Plan(liste.get(7), liste.get(1), liste.get(2)) , liste.get(6)));
    	for(int i=0; i < parallelepipede.size(); i++){
    		if (parallelepipede.get(i) != (new Plan(new Point(),new Point(),new Point()))){
    			Point inter = pointIntersect( rayonIncidant(pointLumiere), parallelepipede.get(i));
    			if (dist < calculdist( pointLumiere, inter)){
    				dist = calculdist( pointLumiere, inter);
    				def = inter;
    				p = parallelepipede.get(i);
    			}
    		}
    	}
    	return p;
    }

    /**<h1>la methode pointIncid</h1>
 *<p> On récupere le point d'incidence, c'est a dire, le point ou le rayon lumineux touche l'objet.
  *</p>*/
    public Point pointIncid(Point pointLumiere){
    	Plan p = planIncid(pointLumiere);
    	Droite lum = rayonIncidant(pointLumiere);
    	Point pointe = pointIntersect(lum, p);

    	return pointe;
    }

/**<h1>la methode pointIntersect</h1>
 *<p> Dans cette méthode, on calcul le point ou la lumière entre en contacte avec la forme.
 *	on va chercher le point d'impact de la droite (entre le centre du parallelepipede avec le point lumineux)
 *	et le plan représenté par la face du P.
  *</p>*/
	public Point pointIntersect(Droite lumiere, Plan plane){
		double t = plane.retrouveT(lumiere);
		return (lumiere.represPram(t));
	}

/**<h1>la methode droiteNormal</h1>
 *<p> Dans cette méthode, on calcul la droite normal d'un objet en un point.
 *	La droite normal d'un objet est une droite perpendiculaire a la face
 *	ou elle est calculer ici, on veut cette droite en un point particulier.
  *</p> On a un vecteur et un point pour la droite normal ainsi,
  on calcul un autre point en faisant les coordonnée du premier point - celle du vecteur.
  *</p>*/
  	public Droite droiteNormal(Point pointLumiere){
  		Point incidence = pointIncid(pointLumiere);
  		Plan p = planIncid(pointLumiere);
  		Vecteur vect = p.vectorme();
  		Point recup = new Point((incidence.getX() - vect.getX()), (incidence.getY() - vect.getY()), (incidence.getZ() - vect.getZ()));
    	Droite d = new Droite(incidence,recup);
    	return d;
  	}

/**<h1>la methode calculdist</h1>
 *<p> Cette méthode nous permet en faisant appel,
  *a la fonction calculLongueur de la classe Droite,
 * de récupérer la distance entre deux points et ainsi,
  *la largeur et la hauteur du parallelepipède rectangle.
  *</p>
  *<p>En premier lieu on appel la fonction sommet() qu'ici je surnome listeP.
  *Pour récuperer la hauteur, on "remplace":
  *	 - p  par listeP[2].
  *	 - q par listeP[0].
  *Pour récuperer la largeur:
  *	 - p  par listeP[1].
  *	 - q listeP[0].</p>*/
	public double calculdist( Point p, Point q){
		Droite debP5 = new Droite(p, q);
		return debP5.calculLongueur();
	}

	public Point getPointDebut(){
		return point_debut;
	}

	public Point getPointFin(){
		return point_fin;
	}

	public Color getCoul(){
		return this.coul;
	}
}
