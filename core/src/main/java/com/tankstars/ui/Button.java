package com.tankstars.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/** A simple rectangular button that runs an action when clicked. */
public class Button {
    private final Rectangle bounds;
    private final String label;
    private final Runnable action;
    private final GlyphLayout layout = new GlyphLayout();

    public Button(String label, float x, float y, float width, float height, Runnable action) {
        this.label = label;
        this.bounds = new Rectangle(x, y, width, height);
        this.action = action;
    }

    /** Call between shapes.begin(Filled) and shapes.end(). */
    public void drawShape(ShapeRenderer shapes) {
        shapes.setColor(Color.ORANGE);
        shapes.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /** Call between batch.begin() and batch.end(). */
    public void drawLabel(SpriteBatch batch, BitmapFont font) {
        layout.setText(font, label);
        float textX = bounds.x + (bounds.width - layout.width) / 2f;
        float textY = bounds.y + (bounds.height + layout.height) / 2f;
        font.setColor(Color.BLACK);
        font.draw(batch, label, textX, textY);
    }

    /** Runs the action and returns true if (x, y) is inside the button. */
    public boolean handleClick(float x, float y) {
        if (bounds.contains(x, y)) {
            action.run();
            return true;
        }
        return false;
    }
}