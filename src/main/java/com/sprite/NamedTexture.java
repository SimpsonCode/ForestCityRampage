package com.sprite;

import com.badlogic.gdx.graphics.Texture;

// This class is bullshit - this will DO the SAME
// HashMap<String, Texture> ivImageList 
public class NamedTexture {
    
    private String textureName = "no name";
    private Texture singleImage = null;

    public NamedTexture(String aName)
    {
        setName(textureName);
    }

    public void setName(String aName)
    {
        textureName = aName;
    }

    public String getName()
    {
        return textureName;
    }

    public void setTexture( Texture aTexture )
    {
        singleImage = aTexture;
    }

    public Texture getTexture()
    {
        return singleImage;
    }
}
