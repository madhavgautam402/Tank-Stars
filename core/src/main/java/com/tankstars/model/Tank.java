package com.tankstars.model;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.io.Serializable;

/** A player's tank: type, position, health and aiming angle. */
public class Tank implements Serializable {
    private static final long serialVersionUID = 1L;

    private final TankType type;
    private float x;
    private float y;
    private int health;
    private float angle = 45f;           // barrel angle in degrees
    private final boolean facingRight;

    public Tank(TankType type, float x, boolean facingRight) {
        this.type = type;
        this.x = x;
        this.facingRight = facingRight;
        this.health = type.getMaxHealth();
    }

    /** Puts the tank on top of the ground at its current x. */
    public void placeOn(Terrain terrain) {
        this.y = terrain.getHeightAt(x);
    }

    /** Call between shapes.begin(Filled) and shapes.end(). */
    public void draw(ShapeRenderer shapes) {
        shapes.setColor(type.getColor());
        shapes.rect(x - 35, y, 70, 22);                 // body
        shapes.circle(x, y + 24, 14);                   // turret
        float dir = facingRight ? angle : 180f - angle;
        float bx = x + 45 * MathUtils.cosDeg(dir);
        float by = y + 24 + 45 * MathUtils.sinDeg(dir);
        shapes.rectLine(x, y + 24, bx, by, 7);          // barrel
    }

    public TankType getType() { return type; }
    public float getX() { return x; }
    public float getY() { return y; }
    public int getHealth() { return health; }
    public float getAngle() { return angle; }
    public boolean isFacingRight() { return facingRight; }
}