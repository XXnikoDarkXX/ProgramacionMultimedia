package Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

import java.util.HashSet;

import Teclado.Teclado;

public class BadLogic extends Actor {
    private HashSet<Movimiento> movimientosActivos;

    private Sprite sprite;

    public BadLogic(){
        this.addListener(new Teclado(this));
        movimientosActivos=new HashSet<Movimiento>();
        sprite=new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        this.setRotation(sprite.getRotation());

        sprite.setBounds(0,0,Gdx.graphics.getWidth()/3,
                Gdx.graphics.getHeight()/3);
        this.setBounds(0,0,Gdx.graphics.getWidth()/3,
                Gdx.graphics.getHeight()/3);
    }

    public void iniciarMovimiento(Movimiento p){

        this.movimientosActivos.add(p);
    }

    public void finalizarMovimiento(Movimiento p){
        this.movimientosActivos.remove(p);
    }

    public void limpiarMovimiento(){

        this.movimientosActivos=new HashSet<Movimiento>();
    }

    private void mover() {
        if (movimientosActivos.contains(Movimiento.GIRO)) {
           RotateByAction rotarDcha=new RotateByAction();
            this.setOrigin(getX()/2,getHeight()/2);
            sprite.setOrigin(100,getHeight()/2);
            rotarDcha.setAmount(-20f);


            rotarDcha.setDuration(0.3f);
            this.addAction(rotarDcha);
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
       mover();
        super.draw(batch, parentAlpha);
        sprite.setRotation(this.getRotation());
        sprite.setPosition(getX(),getY());
        sprite.draw(batch);
    }

}
