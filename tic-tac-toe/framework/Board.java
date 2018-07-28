import java.util.ArrayList;

public class Board {
    private Cell[][] c = new Cell[3][3];
    private int round;
    private int free_space;
    private Player px;
    private Player py;

    public Board(Player px, Player py) {
        round = 0;
        free_space = 9;
        c = fillBoard(c);
        this.px = px;
        this.py = py;
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
        if (!px.IsAi())
            return px;
        else if (!py.IsAi())
            return py;

        return null;
    }
    public Player getAiObj() {
        if (px.IsAi())
            return px;
        else if (py.IsAi())
            return py;

        return null;
    }

    public Player getOpponent(Player p) {
        if( p.getID() == this.px.getID() )
            return this.py;
        else if( p.getID() == this.py.getID() )
            return this.px;

        return null;
    }

    public void setMove(Player p, int x, int y) {
        // System.out.println(p.getID());
        this.c[x][y].setOwnership(p);
        this.free_space--;
    }
    public void setMove(boolean p, int x, int y) {
        if (p)
            this.c[x][y].setOwnership(this.getAiObj());
        else
            this.c[x][y].setOwnership(this.getHumanObj());

        this.free_space--;
    }

    public void resetMove(int x, int y) {
        this.c[x][y].resetOwnership();
        this.free_space++;
    }
    public void resetCells() {
        for(int i=0; i<3; ++i)
            for(int j=0; j<3; ++j)
                c[i][j].resetOwnership();

        this.free_space = 9;
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

        if( col == px.getID() || lin == px.getID() || dia == px.getID() )
            return px;
        else if ( col == py.getID() || lin == py.getID() || dia == py.getID() )
            return py;
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



    public void printGame(Player px, Player py) {

        // System.out.printf("Round %d\n", round);

        // Apaga as linhas já escritas no console
        for(int x=0; x<8; ++x)
            System.out.print("\33[1A\33[2K");

        System.out.println(":  0    1    2");
        System.out.println(":---------------");
        System.out.printf(":0 | %c | %c | %c |\n", c[0][0].getLabel(), c[0][1].getLabel(), c[0][2].getLabel());
        System.out.printf(":  -------------             %s -> %c\n", px.getName(), px.getLabel());
        System.out.printf(":1 | %c | %c | %c |             %s -> %c\n", c[1][0].getLabel(), c[1][1].getLabel(), c[1][2].getLabel(), py.getName(), py.getLabel());
        System.out.println(":  -------------");
        System.out.printf(":2 | %c | %c | %c |\n", c[2][0].getLabel(), c[2][1].getLabel(), c[2][2].getLabel());
        System.out.println(":---------------");
    }

    public int[][] getFreeCells() {
        // int[][] free_cells = new int[][2]
        int[][] free_cells = new int[this.free_space][2];  // famoso exoterismo

        int ctd = 0;
        for( int i=0; i<3; ++i ) {
            for( int j=0; j<3; ++j ) {
                if( c[i][j].isEmpty() ) {
                    int[] pair = {i, j};
                    free_cells[ctd] = pair;
                    ctd++;
                }
            }
        }

            // for(int[] move : free_cells)
            //  System.out.printf("%d %d\n", move[0], move[1]);

        return free_cells;
    }

}
