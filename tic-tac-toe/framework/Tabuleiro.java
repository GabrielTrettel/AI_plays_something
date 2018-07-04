class Tabuleiro {
    int[][] tabuleiro;

    public Tabuleiro() {
        tabuleiro = new int[3][3];
    }

    public void imprimeJogo() {
        System.out.println("-------------");
        System.out.printf("| %c | %c | %c |\n", cIc(tabuleiro[0][1]), cIc(tabuleiro[0][1]), cIc(tabuleiro[0][2]));
        System.out.println("-------------");
        System.out.printf("| %c | %c | %c |\n", cIc(tabuleiro[1][1]), cIc(tabuleiro[1][1]), cIc(tabuleiro[1][2]));
        System.out.println("-------------");
        System.out.printf("| %c | %c | %c |\n", cIc(tabuleiro[2][1]), cIc(tabuleiro[2][1]), cIc(tabuleiro[2][2]));
        System.out.println("-------------");
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
}
