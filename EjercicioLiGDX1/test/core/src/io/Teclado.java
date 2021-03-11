package io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;

import personajes.Cerebro;

public class Teclado implements InputProcessor {
    private OrthographicCamera camera; //Aquí capturaremos la cámara de la clase del juego
    private TiledMap map;
    private Cerebro cerebro;

    public Teclado(OrthographicCamera camera, TiledMap map,Cerebro cerebro){
        super();
        this.camera = camera; //Capturamos el objeto cámara de la clase del juego
        this.map= map;
        this.cerebro=cerebro;
    }



    @Override
    public boolean keyDown(int keycode) {

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        Gdx.app.log("Pulsando tecla",keycode+" . Tecla esperada: "+Input.Keys.W);
        switch(keycode){
            case Input.Keys.A:
                camera.position.x = Math.max(camera.position.x - 1, 0); //Se mueve la cámara hasta el mínimo del mapa
                break;
                case Input.Keys.D:
                camera.position.x = Math.min(camera.position.x + 1, (int)map.getProperties().get("width")
                ); //Se mueve la cámara hasta el máximo del mapa
                break;
            case Input.Keys.W:
                camera.position.y = Math.min(camera.position.y + 1, (int)map.getProperties().get("height")); //Se mueve la cámara hasta el máximo del mapa
                break;
            case Input.Keys.S:
                camera.position.y = Math.max(camera.position.y - 1, 0); //Se mueve la cámara hasta el mínimo del mapa
                break;
            case Input.Keys.X:
                camera.zoom = Math.max(camera.zoom - 0.2f, 0.1f); //Se hace zoom entre 0.1 y 2
                break;
            case Input.Keys.MINUS:
                camera.zoom = Math.min(camera.zoom + 0.2f, 2); //Se hace zoom entre 0.1 y 2
                break;
          case Input.Keys.O:
                cerebro.setPosition(Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/6,
                        Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/7);
                break;
            case Input.Keys.I:
                cerebro.setPosition(-Gdx.graphics.getWidth()/17
                                +Gdx.graphics.getWidth()/10,
                        Gdx.graphics.getHeight()
                                -Gdx.graphics.getHeight()/7
                                );
                break;


            case Input.Keys.K:
                cerebro.setPosition(0,
                        Gdx.graphics.getHeight()/16
                );

                break;
            case Input.Keys.L:
                cerebro.setPosition(Gdx.graphics.getWidth()-100,
                        Gdx.graphics.getHeight()/16 );


/**
 * cerebro.setPosition(Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/7,
 *                         Gdx.graphics.getHeight()/16 );
 */
                break;
        }
        //this.keepCameraInBounds(); con este metodo nos aseguramos de que no sobrepasemos los limites del mapa
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    private void keepCameraInBounds(){
        int mapWidth=(int)map.getProperties().get("height");
        int mapHeight=(int)map.getProperties().get("width");
        if(camera.position.x-(camera.viewportWidth/2)*camera.zoom<0){camera.position.x=(camera.viewportWidth/2)*camera.zoom;}if(camera.position.y-(camera.viewportHeight/2)*camera.zoom<0){camera.position.y=(camera.viewportHeight/2)*camera.zoom;}if(camera.position.x+(camera.viewportWidth/2)*camera.zoom>mapWidth){camera.position.x=mapWidth-(camera.viewportWidth/2)*camera.zoom;}if(camera.position.y+(camera.viewportHeight/2)*camera.zoom>mapHeight){camera.position.y=mapHeight-(camera.viewportHeight/2)*camera.zoom;}
    }

}
