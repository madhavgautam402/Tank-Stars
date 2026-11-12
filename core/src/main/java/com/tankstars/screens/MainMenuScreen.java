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

/** Home page: New Game, Resume Game, Exit. */
public class MainMenuScreen extends ScreenAdapter {
    private final TankStarsGame game;
    private final Viewport viewport;
    private final List<Button> buttons = new ArrayList<>();

    public MainMenuScreen(TankStarsGame game) {
        this.game = game;
        this.viewport = new FitViewport(TankStarsGame.WORLD_WIDTH,
                TankStarsGame.WORLD_HEIGHT, new OrthographicCamera());

        float w = 320, h = 70;
        float x = (TankStarsGame.WORLD_WIDTH - w) / 2f;
        buttons.add(new Button("New Game", x, 380, w, h, () -> {
            // TODO Phase 3: game.setScreen(new TankSelectScreen(game));
        }));
        buttons.add(new Button("Resume Game", x, 280, w, h, () -> {
            // TODO Phase 6: show list of saved games
        }));
        buttons.add(new Button("Exit", x, 180, w, h, () -> Gdx.app.exit()));
    }

    @Override
    public void render(float delta) {
        handleInput();

        ScreenUtils.clear(0.1f, 0.15f, 0.3f, 1f);      // dark blue background
        viewport.apply();
        game.shapes.setProjectionMatrix(viewport.getCamera().combined);
        game.batch.setProjectionMatrix(viewport.getCamera().combined);

        game.shapes.begin(ShapeType.Filled);
        for (Button b : buttons) b.drawShape(game.shapes);
        game.shapes.end();

        game.batch.begin();
        game.font.setColor(Color.WHITE);
        game.font.getData().setScale(3f);
        game.font.draw(game.batch, "TANK STARS", 490, 600);
        game.font.getData().setScale(2f);
        for (Button b : buttons) b.drawLabel(game.batch, game.font);
        game.batch.end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            // Convert screen pixels to our 1280x720 world coordinates
            Vector2 p = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
            for (Button b : buttons) {
                if (b.handleClick(p.x, p.y)) break;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}