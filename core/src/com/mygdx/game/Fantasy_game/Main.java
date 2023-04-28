package com.mygdx.game.Fantasy_game;

import com.mygdx.game.Fantasy_game.units.Hero;
import com.mygdx.game.Fantasy_game.units.*;
import java.util.*;

public class Main {

            public static ArrayList<Hero> group_elves = new ArrayList<>();
            public static ArrayList<Hero> group_people = new ArrayList<>();
            public static PriorityQueue<Hero> bothTeamsInTurnOrder;
            public int turnCount;
            public int teamSize;


            public void setTeams(int teamSize) {
                    this.teamSize = teamSize;
                    this.group_elves = new ArrayList<>();
                    this.group_people = new ArrayList<>();
                for (int i = 0; i < teamSize; i++) {
                    int rnd = new Random().nextInt(4);
                    switch (rnd) {
                        case (0):
                            group_elves.add(new Archer(getName(),new Vector2D(i+1, 1)));
                            break;
                        case (1):
                            group_elves.add(new Knight(getName(),new Vector2D(i+1, 1)));
                            break;
                        case (2):
                            group_elves.add(new Spectrum(getName(),new Vector2D(i+1, 1)));
                            break;
                        default:
                            group_elves.add(new Militiaman(getName(),new Vector2D(i+1, 1)));
                    }

                    rnd = new Random().nextInt(4);
                    switch (rnd) {
                        case (0):
                            group_people.add(new Militiaman(getName(), new Vector2D(i+1, 10)));
                            break;
                        case (1):
                            group_people.add(new Crossbowman(getName(),new Vector2D(i+1, 10)));
                            break;
                        case (2):
                            group_people.add(new Monk(getName(),new Vector2D(i+1, 10)));
                            break;
                        default:
                            group_people.add(new Spearman(getName(),new Vector2D(i+1, 10)));
                    }
                }
            }
            public void setTurnOrder(){
                this.bothTeamsInTurnOrder = new PriorityQueue<>(new Comparator<Hero>() {
                    @Override
                    public int compare(Hero h1, Hero h2) {
                        if (h1.speed==h2.speed) {
                            if (h2.getStrPerc()>h1.getStrPerc()) return 1;
                            else return -1;
                        }
                        return h2.speed-h1.speed;
                    }
                });
                this.bothTeamsInTurnOrder.addAll(this.group_elves);
                this.bothTeamsInTurnOrder.addAll(this.group_people);
            }
            public void gameTurn(){
                while (!this.bothTeamsInTurnOrder.isEmpty()){
                    if (this.group_elves.contains(this.bothTeamsInTurnOrder.peek()))
                        this.bothTeamsInTurnOrder.poll().step(this.group_people, this.group_elves);
                    else this.bothTeamsInTurnOrder.poll().step(this.group_elves, this.group_people);
                }
            }

            public boolean teamOneDead(){
                for (Hero hero:
                        this.group_elves) {
                    if (!hero.state.equals("dead")) return false;
                }
                return true;
            }
            public boolean teamTwoDead(){
                for (Hero hero:
                        this.group_people) {
                    if (!hero.state.equals("dead")) return false;
                }
                return true;
            }
            protected static String getName(){
                return Nme.values()[new Random().nextInt( Nme.values().length ) ].toString();
            }
        }