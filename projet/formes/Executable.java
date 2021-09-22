package formes;

import javafx.scene.paint.Color;

public class Executable{

	public static void main (String[] args) {

		Point centre= new Point();
		Point lum=new Point(0,0,6);
		Droite d = new Droite();
		Sphere bouleDeGlace = new Sphere(centre,5,Color.rgb(125,125,125));
		System.out.println(bouleDeGlace.pointIncidence(lum));
	    d = bouleDeGlace.droiteNormal(lum);
	    System.out.println(d);


	}
}
