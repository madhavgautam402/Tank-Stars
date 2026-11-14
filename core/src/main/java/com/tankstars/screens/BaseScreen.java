package com.tankstars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tankstars.TankStarsGame;
import com.tankstars.ui.Button;

import java.util.ArrayList;
import java.util.List;

/** Template Method pattern: render() is fixed, subclasses supply drawContent(). */
public abstract class BaseScreen extends ScreenAdapter {
    private static final Color DEFAULT_BACKGROUND = new Color(0.1f, 0.15f, 0.5f, 1f);

    protected final TankStarsGame game;
    protected final Viewport viewport = new FitViewport(
            TankStarsGame.WORLD_WIDTH, TankStarsGame.WORLD_HEIGHT, new OrthographicCamera());
    protected final List<Button> buttons = new ArrayList<>();

    protected BaseScreen(TankStarsGame game) {
        this.game = game;
    }

    @Override
    public final void render(float delta) {
        handleInput();
        Color bg = backgroundColor();
        ScreenUtils.clear(bg.r, bg.g, bg.b, 1f);
        viewport.apply();
        game.shapes.setProjectionMatrix(viewport.getCamera().combined);
        game.batch.setProjectionMatrix(viewport.getCamera().combined);
        drawContent();   // the part each screen customises
        drawButtons();
    }

    /** Subclasses draw their own scene here. */
    protected abstract void drawContent();

    /** Override to change the background colour. */
    protected Color backgroundColor() {
        return DEFAULT_BACKGROUND;
    }

    /** Override when a different set of buttons should be clickable (e.g. pause menu). */
    protected List<Button> activeButtons() {
        return buttons;
    }

    /** Draws one line of text. */
    protected void drawText(String text, float x, float y, float scale, Color color) {
        game.batch.begin();
        game.font.getData().setScale(scale);
        game.font.setColor(color);
        game.font.draw(game.batch, text, x, y);
        game.batch.end();
    }

    private void drawButtons() {
        List<Button> active = activeButtons();
        game.shapes.begin(ShapeType.Filled);
        for (Button b : active) b.drawShape(game.shapes);
        game.shapes.end();

        game.batch.begin();
        game.font.getData().setScale(2f);
        for (Button b : active) b.drawLabel(game.batch, game.font);
        game.batch.end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector2 p = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            for (Button b : activeButtons()) {
                if (b.handleClick(p.x, p.y)) break;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}