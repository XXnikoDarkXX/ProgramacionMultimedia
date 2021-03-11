package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

import java.util.HashSet;

import controles.Posiciones;
import controles.Teclado;
import pantallas.Pantalla;

public abstract class Objeto extends Actor {
    private Sprite sprite;
    private HashSet<Posiciones> movimientosActivos;
    private BodyDef propiedades; //propiedades físicas del cuerpo
    private Body cuerpo; //Cuerpo físico del Objeto
    private Pantalla pantalla; //pantalla donde me muevo

    public Objeto(Pantalla p,String rutaSprite,int posX,int posY,int ancho,int alto){
        this.pantalla=p;
        movimientosActivos=new HashSet<Posiciones>();
        sprite=new Sprite(new Texture(Gdx.files.internal(rutaSprite)));
        sprite.setBounds(posX,posY,ancho,alto);
        this.setBounds(posX,posY,ancho,alto);
        this.setRotation(sprite.getRotation());
        this.addListener(new Teclado(this,p));
        this.setOrigin(0,0);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
        this.propiedades=new BodyDef();
        propiedades.type= BodyDef.BodyType.DynamicBody;
        propiedades.position.set(posX,posY);
        this.cuerpo=pantalla.getMundo().createBody(propiedades);
        FixtureDef propiedadesFisicas=new FixtureDef();
        propiedadesFisicas.shape=new PolygonShape();
        ((PolygonShape)propiedadesFisicas.shape).setAsBox(
                sprite.getWidth()/2,sprite.getHeight()/2
        );
        propiedadesFisicas.density=1;
        cuerpo.createFixture(propiedadesFisicas);

    }

    public void iniciarMovimiento(Posiciones p){
        this.movimientosActivos.add(p);
    }

    public void finalizarMovimiento(Posiciones p){
        this.movimientosActivos.remove(p);
    }

    public void limpiarMovimiento(){
        this.movimientosActivos=new HashSet<Posiciones>();
    }

    private void mover(){
        if(movimientosActivos.contains(Posiciones.ARRIBA)){
            MoveByAction movArriba=new MoveByAction();
            movArriba.setAmount(0,5);
            movArriba.setDuration(Gdx.graphics.getDeltaTime());
            this.addAction(movArriba);
        }
        if(movimientosActivos.contains(Posiciones.ABAJO)){
            MoveByAction movAbajo=new MoveByAction();
            movAbajo.setAmount(0,-5);
            movAbajo.setDuration(Gdx.graphics.getDeltaTime());
            this.addAction(movAbajo);
        }
        if(movimientosActivos.contains(Posiciones.IZQUIERDA)){
            MoveByAction movIzda=new MoveByAction();
            movIzda.setAmount(-5,0);
            movIzda.setDuration(Gdx.graphics.getDeltaTime());
            this.addAction(movIzda);
        }
        if(movimientosActivos.contains(Posiciones.DERECHA)){
            MoveByAction movDerecha=new MoveByAction();
            movDerecha.setAmount(5,0);
            movDerecha.setDuration(Gdx.graphics.getDeltaTime());
            this.addAction(movDerecha);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        mover();
        sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,cuerpo.getPosition().y-sprite.getHeight()/2);
       this.setPosition(cuerpo.getPosition().x,cuerpo.getPosition().y);
        sprite.setScale(getScaleX(),getScaleY());
        sprite.setRotation(cuerpo.getAngle());
        this.setRotation(cuerpo.getAngle());
        sprite.setColor(getColor().r,getColor().g,getColor().b,getColor().a);
        sprite.draw(batch);
    }

    public void setPantalla(Pantalla p){
        ((Teclado)this.getListeners().get(0)).setPantalla(p);
    }
}
