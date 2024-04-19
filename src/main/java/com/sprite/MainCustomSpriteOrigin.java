package com.sprite;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;



public class MainCustomSpriteOrigin 
{
    
        public static void main( String[] args )
        {
            System.out.println( "Hello World!" );
    
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setTitle("Sprite Master v0.1 - should have been coded in 2010");
            config.setWindowedMode(800, 480);
            //config.setWindowedMode(320, 240); // this does not double the pix size ... find the camera
            config.useVsync(true);
            config.setForegroundFPS(60);
            
            new Lwjgl3Application(new SpriteTesterAppCode(), config);
    
            System.out.println("... fin");
        }
    
}


