public class Rules {
    private boolean game_status;
    private Board board;
    private Player p1, p2;

    public Rules(Player p1, Player p2) {
        board = new Board(p1, p2);
        this.p1 = p1;
        this.p2 = p2;
        game_status = true;
    }

    public boolean getGameStatus() {
        return this.game_status;
    }

    // Efetua a jogada que o usuário está tentando fazer
    public boolean makeMove(Player p) {
        int[] cord = p.getMove(board);

        // Verifica se podemos realizar a jogada desejada
        if(!checkMove( board, cord[0], cord[1] )) {
            return false;
        }

        board.setMove(p, cord[0], cord[1]);
        return true;
    }

    // Verifica se a jogada que o usuário pretende fazer é válida
    private boolean checkMove(Board board, int cordX, int cordY) {
        if( board.getCell(cordX, cordY) != 0 )
            return false;
        else
            return true;
    }


    // Valida se algém ganhou ou se deu velha
    public boolean validate(Player p1, Player p2) {
        Player winner = board.checkWinner();

        if(board.getFreeSpace() == 0 ) {
            this.game_status = false;
            return false;
        }

        if( winner != null ) {
            congratPlayer(winner);
            return false;
        } else
            return true;
    }



    private void congratPlayer(Player p) {
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.printf("O jogador %s ganhou o jogo!\n\n\n\n\n\n\n\n\n\n\n", p.getName());
    }


    public void printBoard() {
        board.printGame(this.p1, this.p2);
    }


}
