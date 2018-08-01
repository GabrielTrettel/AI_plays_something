/*******************************************************************************
* Copyright (c) 2018, Gabriel M. Trettel, Eric S. Karbstein,
* Lucas Z. de Oliveira, Daniel de O. Montenegro
* All rights reserved.
*
* This Source Code Form is subject to the terms of the BSD 3-Clause License.
*******************************************************************************/

import java.util.Scanner;

class GameLoop {
    private static Scanner sc = new Scanner(System.in);

    public static boolean askIfAi() {
        System.out.printf("Jogar contra computador? [s/N] ");
        char ans = sc.next().toLowerCase().charAt(0);
        Rules.eraseConsole(1);

        if (ans == 's')
            return true;
        else
            return false;
    }

    public static boolean askIfContinue() {
        System.out.printf("Continue? [s/N] ");
        char ans = sc.next().toLowerCase().charAt(0);
        Rules.eraseConsole(1);

        if (ans == 's')
            return true;
        else
            return false;
    }

    public static boolean askIfSamePlayers() {
        System.out.printf("São os mesmos jogadores? [s/N] ");
        char ans = sc.next().toLowerCase().charAt(0);
        Rules.eraseConsole(1);

        if (ans == 's')
            return true;
        else {
            ScoreBoard.resetScore();
            return false;
        }
    }

    public static Player[] setPlayers() {
        Player px, py;
        if (askIfAi()) {
            px = new Player();
            px.setName();
            px.setLabel();

            Ai ai = new Ai(px);
            py = ai;
        } else {
            px = new Player();
            px.setName();
            px.setLabel();

            py = new Player();
            py.setName();
            py.setLabel(px);
        }

        Player[] players = {px, py};
        return players;
    }

    public static void runGame(Rules game) {
        Player px = game.getFirstPlayer();
        Player py = game.getSecondPlayer();

        while (game.getGameStatus()) {
            game.printConsole();

            // Jogador 1 faz a sua jogada.
            while (!game.takeMoveFromPlayer(px));

            game.eraseConsole();
            game.printConsole();

            // Verifica se jogador 1 ganhou.
            if (!game.validate(px, py))
                break;

            game.eraseConsole();
            game.printConsole();

            // Jogador 2 faz sua jogada.
            while (!game.takeMoveFromPlayer(py));
            game.eraseConsole();
            game.printConsole();

            // Verifica se jogador 2 ganhou.
            if (!game.validate(px, py))
                break;

            game.eraseConsole();
        }
    }

    public static void main(String[] args) {
        Player[] ps = setPlayers();
        boolean status = true;

        do {
            Rules game = new Rules(ps[0], ps[1]);

            runGame(game);

            status = askIfContinue();

            // Limpa o console após o termino da rodada do jogo.
            game.eraseConsole(9);

            if(status && !askIfSamePlayers())
                ps = setPlayers();

        } while (status);
    }

}
