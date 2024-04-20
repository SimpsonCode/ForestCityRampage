package com.sprite.WorldObjects;

import java.util.ArrayList;

import com.sprite.CustomGDX.BucketSprite;
import com.sprite.GeneralUtils.BucketList;
import com.sprite.GeneralUtils.UniversalBucket;

// I will load up a script and get wired together with a BucketSprite
// I follow all the physics of the world - people, things, prizes&powerups?
// do I handle my own factory?
public class PhysicsScript {
    
    ArrayList<Instruction> ivInstructionList = null;    

    public PhysicsScript()
    {

    }

    // same script - to be compiled with different images 
    public void deepClone()
    {
        // must re compile for different images 
    }

    // really want to make this TIME BASED now 
    public void createFromBucketData(BucketList bl)
    {
        for ( int i=0;i<bl.getSize();i++)
        {
            // iterate over this and parse out instructions 
            UniversalBucket ub = bl.getBucketAtIndex(i);
            String instructionType = ub.getValue("TOK");
            if ( instructionType!=null )
            {
                String hasLabel = ub.getValue("::");
            }
            

        }
    }
    
    // find all images that will be updated  
    public void compileUsing(BucketSprite bs)
    {
        
    }

    public void doLogicToThis( DummyBlock d, int ip, float timeRemaining )
    {
        // get the thing 
    }
    

}
