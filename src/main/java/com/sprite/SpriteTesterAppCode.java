package com.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// this owns all of the shared assets for each of the Screens 
public class SpriteTesterAppCode extends Game {

	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	public BitmapFont font;

	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		//font = new BitmapFont(); // use libGDX's default Arial font
		// NOW - does this is WHITE on TRANSPARENT and you can't see shit in mspaint
		// does this look into the class path - fuck yes it does ... now find a fill on
		// https://github.com/libgdx/libgdx/blob/master/tests/gdx-tests-android/assets/data/font.fnt
		// defaulkt?
		// https://github.com/libgdx/libgdx/blob/master/extensions/gdx-tools/assets/default.fnt
		// says small bu it's not
		// https://github.com/libgdx/libgdx-skins/blob/master/skins/visui/assets/font-small.fnt

		font = new BitmapFont(Gdx.files.internal("ARCADE.fnt"), Gdx.files.internal("ARCADE.png"), false);
		this.setScreen(new MainSpriteScreen(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}