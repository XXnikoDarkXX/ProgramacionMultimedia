package ElementosTerreno;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import world.Mundo;

/**
 * Created by Miguel on 17/02/2019.
 */

public abstract class BloqueFijo extends Actor {
    private BodyDef propiedadesSuelo;
    private Body cuerpo;
    private Mundo mundo;
    private Sprite sprite;

    public BloqueFijo(Mundo w, int posX, int posY,String spritePath){

        this.mundo=w;
        //Asignamos la textura
        sprite=new Sprite(new Texture(Gdx.files.internal(spritePath)));
        //2x4 metros, dimensiones predefinidas. Tenemos un pollo hipervitaminado.
        sprite.setSize(2, 2);
        sprite.setOrigin(posX, posY);

        //Creando cuerpo
        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 1;

        PolygonShape shape = new PolygonShape();

        //shape.setAsBox(camera.viewportWidth, 1);
        Vector2[] vertices=new Vector2[4];
        vertices[0]=new Vector2(0,0);
        vertices[1]=new Vector2(sprite.getWidth(),0);
        vertices[2]=new Vector2(sprite.getWidth(),sprite.getHeight());
        vertices[3]=new Vector2(0,sprite.getHeight());
        shape.set(vertices);

        fixtureDef.shape = shape;

        cuerpo = mundo.getWorld().createBody(bodyDef);
        cuerpo.createFixture(fixtureDef);

        cuerpo.setTransform(posX, posY, 0);

        shape.dispose();

    }

    protected void setInactivo(){
        this.cuerpo.setActive(false);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.setScale(getScaleX(),getScaleY());
        sprite.setRotation(getRotation());
        sprite.setPosition(getX(),getY());
        sprite.setColor(getColor().r,getColor().g,getColor().b,getColor().a);
        sprite.setPosition(cuerpo.getPosition().x, cuerpo.getPosition().y);
        sprite.setRotation(cuerpo.getAngle());
        sprite.draw(batch);
    }

    public void dispose() {
        for (Fixture fixture : cuerpo.getFixtureList()) {
            this.cuerpo.destroyFixture(fixture);
        }
    }


}
