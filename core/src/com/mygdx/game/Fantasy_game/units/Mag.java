package com.mygdx.game.Fantasy_game.units;

import com.mygdx.game.Fantasy_game.SpellBook;

import java.util.ArrayList;

public abstract class Mag extends Hero {
    protected int mana;
    protected int maxMana;
    protected int cost;

    public Mag(int mana, int cost, int maxMana, String name, int hp, int maxHp, int armor, int armorMax,int defenseBreak,
               int speed,int posX, int posY) {
        super(name, hp, maxHp, armor, armorMax,defenseBreak, speed, posX, posY);
        this.mana = mana;
        this.maxMana = maxMana;
        this.cost = cost;
    }
    protected void castSpell(SpellBook spell){
        if (spell==null) {
            System.out.println(this.getInfo()+this.name+" can't do anything useful...");
            return;
        }
        System.out.println(this.getInfo()+this.name+" casts "+spell.name+" on "
                +this.targetHero.getInfo()+this.targetHero.name);
        this.mana -= spell.manaCost;
        this.targetHero.getStrengthDamage(spell.spellPower);
    }
    protected abstract SpellBook selectSpell();
    protected abstract void pickTarget(ArrayList<Hero> enemies,ArrayList<Hero> allies);

    @Override
    public void step(ArrayList<Hero> enemies, ArrayList<Hero> allies)  {
        super.step(enemies, allies);
        if (this.state.equals("dead")) return;
        if (this.mana<3) {
            System.out.println(this.getInfo()+this.name+" hasn't got enough mana to cast anything..." );
            return;
        }
        this.pickTarget(enemies, allies);
        this.castSpell(this.selectSpell());
    }
    @Override
    public String toString() {
        return super.toString()+" \uD83E\uDE84"+this.mana+"("+this.maxMana+")";
    }
}