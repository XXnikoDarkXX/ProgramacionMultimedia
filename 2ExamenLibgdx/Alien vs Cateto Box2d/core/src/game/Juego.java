package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import basedatos.BaseDatos;
import pantallas.Pantalla;
import pantallas.Sembrao;
import personajes.Alien;
import personajes.Bala;
import personajes.Cateto;
import personajes.Objeto;

/**

 * MOVIMIENTO CONTINUO CON LAS TECLAS PULSADAS
 * APRENDER A MOVER CON TELEFONO
 */
public class Juego extends Game {
	private Pantalla pantalla;
	private BaseDatos bd;
	private Bala bala;
	private boolean controlBala;

	private Batch batch;
	public Juego(BaseDatos bd){
		this.bd=bd;
	}

	@Override
	public void create () {

		batch = new SpriteBatch();
		pantalla=new Sembrao(bd);
		Alien alien=new Alien(pantalla);
		pantalla.añadirJugador(alien);
		alien.setZIndex(1);
		Cateto cateto=new Cateto(pantalla);
		pantalla.añadirNPC(cateto);
		Gdx.app.log("zIndex Cateto",""+cateto.getZIndex());
		cateto.setZIndex(0);
		//Le pone el foco de teclado al primer jugador
		pantalla.ponerFocoControl(pantalla.getJugador(0));
		///bala=new Bala(pantalla,"texturas/bala.png",(int)alien.getX(),(int)alien.getY(),1,1);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		pantalla.act(Gdx.graphics.getDeltaTime());
		pantalla.draw();
		batch.begin();
		if (controlBala=true){
			masBalas();
		}
		batch.end();
	}


	@Override
	public void dispose () {
		pantalla.dispose();
	}

		public void masBalas(){
	controlBala=	pantalla.generarBalas(bala,controlBala);
	controlBala=false;

		}
}
