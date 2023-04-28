package com.mygdx.game.Fantasy_game.units;

import java.util.ArrayList;
import java.util.Random;

public class Archer extends Marksman {
    private final int BowDamage;
    public Archer(String name,Vector2D coords) {
        super(15,15,75,name,35,35,4,4,4,9,coords.posX, coords.posY);
        this.BowDamage = 5;

    }
    @Override
    public void attackTarget() {
        if (new Random().nextInt(1, 101) <= this.accuracy) {
            if ((float) (this.BowDamage - this.targetHero.armor) / (float) this.targetHero.hp >= 0.2){
                System.out.println(this.getInfo() + " " + this.name +
                        " attacks ♥️ of " + this.targetHero.getInfo() + this.targetHero.name);
                this.targetHero.getStrengthDamage(Math.max(this.BowDamage - this.targetHero.armor, 0));
            }else{
                System.out.println(this.getInfo() + " " + this.name +
                        " attacks \uD83D\uDEE1 of " + this.targetHero.getInfo() + this.targetHero.name);
                this.targetHero.getArmorDamage(this.defenseBreak);
            }
        } else {
            System.out.println(this.getInfo() + this.name + " misses "+
                    "trying to attack "+this.targetHero.getInfo()+" "+this.targetHero.name);
        }

    }
    @Override
    public String getInfo() {
        return "Archer: ";
    }
}
