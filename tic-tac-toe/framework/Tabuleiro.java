class Tabuleiro {
    private int[][] t;
    private int rodada;
    private int espaco_livre;

    public Tabuleiro() {
        t = new int[3][3];
        rodada = 0;
        espaco_livre = 9;
        t = completaTabuleiro(t);
    }

    private int[][] completaTabuleiro(int[][] t) {
        for(int i=0; i<3; ++i)
            for(int j=0; j<3; ++j)
                t[i][j] = 0;

        return t;
    }

    public int getEspacoLivre() {
        return espaco_livre;
    }

    public int getCelula(int x, int y) {
        return t[x][y];
    }

    public void setJogada(Jogador j, int x, int y) {
        this.t[x][y] = j.getID();
        this.espaco_livre--;
    }

    public int verificaColunas(Jogador j) {
        // for(int i=0; i<3; ++i) {
        //     for(int k=0; k<3; ++k)
        //         System.out.printf("%d ",t[i][k]);
        //
        //     System.out.printf("\n\n");
        //
        // }

        if( t[0][0] == t[1][0] && t[1][0] == t[2][0] && t[0][0] == j.getID())
            return t[0][0];
        else if ( t[0][1] == t[1][1] && t[1][1] == t[2][1] && t[0][1] == j.getID())
            return t[0][1];
        else if ( t[0][2] == t[1][2] && t[1][2] == t[2][2] && t[0][2] == j.getID() )
            return t[0][2];

        return -1;
    }

    public int verificaLinhas(Jogador j) {
        if( t[0][0] == t[0][1] && t[0][1] == t[0][2] && t[0][0] == j.getID() )
            return t[0][0];
        else if ( t[1][0] == t[1][1] && t[1][1] == t[1][2] && t[1][0] == j.getID() )
            return t[1][0];
        else if ( t[2][0] == t[2][1] && t[2][1] == t[2][2] && t[2][0] == j.getID() )
            return t[2][0];

        return -1;
    }

    public int verificaDiagonais(Jogador j) {
        if( t[0][0] == t[1][1] && t[1][1] == t[2][2] && t[0][0] == j.getID() )
            return t[0][0];
        else if ( t[0][2] == t[1][1] && t[1][1] == t[2][0] && t[0][2] == j.getID() )
            return t[0][2];

        return -1;
    }


    public void imprimeJogo(Jogador j1, Jogador j2) {

        // System.out.printf("Rodada %d\n", rodada);
        System.out.println(":  0    1    2");
        System.out.println(":  -------------");
        System.out.printf(":0 | %c | %c | %c |\n", cIc(t[0][0], j1, j2), cIc(t[0][1], j1, j2), cIc(t[0][2], j1, j2));
        System.out.println(":  -------------");
        System.out.printf(":1 | %c | %c | %c |\n", cIc(t[1][0], j1, j2), cIc(t[1][1], j1, j2), cIc(t[1][2], j1, j2));
        System.out.println(":  -------------");
        System.out.printf(":2 | %c | %c | %c |\n", cIc(t[2][0], j1, j2), cIc(t[2][1], j1, j2), cIc(t[2][2], j1, j2));
        System.out.println(":---------------");
    }

    private char cIc(int x, Jogador j1, Jogador j2) {
        if( x == j1.getID() )
            return 'X';
        else if ( x == j2.getID() )
            return 'O';

        return ' ';
    }

    public int getRodada(){
        return rodada;
    }

}
