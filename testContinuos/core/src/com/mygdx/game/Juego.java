package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import pantallas.Pantalla;
import pantallas.Sembrao;
import personajes.Alien;
import personajes.Cateto;

/**

 * MOVIMIENTO CONTINUO CON LAS TECLAS PULSADAS
 * APRENDER A MOVER CON TELEFONO
 */
public class Juego extends Game {
	private Pantalla pantalla;

	
	@Override
	public void create () {
		pantalla=new Sembrao();
		Alien alien=new Alien(pantalla);
		pantalla.añadirJugador(alien);
		alien.setZIndex(1);
		Cateto cateto=new Cateto(pantalla);
		pantalla.añadirNPC(cateto);
		Gdx.app.log("zIndex Cateto",""+cateto.getZIndex());
		cateto.setZIndex(0);
		//Le pone el foco de teclado al primer jugador
		pantalla.ponerFocoControl(pantalla.getJugador(0));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		pantalla.act(Gdx.graphics.getDeltaTime());
		pantalla.draw();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		pantalla.resize(width,height);
	}

	@Override
	public void dispose () {
		pantalla.dispose();
	}
}
