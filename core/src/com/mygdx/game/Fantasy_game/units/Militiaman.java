package com.mygdx.game.Fantasy_game.units;

import java.util.ArrayList;

public class Militiaman extends Hero {

    public Militiaman(String name, Vector2D coords) {
        super(name,30,30,2,2,1,4, coords.posX, coords.posY);
    }

    @Override
    public void step(ArrayList<Hero> enemies, ArrayList<Hero> allies){
        super.step(enemies,allies);
        if (!this.state.equals("dead")) this.state = "ready";
    }

    @Override
    public String getInfo() {
        return "Militiaman: ";
    }
}
