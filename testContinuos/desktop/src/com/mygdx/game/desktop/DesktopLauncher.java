package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name","MParamos");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=800;
		config.height=800;
		config.resizable=false;
		config.title="Aliens vs Catetos";
		config.audioDeviceBufferCount=0;
		config.audioDeviceBufferSize=0;


		//config.addIcon("icono.ico", Files.FileType.Classpath);
		new LwjglApplication(new Juego(), config);
	}
}
