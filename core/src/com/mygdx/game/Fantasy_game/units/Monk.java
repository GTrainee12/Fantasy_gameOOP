package com.mygdx.game.Fantasy_game.units;

import com.mygdx.game.Fantasy_game.SpellBook;


import java.util.ArrayList;
import java.util.Comparator;

public class Monk extends Mag {
    public Monk(String name, Vector2D coords) {
        super( 15,10,15,name,25,25,2,2,0,8, coords.posX, coords.posY);

    }

    @Override
    protected void pickTarget(ArrayList<Hero> enemies, ArrayList<Hero> allies) {
        allies.sort(new Comparator<Hero>() {
            @Override
            public int compare(Hero h1, Hero h2) {
                if (h1.state.equals("dead")) return 1;
                if (h1.getStrPerc()>h2.getStrPerc()) return 1;
                return -1;
            }
        });
        this.targetHero = allies.get(0);
    }

    @Override
    protected SpellBook selectSpell() {
        if (this.targetHero.hp==this.targetHero.maxHp) return null;
        if (this.mana <=4 || (this.targetHero.maxHp-this.targetHero.hp)<=3)
            return SpellBook.OmsHealing;
        else if ((this.targetHero.maxHp-this.targetHero.hp)<=5) return SpellBook.DmsHealing;
        else if ((this.mana)>=7) return SpellBook.RealHealing;
        else return SpellBook.DmsHealing;
    }

    @Override
    public String getInfo() {
        return "Monk: ";
    }
}
