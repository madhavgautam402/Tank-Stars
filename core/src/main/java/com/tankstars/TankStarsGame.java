package com.tankstars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tankstars.screens.MainMenuScreen;

/** Entry point. Owns the resources shared by all screens. */
public class TankStarsGame extends Game {
    public static final float WORLD_WIDTH = 1280f;
    public static final float WORLD_HEIGHT = 720f;

    public SpriteBatch batch;      // draws text and images
    public ShapeRenderer shapes;   // draws rectangles, lines, circles
    public BitmapFont font;        // built-in font, no asset files needed

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapes = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(2f);
        setScreen(new MainMenuScreen(this));  // first screen shown
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapes.dispose();
        font.dispose();
    }
}