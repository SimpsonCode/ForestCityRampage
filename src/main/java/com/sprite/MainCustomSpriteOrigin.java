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
            //https://stackoverflow.com/questions/35668943/libgdx-borderless-fullscreen
            // can't start in debug
            
            

            config.useVsync(true);
            config.setForegroundFPS(60);
            
            new Lwjgl3Application(new SpriteTesterAppCode(), config);
    
            System.out.println("... fin");
        }
    
}


