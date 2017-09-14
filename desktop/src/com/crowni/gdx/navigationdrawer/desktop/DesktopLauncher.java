package com.crowni.gdx.navigationdrawer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.crowni.gdx.navigationdrawer.Begin;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Navigation Drawer Example";
		config.width = 1080 / 3;
		config.height = 1920 / 3;
		new LwjglApplication(new Begin(), config);
	}
}
