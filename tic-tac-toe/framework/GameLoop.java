import java.util.Scanner;
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

        Player p1 = new Player();
        p1.setName();

        Scanner sc1 = new Scanner(System.in);
        System.out.printf("Jogar contra computador? [s/N] ");
        char ans = sc1.next().charAt(0);

        Player p2;

        if (ans == 'S') {
            Ai ai = new Ai();
            Player ai_player = ai;   // Exemplo de polimorfismo.

            // ATENÇÃO O JOGADOR ARTIFICIAL PRECISARÁ SEMPRE SER O SEGUNDO ARGUMENTO DOS MÉTODOS
            p2 = ai_player;
            // System.out.println(p1.getIsAi());
        }
        else {
            p2 = new Player();
            p2.setName();
        }

        Rules game = new Rules(p1, p2);

        do {
            game.printBoard();
            while(game.getGameStatus()) {

                // Jogador 1 faz sua jogada e é verificado se ele ganhou.
                while(!game.makeMove(p1));

                if(!game.validate(p1, p2)) {
                    game.printBoard();
                    break;
                }

                game.printBoard();

                // Jogador 2 faz sua jogada e é verificado se ele ganhou.
                while(!game.makeMove(p2));

                if(!game.validate(p1, p2)) {
                    game.printBoard();
                    break;
                }

                game.printBoard();
            }

            Scanner sc2 = new Scanner(System.in);
            System.out.printf("Continue? [s/N] ");
            char ans2 = sc2.next().charAt(0);

            if (ans2 == 'S')
                game.setGameStatus(true);

            game.resetBoard();

        } while(game.getGameStatus());
    }
}
