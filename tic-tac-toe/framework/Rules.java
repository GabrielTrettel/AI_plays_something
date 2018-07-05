class Rules {
    private boolean game_status;
    private Board t;
    private Player j1, j2;

    public Rules(Player j1, Player j2) {
        t = new Board();
        this.j1 = j1;
        this.j2 = j2;
        game_status = true;
    }

    public boolean getGameStatus() {
        return this.game_status;
    }

    // Efetua a jogada que o usuário está tentando fazer
    public boolean makeMove(Player j, int cordX, int cordY) {

        // Verifica se podemos realizar a jogada desejada
        if(!checkMove(t, cordX, cordY)) {
            return false;
        }

        t.setMove(j, cordX, cordY);

        return true;
    }

    // Verifica se a jogada que o usuário pretende fazer é válida
    private boolean checkMove(Board t, int cordX, int cordY) {
        if( t.getCell(cordX, cordY) != 0 )
            return false;
        else
            return true;
    }


    // Valida se algém ganhou ou se deu velha
    public boolean validate(Player j1) {
        int col = t.checkColumns(j1);
        int diag = t.checkDiagonals(j1);
        int lin = t.checkLines(j1);

        if( t.getFreeSpace() == 0 ) {
            this.game_status = false;
            return false;
        }

        if( col != -1 ) {
            congratPlayer(col, j1);
            return false;
        } else if( lin != -1 ) {
            congratPlayer(lin, j1);
            return false;
        }else if( diag != -1 ) {
            congratPlayer(diag, j1);
            return false;
        }

        return true;
    }



    private void congratPlayer(int x, Player j1) {
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.print("\33[1A\33[2K");
        System.out.printf("O jogador %s ganhou o jogo!\n\n\n\n\n\n\n\n\n\n\n", j1.getName());
    }


    public void printBoard() {
        t.printGame(this.j1, this.j2);
    }


}
