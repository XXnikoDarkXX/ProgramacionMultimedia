package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.TecladoActor;

public class Cerebro extends Actor {
    private  Sprite sprite;
    public Cerebro(){

     //   addListener(new TecladoActor(this));

        sprite=new Sprite(new Texture(Gdx.files.internal("cerebro.png")));
        sprite.setBounds(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()/7,
                Gdx.graphics.getHeight()/7);
        this.setBounds(Gdx.graphics.getWidth()/2-sprite.getWidth()/2,Gdx.graphics.getHeight()/2-sprite.getHeight()/2,Gdx.graphics.getWidth()/7,
                Gdx.graphics.getHeight()/7);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    sprite.setPosition(getX(),getY());
        sprite.draw(batch);
    }
}


