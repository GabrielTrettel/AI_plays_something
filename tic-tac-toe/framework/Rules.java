public class Rules {
    private boolean game_status;
    private Board board;
    private Player px, py;


    public Rules(Player px, Player py) {
        board = new Board(px, py);
        this.px = px;
        this.py = py;
        game_status = true;
    }


    public boolean getGameStatus() {
        return this.game_status;
    }

    // Bizarro, mas funciona xD
    // Descobre quem vai primeiro
    public Player getFirstPlayer() {
        if (py.getLabel() == 'X') {
            return py;
        }
        else
            return px;
    }

    // Descobre quem vai segundo
    public Player getSecondPlayer() {
        if (px.getLabel() == 'O') {
            return px;
        }
        else
            return py;
    }

    // Efetua a jogada que o usuário está tentando fazer
    public boolean makeMove(Player p) {
        int[] cord = p.getMove(board);

        // Verifica se podemos realizar a jogada desejada
        if (!checkMove( board, cord[0], cord[1] ))
            return false;

        board.setMove(p, cord[0], cord[1]);
        return true;
    }

    // Verifica se a jogada que o usuário pretende fazer é válida
    private boolean checkMove(Board board, int cordX, int cordY) {
        if (board.getCell(cordX, cordY).getID() != 0)
            return false;
        else
            return true;
    }

    public void eraseBoard() {
        // Apaga as linhas já escritas no console
        for (int x=0; x<8; ++x)
            System.out.print("\33[1A\33[2K");
    }

    public void eraseBoard(int i) {
        // Apaga as linhas já escritas no console
        for (int x=0; x<i; ++x)
            System.out.print("\33[1A\33[2K");
    }

    // Valida se algém ganhou ou se deu velha
    public boolean validate(Player px, Player py) {
        Player winner = board.checkWinner();

        if (board.getFreeSpace() == 0) {
            this.game_status = false;
            return false;
        }

        if (winner != null) {
            congratPlayer(winner);
            ScoreBoard.setScore(winner.getLabel());
            return false;
        } else
            return true;
    }

    private void congratPlayer(Player p) {
        System.out.printf("O jogador %s ganhou o jogo!\n", p.getName());
    }

    public void printBoard() {
        board.printGame(this.px, this.py);
    }

}
