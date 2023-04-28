package com.mygdx.game.Fantasy_game.units;
import java.util.ArrayList;

public abstract class Marksman extends Hero {
    protected int accuracy, maxAmmo;
    protected int ammo;

    protected Marksman(int ammo, int maxAmmo, int accuracy, String name,int hp, int maxHp, int armor, int armorMax,int defenseBreak, int speed, int posX, int posY) {
        super(name,hp, maxHp,  armor, armorMax, defenseBreak, speed, posX, posY);
        this.accuracy = accuracy;
        this.ammo = ammo;
        this.maxAmmo = maxAmmo;
    }

    protected abstract void attackTarget();
    @Override
    public void step(ArrayList<Hero> enemies, ArrayList<Hero> allies) {
        super.step(enemies, allies);
        if (this.state.equals("dead")) return;
        if (this.ammo == 0) {
            System.out.println(this.name + " has nothing to shoot with...");
            return;
        }
        this.targetHero = this.nearestAliveEnemy(enemies);
        this.attackTarget();
        this.ammo--;
        for (Hero ally :
                allies) {
            if ((ally instanceof Militiaman) && (ally.state.equals("ready"))) {
                this.ammo++;
                System.out.println(this.getInfo()+" "+this.name+" receives a projectile from "+ally.getInfo()+" "+ally.name);
                ally.state = "busy";
                break;
            }
        }
    }

    @Override
    public String toString() {
        return super.toString()+" \uD83C\uDFF9:"+this.ammo+"("+this.maxAmmo+")";
    }
}

