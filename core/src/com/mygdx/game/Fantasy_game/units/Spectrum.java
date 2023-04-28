package com.mygdx.game.Fantasy_game.units;




import com.mygdx.game.Fantasy_game.SpellBook;

import java.util.ArrayList;

public class Spectrum extends Mag {


    public Spectrum(String name, Vector2D coords) {
        super(15,10,15,name,25,25,2,2,0,7,coords.posX, coords.posY);
    }

    @Override
    public String getInfo() {
        return "Spectrum: ";
    }

    @Override
    protected void pickTarget(ArrayList<Hero> enemies, ArrayList<Hero> allies) {
        this.targetHero = this.nearestAliveEnemy(enemies);
    }

    @Override
    protected SpellBook selectSpell() {
        if (this.targetHero.hp<=3 || this.mana<=4) return SpellBook.MagickArrow;
        else if (this.targetHero.hp<=5) return SpellBook.FireBall;
        else if (this.mana>=7) return SpellBook.Lightning;
        else return SpellBook.FireBall;
    }

}