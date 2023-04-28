package com.mygdx.game.Fantasy_game.units;

import java.util.ArrayList;

public abstract class Warrior extends Hero {

    protected int range;
    protected int WeaponDamage;


    public Warrior(int range,int WeaponDamage, String name, int hp, int maxHp,int armor, int maxArmor,int defenseBreak, int speed, int posX, int posY) {
        super(name,hp, maxHp,armor, maxArmor, defenseBreak, speed, posX, posY);
        this.range = range;
        this.WeaponDamage = WeaponDamage;
    }

    protected boolean targetWithinAttackRange() {
        return (this.coords.distanceFrom(this.targetHero.coords) <= this.range);
    }

    protected void meleeAttack() {
        if ((float) (this.WeaponDamage - this.targetHero.armor) / (float) this.targetHero.hp >= 0.2){
            System.out.println(this.getInfo() + " " + this.name +
                    " attacks ♥️ of " + this.targetHero.getInfo() + this.targetHero.name);
            this.targetHero.getStrengthDamage(Math.max(this.WeaponDamage - this.targetHero.armor, 0));
        }else{
            System.out.println(this.getInfo() + this.name +
                    " attacks \uD83D\uDEE1 of " + this.targetHero.getInfo() + " " + this.targetHero.name);
            this.targetHero.getArmorDamage(this.defenseBreak);
        }
    }
    protected void moveTowardsTarget(ArrayList<Hero> allies){
        Vector2D dir = this.coords.directionTo(this.targetHero.coords);
        Vector2D primaryDestination;
        Vector2D secondaryDestination;
        if (Math.abs(dir.posX) > Math.abs(dir.posY)) {
            primaryDestination = new Vector2D(this.coords.posX + (int) Math.signum(dir.posX), this.coords.posY);
            secondaryDestination = new Vector2D(this.coords.posX, this.coords.posY + (int) Math.signum(dir.posY));
        } else {
            secondaryDestination = new Vector2D(this.coords.posX + (int) Math.signum(dir.posX), this.coords.posY);
            primaryDestination = new Vector2D(this.coords.posX, this.coords.posY + (int) Math.signum(dir.posY));
        }
        if (isVacant(primaryDestination, allies)) {
            this.coords = primaryDestination;
            System.out.println(this.getInfo()+this.name+" "+"makes a resolute step towards "
                    +this.targetHero.getInfo()+this.targetHero.name);
        }
        else if (isVacant(secondaryDestination, allies)) {
            this.coords = secondaryDestination;
            System.out.println(this.getInfo()+this.name+" makes a resolute step towards "
                    +this.targetHero.getInfo()+this.targetHero.name);
        }
        else {
            System.out.println(this.getInfo()+this.name+" is waiting anxiously for the right moment");
        }
    }

    protected boolean isVacant(Vector2D pos, ArrayList<Hero> allies){
        for (Hero ally :
                allies) {
            if (ally.coords.equals(pos) && !ally.state.equals("dead")) return false;
        }
        return true;
    }

    @Override
    public void step(ArrayList<Hero> enemies, ArrayList<Hero> allies) {
        super.step(enemies, allies);
        if (this.state.equals("dead")) return;
        this.targetHero = this.nearestAliveEnemy(enemies);
        if (this.targetWithinAttackRange()) this.meleeAttack();
        else this.moveTowardsTarget(allies);
    }
}