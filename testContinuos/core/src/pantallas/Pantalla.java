package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import basedatos.BaseDatos;
import personajes.Alien;
import personajes.Objeto;

public abstract class Pantalla extends Stage {
    private BaseDatos baseDatos;
    private ScreenViewport camara; //Cámara
    private ArrayList<Objeto> jugadores; //Personajes que controlo
    private ArrayList<Objeto> npc; //Personajes que no controlo
    private SpriteBatch batch; //De momento, solo para dibujar fondo
    private Box2DDebugRenderer debugRenderer;//Renderizador de hitbox de objetos
    private int jugadorConFoco; //Que jugador tiene el foco de teclado
    private int npcConFoco; //Qué cateto tiene el foco
    private World mundo; //Mundo de Box2D para aplicar físicas
    private OrthogonalTiledMapRenderer renderer; //Renderizador del mapa.
    private static float unitScale=1/16f; //Depende del ancho de pixels de los patrones del mapa
    private TiledMap mapa; //Mapa 2D de la pantalla actual

    public Pantalla(String rutaFondo){
        super(new ScreenViewport());
        camara=(ScreenViewport)this.getViewport();
        jugadores=new ArrayList<Objeto>();
        npc=new ArrayList<Objeto>();
        batch=new SpriteBatch();

        Gdx.input.setInputProcessor(this);
        mundo=new World(new Vector2(0,-9.8f),true);
        mapa=new TmxMapLoader().load("mapas/pantalla1.tmx");
        renderer=new OrthogonalTiledMapRenderer(mapa,unitScale);
        camara.update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

       Iterator iterator =mapa.getLayers().get("objetos").getObjects().iterator();


       while(iterator.hasNext()){
           RectangleMapObject sueloActual= (RectangleMapObject)iterator.next();
           BodyDef cuerpoSueloDef=new BodyDef();
          cuerpoSueloDef.type= BodyDef.BodyType.StaticBody;
          Body cuerpooSuelo=mundo.createBody(cuerpoSueloDef);

           Fixture fix=cuerpooSuelo.createFixture(getShapeFromRectangle(sueloActual.getRectangle()),1);
           }



    }




    private static Shape getShapeFromRectangle(Rectangle rectangle){
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(rectangle.width*0.5F/ unitScale,rectangle.height*0.5F/ unitScale);
        return polygonShape;
    }


    public World getMundo(){
        return mundo;
    }

    @Override
    public void draw() {

        mundo.step(Gdx.graphics.getDeltaTime(),
                6,2);
        ((OrthographicCamera) camara.getCamera()).setToOrtho(false,15,15);//la anchura de la pantala se ajusta
        //a 10 metros
        // Gdx.app.log("tamañoPantalla",Gdx.graphics.getWidth()+" x "+Gdx.graphics.getHeight());

        getCamera().position.x=this.jugadores.get(jugadorConFoco).getX();
        getCamera().position.y=this.jugadores.get(jugadorConFoco).getY();
        debugRenderer=new Box2DDebugRenderer();
        renderer.setView((OrthographicCamera)camara.getCamera());
        renderer.render();
        camara.getCamera().update();
        debugRenderer.render(mundo,camara.getCamera().combined);
        super.draw();
    }

    public void resize(int ancho,int alto){
        camara.update(ancho,alto);
    }

    public void añadirJugador(Objeto o){
        this.addActor(o);
        this.jugadores.add(o);
    }

    public void añadirNPC(Objeto o){
        this.addActor(o);
        this.npc.add(o);
    }

    public void ponerFocoControl(Objeto o){
        //una de las dos: jugadorConFoco o npcConFoco, me va a dar -1
        //porque el objeto solo está en uno de los dos arrays
        this.jugadorConFoco=jugadores.indexOf(o);
        this.npcConFoco=npc.indexOf(o);
        this.setKeyboardFocus(o);
    }

    public void focoAlSiguiente(){
        if(jugadorConFoco!=-1) { //Si estoy con el foco en jugadores
            jugadores.get(jugadorConFoco).limpiarMovimiento();
            jugadorConFoco++;
            if(jugadorConFoco==jugadores.size()){
                jugadorConFoco=0;
            }
            this.setKeyboardFocus(jugadores.get(jugadorConFoco));
        }else{ //El foco está en el array de catetos
            npc.get(npcConFoco).limpiarMovimiento();
            this.npcConFoco++;
            if(npcConFoco==npc.size()){
                npcConFoco=0;
            }
            this.setKeyboardFocus(npc.get(npcConFoco));
        }
    }

    public Objeto getJugador(int index){
        return this.jugadores.get(index);
    }

    public Objeto getNPC(int index){
        return this.npc.get(index);
    }

    public void generarAlien(){
        Random r=new Random();
        //Elijo pos aleatoria en x e y
        int posX=r.nextInt(10);
        int posY=r.nextInt(10)+10;
        Alien nuevo=new Alien(this,posX,posY);
        jugadores.add(nuevo);
        Gdx.app.log("zIndex Alien",""+nuevo.getZIndex());
        this.addActor(jugadores.get(jugadores.size()-1));
    }

    public void dispose(){
        mundo.dispose();
        this.dispose();
    }



}
