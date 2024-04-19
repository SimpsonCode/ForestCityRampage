package com.sprite.CustomGDX;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

// this is a bean - lombok this finally?
// this is a single frame - not a compound one - how to do that 
// do I want o make this a one stop shop class?
// 
// This guys has to DRAW without lookups and minimal calculations
// things are "COMPILED" - lombok it?
public class NamedFrame {
    
    public String ivName = "noname";
    BucketSprite ivOwner;
    Texture ivTexture;    
    ArrayList<CompoundChild> ivCompoundList = null;
    
    int ii = 0; // image index
    int sx0 = 0;
    int sy0 = 0;
    int sx1 = 0; // used for widht calcs  
    int sy1 = 0; // used for height calcs 
    int width = 0;
    int halfWidth = 0;
    int height = 0;

    // this is for compound named frames - so it moved
    // int childIndex = 0;
    // int fw = 0;
    // int up = 0;

}
