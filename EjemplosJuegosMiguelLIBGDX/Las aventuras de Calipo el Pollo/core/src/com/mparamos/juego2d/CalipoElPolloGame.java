package com.mparamos.juego2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Actor;

import Actors.Calipo;
import Input.Teclado;
import world.Mundo;

public class CalipoElPolloGame extends ApplicationAdapter {
	Calipo personaje;
	Mundo mundo;
	Teclado teclado;


	@Override
	public void create() {
		mundo=new Mundo();
		personaje=new Calipo(mundo);


		teclado=new Teclado(personaje);
		Gdx.input.setInputProcessor(teclado);

	}




	/**
	 * This is called when the application is resized, and can happen at any
	 * time, but will never be called before {@link #create()}.
	 *
	 * @param width  The screen's new width in pixels.
	 * @param height The screen's new height in pixels.
	 */
	@Override
	public void resize(int width, int height) {
		mundo.redimensionar();
	}


	/**
	 * Called once per frame to render the game. You can use
	 * {@code Gdx.graphics.getDeltaTime()} to find out how much time in seconds
	 * has passed between the current and last frame.
	 */
	@Override
	public void render() {
		// Clear the screen using a sky-blue background.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mundo.dibujar(this.personaje);
	}


	@Override
	public void dispose() {
		mundo.dispose();
	}
}

