package com.sprite;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

// try to load the image only ONCE even if requested many times 
// I own the whole list of images 
// I clean it up after done - I may be able to reload all someday
// bad design singletons - schmuck
public class TexturePool {
    
    private static TexturePool ivInstance = null;
    private HashMap<String, Texture> ivTextureList = new HashMap<String, Texture>();

    private TexturePool()
    {

    }

    public static TexturePool instance()
    {
        if (ivInstance==null)
        {
            ivInstance = new TexturePool();
        }
        return ivInstance;
    }

    // this will load it in RAW - who does transparency 
    public void loadImageFromClasspath(String anImageFile, String anLookupKey)
    {
        Texture t = ivTextureList.get(anLookupKey);
        if (t==null)
        {
            // Image load and put into table
        }
    }
}
