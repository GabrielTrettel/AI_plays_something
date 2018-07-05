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

class Board {
    private int[][] t;
    private int round;
    private int free_space;

    public Board() {
        t = new int[3][3];
        round = 0;
        free_space = 9;
        t = fillBoard(t);
    }

    private int[][] fillBoard(int[][] t) {
        for(int i=0; i<3; ++i)
            for(int j=0; j<3; ++j)
                t[i][j] = 0;

        return t;
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

    // !IMPORTANTE! O jogo preenche a matriz com os id's dos jogadores.
    // Dessa forma, fica trivial ver de quem é a jogada ganhadora.
    public void setMove(Jogador j, int x, int y) {
        this.t[x][y] = j.getID();
        this.free_space--;
    }

    // Verifica se algum jogador ganhou completando pelas colunas da matriz
    public int checkColumns(Jogador j) {
        if( t[0][0] == t[1][0] && t[1][0] == t[2][0] && t[0][0] == j.getID())
            return t[0][0];
        else if ( t[0][1] == t[1][1] && t[1][1] == t[2][1] && t[0][1] == j.getID())
            return t[0][1];
        else if ( t[0][2] == t[1][2] && t[1][2] == t[2][2] && t[0][2] == j.getID() )
            return t[0][2];

        return -1;
    }


    // Verifica se algum jogador ganhou completando pelas linhas da matriz
    public int checkLines(Jogador j) {
        if( t[0][0] == t[0][1] && t[0][1] == t[0][2] && t[0][0] == j.getID() )
            return t[0][0];
        else if ( t[1][0] == t[1][1] && t[1][1] == t[1][2] && t[1][0] == j.getID() )
            return t[1][0];
        else if ( t[2][0] == t[2][1] && t[2][1] == t[2][2] && t[2][0] == j.getID() )
            return t[2][0];

        return -1;
    }


    // Verifica se algum jogador ganhou completando pelas diagonais da matriz
    public int checkDiagonals(Jogador j) {
        if( t[0][0] == t[1][1] && t[1][1] == t[2][2] && t[0][0] == j.getID() )
            return t[0][0];
        else if ( t[0][2] == t[1][1] && t[1][1] == t[2][0] && t[0][2] == j.getID() )
            return t[0][2];

        return -1;
    }



    public void printGame(Jogador j1, Jogador j2) {

        // System.out.printf("Round %d\n", round);

        // Apaga as linhas já escritas no console
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");

        System.out.println(":  0    1    2");
        System.out.println(":---------------");
        System.out.printf(":0 | %c | %c | %c |\n", cIc(t[0][0], j1, j2), cIc(t[0][1], j1, j2), cIc(t[0][2], j1, j2));
        System.out.printf(":  -------------             %s -> X\n", j1.getNome());
        System.out.printf(":1 | %c | %c | %c |             %s -> O\n", cIc(t[1][0], j1, j2), cIc(t[1][1], j1, j2), cIc(t[1][2], j1, j2), j2.getNome());
        System.out.println(":  -------------");
        System.out.printf(":2 | %c | %c | %c |\n", cIc(t[2][0], j1, j2), cIc(t[2][1], j1, j2), cIc(t[2][2], j1, j2));
        System.out.println(":---------------");
    }

    // Converte os numeros da matriz para um 'X', 'O' ou um ' ' dependendo do
    // identificador do usuário
    private char cIc(int x, Jogador j1, Jogador j2) {
        if( x == j1.getID() )
            return 'X';
        else if ( x == j2.getID() )
            return 'O';

        return ' ';
    }

}
