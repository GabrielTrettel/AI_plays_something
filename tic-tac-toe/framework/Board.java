/**
       Aqui colocaremos a matriz, que ainda precisa ser ajustada para ser de objetos
    e não de estruturas primitivas. Como optamos por usar GUI, acho que seria legal
    criar uma classe "peça", por exemplo, e lá colocarmos seu rótulo ('X', 'O' ou ' '),
    cor e outras informações legais.

    Inicialmente, só por questão de testes, farei uma matriz 3x3 de inteiros
    em que cada numero (o ID do jogador) representará um jogador e o valor 0
    será para uma posição livre. A idéia de fazer isso é coseguir já desenvolver
    a IA e construir a GUI.

**/

import java.util.ArrayList;

public class Board {
    private Cell[][] c;
    private int round;
    private int free_space;
    private Player p1;
    private Player p2;

    public Board(Player p1, Player p2) {
        c = new Cell[3][3];
        round = 0;
        free_space = 9;
        c = fillBoard(c);
        this.p1 = p1;
        this.p2 = p2;
    }

    public int getRound(){
        return round;
    }

    public int getFreeSpace() {
        return free_space;
    }

    public Cell getCell(int x, int y) {
        return c[x][y];
    }

    public Player getHumanObj() {
        return p1;
    }
    public Player getAiObj() {
        return p2;
    }

    public Player getOpponent(Player p) {
        if( p.getID() == this.p1.getID() )
            return this.p2;
        else if( p.getID() == this.p2.getID() )
            return this.p1;

        return null;
    }

    // !IMPORTANTE! O jogo preenche a matriz com os id's dos jogadores.
    // Dessa forma, fica trivial ver de quem é a jogada ganhadora.
    public void setMove(Player p, int x, int y) {
        // System.out.println(p.getID());
        this.c[x][y].setOwnership(p);
        this.free_space--;
    }
    public void resetMove(int x, int y) {
        this.c[x][y].resetID();
        this.c[x][y].resetLabel();
        this.free_space++;
    }

    private Cell[][] fillBoard(Cell[][] t) {
        for(int i=0; i<3; ++i)
            for(int j=0; j<3; ++j)
                t[i][j] = new Cell();

        return t;
    }

    public Player checkWinner() {
        int col = checkColumns();
        int lin = checkLines();
        int dia = checkDiagonals();

        if( col == p1.getID() || lin == p1.getID() || dia == p1.getID() )
            return p1;
        else if ( col == p2.getID() || lin == p2.getID() || dia == p2.getID() )
            return p2;
        else
            return null;
    }

    public boolean checkWinnerPlayer(Player p) {
        int col = checkColumns();
        int lin = checkLines();
        int dia = checkDiagonals();

        if( col == p.getID() || lin == p.getID() || dia == p.getID() )
            return true;

        return false;

    }


    // Verifica se algum jogador ganhou completando pelas colunas da matriz
    private int checkColumns() {
        if( c[0][0].getID() == c[1][0].getID() && c[1][0].getID() == c[2][0].getID())
            return c[0][0].getID();
        else if ( c[0][1].getID() == c[1][1].getID() && c[1][1].getID() == c[2][1].getID())
            return c[0][1].getID();
        else if ( c[0][2].getID() == c[1][2].getID() && c[1][2].getID() == c[2][2].getID())
            return c[0][2].getID();

        return -1;
    }


    // Verifica se algum jogador ganhou completando pelas linhas da matriz
    private int checkLines() {
        if( c[0][0].getID() == c[0][1].getID() && c[0][1].getID() == c[0][2].getID())
            return c[0][0].getID();
        else if ( c[1][0].getID() == c[1][1].getID() && c[1][1].getID() == c[1][2].getID())
            return c[1][0].getID();
        else if ( c[2][0].getID() == c[2][1].getID() && c[2][1].getID() == c[2][2].getID())
            return c[2][0].getID();

        return -1;
    }


    // Verifica se algum jogador ganhou completando pelas diagonais da matriz
    private int checkDiagonals() {
        if( c[0][0].getID() == c[1][1].getID() && c[1][1].getID() == c[2][2].getID())
            return c[0][0].getID();
        else if( c[0][2].getID() == c[1][1].getID() && c[1][1].getID() == c[2][0].getID())
            return c[0][2].getID();

        return -1;
    }



    public void printGame(Player p1, Player p2) {

        // System.out.printf("Round %d\n", round);

        // Apaga as linhas já escritas no console
        // for(int x=0; x<8; ++x)
        //     System.out.print("\33[1A\33[2K");

        System.out.println(":  0    1    2");
        System.out.println(":---------------");
        System.out.printf(":0 | %c | %c | %c |\n", c[0][0].getLabel(), c[0][1].getLabel(), c[0][2].getLabel());
        System.out.printf(":  -------------             %s -> X\n", p1.getName());
        System.out.printf(":1 | %c | %c | %c |             %s -> O\n", c[1][0].getLabel(), c[1][1].getLabel(), c[1][2].getLabel(), p2.getName());
        System.out.println(":  -------------");
        System.out.printf(":2 | %c | %c | %c |\n", c[2][0].getLabel(), c[2][1].getLabel(), c[2][2].getLabel());
        System.out.println(":---------------");
    }

    public ArrayList<int[]> getFreeCells() {
        // int[][] free_cells = new int[][2]
        ArrayList<int[]> free_cells = new ArrayList<int[]>();  // famoso exoterismo


        for( int i=0; i<3; ++i ) {
            for( int j=0; j<3; ++j ) {
                if( c[i][j].getID() == 0 ) {
                    // System.out.printf("%d - %d\n", i, j);
                    int[] pair = new int[2];
                    pair[0] = i;
                    pair[1] = j;
                    free_cells.add(pair);
                }
            }
        }

            // for(int[] move : free_cells)
            //  System.out.printf("%d %d\n", move[0], move[1]);

        return free_cells;
    }

}
