#include "textures.inc"
camera{

    location <1000,1000,1000>
    look_at <0,00,0>
}

light_source{
    <510,800,600>
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
    <210,100,100>,<400,320,30>
    pigment {rgb <200,1,1>}

}


cylinder{
    <800,400,500>,<800,500,500>,66
    pigment {rgb <1,12,56>}
}

sphere{
    <120,400,500>, 80
    pigment {rgb <168,2,69>}
}

cone{
    <600,600,30>, 50 , <600,400,90>, 2
    pigment {rgb <200,200,1>}
}
