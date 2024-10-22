/*******************************************************************************
* Copyright (c) 2018, Gabriel M. Trettel, Eric S. Karbstein,
* Lucas Z. de Oliveira, Daniel de O. Montenegro
* All rights reserved.
*
* This Source Code Form is subject to the terms of the BSD 3-Clause License.
*******************************************************************************/

public class ScoreBoard {
    public static int scr1 = 0;
    public static int scr2 = 0;


    public static int getScore1() {
        return scr1;
    }

    public static int getScore2() {
        return scr2;
    }

    public static void setScore(char winnerLabel) {
        if (winnerLabel == 'X')
            scr1++;
        else
            scr2++;
    }

    public static void resetScore() {
        scr1 = 0;
        scr2 = 0;
    }

    public static void ImprimirScoreBoard(Player p1, Player p2) {
        if (p1.getLabel() == 'X')
            System.out.printf("%s %d X %d %s", p1.getName(), ScoreBoard.getScore1(), ScoreBoard.getScore2(), p2.getName());
        else
            System.out.printf("%s %d X %d %s", p2.getName(), ScoreBoard.getScore1(), ScoreBoard.getScore2(), p1.getName());
    }

}
