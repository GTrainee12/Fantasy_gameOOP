package com.mygdx.game.Fantasy_game;
import com.mygdx.game.Fantasy_game.units.*;
import java.util.Collections;

public class View {
    public Main game;
    public View(Main game){
        this.game = game;
        this.top = formatDiv("a") + String.join("", Collections.nCopies(game.teamSize-1, formatDiv("-b"))) + formatDiv("-c");
        this.midl = formatDiv("d") + String.join("", Collections.nCopies(game.teamSize-1, formatDiv("-e"))) + formatDiv("-f");
        this.bottom = formatDiv("g") + String.join("", Collections.nCopies(game.teamSize-1, formatDiv("-h"))) + formatDiv("-i");

    }
    private final int[] l = {0};
    private final String top;
    private final String midl;
    private final String bottom;
    private void tabSetter(int cnt, int max){
        int dif = max - cnt + 2;
        if (dif>0) System.out.printf("%" + dif + "s", ":"); else System.out.print(":");
    }
    private static String formatDiv(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
    }
    private String getChar(int x, int y){
        String out = "| ";
        for (Hero human: this.game.bothTeamsInTurnOrder) {
            if (human.getCoords()[0] == x && human.getCoords()[1] == y){
                if (human.state.equals("dead")) {
                    out = "|" + (AnsiColors.ANSI_RED + human.getInfo().charAt(0) + AnsiColors.ANSI_RESET);
                }
                else if (this.game.group_elves.contains(human)) out = "|" + (AnsiColors.ANSI_GREEN + human.getInfo().charAt(0) + AnsiColors.ANSI_RESET);
                else if (this.game.group_people.contains(human)) out = "|" + (AnsiColors.ANSI_BLUE + human.getInfo().charAt(0) + AnsiColors.ANSI_RESET);
                break;
            }
        }
        return out;
    }
    public void view() {

        System.out.print(AnsiColors.ANSI_RED + "Step:" + this.game.turnCount + AnsiColors.ANSI_RESET);
//        this.game.group_elves.forEach((v) -> l[0] = Math.max(l[0], v.toString().length()));
        System.out.print("_".repeat(l[0]*2));
        System.out.println("");
        System.out.print(this.top + " ");
        System.out.print("Green side");
        //for (int i = 0; i < l[0]-10; i++)
        System.out.print(" ".repeat(l[0]-9));
        System.out.println(":Blue side");
        for (int i = 1; i < this.game.teamSize+1; i++) {
            System.out.print(getChar(1, i));
        }
        System.out.print("| ");
        System.out.print(this.game.group_elves.get(0));
        tabSetter(this.game.group_elves.get(0).toString().length(), l[0]);
        System.out.println(this.game.group_people.get(0));
        System.out.println(this.midl);

        for (int i = 2; i < this.game.teamSize; i++) {
            for (int j = 1; j < this.game.teamSize+1; j++) {
                System.out.print(getChar(i, j));
            }
            System.out.print("| ");
            System.out.print(this.game.group_elves.get(i-1));
            tabSetter(this.game.group_elves.get(i-1).toString().length(), l[0]);
            System.out.println(this.game.group_people.get(i-1));
            System.out.println(this.midl);
        }
        for (int j = 1; j < this.game.teamSize+1; j++) {
            System.out.print(getChar(this.game.teamSize, j));
        }
        System.out.print("| ");
        System.out.print(this.game.group_elves.get(this.game.teamSize-1));
        tabSetter(this.game.group_elves.get(this.game.teamSize-1).toString().length(), l[0]);
        System.out.println(this.game.group_people.get(this.game.teamSize-1));
        System.out.println(this.bottom);
    }
}