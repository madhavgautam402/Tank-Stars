package com.tankstars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tankstars.TankStarsGame;
import com.tankstars.model.Tank;
import com.tankstars.model.TankType;
import com.tankstars.model.Terrain;
import com.tankstars.ui.Button;

import java.util.ArrayList;
import java.util.List;

/** The battle screen: terrain, two tanks, health bars and the pause menu. */
public class GameScreen extends BaseScreen {
    private static final Color SKY = new Color(0.5f, 0.8f, 0.95f, 1f);
    private static final float BAR_WIDTH = 500f;

    private final Terrain terrain;
    private final Tank player1;
    private final Tank player2;
    private final List<Button> pauseButtons = new ArrayList<>();
    private boolean paused = false;

    public GameScreen(TankStarsGame game, TankType type1, TankType type2) {
        super(game);
        terrain = new Terrain((int) TankStarsGame.WORLD_WIDTH);
        player1 = new Tank(type1, 200, true);
        player2 = new Tank(type2, 1080, false);
        player1.placeOn(terrain);
        player2.placeOn(terrain);

        buttons.add(new Button("Pause", 590, 650, 100, 50, () -> paused = true));

        float w = 320, h = 70;
        float x = (TankStarsGame.WORLD_WIDTH - w) / 2f;
        pauseButtons.add(new Button("Resume", x, 400, w, h, () -> paused = false));
        pauseButtons.add(new Button("Save Game", x, 300, w, h, () -> {
            // TODO Phase 6: save the game state
        }));
        pauseButtons.add(new Button("Main Menu", x, 200, w, h,
                () -> game.setScreen(new MainMenuScreen(game))));
    }

    @Override
    protected Color backgroundColor() {
        return SKY;
    }

    @Override
    protected List<Button> activeButtons() {
        return paused ? pauseButtons : buttons;
    }

    @Override
    protected void drawContent() {
        ShapeRenderer s = game.shapes;
        s.begin(ShapeType.Filled);
        terrain.draw(s);
        player1.draw(s);
        player2.draw(s);
        drawHealthBar(s, player1, 40);
        drawHealthBar(s, player2, 740);
        s.end();

        drawText("P1 " + player1.getType().getDisplayName() + "  " + player1.getHealth(),
                40, 715, 1.5f, Color.BLACK);
        drawText("P2 " + player2.getType().getDisplayName() + "  " + player2.getHealth(),
                740, 715, 1.5f, Color.BLACK);

        if (paused) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            s.begin(ShapeType.Filled);
            s.setColor(0f, 0f, 0f, 0.6f);
            s.rect(0, 0, TankStarsGame.WORLD_WIDTH, TankStarsGame.WORLD_HEIGHT);
            s.end();
            drawText("PAUSED", 540, 560, 3f, Color.WHITE);
        }
    }

    private void drawHealthBar(ShapeRenderer s, Tank tank, float x) {
        float fraction = (float) tank.getHealth() / tank.getType().getMaxHealth();
        s.setColor(Color.DARK_GRAY);
        s.rect(x, 650, BAR_WIDTH, 30);
        s.setColor(Color.RED);
        s.rect(x, 650, BAR_WIDTH * fraction, 30);
    }
}