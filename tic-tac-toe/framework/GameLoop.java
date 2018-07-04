/*
       A idéia que ainda falta resolver no game loop é colocar um laço externo ao
     while já criado para continuar as partidas. Seria legal implementar uma
     ferramenta que consiga alterar os jogadores ou então mante-los iguais. Caso
     o usuário opte por não mudar os jogadores, poderiamos implementar alguma forma
     de pontuar as partidas (mostrando no console mesmo) desses dois usuários até
     que um deles (ou os dois?) saiam do jogo.
        Também seria legal implementar aqui uma forma de salvar os resultados como
     for dito no arquivo Jogador.java



*/
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

            if(!jogo.validade(j1)) {
                jogo.imprimeTabuleiro();
                break;
            }

            jogo.imprimeTabuleiro();


            // Jogador 2 faz sua jogada e é verificado se ele ganhou.
            while(!jogo.fazJogada(j2, j2.getJogadaX(), j2.getJogadaY()) );

            if(!jogo.validade(j2)) {
                jogo.imprimeTabuleiro();
                break;
            }

            jogo.imprimeTabuleiro();

        }
        // jogo.imprimeTabuleiro();



    }
}
