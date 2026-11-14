package com.tankstars.model;

import com.badlogic.gdx.graphics.Color;

/** The selectable tank types and their stats. */
public enum TankType {
    BURATINO("Bulbasaur", new Color(0.3f, 0.7f, 0.3f, 1f), 100),
    BLAZER("Charmander", new Color(0.9f, 0.4f, 0.1f, 1f), 120),
    FROST("Squirtle", new Color(0.4f, 0.5f, 0.99f, 1f), 90);

    private final String displayName;
    private final Color color;
    private final int maxHealth;

    TankType(String displayName, Color color, int maxHealth) {
        this.displayName = displayName;
        this.color = color;
        this.maxHealth = maxHealth;
    }

    public String getDisplayName() { return displayName; }
    public Color getColor() { return color; }
    public int getMaxHealth() { return maxHealth; }
}