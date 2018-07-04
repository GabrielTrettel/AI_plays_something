class Rules {
    /**
        Aqui colocaremos a matriz e as regras que definem se o jogador ganhou ou
        não.

        Inicialmente, só por questão de testes, farei uma matriz 3x3 de inteiros
        em que cada numero representará um jogador e o valor 0 será para uma po-
        sição livre. A idéia de fazer isso é coseguir já desenvolver a IA e cons-
        truir a GUI.
    **/

    private boolean game_status;
    private Tabuleiro t;
    Jogador j1, j2;

    public Rules(Jogador j1, Jogador j2) {
        t = new Tabuleiro();
        this.j1 = j1;
        this.j2 = j2;
        game_status = true;
    }

    public boolean getGameStatus() {
        return this.game_status;
    }

    // Efetua a jogada que o usuário está tentando fazer
    public boolean fazJogada(Jogador j, int cordX, int cordY) {
        if(!validaJogada(t, cordX, cordY)) {

            return false;
        }

        t.setJogada(j, cordX, cordY);

        return true;
    }

    // Verifica se a jogada que o usuário pretende fazer é válida
    private boolean validaJogada(Tabuleiro t, int cordX, int cordY) {
        if( t.getCelula(cordX, cordY) != 0 )
            return false;
        else
            return true;
    }


    // Valida se algém ganhou ou se deu velha
    public boolean validade(Jogador j1) {
        int col = t.verificaColunas(j1);
        int diag = t.verificaDiagonais(j1);
        int lin = t.verificaLinhas(j1);

        if( t.getEspacoLivre() == 0 ) {
            this.game_status = false;
            return false;
        }

        if( col != -1 ) {
            parabenizaJogador(col, j1);
            return false;
        } else if( lin != -1 ) {
            parabenizaJogador(lin, j1);
            return false;
        }else if( diag != -1 ) {
            parabenizaJogador(diag, j1);
            return false;
        }

        return true;
    }



    private void parabenizaJogador(int x, Jogador j1) {
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.printf("O jogador %s ganhou o jogo!\n\n\n\n\n\n\n\n\n\n\n", j1.getNome());
    }


    public void imprimeTabuleiro() {
        t.imprimeJogo(this.j1, this.j2);
    }


}
