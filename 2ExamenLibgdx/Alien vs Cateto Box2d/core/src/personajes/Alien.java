package personajes;

import pantallas.Pantalla;

public class Alien extends Objeto {

    public Alien(Pantalla p){
        super(p,"jugadores/alien.png", 1,
                10,1,
                1);
    }



    public Alien(Pantalla p, int posX, int posY){
        super(p,"jugadores/alien.png",posX,posY,1,
                1);
    }



}
