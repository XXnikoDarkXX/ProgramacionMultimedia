package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Random;

import Actors.Calipo;
import ElementosTerreno.BloqueFijo;
import ElementosTerreno.Meta;
import ElementosTerreno.Nube;
import ElementosTerreno.Suelo;

/**
 * Created by Miguel on 26/02/2019.
 */

public class Mundo {
    SpriteBatch batch; //Batch que dibujará Sprites
    OrthographicCamera camera; //Cámara para escalar Sprites.
    World world; //Mundo en el que se dibujarán los actores.
    Box2DDebugRenderer debugRenderer; //Solo para mostrar formas de depuración.
    public static Texture fondo; //Textura de fondo de pantalla
    ArrayList<BloqueFijo> elementosFijos; //Suelo, techo de nubes y meta.
    SpriteBatch batchTexto; //Batch independiente del mundo, en el que se dibuja el texto
    BitmapFont textoGanar; //El texto que sale al ganar la partida

    public Mundo(){

        batch = new SpriteBatch();
        batchTexto=new SpriteBatch();
        camera = new OrthographicCamera(30,30);
        world = new World(new Vector2(0, -120), true);
        debugRenderer = new Box2DDebugRenderer();
        fondo=new Texture(Gdx.files.internal("fondos/cieloMinecraft.png"));

        //Generator es auxiliar para crear bien creado un bitmapfont
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fuentes/FFF_Tusj.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        parameter.borderColor=new Color(0.1f,0.1f,0.1f,1);
        parameter.borderWidth=3f;
        parameter.incremental=true;
        textoGanar = generator.generateFont(parameter);


        //Construimos el mundo
        reconstruir();

    }

    public void reconstruir(){
        //Si ya tenemos elementos, los destruimos
        if(elementosFijos!=null){
            for(BloqueFijo elemento:this.elementosFijos){
                elemento.dispose();
            }
        }

        elementosFijos=new ArrayList<BloqueFijo>();
        //Añador tres bloques a ras de suelo en las primeras posiciones siempre, para que tenga donde caer al resetear
        elementosFijos.add(new Suelo(this,0,0));
        elementosFijos.add(new Suelo(this,2,0));
        elementosFijos.add(new Suelo(this,4,0));
        //durante 25 niveles de pixels
        for(int nivel=0;nivel<25;nivel++) {
            Random random=new Random();
            //Creo el suelo de forma aleatoria, en los primeros 300 metros, sabiendo que el ancho del suelo es de 2 mentros.
            //A partir de la seis, porque en los 6 primeros metros hay bloques fijos. Dejo 3 metros en lugar de 2 para que haya separación suficiente
            for (int distancia =6; distancia < 300; distancia = distancia + 3) {
                //Establecemos un porcentaje bajo de que haya suelo
                if(random.nextInt(10)==0){
                    elementosFijos.add(new Suelo(this,distancia,nivel*2));
                }
            }
        }

        //Después, añado nubes por arriba, para que no pueda saltar demasiado
        for (int distancia =0; distancia < 300; distancia = distancia + 2) {
            elementosFijos.add(new Nube(this,distancia,27*2));

        }

        //Al final del recorrido, ponemos los bloques de la meta
        for (int altura =-25; altura < 50; altura++) {
            elementosFijos.add(new Meta(this,300,altura*2));

        }
    }

    public World getWorld(){
        return world;
    }

    public void redimensionar(){
        batch.setProjectionMatrix(camera.combined);
    }

    public void dibujar(Calipo personaje){
        //Lo primero, comprobamos la rotación para que se mueva el pollo usando giroscopios.
        float actualAcelY = Gdx.input.getAccelerometerY();
        float actualAcelX = Gdx.input.getAccelerometerX();

        if (actualAcelY < -3) {
            Gdx.app.log("Aceleración y:",Float.toString(actualAcelY));
            personaje.getCuerpo().applyForceToCenter(1000,0,true);
        }
        if (actualAcelY > 3) {
            Gdx.app.log("Aceleración y:",Float.toString(actualAcelY));
            personaje.getCuerpo().applyForceToCenter(-1000,0,true);
        }
        if (actualAcelX < -3) {
            Gdx.app.log("Aceleración x:",Float.toString(actualAcelX));
            personaje.getCuerpo().applyForceToCenter(0,-1000,true);
        }
        if (actualAcelX > 3) {
            Gdx.app.log("Aceleración x:",Float.toString(actualAcelX));
            personaje.getCuerpo().applyForceToCenter(0,1000,true);
            //Gdx.input.vibrate(100);
        }




        // Step the physics world.
        world.step(Gdx.graphics.getDeltaTime(), 8, 4);





        //Centro la camara en el jugador
        //Gdx.app.log("posicion Calipo",actores[0].getX()+" : "+actores[0].getY());
        camera.position.x=personaje.getX();
        camera.position.y=personaje.getY();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //Dibujo el fondo de este mundo
        batch.draw(fondo, 0, 0, 300,56);
        //Dibujo todos los actores
        personaje.draw(batch, 0);

        for(int i=0;i<elementosFijos.size();i++){
            elementosFijos.get(i).draw(batch,0);
        }



        batch.end();

        //Si calipo ha superado la meta, escribir ganar
        //No lo dibujo en el otro batch porque está asociado a las características del mundo
        batchTexto.begin();
        if(personaje.getX()>300){
            //Esta transformMatrix está para que el texto salga rotado
            batchTexto.setTransformMatrix(new Matrix4().setToRotation(0,0,1,30));

            textoGanar.draw(batchTexto, "¡GANAR!",70, Gdx.graphics.getHeight()/2-100,Gdx.graphics.getWidth(),1,false);

            //El Random Siguiente son solo para que se dibuje multicolor el texto
            Random r=new Random();
            textoGanar.setColor(new Color(r.nextFloat(),r.nextFloat(),r.nextFloat(),1));
            textoGanar.getData().setScale(1.2f);

            //Además, paramos a calipo para siempre
            personaje.getCuerpo().setLinearVelocity(new Vector2(0,0));
            personaje.getCuerpo().setAngularVelocity(0);
            personaje.resetearMuertes();
        }
        batchTexto.end();


        //Por último dibujamos el informe de muertes
        personaje.dibujarHUD();

        // uncomment to show the physics polygons
        debugRenderer.render(world, camera.combined);
    }

    public void dispose(){
        world.dispose();
        debugRenderer.dispose();
    }
}
