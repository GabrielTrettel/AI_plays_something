class GameLoop {

    public static void main(String[] args) {
        Jogador j1 = new Jogador("Daniel");
        Jogador j2 = new Jogador("Gabriel");
        Rules jogo = new Rules(j1, j2);

        System.out.printf("Nome : %s\nID: %d\n\nNome : %s\nID: %d\n\n", j1.getNome(), j1.getID(), j2.getNome(), j2.getID());



        jogo.imprimeTabuleiro();
        while(jogo.getGameStatus() == true) {

            // Jogador 1 faz sua jogada e é verificado se ele ganhou.
            while(!jogo.fazJogada(j1, j1.getJogadaX(), j1.getJogadaY()) );
            jogo.imprimeTabuleiro();


            if(!jogo.validade(j1))
                break;


            // Jogador 2 faz sua jogada e é verificado se ele ganhou.
            while(!jogo.fazJogada(j2, j2.getJogadaX(), j2.getJogadaY()) );

            if(!jogo.validade(j2))
                break;

            jogo.imprimeTabuleiro();

        }


    }
}
