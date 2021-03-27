package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import basedatos.BaseDatosDesktop;
import game.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=800;
		config.height=800;
		config.resizable=false;
		config.title="Alien vs Cateto";
		config.audioDeviceBufferCount=0;
		config.audioDeviceBufferSize=0;
		new LwjglApplication(new Juego(new BaseDatosDesktop()), config);
	}
}
