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
        Player p2 = new Player();
        p2.setName();

        Ai ai = new Ai();
        Player ai_player = ai;   // Exemplo de polimorfismo

        Rules game = new Rules(p1, p2);

        game.printBoard();

        while(game.getGameStatus()) {

            // Jogador 1 faz sua jogada e é verificado se ele ganhou.
            while(!game.makeMove(p1));

            if(!game.validate(p1)) {
                game.printBoard();
                break;
            }

            game.printBoard();


            // Jogador 2 faz sua jogada e é verificado se ele ganhou.
            while(!game.makeMove(p2));

            if(!game.validate(p2)) {
                game.printBoard();
                break;
            }

            game.printBoard();

        }
    }
}
