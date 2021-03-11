package com.mparamos.juego2d.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mparamos.juego2d.CalipoElPolloGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Las aventuras de Calipo el Pollo";
		config.addIcon("actores/calipo.png", Files.FileType.Local);
		new LwjglApplication(new CalipoElPolloGame(), config);
	}
}
