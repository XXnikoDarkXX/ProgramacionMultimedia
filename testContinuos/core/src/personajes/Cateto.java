package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import pantallas.Pantalla;

public class Cateto extends Objeto {

    public Cateto(Pantalla p){
        super(p,"npc/cateto.png", 5, 10, 1, 1);
    }

    public Cateto(Pantalla p,int posX,int posY){
        super(p,"npc/cateto.png",posX,posY,1,1);
    }

}
