package com.mygdx.game.Fantasy_game.units;

public class Vector2D {
    public int posX;
    public int posY;

    public Vector2D(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public double distanceFrom(Vector2D pos) {

        return Math.abs(pos.posX - this.posX)+Math.abs(pos.posY - this.posY);
    }

    public Vector2D directionTo(Vector2D destination) {
        return new Vector2D(destination.posX - this.posX, destination.posY - this.posY);
    }

    @Override
    public String toString() {
        return "(" + this.posX + "," + this.posY + ")";
    }

    public boolean equals(Vector2D pos) {
        return this.posX == pos.posX && this.posY == pos.posY;
    }
}

