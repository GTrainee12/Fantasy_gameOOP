package com.mygdx.game.Fantasy_game.units;

import java.util.ArrayList;

public interface  HeroInterface {
    void step(ArrayList<Hero> enemies, ArrayList<Hero> allies);

    String getInfo();
}
