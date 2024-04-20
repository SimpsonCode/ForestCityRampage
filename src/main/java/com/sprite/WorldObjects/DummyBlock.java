package com.sprite.WorldObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sprite.CustomGDX.BucketSprite;

// will eventually become the player?
public class DummyBlock {
    
    BucketSprite ivImageData = null;
    PhysicsScript ivScript = null;

    // make the forward progress of IPS based on a countdown
    int ivInstructionPointer = 0;
    float timeLeftBeforeIPmove = 0f;
    // This will be new 

    int ivFrame = 0;
    // this is SIDE SCROLL - so 
    // +1 is to the right
    // -1 is to the left 
    // 0 is ??? unknown ???
    int ivDirection;
    float xPos = 0f;
    float yPos = 0f;
    float zPos = 0f;
    // velocity or xSpd ySpd 
    // - Z is HEIGHT? or is Z depth? - how do I want physics 
    float xSpd = 0f;
    float ySpd = 0f;
    float zSpd = 0f; // DEPTH and SORT DISTANCE
    boolean isGrounded = false;

    public DummyBlock()
    {
        ivDirection = 1;
    }

    public void processPhysics( float timeInSeconds )
    {

        // get the input collected up

        // hard drift 

        // forced move 

        // gravity and friction
    }

    // This has to be sorted 
    public void batchThis(SpriteBatch sb)
    {
        boolean aFlip = false;
        if (ivDirection<0)
        {
            aFlip = true;
        }
        // submit it for drawing 
        int x = (int)xPos;
        int y = (int)yPos + (int)zPos; // goes up when jumpping and moving into the screen
        ivImageData.addToBatch(sb, ivFrame, x,y, aFlip, true);

    }
}
