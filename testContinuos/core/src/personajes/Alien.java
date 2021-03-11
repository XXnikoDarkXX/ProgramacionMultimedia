package personajes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
