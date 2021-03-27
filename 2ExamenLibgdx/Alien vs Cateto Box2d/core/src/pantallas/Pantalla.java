package pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
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
import personajes.Bala;
import personajes.Objeto;

public abstract class Pantalla extends Stage {
    /** Punto de vista para la cámara**/
    private ScreenViewport viewport;
    private BaseDatos baseDatos;
    private ArrayList<Objeto> jugadores; //Personajes que controlo
    private Box2DDebugRenderer debugRenderer; //Renderizador de hitbox de cuerpos
    private ArrayList<Objeto> npc; //Personajes que no controlo
    private ArrayList<Objeto>balas;
    private SpriteBatch batch; //De momento, solo para dibujar fondo
    private int jugadorConFoco; //Que jugador tiene el foco de teclado
    private int npcConFoco; //Qué cateto tiene el foco
    private World mundo; //Mundo de Box2D para aplicar físicas
    private OrthogonalTiledMapRenderer renderer; //Renderizador del mapa.
    private static float unitScale=1/16f; //Depende del ancho de pixels de los patrones del mapa
    private TiledMap mapa; //Mapa 2D de la pantalla actual
    private SpriteBatch batchTexto; //batch para dibujar texto
    private BitmapFont font; //Texto a dibjuar



   // private Bala bala;
    public Pantalla(String rutaFondo,BaseDatos bd){
        super(new ScreenViewport());
        baseDatos=bd;
        viewport= (ScreenViewport) this.getViewport();
        jugadores=new ArrayList<Objeto>();
        npc=new ArrayList<Objeto>();
        batch=new SpriteBatch();
        Gdx.input.setInputProcessor(this);


        debugRenderer=new Box2DDebugRenderer();
        mundo=new World(new Vector2(0,-9.8f),true);
        mapa=new TmxMapLoader().load("mapas/pantalla1.tmx");
        Iterator it=mapa.getLayers().get("objetos").getObjects().iterator();
  //      bala=new Bala(this,"texturas/bala.png",(int)jugadores.get(0).getX(),(int)jugadores.get(0).getY(),1,1);
        while(it.hasNext()){
            BodyDef propiedadesRectangulo = new BodyDef(); //Establecemos las propiedades del cuerpo
            propiedadesRectangulo.type = BodyDef.BodyType.StaticBody;
            Body rectanguloSuelo = mundo.createBody(propiedadesRectangulo);
            FixtureDef propiedadesFisicasRectangulo = new FixtureDef();
            Shape formaRectanguloSuelo = getRectangle((RectangleMapObject) it.next());
            propiedadesFisicasRectangulo.shape = formaRectanguloSuelo;
            propiedadesFisicasRectangulo.density = 1f;
            rectanguloSuelo.createFixture(propiedadesFisicasRectangulo);

        }

        renderer=new OrthogonalTiledMapRenderer(mapa,unitScale);
        viewport.getCamera().position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        viewport.update(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        baseDatos.cargarCuenta();
        batchTexto=new SpriteBatch();
        font=new BitmapFont();

        mundo.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Gdx.app.log("Colision!","Detectada");
                if(contact.getFixtureA().getBody().
                        equals(jugadores.get(0).getCuerpo())&&
                contact.getFixtureB().getBody().equals(npc.get(0).getCuerpo())){
                    Gdx.app.log("Colision!",
                            "El alien ha chocado con el cateto");

                    baseDatos.persistir();
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public World getMundo(){
        return mundo;
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) *unitScale,
                (rectangle.y + rectangle.height * 0.5f) *unitScale);
        polygon.setAsBox(rectangle.width * 0.5f *unitScale,
                rectangle.height * 0.5f *unitScale,
                size,
                0.0f);
        return polygon;
    }

    @Override
    public void draw() {

        mundo.step(Gdx.graphics.getDeltaTime(),
                6,2);
        ((OrthographicCamera)viewport.getCamera()).setToOrtho(false,15,15);
        viewport.getCamera().position.x=this.jugadores.get(jugadorConFoco).getX();
        viewport.getCamera().position.y=this.jugadores.get(jugadorConFoco).getY();
        //Gdx.app.log("posCamara",viewport.getCamera().position.x+" : "+viewport.getCamera().position.y);
        renderer.render();
        ((OrthographicCamera)viewport.getCamera()).update();
        debugRenderer.render(mundo, ((OrthographicCamera)viewport.getCamera()).combined);
        renderer.setView( ((OrthographicCamera)viewport.getCamera()));
        batchTexto.begin();
        font.draw(batchTexto,
                "Aliens generados: "+baseDatos.getAliensGenerados(),
                30,Gdx.graphics.getHeight()-30,
                Gdx.graphics.getWidth()/6,
                Gdx.graphics.getHeight()/10,false);
        batchTexto.end();
        super.draw();

    }

    @Override
    public Camera getCamera() {
        return viewport.getCamera();
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
        baseDatos.aumentarCuenta();
    }



    public void dispose(){
        mundo.dispose();
        this.dispose();
        renderer.dispose();
        debugRenderer.dispose();
    }
    public Boolean generarBalas(Bala bala,boolean controBala){



            bala=new Bala(this,"texturas/bala.png",(int)jugadores.get(0).getX(),(int)jugadores.get(0).getY(),1,1);
           return true;
        }
    }


