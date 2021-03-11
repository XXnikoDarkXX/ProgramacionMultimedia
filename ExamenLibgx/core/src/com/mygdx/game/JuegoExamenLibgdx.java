package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Actores.BadLogic;

public class JuegoExamenLibgdx extends ApplicationAdapter {

	private Stage stage;//lo usaremos para dibujar un actor img;
	private OrthographicCamera camera; //Cámara a través de la que veremos el mundo.

	@Override
	public void create () {
		stage=new Stage((new ScreenViewport()));//Inicializmos el Stage
		Gdx.input.setInputProcessor(stage);//añadimos a nuestro Stagee el metodo de entrada
		BadLogic badLogic=new BadLogic();
		stage.addActor(badLogic);//añadimo a nuestro stage los actores
		stage.setKeyboardFocus(badLogic);//Establecemos el foco del teclado en uno de los actoers o varios
		camera = new OrthographicCamera(); //Declaramos la cámara a través de la que veremos el mundo



		camera.update(); //Colocamos la Cámara.
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update(); //Colocamos la Cámara.
		stage.act(Gdx.graphics.getDeltaTime());


		stage.draw();//dibujamos


	}
	
	@Override
	public void dispose () {

		stage.dispose();
	}
}
