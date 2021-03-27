package controles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import pantallas.Pantalla;
import personajes.Bala;
import personajes.Objeto;

public class Teclado extends InputListener {
    private Objeto actor;
    private Pantalla pantalla;
    private Bala bala;

    public Teclado(Objeto o, Pantalla p,Bala bala){
        this.actor=o;
        this.pantalla=p;
        this.bala=bala;
    }

    public Teclado(Objeto o, Pantalla p){
        this.actor=o;
        this.pantalla=p;

    }

    public void setPantalla(Pantalla p){
        this.pantalla=p;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        //Gdx.app.log("Tecla pulsada",""+keycode);
        switch (keycode){
            case Input.Keys.D:
                actor.iniciarMovimiento(Posiciones.DERECHA);
                //actor.getCuerpo().applyForceToCenter(new Vector2(100,0),true);
                break;
            case Input.Keys.A:
                actor.iniciarMovimiento(Posiciones.IZQUIERDA);
                break;
            case Input.Keys.W:
                actor.getCuerpo().applyForceToCenter(new Vector2(0,200),true);
                break;
            case Input.Keys.S:
                actor.iniciarMovimiento(Posiciones.ABAJO);
                break;
            case Input.Keys.P:
                Gdx.app.log("Escalado pulsado","!!!");
                ScaleByAction crecer=new ScaleByAction();
                crecer.setAmount(0.3f,0.3f);
                crecer.setDuration(0.3f);
                actor.addAction(crecer);
                break;
            case Input.Keys.O:
                if(actor.getScaleX()>0.3f) {
                    ScaleByAction decrecer = new ScaleByAction();
                    decrecer.setAmount(-0.3f, -0.3f);
                    decrecer.setDuration(0.3f);
                    actor.addAction(decrecer);
                }
                break;
            case Input.Keys.L:
                RotateByAction rotarIzda=new RotateByAction();
                rotarIzda.setAmount(20);
                rotarIzda.setDuration(0.3f);
                actor.addAction(rotarIzda);
                break;
            case Input.Keys.K:
                RotateByAction rotarDcha=new RotateByAction();
                rotarDcha.setAmount(-20);
                rotarDcha.setDuration(0.3f);
                actor.addAction(rotarDcha);
                break;
            case Input.Keys.E:
                //accion de crecer moviendose
                MoveByAction movSaltarE=new MoveByAction();
                movSaltarE.setAmount(25,25);
                movSaltarE.setDuration(0.25f);
                ScaleByAction scaleSaltarE=new ScaleByAction();
                scaleSaltarE.setAmount(0.3f);
                scaleSaltarE.setDuration(0.25f);
                ParallelAction saltarE=new ParallelAction(movSaltarE,scaleSaltarE);

                //accion de decrecer moviendose
                ScaleByAction scaleSaltarE2=new ScaleByAction();
                scaleSaltarE2.setAmount(-0.3f);
                scaleSaltarE2.setDuration(0.25f);
                ParallelAction saltarE2=new ParallelAction(movSaltarE,scaleSaltarE2);

                SequenceAction saltarESeq=new SequenceAction(saltarE,saltarE2);
                actor.addAction(saltarESeq);
                break;
            case Input.Keys.Z:
                actor.addAction(new SequenceAction(new SequenceAction(
                        Actions.alpha(0.1f,0.2f),
                        Actions.alpha(1f,0.2f),
                        Actions.alpha(0.1f,0.2f),
                        Actions.alpha(1f,0.2f)
                ),new SequenceAction(
                        Actions.alpha(0.1f,0.2f),
                        Actions.alpha(1f,0.2f),
                        Actions.alpha(0.1f,0.2f),
                        Actions.alpha(1f,0.2f))));
                break;

            case Input.Keys.ENTER:
                MoveToAction aOrigen=new MoveToAction();
                aOrigen.setPosition(Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/30);
                aOrigen.setDuration(3);
                actor.addAction(aOrigen);
                break;

            case Input.Keys.SPACE:
                bala =new Bala(this.pantalla,"texturas/bala.png",(int)actor.getX()+10,(int)actor.getY(),1,1);
                boolean control=true;
            pantalla.generarBalas(this.bala,control);

                break;
            case Input.Keys.F:
                pantalla.focoAlSiguiente();
                break;
            case Input.Keys.C:
                //Cambiar el control a los alien
                //pantalla.ponerFocoControl(pantalla.getJugador(0));
                break;
            case Input.Keys.V:
                //pantalla.ponerFocoControl(pantalla.getNPC(0));
                break;
        }
        return super.keyDown(event, keycode);
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {

        switch (keycode) {
            case Input.Keys.D:
                actor.finalizarMovimiento(Posiciones.DERECHA);
                break;
            case Input.Keys.A:
                actor.finalizarMovimiento(Posiciones.IZQUIERDA);
                break;
            case Input.Keys.W:
                actor.finalizarMovimiento(Posiciones.ARRIBA);
                break;
            case Input.Keys.S:
                actor.finalizarMovimiento(Posiciones.ABAJO);
                break;
        }
        return super.keyUp(event, keycode);

    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
        return super.scrolled(event, x, y, amountX, amountY);
    }
}
