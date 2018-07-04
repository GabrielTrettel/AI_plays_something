class Rules {
    /**
        Aqui colocaremos a matriz e as regras que definem se o jogador ganhou ou
        não.

        Inicialmente, só por questão de testes, farei uma matriz 3x3 de inteiros
        em que cada numero representará um jogador e o valor 0 será para uma po-
        sição livre. A idéia de fazer isso é coseguir já desenvolver a IA e cons-
        truir a GUI.
    **/

    // Da matriz 3x3 numerica de testes,
    protected Jogador jogador1;
    protected Jogador jogador2;
    int game_status;

    public Rules(Jogador j1, Jogador j2) {
        this.jogador1 = j1;
        this.jogador2 = j2;
        boolean game_status = true;
    }

    public boolean jogada(Tabuleiro tabuleiro, int cordX, int cordY) {
        //TODO

        return true;
    }

}
