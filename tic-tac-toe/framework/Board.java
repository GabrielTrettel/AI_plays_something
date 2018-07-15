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
    private int[][] t;
    private int round;
    private int free_space;
    private Player p1;
    private Player p2;

    public Board(Player p1, Player p2) {
        t = new int[3][3];
        round = 0;
        free_space = 9;
        t = fillBoard(t);
        this.p1 = p1;
        this.p2 = p2;
    }

    public int getRound(){
        return round;
    }

    public int getFreeSpace() {
        return free_space;
    }

    public int getCell(int x, int y) {
        return t[x][y];
    }

    public Player getHumanObj() {
        if(this.p1.getIsAi())
            return p1;

        return p2;
    }

    // !IMPORTANTE! O jogo preenche a matriz com os id's dos jogadores.
    // Dessa forma, fica trivial ver de quem é a jogada ganhadora.
    public void setMove(Player p, int x, int y) {
        this.t[x][y] = p.getID();
        this.free_space--;
    }
    public void resetMove(int x, int y) {
        this.t[x][y] = 0;
        this.free_space++;
    }

    private int[][] fillBoard(int[][] t) {
        for(int i=0; i<3; ++i)
            for(int j=0; j<3; ++j)
                t[i][j] = 0;

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


    // Verifica se algum jogador ganhou completando pelas colunas da matriz
    private int checkColumns() {
        if( t[0][0] == t[1][0] && t[1][0] == t[2][0])
            return t[0][0];
        else if ( t[0][1] == t[1][1] && t[1][1] == t[2][1])
            return t[0][1];
        else if ( t[0][2] == t[1][2] && t[1][2] == t[2][2])
            return t[0][2];

        return -1;
    }


    // Verifica se algum jogador ganhou completando pelas linhas da matriz
    private int checkLines() {
        if( t[0][0] == t[0][1] && t[0][1] == t[0][2])
            return t[0][0];
        else if ( t[1][0] == t[1][1] && t[1][1] == t[1][2])
            return t[1][0];
        else if ( t[2][0] == t[2][1] && t[2][1] == t[2][2])
            return t[2][0];

        return -1;
    }


    // Verifica se algum jogador ganhou completando pelas diagonais da matriz
    private int checkDiagonals() {
        if( t[0][0] == t[1][1] && t[1][1] == t[2][2] )
            return t[0][0];
        else if ( t[0][2] == t[1][1] && t[1][1] == t[2][0] )
            return t[0][2];

        return -1;
    }



    public void printGame(Player p1, Player p2) {

        // System.out.printf("Round %d\n", round);

        // Apaga as linhas já escritas no console
        for(int x=0; x<8; ++x)
            System.out.print("\33[1A\33[2K");

        System.out.println(":  0    1    2");
        System.out.println(":---------------");
        System.out.printf(":0 | %c | %c | %c |\n", cIc(t[0][0], p1, p2), cIc(t[0][1], p1, p2), cIc(t[0][2], p1, p2));
        System.out.printf(":  -------------             %s -> X\n", p1.getName());
        System.out.printf(":1 | %c | %c | %c |             %s -> O\n", cIc(t[1][0], p1, p2), cIc(t[1][1], p1, p2), cIc(t[1][2], p1, p2), p2.getName());
        System.out.println(":  -------------");
        System.out.printf(":2 | %c | %c | %c |\n", cIc(t[2][0], p1, p2), cIc(t[2][1], p1, p2), cIc(t[2][2], p1, p2));
        System.out.println(":---------------");
    }

    // Converte os numeros da matriz para um 'X', 'O' ou um ' ' dependendo do
    // identificador do usuário
    private char cIc(int x, Player p1, Player p2) {
        if( x == p1.getID() )
            return 'X';
        else if ( x == p2.getID() )
            return 'O';

        return ' ';
    }

    public ArrayList<int[]> getFreeCells() {
        // int[][] free_cells = new int[][2]
        ArrayList<int[]> free_cells = new ArrayList<int[]>();  // famoso exoterismo
        int[] pair = new int[2];

        for( int i=0; i<3; ++i )
            for( int j=0; j<3; ++j ) {
                if( t[i][j] == 0 ){
                    pair[0] = i;
                    pair[1] = j;
                    free_cells.add(pair);
                }
            }

        return free_cells;
    }

}
