# Rendu 3D par lancer de rayon groupe 3A

Projet de seconde année de licence d'informatique ayant pour but de transformé un fichier de conf d'image Pov-Ray en image png (afficher et enregistrer) à l'aide de Java

## Membre du groupe
* Bauchez Marguerite 21803320
* Cocquerez Olivier 21803229
* Lebranchu Paul 21403460
* Lemaire Raphaëlle 21802756



## Comment utiliser le projet? 

Le script exe.sh compile l'ensemble des fichiers .java lance le programme.

Pour executer le fichier, il faut ouvrir un terminal dans le dossier rendu_3d_g3a_morp et rentrer la commande ./exe.sh

Si vous avez des problèmes de droit faite chmod -x exe.sh sous windows, chmod 774 exe.sh sous linux et réexecuter la commande.

Si la commande ne s'execute toujours pas, il est possible que vous ayez un problème de droit avec le script compile.sh; pour régler ceci rentrer la série de commande suivante:

cd script
chmod -x compile.sh (windows) ou chmod 774 compile.sh (linux)
cd ..
exe.sh

Vous pouvez également lancer le programme en rentrant la commande: java -jar exe.jar


## Organisation des fichier: 

* expression: contient le rapport, la soutenance et les fichiers LaTex les générant

* images: fichier où seront stockés les images généré par le projet

* projet: contient les classe java un fichier build qui receptionnera les .class lors de la compilation et un .jar executable(placé ici pour que les images généré par le programme soit envoyé au bonne endroit)

* script: contient les script de compile et exeTest 

* exe.sh: script de compilation executant le programme


## Classe test disponibles et executables avant le script exeTest.sh du dossier script:

* projet/formes/Executables : effectue des test sur les premières formes d'un objet

* projet/formes/TestDroite

* projet/formes/TestCercle

* projet/formes/TestCone

* projet/formes/TestCylindre

* projet/lumiere/TestLum

* projet/lumiere/TestSha

* projet/main/Translate : effectue des test sur un fichier povray contenant différentes formes avec différentes option

* projet/main/TestBox : créé des objet de type Parallepipede à partir d'un povray

* projet/main/TestSphere : créé des objet de type Sphere à partir d'un povray

* projet/main/TestCone : créé des objet de type Cone à partir d'un povray

* projet/main/TestCylindre : créé des objet de type Cylindre à partir d'un povray


