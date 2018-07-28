import java.util.Random;

public class Ai extends Player {
    // this.level pertence ao intervalo [0, 3]
    private int level;
    private Random generator;

    public Ai() {
        this.is_ai = true;
        this.name = "Chuck Norris";
        this.level = 0;
        this.generator = new Random();
    }
    // Sobrescrita de método para se aproveitar do polimorfismo de player e Ai
    @Override
    public void setName() {
        //TODO
        // Scanner sc1 = new Scanner(System.in);
        // System.out.printf("Escolha com quem você quer jogar %d: ", player_id);
        // name = sc1.nextLine();

    @Override
    public int[] getMove(Board board) {
        float probability = this.generator.nextFloat();
        int[] moves;

        if(this.level > probability || this.level == 20)
            moves = board.getFreeCells()[ this.generator.nextInt( board.getFreeSpace()-1 ) ];
        else
            moves = thinkBestMove(board);


        return moves;

    }

}

    public void setLevel(int x) {
        int lvl;
        Scanner sc1 = new Scanner(System.in);

        do {
            System.out.printf("Escolha o nível que voce quer jogar \n 0 -  Impossível  |  1 - Dificil  |  2 - Médio  |  3 - Fácil  |  4 - Modo hu3");
            lvl = sc1.nextInt();
            System.out.print("\33[1A\33[2K");
        } while(lvl < 0 || lvl > 4);

        this.level = lvl*5;
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
