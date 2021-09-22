#include "textures.inc"
//ce fichier n'affichera pas de contenu, il est créé uniquement pour tester l'interpréteur
camera{

    location <1000,1000,0>
    look_at <0,00,0>
}

light_source{
    <400,400,9>
    color <1,1,1>


}

plane {
    y
    0
    pigment {
        rgb <1,1,1>
    }
}

box {
    <150,250,0>,<150,200,50>
    rotate <74,0,0>
    scale <2,1,0.5>
    pigment {rgb <10,19,152>}
    texture {Jade}

}

box {
    <600,600,3>,<800,700,1>
    translate <44, 27, 12>
    pigment {rgb <10,19,152>}
}


cylinder{
    <500,400,4>, <400,806,6>,80
    translate <5, 3, 5>
    pigment {rgb <10,19,152>}
}
cylinder{
    <300,800,2>,<250,600,6>,65
    rotate <0,12,0>
    scale <8,8,8>
    pigment {rgb <12,123,56>}
    texture {Blood_Sky}
}

sphere{
    <20,800,120>, 66
    rotate <0,0,63>
    scale <7.2,0.2,0.5>
    pigment {rgb <57,68,54>}
    texture {Glass}
}

sphere{
    <200,400,54>,32
    translate <7, 7, 7>
}

sphere{
    <800,100,40>, 45
    rotate <0,24,0>
    scale <2.1,7.4,6.5>
    pigment {rgb <45,87,69>}
    texture {Glass}
}

cone{
    <700,400,4>, 50 , <800,200,7>, 1
    translate <8, -7, 11>
}
cone{
    <570,150,4>, 45 , <530,100,7>,8
    rotate <56,0,0>
    scale <20,20,20>
    pigment {rgb <108,0,0>}
    texture {Metal}
}
