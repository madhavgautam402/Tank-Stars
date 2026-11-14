package com.tankstars.screens;

import com.badlogic.gdx.graphics.Color;
import com.tankstars.TankStarsGame;
import com.tankstars.model.TankType;
import com.tankstars.ui.Button;

/** Lets each player choose one of the tank types. */
public class TankSelectScreen extends BaseScreen {
    private static final float CARD_X0 = 130f;
    private static final float CARD_STEP = 370f;

    private TankType firstPick;   // null until Player 1 has chosen

    public TankSelectScreen(TankStarsGame game) {
        super(game);
        TankType[] types = TankType.values();
        for (int i = 0; i < types.length; i++) {
            TankType type = types[i];
            Button card = new Button(type.getDisplayName(), CARD_X0 + i * CARD_STEP, 220, 320, 300,
                    () -> onTankChosen(type));
            card.setColor(type.getColor());
            buttons.add(card);
        }
        buttons.add(new Button("Back", 40, 40, 200, 60,
                () -> game.setScreen(new MainMenuScreen(game))));
    }

    private void onTankChosen(TankType type) {
        if (firstPick == null) {
            firstPick = type;
        } else {
            game.setScreen(new GameScreen(game, firstPick, type));
        }
    }

    @Override
    protected void drawContent() {
        String who = (firstPick == null) ? "Player 1" : "Player 2";
        drawText(who + ": choose your tank", 340, 620, 2.5f, Color.WHITE);

        TankType[] types = TankType.values();
        for (int i = 0; i < types.length; i++) {
            drawText("Health: " + types[i].getMaxHealth(),
                    CARD_X0 + i * CARD_STEP + 90, 190, 1.5f, Color.WHITE);
        }
    }
}