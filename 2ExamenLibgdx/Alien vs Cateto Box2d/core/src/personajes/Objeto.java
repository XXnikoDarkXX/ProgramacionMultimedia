package personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

import java.util.HashSet;
import java.util.Vector;

import controles.Posiciones;
import controles.Teclado;
import pantallas.Pantalla;

public abstract class Objeto extends Actor {
    private Sprite sprite;
    private HashSet<Posiciones> movimientosActivos;
    private BodyDef propiedades; //propiedades físicas del cuerpo
    private Body cuerpo; //Cuerpo físico del Objeto
    private Pantalla pantalla; //pantalla donde me muevo
    private Bala bala;



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
        propiedades.fixedRotation=true;
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


    public Objeto(Pantalla p,String rutaSprite,int posX,int posY,int ancho,int alto,Bala bala){
        this.pantalla=p;
        movimientosActivos=new HashSet<Posiciones>();
        sprite=new Sprite(new Texture(Gdx.files.internal(rutaSprite)));
        sprite.setBounds(posX,posY,ancho,alto);
        this.setBounds(posX,posY,ancho,alto);
        this.setRotation(sprite.getRotation());
        this.bala=bala;
        this.addListener(new Teclado(this,p,this.bala));
        this.setOrigin(0,0);
        sprite.setOrigin(this.getOriginX(),this.getOriginY());
        this.propiedades=new BodyDef();
        propiedades.type= BodyDef.BodyType.DynamicBody;
        propiedades.fixedRotation=true;
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

    public  Objeto(){

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
        if(movimientosActivos.contains(Posiciones.IZQUIERDA)){
            this.cuerpo.applyForceToCenter(new Vector2(-10,0),true);
        }
        if(movimientosActivos.contains(Posiciones.DERECHA)){
            this.cuerpo.applyForceToCenter(new Vector2(10,0),true);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setProjectionMatrix(this.pantalla.getCamera().combined);
        super.draw(batch, parentAlpha);
        mover();
        sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,
                cuerpo.getPosition().y-sprite.getHeight()/2);
        this.setPosition(cuerpo.getPosition().x,
                cuerpo.getPosition().y);
        sprite.setScale(getScaleX(),getScaleY());
        sprite.setRotation(cuerpo.getAngle());
        this.setRotation(cuerpo.getAngle());
        sprite.setColor(getColor().r,getColor().g,getColor().b,getColor().a);
        sprite.draw(batch);
    }

    public Body getCuerpo(){
        return this.cuerpo;
    }

    public Bala obtenerBala(Bala bala){
        return bala;
    }

    public void setPantalla(Pantalla p){
        ((Teclado)this.getListeners().get(0)).setPantalla(p);
    }
}
