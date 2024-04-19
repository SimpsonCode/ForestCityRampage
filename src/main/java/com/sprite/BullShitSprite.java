package com.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// this is version ZEROf
public class BullShitSprite {
    
    Texture singleImage = null;
    
    public BullShitSprite()
    {

    }


    // if (!texture.getTextureData().isPrepared()) {
    //     texture.getTextureData().prepare();

    public void setImageAndRectangles(String aPathedImageFile, String aPathedBucketFile)
    {
/*
        Pixmap pm = new Pixmap(Gdx.files.internal(aPathedImageFile));
                
        int p0 = pm.getPixel(0, 0);

        // gotta do this or transparent pixels have ZERO to blend over
        pm.setBlending(Pixmap.Blending.None);
        int c=0;
        for (int x=0;x<32;x++)
        {
            for (int y=0;y<32;y++)
            {
                int p = pm.getPixel(x,y);
                if (p==p0)
                {
                    //pixmap.drawPixel(x, y, pixmap.getPixel(x, y) & 0xffffff00);
                    // WHIE?T
                    pm.drawPixel(x,y,0xFFFFFFFF); // CHANGE to WHITE!!!
                    pm.drawPixel(x,y,0xFF0000FF); // CHANGE to RED!!!
                    pm.drawPixel(x,y,0x00FF00FF); // CHANGE to GREEN!!!
                    pm.drawPixel(x,y,0x0000FFFF); // CHANGE to BLUE!!!

                    // the 0 ALPHA means ZERO BLEND
                    pm.drawPixel(x,y,0x0000FF00); //

                    pm.drawPixel(x,y,0x00FF0000); // CHANGE to GREEN!!!
                    
                    

                    //pm.drawPixel(x,y,0xFFFFFF00);

                    // NO ALpHA no nothing
                    //pixmap.drawPixel(x,y,0);
                    c++;
                }
            }
        }
        System.out.println("Found and changed " + c);
        pm.setBlending(Pixmap.Blending.SourceOver);

        String s = Integer.toHexString(p0); //- lets pretend this is top left?
        System.out.println("What is x?  " + p0 );
        System.out.println("Hex String? " + s);
        System.out.println("R: " + ((p0>>6&0xFF) ));
        System.out.println("G: " + ((p0>>4&0xFF) ));
        System.out.println("B: " + ((p0>>2&0xFF) ));
        System.out.println("A: " + ((p0&0xFF) ));
        
        // it is a pixmap before texture here 
        singleImage = new Texture(pm);
*/
        
        singleImage = new Texture(Gdx.files.internal(aPathedImageFile));        
        
        
    }

    // These assholes say to do it externally and manually
    // https://stackoverflow.com/questions/22116033/how-do-you-tell-libgdx-to-use-a-specific-color-for-transparency

    // aka - make transparent according to the top left pixel
    public void precompileForMyUse()
    {
        // TODO: design perhaps I can choose things out of the REX file
        // find color at postiion 0,0 and make all the same color pixels ALPHA=0
        // 

        // iterate over the pixels in the texture 
        // ??? https://stackoverflow.com/questions/24034352/libgdx-change-color-of-texture-at-runtime
        // !!! https://stackoverflow.com/questions/33744330/how-to-get-and-set-pixels-in-textures-libgdx
        //singleImage.pixels; 

        // the hell?
        singleImage.getTextureData().prepare();
        Pixmap pixmap = singleImage.getTextureData().consumePixmap();
        // now what? 
        int p0 = pixmap.getPixel(0, 0);
        
        //? Pixmap.setBlending(Pixmap.Blending.None); // before you start drawing pixels.

        pixmap.setBlending(Pixmap.Blending.None);
        int c=0;
        for (int x=0;x<32;x++)
        {
            for (int y=0;y<32;y++)
            {
                int p = pixmap.getPixel(x,y);
                if (p==p0)
                {
                    //pixmap.drawPixel(x, y, pixmap.getPixel(x, y) & 0xffffff00);
                    // WHIE?T
                    //pixmap.drawPixel(x,y,0);
                    pixmap.drawPixel(x,y,0x00FF0000); // CHANGE to GREEN!!!
                    // NO ALpHA no nothing
                    //pixmap.drawPixel(x,y,0);
                    c++;
                }
            }
        }
        pixmap.setBlending(Pixmap.Blending.SourceOver);

        


        System.out.println("Found and changed " + c);
        String s = Integer.toHexString(p0); //- lets pretend this is top left?
        System.out.println("What is x?  " + p0 );
        System.out.println("Hex String? " + s);
        

        //do I now have to set it back?

        /// have to set it back after modifications as this is a consumed? "COPY"
        singleImage = new Texture(pixmap);        

    }

    // blah - this is DEBUG
    // FIGURE this out: what about slicing up the image into smaller images like TEXT does... ??? 
    public void addToBatch(SpriteBatch b, int x, int y)
    {
        //System.out.println("Do something");
        b.draw(singleImage, x, y, 32, 32);
        //b.draw(singleImage, x, y, 32, 32);

        // draw the bottom quarter of it?  NOPE - TOP QUARTER of it - so coordinates are top left to bottom right
        TextureRegion tr1 = new TextureRegion(singleImage, 0,0,24,24);
        b.draw( tr1, 100,100);

        // aka NORMAL
        b.draw(singleImage, 150,100,32,32, 0,0,32,32, false,false);

        // aka FLIPPED really good?!
        b.draw(singleImage, 200,100,32,32, 0,0,32,32, true,false);

        // regular but chopped - looking good too 
        b.draw(singleImage, 250,100,28,28, 4,4,24,24, false,false);

        // this rotates it - whu?!
        //TextureRegion tr2 = new TextureRegion(singleImage, 0,0,24,24);
        //b.draw( tr2, 150,100, 0,0,32,32,1.0f,1.0f, 0.0f, false);


        // b.draw(singleImage, draw(Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) : void


    }
}

