class GameLoop {

    public static void main(String[] args) {
        Jogador j1 = new Jogador("Luis");
        Jogador j2 = new Jogador("Cleber");
        Rules jogo = new Rules(j1, j2);

        System.out.printf("Nome : %s\nID: %d\n\nNome : %s\nID: %d\n\n", j1.getNome(), j1.getID(), j2.getNome(), j2.getID());
        tabuleiro.imprimeJogo();


        while(jogo.getGameStatus() == true) {
            jogo.imprimeTabuleiro();

            jogo.fazJogada(j1, a, b)

            break;
        }


    }
}
