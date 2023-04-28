package com.mygdx.game.Fantasy_game.units;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Comparator;

public abstract class Hero implements HeroInterface {


    public String name;
    public String state;
    public com.mygdx.game.Fantasy_game.units.Vector2D Vector2D;

    protected Vector2D coords;

    protected int hp, maxHp;

    protected int armor,maxArmor;

    protected int defenseBreak;
    public int speed;

    protected Hero targetHero;

    protected static int heroCnt;


    public int[] getCoords() {return new int[]{coords.posX, coords.posY};}

    protected Hero(String name, int hp, int maxHp, int armor, int maxArmor, int defenseBreak, int speed, int posX, int posY) {

        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.armor = armor;
        this.maxArmor = maxArmor;
        this.defenseBreak = defenseBreak;
        this.speed = speed;
        this.targetHero = null;
        coords = new Vector2D(posX, posY);
        state = "Stand";
        heroCnt++;
    }

    public int getSpeed() { return speed;}
    public float getHp() { return hp;}

    protected void Die() {
        System.out.println(this.getInfo()+this.name+" dies a horrible death...");
        this.state = "dead";
    }

    protected void getStrengthDamage(int dmg) {
        if (dmg<0) {
            System.out.println(this.getInfo()+this.name+" restores "+Math.min(-dmg, this.maxHp-this.hp)+" ♥️ ");
            this.hp += Math.min(-dmg, this.maxHp-this.hp);

        }else {

            System.out.println(this.getInfo() + this.name + " gets " + Math.min(dmg, this.hp) + " ♥️ dmg");
            this.hp -= Math.min(dmg, this.hp);
            if (hp == 0) this.Die();
        }
    }

    protected void getArmorDamage(int armorDmg) {
        System.out.println(this.getInfo()+this.name+" gets "+Math.min(armorDmg, this.armor)+" \uD83D\uDEE1 dmg");
        this.armor -= Math.min(armorDmg, this.armor);
    }

    protected void getHealing(int healPoints) {
        this.hp += Math.min(healPoints, this.maxHp - this.hp);
    }

    @Override
    public void step(ArrayList<Hero> enemies, ArrayList<Hero> allies) {
        if (this.state.equals("dead")) System.out.println(this.getInfo()+" "+this.name+" is very very dead...");
    }

    @Override
    public String toString() {
        return this.getStateSymbol()+" "+this.getInfo()+this.name+
                " \uD83C\uDFAF"+this.coords.toString()+
                " \uD83C\uDF1F:"+this.speed+
                " ♥️:"+this.hp+"("+this.maxHp+")"+
                " \uD83D\uDEE1"+this.armor+"("+this.maxArmor+")";

    }

    public Hero nearestAliveEnemy(ArrayList<Hero> enemies){
        PriorityQueue<Hero> aliveEnemies = new PriorityQueue<>(new Comparator<Hero>() {
            @Override
            public int compare(Hero h1, Hero h2) {
                if (h1.state.equals("dead")) return 1;
                if (h2.state.equals("dead")) return -1;
                if (coords.distanceFrom(h1.coords)>coords.distanceFrom(h2.coords)) return 1;
                return  -1;
            }
        });
        aliveEnemies.addAll(enemies);
        return aliveEnemies.poll();
    }
    public float getStrPerc(){
        return (float)this.hp/(float)this.maxHp;
    }
    private String getStateSymbol(){
        switch (this.state){
            case "dead": return "☠️";
            case "ready": return "\uD83D\uDC40";
            case "busy": return "⏱️";
            default: return this.state;
        }
    }

}

