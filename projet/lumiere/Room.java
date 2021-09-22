package lumiere;

import java.util.*;
import formes.*;
import java.lang.Math.*;

import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial ;
import javafx.scene.effect.Light.*;
import javafx.scene.effect.Light.Point;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.BlurType;
/**
	*<h1> class Room </h1>
	*<p> La class room a pour but de créé une Image conteant un
	*ensemble de formes tout en gérant la lumière et leur ombre </p>
*/
public class Room{

	/**
	*<p>ArrayList contenant l'ensembles des parallépides à représenter</p>
	*/
	protected ArrayList<Parallepipede> para;

	/**
	*<p> ArrayList conteant l'ensemble des sphères à représenter</p>
	*/
	protected ArrayList<formes.Sphere> sphere;

	/**
	*<p> ArrayList contenant l'ensemble des cones à représenter </p>
	*/
	protected ArrayList<Cone> cone;

	/**
	*<p> ArrayList contenant l'ensemble des cylindre à dessiner </p>
	*/
	protected ArrayList<Cylindre> cyl;

	/**
	*<p> coordonnés du point d'où provient la lumière</p>
	*/
	protected formes.Point lum;

	/**
	*<p> coordonnées du point de la camera</p>
	*/
	protected formes.Point cam;

	/**
	*<p> prend en paralètre quatre ArrayList représentant les formes à représenter, deux point représentant la camera et source de lumière et appellle la fonction createForme</p>
	*/
	public Room(ArrayList<Parallepipede> para, ArrayList<formes.Sphere> sphere, ArrayList<Cone> cone,ArrayList<Cylindre> cyl,formes.Point lum, formes.Point cam){
		this.para = para;
		this.sphere = sphere;
		this.cone = cone;
		this.cyl =cyl;

		this.lum = lum;
		this.cam = cam;
	}

