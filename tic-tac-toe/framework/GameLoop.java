/*
       A idéia que ainda falta resolver no game loop é colocar um laço externo ao
     while já criado para continuar as partidas. Seria legal implementar uma
     ferramenta que consiga alterar os jogadores ou então mante-los iguais. Caso
     o usuário opte por não mudar os jogadores, poderiamos implementar alguma forma
     de pontuar as partidas (mostrando no console mesmo) desses dois usuários até
     que um deles (ou os dois?) saiam do jogo.
        Também seria legal implementar aqui uma forma de salvar os resultados como
     for dito no arquivo Player.java



*/
class GameLoop {

    public static void main(String[] args) {

        Player j1 = new Player();
        j1.setName();
        Player j2 = new Player();
        j2.setName();

        Rules jogo = new Rules(j1, j2);

        System.out.printf("Nome : %s\nID: %d\n\nNome : %s\nID: %d\n\n", j1.getName(), j1.getID(), j2.getName(), j2.getID());

        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");

        jogo.printBoard();

        while(jogo.getGameStatus()) {
            // Jogador 1 faz sua jogada e é verificado se ele ganhou.
            while(!jogo.makeMove(j1, j1.getMoveX(), j1.getMoveY()) );

            if(!jogo.validate(j1)) {
                jogo.printBoard();
                break;
            }

            jogo.printBoard();


            // Jogador 2 faz sua jogada e é verificado se ele ganhou.
            while(!jogo.makeMove(j2, j2.getMoveX(), j2.getMoveY()) );

            if(!jogo.validate(j2)) {
                jogo.printBoard();
                break;
            }

            jogo.printBoard();

        }
        // jogo.printBoard();



    }
}
