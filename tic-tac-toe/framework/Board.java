/*******************************************************************************
* Copyright (c) 2018, Gabriel M. Trettel, Eric S. Karbstein,
* Lucas Z. de Oliveira, ddom
* All rights reserved.
*
* This Source Code Form is subject to the terms of the BSD 3-Clause License.
*******************************************************************************/

public class Board {
    private Cell[][] c = new Cell[3][3];
    private int free_space;


    public Board() {
        free_space = 9;
        c = fillBoard(c);
    }


    public int getFreeSpace() {
        return free_space;
    }

    public Cell getCell(int x, int y) {
        return c[x][y];
    }

    public void setMove(Player p, int x, int y) {
        this.c[x][y].setOwnership(p);
        this.free_space--;
    }

    public void resetMove(int x, int y) {
        this.c[x][y].resetOwnership();
        this.free_space++;
    }

    private Cell[][] fillBoard(Cell[][] t) {
        for (int i=0; i<3; ++i)
            for (int j=0; j<3; ++j)
                t[i][j] = new Cell();

        return t;
    }

    public void printGame(Player px, Player py) {
        System.out.println(":    0   1   2");
        System.out.printf(":---------------             ");
        ScoreBoard.ImprimirScoreBoard(px, py);
        System.out.printf("\n:0 | %c | %c | %c |\n", c[0][0].getLabel(), c[0][1].getLabel(), c[0][2].getLabel());
        System.out.printf(":  -------------             %s -> %c\n", px.getName(), px.getLabel());
        System.out.printf(":1 | %c | %c | %c |             %s -> %c\n", c[1][0].getLabel(), c[1][1].getLabel(), c[1][2].getLabel(), py.getName(), py.getLabel());
        System.out.println(":  -------------");
        System.out.printf(":2 | %c | %c | %c |\n", c[2][0].getLabel(), c[2][1].getLabel(), c[2][2].getLabel());
        System.out.println(":---------------");
    }

    public int[][] getFreeCells() {
        int[][] free_cells = new int[this.free_space][2];

        int ctd = 0;
        for (int i=0; i<3; ++i) {
            for (int j=0; j<3; ++j) {
                if (c[i][j].isEmpty()) {
                    int[] pair = {i, j};
                    free_cells[ctd] = pair;
                    ctd++;
                }
            }
        }

        return free_cells;
    }

}
