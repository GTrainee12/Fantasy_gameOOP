package com.mygdx.game.Fantasy_game.units;

import java.util.Random;

public class Crossbowman extends Marksman {

    protected final int CrossbowDamage;
        public Crossbowman(String name, Vector2D coords) {
        super(15,15,75,name,40,40,4,4,6,9, coords.posX, coords.posY);
        this.CrossbowDamage = 8;
    }

    @Override
    public void attackTarget() {
        if (new Random().nextInt(1, 101) <= this.accuracy) {
            if ((float) (this.CrossbowDamage - this.targetHero.armor) / (float) this.targetHero.hp >= 0.2) {
                System.out.println(this.getInfo() + " " + this.name +
                        " attacks ♥️ of " + this.targetHero.getInfo() + this.targetHero.name);
                this.targetHero.getStrengthDamage(Math.max(this.CrossbowDamage - this.targetHero.armor, 0));
            } else {
                System.out.println(this.getInfo() + " " + this.name +
                        " attacks \uD83D\uDEE1 of " + this.targetHero.getInfo() + this.targetHero.name);
                this.targetHero.getArmorDamage(this.defenseBreak);
            }
        } else {
            System.out.println(this.getInfo() + this.name + " misses " +
                    "trying to attack " + this.targetHero.getInfo() + " " + this.targetHero.name);
        }

    }
    @Override
    public String getInfo() {
    return "Crossbowman: ";
    }
}