#include "textures.inc"
camera{

    location <1000,1000,1000>
    look_at <0,00,0>
}

light_source{
    <100,800,400>
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
    <100,100,100>,<200,200,401>
    pigment {rgb<1,200,10>}


}

box {
    <600,600,100>,<800,700,204>
    pigment {rgb<1,120,6>}

}
