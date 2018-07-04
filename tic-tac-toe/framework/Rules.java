class Rules {
    /**
        Aqui colocaremos a matriz e as regras que definem se o jogador ganhou ou
        não.

        Inicialmente, só por questão de testes, farei uma matriz 3x3 de inteiros
        em que cada numero representará um jogador e o valor 0 será para uma po-
        sição livre. A idéia de fazer isso é coseguir já desenvolver a IA e cons-
        truir a GUI.
    **/

    private int game_status;
    private Tabuleiro tabuleiro;

    public Rules() {
        this.jogador1 = j1;
        this.jogador2 = j2;
        tabuleiro = new Tabuleiro();
        game_status = true;
    }
    public boolean getGameStatus() {
        return this.game_status;
    }

    // Efetua a jogada que o usuário está tentando fazer
    public boolean fazJogada(Jogador j, int cordX, int cordY) {
        if(!validaJogada(tabuleiro, cordX, cordY))
            return false;

        //TODO

        return true;
    }


    // Valida se algém ganhou ou se deu velha
    public boolean validade() {
        //TODO
        return true;
    }

    // Verifica se a jogada que o usuário pretende fazer é válida
    private boolean validaJogada(int cordX, int cordY) {
        //TODO
        if()


        return false;
    }

    public void imprimeTabuleiro() {
        tabuleiro.imprimeJogo();
    }


}
