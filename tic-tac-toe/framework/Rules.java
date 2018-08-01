/*******************************************************************************
* Copyright (c) 2018, Gabriel M. Trettel, Eric S. Karbstein,
* Lucas Z. de Oliveira, Daniel de O. Montenegro
* All rights reserved.
*
* This Source Code Form is subject to the terms of the BSD 3-Clause License.
*******************************************************************************/

public class Rules {
    private boolean game_status;
    private Board board;
    private Player px, py;


    public Rules(Player px, Player py) {
        board = new Board();
        this.px = px;
        this.py = py;
        game_status = true;
    }


    public boolean getGameStatus() {
        return this.game_status;
    }

    public Board getBoard() {
        return this.board;
    }

    public Player getFirstPlayer() {
        if (py.getLabel() == 'X')
            return py;
        else
            return px;
    }

    public Player getSecondPlayer() {
        if (px.getLabel() == 'O')
            return px;
        else
            return py;
    }

    public Player getHumanObj() {
        if (!px.IsAi())
            return px;
        else if (!py.IsAi())
            return py;

        return null;
    }

    public Player getAiObj() {
        if (px.IsAi())
            return px;
        else if (py.IsAi())
            return py;

        return null;
    }

    public boolean takeMoveFromPlayer(Player p) {
        int[] cord = p.getMove(this);

        if (!checkMove( board, cord[0], cord[1] ))
            return false;

        board.setMove(p, cord[0], cord[1]);
        return true;
    }

    private boolean checkMove(Board board, int cordX, int cordY) {
        if (board.getCell(cordX, cordY).getID() != 0)
            return false;
        else
            return true;
    }

    public boolean validate(Player px, Player py) {
        Player winner = this.checkWinner();
        if (winner != null) {
            congratPlayer(winner);
            ScoreBoard.setScore(winner.getLabel());
            return false;
        }

        if (board.getFreeSpace() == 0) {
            this.game_status = false;
            return false;
        }

        return true;
    }

    private void congratPlayer(Player p) {
        System.out.printf("O jogador %s ganhou o jogo!\n", p.getName());
    }

    public void printConsole() {
        board.printGame(this.px, this.py);
    }

    // Apaga as linhas já escritas no console.
    public static void eraseConsole() {
        for (int x=0; x<8; ++x)
            System.out.print("\33[1A\33[2K");
    }

    // Apaga uma quantidade 'i' de linhas já escritas no console.
    public static void eraseConsole(int i) {
        for (int x=0; x<i; ++x)
            System.out.print("\33[1A\33[2K");
    }

    public Player checkWinner() {
        int col = checkColumns();
        int lin = checkLines();
        int dia = checkDiagonals();

        if (col == px.getID() || lin == px.getID() || dia == px.getID())
            return px;
        else if (col == py.getID() || lin == py.getID() || dia == py.getID())
            return py;
        else
            return null;
    }

    private int checkColumns() {
        if (board.getCell(0,0).getID() == board.getCell(1,0).getID() && board.getCell(1,0).getID() == board.getCell(2,0).getID())
            return board.getCell(0,0).getID();

        else if (board.getCell(0,1).getID() == board.getCell(1,1).getID() && board.getCell(1,1).getID() == board.getCell(2,1).getID())
            return board.getCell(0,1).getID();

        else if (board.getCell(0,2).getID() == board.getCell(1,2).getID() && board.getCell(1,2).getID() == board.getCell(2,2).getID())
            return board.getCell(0,2).getID();

        return -1;
    }

    private int checkLines() {
        if (board.getCell(0,0).getID() == board.getCell(0,1).getID() && board.getCell(0,1).getID() == board.getCell(0,2).getID())
            return board.getCell(0,0).getID();

        else if (board.getCell(1,0).getID() == board.getCell(1,1).getID() && board.getCell(1,1).getID() == board.getCell(1,2).getID())
            return board.getCell(1,0).getID();

        else if (board.getCell(2,0).getID() == board.getCell(2,1).getID() && board.getCell(2,1).getID() == board.getCell(2,2).getID())
            return board.getCell(2,0).getID();

        return -1;
    }

    private int checkDiagonals() {
        if (board.getCell(0,0).getID() == board.getCell(1,1).getID() && board.getCell(1,1).getID() == board.getCell(2,2).getID())
            return board.getCell(0,0).getID();

        else if (board.getCell(0,2).getID() == board.getCell(1,1).getID() && board.getCell(1,1).getID() == board.getCell(2,0).getID())
            return board.getCell(0,2).getID();

        return -1;
    }

}
