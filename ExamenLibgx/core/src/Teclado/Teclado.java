package Teclado;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import Actores.BadLogic;
import Actores.Movimiento;

public class Teclado extends InputListener {



    private BadLogic badLogic;


    public Teclado( BadLogic badLogic){
    super();


        this.badLogic=badLogic;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        Gdx.app.log("Tecla pulsada",""+keycode);
        switch (keycode){
            case Input.Keys.SPACE:
                badLogic.iniciarMovimiento(Movimiento.GIRO);
                break;

        }
        return super.keyDown(event, keycode);
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {

        switch (keycode) {
            case Input.Keys.SPACE:
                badLogic.finalizarMovimiento(Movimiento.GIRO);
                break;
        }
        return super.keyUp(event, keycode);

    }



}
