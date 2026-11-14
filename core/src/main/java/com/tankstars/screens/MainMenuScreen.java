package com.tankstars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tankstars.TankStarsGame;
import com.tankstars.ui.Button;

/** Home page: New Game, Resume Game, Exit. */
public class MainMenuScreen extends BaseScreen {

    public MainMenuScreen(TankStarsGame game) {
        super(game);
        float w = 320, h = 70;
        float x = (TankStarsGame.WORLD_WIDTH - w) / 2f;
        buttons.add(new Button("New Game", x, 380, w, h,
                () -> game.setScreen(new TankSelectScreen(game))));
        buttons.add(new Button("Resume Game", x, 280, w, h, () -> {
            // TODO Phase 6: list of saved games
        }));
        buttons.add(new Button("Exit", x, 180, w, h, () -> Gdx.app.exit()));
    }

    @Override
    protected void drawContent() {
        drawText("TANK STARS", 490, 600, 3f, Color.WHITE);
    }
}