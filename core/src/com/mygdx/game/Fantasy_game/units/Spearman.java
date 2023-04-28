package com.mygdx.game.Fantasy_game.units;

public class Spearman extends Warrior {

    public Spearman(String name, Vector2D coords) {
        super(2, 8, name, 55, 55, 8, 8,
                6, 5, coords.posX, coords.posY);
    }

    @Override
    public String getInfo() {
        return "Spearman: ";
    }
}
