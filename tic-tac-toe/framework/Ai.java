public class Ai extends Player {
    public Ai() {
        this.is_ai = true;
        this.name = "Chuck Norris";
    }
    // Sobrescrita de método para se aproveitar do polimorfismo de player e Ai
    //@Overide
    public int[] getMove(Board board) {
        int[] moves = thinkBestMove(board);
        return moves;
    }
    //@Overide
    public void setName() {
        //TODO
        // Scanner sc1 = new Scanner(System.in);
        // System.out.printf("Escolha com quem você quer jogar %d: ", player_id);
        // name = sc1.nextLine();
    }
    private int[] thinkBestMove(Board board) {
        int[][] avaliableCells = board.getFreeCells();
        int[] best_move = {-1, -1};
        Board new_board = board;
        int best_score = -1000;



        for(int[] move : avaliableCells) {
            new_board.setMove(true, move[0], move[1]);

            int move_score = minmax(new_board, 0, false);

            new_board.resetMove(move[0], move[1]);

            if(move_score > best_score) {
                best_move[0] = move[0];
                best_move[1] = move[1];
                best_score = move_score;
            }
        }

        return best_move;
    }
    private int currScore(Board board) {

        if(board.checkWinner() == null || board.getFreeSpace() == 0)
            return 0;

        if( board.checkWinner().IsAi() == true )
            return 10;
        else
            return -10;

    }


    private int minmax(Board nboard, int depth, boolean is_max) {
        Board board = nboard;

        int score = currScore(board);

        if(score == 10 || score == -10)
            return score - depth;
        if(board.getFreeSpace() == 0)
            return 0 - depth;

        if(is_max) {
            int best = -1000;

            for(int x=0; x<3; ++x) {
                for(int y=0; y<3; ++y) {
                    if(board.getCell(x, y).isEmpty()) {
                        board.setMove(true, x, y);
                        best = Math.max(minmax(board, depth+1, !is_max), best);
                        board.resetMove(x, y);
                    }
                }
            }
            return best;

        } else {
            int best = 1000;

            for(int x=0; x<3; ++x) {
                for(int y=0; y<3; ++y) {
                    if(board.getCell(x, y).isEmpty()) {
                        board.setMove(false, x, y);
                        best = Math.min(minmax(board, depth+1, !is_max), best);
                        board.resetMove(x, y);
                    }
                }
            }
            return best;
        }
    }
}
