package com.mygdx.game.Fantasy_game.units;

import java.util.ArrayList;

public class Knight extends Warrior {

    public Knight(String name, Vector2D coords) {
        super(1,10,name,50,50,10,10,8,6,coords.posX, coords.posY);

    }
    public String getInfo() {
        return "Knight: ";
    }

}
