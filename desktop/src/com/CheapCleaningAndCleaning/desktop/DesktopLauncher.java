package com.CheapCleaningAndCleaning.desktop;

import com.CheapCleaningAndCleaning.CheapCleaningAndCleaning;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
        config.width = 1098;
        config.height = 678;
        new LwjglApplication(new CheapCleaningAndCleaning(), config);
    }
}
