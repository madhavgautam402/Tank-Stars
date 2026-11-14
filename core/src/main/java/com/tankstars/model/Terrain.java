package com.tankstars.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.io.Serializable;

/** Hilly ground stored as one height value per x coordinate. */
public class Terrain implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int STEP = 4;   // draw width of each column

    private final float[] heights;

    public Terrain(int width) {
        heights = new float[width + 1];
        // random phases so every new game gets different hills
        float p1 = MathUtils.random(0f, MathUtils.PI2);
        float p2 = MathUtils.random(0f, MathUtils.PI2);
        for (int x = 0; x < heights.length; x++) {
            heights[x] = 200
                    + 70 * MathUtils.sin(x * 0.006f + p1)
                    + 35 * MathUtils.sin(x * 0.017f + p2)
                    + 15 * MathUtils.sin(x * 0.045f);
        }
    }

    public float getHeightAt(float x) {
        int i = MathUtils.clamp((int) x, 0, heights.length - 1);
        return heights[i];
    }

    /** Call between shapes.begin(Filled) and shapes.end(). */
    public void draw(ShapeRenderer shapes) {
        for (int x = 0; x < heights.length - 1; x += STEP) {
            shapes.setColor(new Color(0.45f, 0.3f, 0.15f, 1f));   // dirt
            shapes.rect(x, 0, STEP, heights[x]);
            shapes.setColor(new Color(0.2f, 0.6f, 0.2f, 1f));     // grass strip on top
            shapes.rect(x, heights[x] - 8, STEP, 8);
        }
    }
}