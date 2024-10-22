/*******************************************************************************
* Copyright (c) 2018, Gabriel M. Trettel, Eric S. Karbstein,
* Lucas Z. de Oliveira, Daniel de O. Montenegro
* All rights reserved.
*
* This Source Code Form is subject to the terms of the BSD 3-Clause License.
*******************************************************************************/

import java.util.Scanner;

public class Player {
    protected String name;
    protected int player_id;
    protected boolean is_ai;
    protected char label;
    private static int global_id = 1;


    public Player() {
        this.is_ai = false;
        this.name = "Random";
        this.player_id = returnID();
    }


    private static int returnID() {
        int aux = global_id;
        global_id = global_id+1;
        return aux;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.player_id;
    }

    public boolean IsAi() {
        return this.is_ai;
    }

    public char getLabel() {
        return this.label;
    }

    public int[] getMove(Rules game) {
        int[] moves = new int[2];
        moves[0] = this.getMoveX();
        moves[1] = this.getMoveY();

        return moves;
    }

    private int getMoveX() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("%s escolha a linha: ", this.name);
        int a = sc1.nextInt();
        Rules.eraseConsole(1);

        while (a>2 || a<0) {
            System.out.printf("Jogada inválida. %s, por favor, repita o movimento desejado\n", this.getName());
            a = sc1.nextInt();
            Rules.eraseConsole(2);
        }
        return a;
    }

    private int getMoveY() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("%s escolha a coluna: ", this.name);
        int a = sc1.nextInt();
        Rules.eraseConsole(1);

        while (a>2 || a<0) {
            System.out.printf("Jogada inválida. %s, por favor, repita o movimento desejado\n", this.getName());
            a = sc1.nextInt();
            Rules.eraseConsole(2);
        }
        return a;
    }

    public void setName() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("Entre com o nome do jogador %d: ", this.player_id);
        this.name = sc1.nextLine();
        Rules.eraseConsole(1);
    }

    public void setLabel() {
        Scanner sc1 = new Scanner(System.in);
        System.out.printf("Jogador %d: X ou O? ", this.player_id);
        this.label = sc1.next().toUpperCase().charAt(0);
        Rules.eraseConsole(1);

        while (this.label != 'X' && this.label != 'O') {
            System.out.printf("Peça inválida. %s, por favor, escolha novamente\n", this.getName());
            this.label = sc1.next().toUpperCase().charAt(0);
            Rules.eraseConsole(2);
        }
    }

    public void setLabel(Player p) {
        if (p.getLabel() == 'X')
            this.label = 'O';
        else
            this.label = 'X';
    }

}
