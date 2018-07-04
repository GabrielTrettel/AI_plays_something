class GameLoop {

    public static void main(String[] args) {

        Jogador j1 = new Jogador();
        j1.setNome();
        Jogador j2 = new Jogador();
        j2.setNome();

        Rules jogo = new Rules(j1, j2);

        System.out.printf("Nome : %s\nID: %d\n\nNome : %s\nID: %d\n\n", j1.getNome(), j1.getID(), j2.getNome(), j2.getID());

        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");

        jogo.imprimeTabuleiro();

        while(jogo.getGameStatus()) {
            // Jogador 1 faz sua jogada e é verificado se ele ganhou.
            while(!jogo.fazJogada(j1, j1.getJogadaX(), j1.getJogadaY()) );

            if(!jogo.validade(j1))
                break;

            jogo.imprimeTabuleiro();


            // Jogador 2 faz sua jogada e é verificado se ele ganhou.
            while(!jogo.fazJogada(j2, j2.getJogadaX(), j2.getJogadaY()) );

            if(!jogo.validade(j2))
                break;

            jogo.imprimeTabuleiro();

        }
        jogo.imprimeTabuleiro();



    }
}
