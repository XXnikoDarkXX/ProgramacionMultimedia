package io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

import personajes.Cerebro;

public class TecladoActor extends InputListener {
   Cerebro actor;

   public TecladoActor(Cerebro c){
       this.actor=c;
   }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {


       switch (keycode){
           case Input.Keys.I://esquina superior izquierda
               MoveByAction moverDerecha=new MoveByAction();
               moverDerecha.setAmount(100,0);
               moverDerecha.setDuration(5);
               actor.addAction(moverDerecha);

               Gdx.app.log("Pulsando tecla","i");

               break;


           case Input.Keys.O:
               MoveByAction   mov=new MoveByAction();
               mov.setAmount(300,20);
               mov.setDuration(5);
               actor.addAction(mov);
               Gdx.app.log("Pulsando tecla","o");
               break;


       }
       return false;
    }
}
