/*******************************************************************************
* Copyright (c) 2018, Gabriel M. Trettel, Eric S. Karbstein,
* Lucas Z. de Oliveira, Daniel de O. Montenegro
* All rights reserved.
*
* This Source Code Form is subject to the terms of the BSD 3-Clause License.
*******************************************************************************/

import java.util.Random;
import java.util.Scanner;

public class Ai extends Player {
    private int level;
    private Random generator;
    private String name;


    public Ai(Player p) {
        this.is_ai = true;
        this.level = 0;
        this.generator = new Random();
        this.setLabel(p);
        this.setLevel();
        this.setName();
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int[] getMove(Rules game) {
        Board board = game.getBoard();

        float probability = this.generator.nextFloat();
        int[] moves;

        if ((this.level*5) > probability*15 || (this.level*5) == 20)
            moves = board.getFreeCells()[ this.generator.nextInt(board.getFreeSpace()) ];
        else
            moves = thinkBestMove(game);

        return moves;
    }

    @Override
    public void setName() {
        if (this.level == 0)
            this.name = "Chuck Norris";
        else if ( this.level == 1)
            this.name = "Rick Sanches";
        else if ( this.level == 2)
            this.name = "Morty Smith";
        else if ( this.level == 3)
            this.name = "Mr. Poopybutt";
        else if ( this.level == 4)
            this.name = "Jerry Smith";
    }

    private void setLevel() {
        int lvl;
        Scanner sc1 = new Scanner(System.in);

        do {
            System.out.printf("Escolha o nível que voce quer jogar \n 0 -  Impossível  |  1 - Difícil  |  2 - Médio  |  3 - Fácil  |  4 - Aleatório\n");
            lvl = sc1.nextInt();
            Rules.eraseConsole(3);
        } while (lvl < 0 || lvl > 4);

        this.level = lvl;
    }

    /*
    *      O array é embaralhado para aumentar a quantidade de jogadas distintas
    *  entre as partidas. Isto acontece pois em estados mais iniciais algumas
    *  jogadas tem o mesmo score, sendo o best_score a primeira jogada recebida
    *  que possui este valor.
    */
    private int[][] shuffleArray(int[][] arr) {
        for(int i=0; i<arr.length; i++) {
            int index = generator.nextInt(i+1);

            int[] aux = arr[index];
            arr[index] = arr[i];
            arr[i] = aux;
        }
        return arr;
    }

    private int[] thinkBestMove(Rules game) {
        Board board = game.getBoard();

        int[][] avaliableCells = shuffleArray(board.getFreeCells());
        int[] best_move = {-1, -1};
        Board new_board = board;
        int best_score = -1000;

        for (int[] move : avaliableCells) {
            new_board.setMove(game.getAiObj(), move[0], move[1]);
            int move_score = minmax(game, 0, false);
            new_board.resetMove(move[0], move[1]);

            if (move_score > best_score) {
                best_move[0] = move[0];
                best_move[1] = move[1];
                best_score = move_score;
            }
        }

        return best_move;
    }

    private int currScore(Rules game) {
        if (game.checkWinner() == null)
            return 0;

        if (game.checkWinner().IsAi() == true)
            return 10;
        else
            return -10;
    }

    private int minmax(Rules game, int depth, boolean Ai_turn) {
        Board board = game.getBoard();

        int score = currScore(game);

        /*
         *      A profundidade é levada em conta para jogadas, mesmo que perdedoras,
         *  desta forma, escolhemos sempre a que ganha mais rápido ou a que perde
         *  mais devagar. Este comportamento é desejável para ganharmos de forma
         *  mais eficiente ou então para carregarmos o jogo por mais tempo mesmo
         *  que a derrota seja inevitável
         */

        if (score == 10 || score == -10)
            return score - depth;

        if (board.getFreeSpace() == 0)
            return 0 - depth;

        if (Ai_turn) {
            int best = -1000;

            for (int x=0; x<3; ++x) {
                for (int y=0; y<3; ++y) {
                    if (board.getCell(x, y).isEmpty()) {
                        board.setMove(game.getAiObj(), x, y);
                        best = Math.max(minmax(game, depth+1, !Ai_turn), best);
                        board.resetMove(x, y);
                    }
                }
            }
            return best;

        } else {
            int best = 1000;

            for (int x=0; x<3; ++x) {
                for (int y=0; y<3; ++y) {
                    if (board.getCell(x, y).isEmpty()) {
                        board.setMove(game.getHumanObj(), x, y);
                        best = Math.min(minmax(game, depth+1, !Ai_turn), best);
                        board.resetMove(x, y);
                    }
                }
            }
            return best;
        }
    }

}
