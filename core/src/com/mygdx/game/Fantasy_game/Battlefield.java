package com.mygdx.game.Fantasy_game;

import java.util.Scanner;

public class Battlefield {
    static Scanner myScanner = new Scanner(System.in);
    public static void main(String[] args) {
        Main start = new Main();
        start.setTeams(10);
        start.setTurnOrder();
       View view = new View(start);

       view.view();
       System.out.println("'1' - НАЧАТЬ СРАЖЕНИЕ\n'2' - МИРНО РАЗОЙТИСЬ");
       while (myScanner.nextLine().equals("1")) {
           start.gameTurn();

           start.setTurnOrder();
           start.turnCount++;
           view.view();
           if (start.teamOneDead()) {
               System.out.println("TEAM2 WINS");
               break;
           }
           if (start.teamTwoDead()) {
               System.out.println("TEAM1 WINS");
               break;
           }
           System.out.println("'1' - СЛЕДУЮЩИЙ ХОД\n'2' - ЗАКОНЧИТЬ");
       }
        System.out.println("конец...");
    }


}
