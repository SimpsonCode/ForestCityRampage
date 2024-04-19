package com.sprite.CustomGDX;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sprite.GeneralUtils.BucketList;
import com.sprite.GeneralUtils.UniversalBucket;


// I will not own the sprites/textures - but I will have references to them 
// something else loads them up and owns them - so don't delete here.  They may need image descriptors too 
// This looks at a rectangle file - something that defines all of the cut outs 
// - I made it a universal bucket - cause I'm lazy - should go learn json - I'm NOT using XML any more
// I know how to carve them up and draw them within the world - (bottom middle hotspot / reflect)
// 
public class BucketSprite {

    String ivName = "NoName";
    
    ArrayList<Texture> ivTextureList = null; 
    // this will be replaced with compiled values - or do a precompile step?    
    
    BucketList ivBucketList = null;
    // dont keep around bucket list - it's only the loader?
    // I will have the real frames list 
    ArrayList<NamedFrame> ivCompiledFrames = null;    
    // named frame will be the NAME and the coord info
    
    // TODO - GENERATE a single PIXMAP with a frame 
    // TODODO - GENERATE an aminated GIF with the series of frames as described somewhere
    public BucketSprite()
    {

    }

    // eventually this will just take a list of image references 
    // and then know how to quick draw
    // this is a factory method
    public Texture getTextureAtIndex(int i )
    {
        return ivTextureList.get(i);
    }
    public void wireTogether(ArrayList<Texture> loadedTextures, BucketList aLoadedBucket)
    {
        ivTextureList = loadedTextures;
        //ivBucketList = aLoadedBucket;

        // Compiling the bucket list into a set of named frames
        // - that can quickly draw themselves 
        // - and be referenced easily by name 
        // - does the bucket have any additional flags 
        // - - like pallete swapping
        // - - making a color partly transparent?
        
        // iterate the universal bucket looking for K:eys to process and add them to the namedimage list 
        // allow the file to have comments - and enjoy 
        // image ZERO is the whole thing? - 
        // do not make negative index the flips ffs

        ivCompiledFrames = new ArrayList<NamedFrame>();
        // dont save the bucket - but compile it here 
        for (int c = 0; c<aLoadedBucket.getSize(); c++ )
        {
            UniversalBucket ub = aLoadedBucket.getBucketAtIndex(c);

            // assume it's a comment if it can't be parsed
            String aKey = ub.getValue("K");
            if (aKey!=null)
            {
                // set values?!
                NamedFrame nf = new NamedFrame();
                nf.ivName = aKey;
                nf.ivOwner = this;

                // THIS SHOULD STILL BE ALIASED SOME HOW ... VARIABLES=true;HEAD=0;BODY=1;
                // ENUM or something - make it a bit more readable
                nf.ii = ub.getNumericValueWithDefault("ii",0);
                nf.ivTexture = nf.ivOwner.getTextureAtIndex(nf.ii);

                nf.sx0 = ub.getNumericValueWithDefault("x0",0);
                nf.sy0 = ub.getNumericValueWithDefault("y0",0);
                nf.sx1 = ub.getNumericValueWithDefault("x1",0)+1; // to be inclusive
                nf.sy1 = ub.getNumericValueWithDefault("y1",0)+1; // to be inclusive

                nf.width = nf.sx1-nf.sx0;
                //System.out.println("WWW: " + w); // some odds and omse evens?
                nf.height = nf.sy1-nf.sy0;
                //System.out.println("HHH " + h);
                nf.halfWidth = nf.width/2;
        
                
                // lets assume things are good if we are in this block
                int aCompoundCount = ub.getInteger("COMPOUND");

                // parse like a compound with the looping values 
                if (aCompoundCount>0)
                {
                    nf.ivCompoundList = new ArrayList<CompoundChild>();
                    System.out.println("PARSING FOUND A cOMPOUND");
                    
                    for (int i=0;i<aCompoundCount;i++)
                    {
                        CompoundChild nfChild = new CompoundChild();
                        nfChild.fw = ub.getNumericValueWithDefault("F"+i,0);
                        nfChild.up = ub.getNumericValueWithDefault("U"+i,0);
                        String nfChildReference = ub.getValueWithDefault("K"+i,"ChildNotSet");
                        nfChild.childIndex = findIndexOfFrame(nfChildReference);
                        nf.ivCompoundList.add( nfChild );
                    }
                }
                
                // if we got here without exceptions - then add it 
                ivCompiledFrames.add(nf);
            }
        }
        
    }
    
    // or I can compile a list of my own
    // or I just keep this to myself
    // the script will want to know this, but it also wants to know other things too 
    // this is for precompiling
    // the "script will want to know ALL of these"
    // public int findIndexOfFrame(String aFrameName)
    // {
    //     System.out.println("This will be replaced with a compiled version - " + aFrameName);
    //     int i = ivBucketList.getFirstIndexFound("K", aFrameName);
    //     if (i<0)
    //     {
    //         System.out.println("Warn: Can not find " + aFrameName + " in bucketList of size " + ivBucketList.getSize() );
    //     }
    //     return i;
    // }

    // returns -1 if cant find - will log, will crash?
    public int findIndexOfFrame(String aFrameName)
    {
        for ( int i=0;i<ivCompiledFrames.size();i++)
        {
            NamedFrame nf = ivCompiledFrames.get(i);
            if ( nf.ivName.equals( aFrameName ) ) 
            {
                return i;
            }
        }
        System.out.println("WARNING:  NamedFrame of " + aFrameName + " not found on BucketSprite " + ivName);
        return -1;
    }
    public UniversalBucket getBucketAtIndex(int i)
    {
        // it will just throw if it does not exist - I am still good with that 
        System.out.println("SHIT method - don't use");
        return ivBucketList.getBucketAtIndex(i);
    }

    // this has lots of ni. in it - should it have gone to the kids?!
    public void addToBatch(SpriteBatch sb, int aFrame, int x, int y, boolean aFlip, boolean bottoomMiddleHotspot)
    {        
        NamedFrame nf = ivCompiledFrames.get(aFrame);
        //nf.batchDrawFighter(sb, x, y, aFlip);
        
        if ( nf.ivCompoundList ==null )
        {
            // do normal and add it to the batch
            sb.draw( nf.ivTexture, x-nf.halfWidth,y,nf.width,nf.height, nf.sx0,nf.sy0,nf.width,nf.height, aFlip,false);
        }
        else
        {
            // System.out.println("LIST GOOD?! " );
            // add all the sub childrens
            for (int i=0;i<nf.ivCompoundList.size();i++)
            {
                //System.out.println("DRAW " + i);
                CompoundChild niChild = nf.ivCompoundList.get(i);
                int facing = niChild.fw;
                if (aFlip)
                {
                    facing *= -1;
                }

                addToBatch( sb, niChild.childIndex, x+facing,y+niChild.up,aFlip,bottoomMiddleHotspot );
            }
        }
    }

    
}
