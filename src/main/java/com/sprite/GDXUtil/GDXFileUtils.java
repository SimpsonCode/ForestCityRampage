package com.sprite.GDXUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class GDXFileUtils {

    public static String loadTextFileFromClasspath(String aFileName) {
        String str = "";

        StringBuffer sb = new StringBuffer(2024);
        // there is also LOCAL and EXTERNAL to muck with
        FileHandle file = Gdx.files.internal("testbucket.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(file.read()));

        try {
            while ((str = reader.readLine()) != null) {
                sb.append(str + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    // assume I want TRANSP
    public static Texture loadTextureAndCompileOnce(String aPathedImageFile, boolean makeTrans) {
        Pixmap pm = new Pixmap(Gdx.files.internal(aPathedImageFile));

        // does this NOT have to be a power of 2 or does it get POOFED UP?
        if (makeTrans) {
            int p0 = pm.getPixel(0, 0);
            int p1 = pm.getPixel(1, 0);

            // gotta do this or transparent pixels have ZERO to blend over
            pm.setBlending(Pixmap.Blending.None);
            int c = 0;
            // DO ALL the SPRITE
            for (int x = 0; x < pm.getWidth(); x++) {
                for (int y = 0; y < pm.getHeight(); y++) {
                    int p = pm.getPixel(x, y);
                    
                    // first or second pixel in the top left - totally works
                    if ( p==p0 ||p == p1) {
                        // pixmap.drawPixel(x, y, pixmap.getPixel(x, y) & 0xffffff00);
                        // WHIE?T
                        pm.drawPixel(x, y, 0xFFFFFFFF); // CHANGE to WHITE!!!
                        pm.drawPixel(x, y, 0xFF0000FF); // CHANGE to RED!!!
                        pm.drawPixel(x, y, 0x00FF00FF); // CHANGE to GREEN!!!
                        pm.drawPixel(x, y, 0x0000FFFF); // CHANGE to BLUE!!!

                        // the 0 ALPHA means ZERO BLEND
                        pm.drawPixel(x, y, 0x0000FF00); //

                        pm.drawPixel(x, y, 0x00FF0000); // CHANGE to GREEN!!!

                        // pm.drawPixel(x,y,0xFFFFFF00);

                        // NO ALpHA no nothing
                        // pixmap.drawPixel(x,y,0);
                        c++;
                    }
                }
            }
            System.out.println("Found and changed " + c + " pixels to transparent");
            pm.setBlending(Pixmap.Blending.SourceOver);

            // String s = Integer.toHexString(p0); // - lets pretend this is top left?
            // System.out.println("STATIC LLOADS BABY x?  " + p0);
            // System.out.println("Hex String? " + s);
            // System.out.println("R: " + ((p0 >> 6 & 0xFF)));
            // System.out.println("G: " + ((p0 >> 4 & 0xFF)));
            // System.out.println("B: " + ((p0 >> 2 & 0xFF)));
            // System.out.println("A: " + ((p0 & 0xFF)));

        }
        // it is a pixmap before texture here
        Texture singleImage = new Texture(pm);
        return singleImage;
    }
}
