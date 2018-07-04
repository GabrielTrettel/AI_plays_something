class Tabuleiro {
    int[][] tabuleiro;
    int rodada;

    public Tabuleiro() {
        tabuleiro = new int[3][3];
        rodada = 0;
    }

    public void imprimeJogo() {

        System.out.printf("Rodada %d\n", rodada);
        System.out.println(":  0    1    2");
        System.out.println(":  -------------");
        System.out.printf(":0 | %c | %c | %c |\n", cIc(tabuleiro[0][1]), cIc(tabuleiro[0][1]), cIc(tabuleiro[0][2]));
        System.out.println(":  -------------");
        System.out.printf(":1 | %c | %c | %c |\n", cIc(tabuleiro[1][1]), cIc(tabuleiro[1][1]), cIc(tabuleiro[1][2]));
        System.out.println(":  -------------");
        System.out.printf(":2 | %c | %c | %c |\n", cIc(tabuleiro[2][1]), cIc(tabuleiro[2][1]), cIc(tabuleiro[2][2]));
        System.out.println(":---------------");
    }

    private char cIc(int x) {
        if( x == 1 )
            return 'X';
        else if ( x == 2 )
            return 'O';
        else if ( x == 0 )
            return ' ';

        return ' ';
    }

    public int getRodada(){
        return rodada;
    }

}