	/**
	*<p> méthode retournant l'image à enregistrer, parcours les liste d'objets mise en paramètres
	*et dessine les formes dans une WritableImage</p>
	*/
	public Image createForme(){
		/* Lumiere et Shadow aurait du être implémenter en début de classe
		mais nous n'avons pas réussi à implémenter correctement ces classes
		Lumiere lum = New Lumiere()
		Shadow ombre = New Shadow()
		*/
		//créé une image vide
		WritableImage tampon = new WritableImage(1200,900);

		/*camera gérant la persepctive
		 c'est ici que l'on aurait du utiliser le point camera
		 mais nous n'avons pas trouvé comment gérer ce paramètre*/
		PerspectiveCamera perspectivecamera = new PerspectiveCamera();

		//création d'un point lumineux qui prendra les coordonnés du point lumineux en attribut
		javafx.scene.effect.Light.Point lumiere = new javafx.scene.effect.Light.Point();
		//retire 1200 à l'axe x et 900 à l'axe y à cause de l'inversion de l'image à la fin
		//du code
 		lumiere.setX(1200-lum.getX());
 		lumiere.setY(900-lum.getY());
 		lumiere.setZ(lum.getZ());

		//ajout du point lumineux dans une variable lighting pour gérer les effets lumineux
 		Lighting lighting = new Lighting();
 		lighting.setLight(lumiere);

    // création du group qui contiendra toutes les formes dessinés
		Group group = new Group();

		//création d'une scène qui stockera l'image
		Scene scene = new Scene(group,1200,900);
		scene.setCamera(perspectivecamera);

		//image de fond + intégration du point lumineux
		Color gris = Color.rgb(25,25,25);
		Rectangle rec = new Rectangle(1200,900,gris);
		rec.setEffect(lighting);
		group.getChildren().add(rec);

		//pour chaque parallépides, ont créé un objet de type box
		for (Parallepipede p: para){
			//création d'une couleur a applqiué à l'objet
			PhongMaterial coul = new PhongMaterial();
			coul.setDiffuseColor(p.getCoul());

			//longeur, largeur et profondeur du parallépides
			double longeur = Math.abs(p.getPointDebut().getX()-p.getPointFin().getX());
			double hauteur = Math.abs(p.getPointDebut().getY()-p.getPointFin().getY());
			double profondeur = Math.abs(p.getPointDebut().getZ()-p.getPointFin().getZ());

			//création de l'ombre portée
			/*si l'objet est à gache de la lumière,sont ombre sera projeté
			à sa gauche, si l'objet est à droite, sont ombre sera projeté
			à sa droite
			*/
			//création d'un effet "DropShadow qui créra une ombre porté d'objet 2D"
			DropShadow dropShadowPara = new DropShadow();
			dropShadowPara.setColor(Color.rgb(0, 0, 0,0.7));
			dropShadowPara.setBlurType(BlurType.GAUSSIAN);

			if(p.getPointDebut().getX() > lumiere.getX())
				{dropShadowPara.setOffsetX(longeur);}
			if(p.getPointDebut().getX() < lumiere.getX())
				{dropShadowPara.setOffsetX(-longeur);}
			else{dropShadowPara.setOffsetX(0);}

			/*si l'objet est au dessus de la lumière,son ombre sera projeté
			au dessus de lui, si l'objet est en dessous, sont ombre sera projeté
			sous lui
			*/
			if(p.getPointDebut().getY() > lumiere.getY())
				{dropShadowPara.setOffsetY(hauteur);}
			if(p.getPointDebut().getY() < lumiere.getY())
				{dropShadowPara.setOffsetY(-hauteur);}
			else{dropShadowPara.setOffsetY(0);}

			/*création de la forme de l'ombre
			on divise la longeur et la hauteur par 2 et on la rajoute ou la retire
			à la coordonnées de chaque point pour traduire les coordonnées de
			la face avant de l'objet de type box en un rectangle*/
			Polygon polyOmbre = new Polygon();
			polyOmbre.getPoints().addAll(new Double[]{
        p.getPointDebut().getX()-longeur/2,p.getPointDebut().getY()-hauteur/2, //point numero 1
				p.getPointDebut().getX()+longeur/2,p.getPointDebut().getY()-hauteur/2, //point numero 2
				p.getPointDebut().getX()+longeur/2,p.getPointDebut().getY()+hauteur/2,//point numero 3
				p.getPointDebut().getX()-longeur/2,p.getPointDebut().getY()+hauteur/2//point numero 4
			} );
			//ajout de la profondeur de l'objet
			polyOmbre.setTranslateZ(p.getPointDebut().getZ());

			//ajout de l'effet d'ombre portée
			polyOmbre.setEffect(dropShadowPara);
 			group.getChildren().add(polyOmbre);

			//création de l'objet boite en calculant sa longeur, sa largeur et sa profondeur
			Box box = new Box(longeur, hauteur, profondeur );

			//position de l'objet de type Box
			box.setTranslateX(p.getPointDebut().getX());
      box.setTranslateY(p.getPointDebut().getY());
			box.setTranslateZ(p.getPointDebut().getZ());

			//ajout effet de couleur
			box.setMaterial(coul);

			//ajout de l'objet dans le group
			group.getChildren().add(box);
		}

		//création des sphères
		for (formes.Sphere s: sphere){

			//couleur sphere
			PhongMaterial coul = new PhongMaterial();
			coul.setDiffuseColor(s.getCoul());

			//création d'un effet "DropShadow qui créra une ombre porté d'objet 2D (cercle)
			DropShadow dropShadowS = new DropShadow();
			dropShadowS.setColor(Color.rgb(0, 0, 0,0.7));
			dropShadowS.setBlurType(BlurType.GAUSSIAN);

			if(s.getCentre().getX() > lumiere.getX())
				{dropShadowS.setOffsetX(s.getRayon());}
			if(s.getCentre().getX() < lumiere.getX())
				{dropShadowS.setOffsetX(-s.getRayon());}
			else{dropShadowS.setOffsetX(0);}

			if(s.getCentre().getY() > lumiere.getY())
				{dropShadowS.setOffsetY(s.getRayon());}
			if(s.getCentre().getY() < lumiere.getY())
				{dropShadowS.setOffsetY(-s.getRayon());}
			else{dropShadowS.setOffsetY(0);}

			//création d'un cercle qui servira pour l'ombre de la sphère
			Circle circ = new Circle(s.getRayon());
			circ.setTranslateX(s.getCentre().getX());
      circ.setTranslateY(s.getCentre().getY());
			circ.setTranslateZ(s.getCentre().getZ());

			//ajout de l'effet ombre portée et de la forme au group
			circ.setEffect(dropShadowS);
 			group.getChildren().add(circ);

			//création de la sphère + position
			javafx.scene.shape.Sphere sphere = new javafx.scene.shape.Sphere(s.getRayon());
			sphere.setTranslateX(s.getCentre().getX());
      sphere.setTranslateY(s.getCentre().getY());
			sphere.setTranslateZ(s.getCentre().getZ());

			//ajout couleur à la sphère
			sphere.setMaterial(coul);

			//ajout sphère dans le group
			group.getChildren().add(sphere);
		}

		//création des cylindres
		for (Cylindre cy: cyl){
			//couleur du cylindre
			PhongMaterial coul = new PhongMaterial();
			coul.setDiffuseColor(cy.getCoul());

			// effet ombre portée
			DropShadow dropShadowCy = new DropShadow();
			dropShadowCy.setColor(Color.rgb(0, 0, 0,0.7));
			dropShadowCy.setBlurType(BlurType.GAUSSIAN);

			//calcul de la hauteur de la sphère
			double hauteur = Math.abs(cy.getHaut().getY()-cy.getBas().getY());

			if(cy.getBas().getX() > lumiere.getX())
				{dropShadowCy.setOffsetX(cy.getRayon());}
			if(cy.getBas().getX() < lumiere.getX())
				{dropShadowCy.setOffsetX(-cy.getRayon());}
			else{dropShadowCy.setOffsetX(0);}

			if(cy.getBas().getY() > lumiere.getY())
				{dropShadowCy.setOffsetY(cy.getRayon());}
			if(cy.getBas().getY() < lumiere.getY())
				{dropShadowCy.setOffsetY(-cy.getRayon());}
			else{dropShadowCy.setOffsetY(0);}

			//polygon représentant l'ombre du cylindre
			Polygon polyOmbre = new Polygon();

			/*retire ou ajoute la hauteur/2 à chaque coordonnées y pour
			effectuer la transition entre le dessin du polygone et la méthode
			de dessin des objets cylindres */
			polyOmbre.getPoints().addAll(new Double[]{
        (cy.getBas().getX()+cy.getRayon()),(cy.getBas().getY()-hauteur/2), //point numero 1
				(cy.getBas().getX()-cy.getRayon()),(cy.getBas().getY()-hauteur/2), //point numero 2
				(cy.getBas().getX()-cy.getRayon()),(cy.getBas().getY()+hauteur/2),//point numero 3
				(cy.getBas().getX()+cy.getRayon()),(cy.getBas().getY()+hauteur/2)//point numero 4
			} );

			polyOmbre.setTranslateZ(cy.getBas().getZ());
			polyOmbre.setEffect(dropShadowCy);
 			group.getChildren().add(polyOmbre);

			Cylinder cylindre = new Cylinder(cy.getRayon(),hauteur);

			cylindre.setTranslateX(cy.getBas().getX());
      cylindre.setTranslateY(cy.getBas().getY());
			cylindre.setTranslateZ(cy.getBas().getZ());

			cylindre.setMaterial(coul);
			group.getChildren().add(cylindre);

		}


		//créations de formes semblable à des cones
		for (Cone co: cone){

			//création de la couleur de la forme:
			PhongMaterial coul = new PhongMaterial();
			coul.setDiffuseColor(co.getCoul());

			// effet ombre portée
			DropShadow dropShadowCo = new DropShadow();
			dropShadowCo.setColor(Color.rgb(0, 0, 0,0.7));
			dropShadowCo.setBlurType(BlurType.GAUSSIAN);

			if(((co.getBas().getX()+co.getHaut().getX())/2) > lumiere.getX())
				{dropShadowCo.setOffsetX(Math.max(co.getRayonH(),co.getRayonB()));}
			if(((co.getBas().getX()+co.getHaut().getX())/2) < lumiere.getX())
				{dropShadowCo.setOffsetX(-Math.max(co.getRayonH(),co.getRayonB()));}
			else{dropShadowCo.setOffsetX(0);}

			if(((co.getBas().getY()+co.getHaut().getY())/2) > lumiere.getY())
				{dropShadowCo.setOffsetY(Math.max(co.getRayonH(),co.getRayonB()));}
			if(((co.getBas().getY()+co.getHaut().getY())/2) < lumiere.getY())
				{dropShadowCo.setOffsetY(-Math.max(co.getRayonH(),co.getRayonB()));}
			else{dropShadowCo.setOffsetY(0);}

			//créations ombres portéel

			//cercle représentant l'ombre du bas du cone
			Circle circB = new Circle(co.getRayonB());
			circB.setTranslateX(co.getBas().getX());
      circB.setTranslateY(co.getBas().getY());
			circB.setTranslateZ(co.getBas().getZ());
			circB.setEffect(dropShadowCo);
 			group.getChildren().add(circB);

			//cercle représentant l'ombre du haut du cone
			Circle circH = new Circle(co.getRayonH());
			circH.setTranslateX(co.getHaut().getX());
      circH.setTranslateY(co.getHaut().getY());
			circH.setTranslateZ(co.getBas().getZ());
			circH.setEffect(dropShadowCo);
 			group.getChildren().add(circH);

			//polygone représentant l'ombre du corps du cone
			Polygon polyOmbre = new Polygon();
			polyOmbre.getPoints().addAll(new Double[]{
        (co.getBas().getX()+co.getRayonB()),(co.getBas().getY()), //point numero 1
				(co.getBas().getX()-co.getRayonB()),(co.getBas().getY()), //point numero 2
				(co.getHaut().getX()-co.getRayonH()),(co.getHaut().getY()),//point numero 3
				(co.getHaut().getX()+co.getRayonH()),(co.getHaut().getY())//point numero 4
			} );

			polyOmbre.setTranslateZ(co.getBas().getZ());
			polyOmbre.setEffect(dropShadowCo);

 			group.getChildren().add(polyOmbre);

			//création du cercle du Bas
			javafx.scene.shape.Sphere coneB = new javafx.scene.shape.Sphere(co.getRayonB());
			coneB.setTranslateX(co.getBas().getX());
      coneB.setTranslateY(co.getBas().getY());
			coneB.setTranslateZ(co.getBas().getZ());
			coneB.setMaterial(coul);
			group.getChildren().add(coneB);

			//création du "corps du cone"
			Polygon poly = new Polygon();
			poly.getPoints().addAll(new Double[]{
        (co.getBas().getX()+co.getRayonB()),(co.getBas().getY()), //point numero 1
				(co.getBas().getX()-co.getRayonB()),(co.getBas().getY()), //point numero 2
				(co.getHaut().getX()-co.getRayonH()),(co.getHaut().getY()),//point numero 3
				(co.getHaut().getX()+co.getRayonH()),(co.getHaut().getY())//point numero 4
			} );
			poly.setTranslateZ(co.getBas().getZ());
			//objet 2D -> impossible d'appliquer un effet de phong les deux effets suivants
			//appliquent un effet quasi similaire
			poly.setFill(co.getCoul());
			poly.setEffect(lighting);

			group.getChildren().add(poly);
			//création du cercle du Haut
			javafx.scene.shape.Sphere coneS = new javafx.scene.shape.Sphere(co.getRayonH());

			coneS.setTranslateX(co.getHaut().getX());
      coneS.setTranslateY(co.getHaut().getY());
			coneS.setTranslateZ(co.getBas().getZ());

			coneS.setMaterial(coul);
			group.getChildren().add(coneS);
		}

		//prend une photo de la scène
		scene.snapshot(tampon);

		//rotation du rendu du à une différence de position de l'origine entre povray et javafx
		ImageView inverse = new ImageView(tampon);
		inverse.setRotate(180);

		//groupe contenant l'image final
		Group groupRes = new Group();
		groupRes.getChildren().add(inverse);
		Scene res = new Scene(groupRes,1200,900);

		//permet prévisulaistion de l'image
		Stage stage = new Stage();
		stage.setScene(res);
    stage.show();

		//stock l'image finale
		WritableImage resultat = new WritableImage(1200,900);
    res.snapshot(resultat);

		return resultat;
	}
}
