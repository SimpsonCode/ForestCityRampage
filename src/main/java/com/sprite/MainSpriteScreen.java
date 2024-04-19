package com.sprite;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.utils.ObjectFloatMap.Keys;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sprite.CustomGDX.BucketSprite;
import com.sprite.GDXUtil.GDXFileUtils;
import com.sprite.GeneralUtils.BucketList;

// Right now it is only 2 chops
// - HEAD
// - BODY
// Could mega chop up the dude into 
// - PANTS
// - SHOES
// - SHIRT
// - HEAD
// Also can probramatically pallete swap - if needed COL1 to COL2
// - Can load  

// 0,0 is bottom left and -> 320 ^ 240 is the pixel dumps 
public class MainSpriteScreen implements Screen {

    final SpriteTesterAppCode game;
    OrthographicCamera camera;

    
    BucketSprite moreBS = null;
    int debugX = 160;
    int debugY = 120;
    int debugD = 1;
    
    public MainSpriteScreen(final SpriteTesterAppCode gam) {
        game = gam;

        
        moreBS = new BucketSprite();
        ArrayList<Texture> aList = new ArrayList<Texture>();

        // these are FN GIFS?
        aList.add( GDXFileUtils.loadTextureAndCompileOnce("pngbod.png", true) );
        aList.add( GDXFileUtils.loadTextureAndCompileOnce("pnghead.png",true ) );
                
        BucketList bl = new BucketList();
        String aClassPathString = "textbucket.txt";
        String sLines = GDXFileUtils.loadTextFileFromClasspath(aClassPathString);
        bl.initFromString(sLines);
        // this should compile things 
        moreBS.wireTogether(aList, bl);

        System.out.println("LOADED: " + aClassPathString);
        
        

        camera = new OrthographicCamera();

        // camera.setToOrtho(false, 800, 480);
        // this is the PIXELS which stretch perfectly for me - for now 
        camera.setToOrtho(false, 320, 240);

        lastTime = System.nanoTime();
        System.out.println(lastTime);
        frame = 0;
    }

    long lastTime = 0;
    long frame;

    
    @Override
    public void render(float delta) {

        // do I do all of the logic here by time before rendering?!
        // multi pass?
        // can it keep up

        // screen to green and a bit solid alpha
        ScreenUtils.clear(0.0f, 0.2f, 0.9f, 1);
        
        
        
        // set shit up
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        // should be somewhat the same 
        // this guy will not have find index of frames - he will just know it 
        moreBS.addToBatch(game.batch, moreBS.findIndexOfFrame("BStand"), 70,200, false,true);
        moreBS.addToBatch(game.batch, moreBS.findIndexOfFrame("BWalkA"), 100,200, false,true);
        moreBS.addToBatch(game.batch, moreBS.findIndexOfFrame("BWalkB"), 130,200, false,true);

        moreBS.addToBatch(game.batch, moreBS.findIndexOfFrame("BStand"), 160,200, true,true);
        moreBS.addToBatch(game.batch, moreBS.findIndexOfFrame("BWalkA"), 190,200, true,true);
        moreBS.addToBatch(game.batch, moreBS.findIndexOfFrame("BWalkB"), 220,200, true,true);

        // moreBS.addToBatch(game.batch, 5, 70,150, false,true);
        // moreBS.addToBatch(game.batch, 6, 100,150, false,true);
        // moreBS.addToBatch(game.batch, 7, 130,150, false,true);

        
        // find the one compound frame I made
        int compound = moreBS.findIndexOfFrame("STANDING");
        if (compound>=0)
        {
            //System.out.println("COMPPOUND");

            // this is the hot spot calculation
            moreBS.addToBatch(game.batch, compound, 160,150, false, true);

            moreBS.addToBatch(game.batch, compound, 210,150, true, true);
            moreBS.addToBatch(game.batch, compound, 190,150, true, true);


            
        }
        else
        {
            System.out.println("No find compound - STANDING");
        }

        int heads = moreBS.findIndexOfFrame("HEADTEST");
        if (heads>=0)
        {
            boolean doFlip=false;
            if (debugD==-1)
            {
                doFlip=true;
            }
            moreBS.addToBatch(game.batch, heads, debugX,debugY, doFlip , true);
        }


        // https://stackoverflow.com/questions/20769767/calculate-fps-in-java-game
        frame++;
        // double seconds = (double)elapsedTime / 1_000_000_000.0;
        // this is FROM the BEGINNING... so do a smooth since last 
        // 1_000_000_000 - milli micro nano numbers
        double seconds = ((System.nanoTime() - lastTime) / 1000000000.0); //This way, lastTime is assigned and used at the same time.
        double fps = frame / seconds ; // aka FRAMES PER SECOND
        game.font.setColor(Color.ORANGE);
        
        game.font.draw(game.batch, "F# " + frame + " FPS~" + (int)fps, 5, 220);
        // game.font.draw(game.batch, "DEL " + delta, 5, 200); - the milliseconds passeds
        
        //System.out.println(lastTime);

        // TODO: LOAD a SPRITE HERE and DO THINGS WITH IT
        // TODO: Then make an interactive editor
        // TODO: Then save sprite files with bucket technology
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "Welcome to A Poopfs!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "abcdefghijklmnopqrsuvwxyz!", 101, 51);
        game.font.setColor(Color.CHARTREUSE);
        game.font.draw(game.batch, "abcdefghijklmnopqrsuvwxyz!", 100, 50);
        game.font.setColor(Color.FIREBRICK);
        game.font.draw(game.batch, "ABCDEFGHIJKLMNOPQRSTUVWXYZ!", 100, 20);

        game.batch.end();

        // the above is SPRITE BATCHING - can I do other things here?
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // this totally works you SOBs - Work it for hit boxes and hurt boxes and etc debugging - possibly a margin or header?!
        game.shapeRenderer.begin(ShapeType.Filled);
        game.shapeRenderer.setProjectionMatrix(camera.combined);

        game.shapeRenderer.setColor(1f,0f,0f,.5f);
        game.shapeRenderer.rect(160, 160, 64, 64);

        game.shapeRenderer.setColor(0f,1f,0f,.5f);
        game.shapeRenderer.rect(200, 120, 64, 64);
        

        game.shapeRenderer.end();
        
        Gdx.gl.glDisable(GL20.GL_BLEND);


        	// process user input
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			//bucket.x = touchPos.x - 64 / 2;
            // System.out.println("Coord here "+Gdx.input.getX()+" "+ Gdx.input.getY());
            // System.out.println("Pixel here "+(int)touchPos.x+" "+ (int)touchPos.y);
            debugX = (int)touchPos.x;
            debugY = (int)touchPos.y;
		}
  
        if (Gdx.input.isKeyPressed(Keys.SPACE))
        {
            debugD *= -1;
        }
        
        // if (Gdx.input.isTouched()) {
        // game.setScreen(new GameScreen(game));
        // dispose();
        // }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
