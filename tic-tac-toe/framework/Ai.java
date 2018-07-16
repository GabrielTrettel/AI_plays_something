import java.util.*;
// import java.lang.Math;
	/*
		Aqui queremos que a IA herde todos os atributos e comportamentos
	de Player, já que a IA é um player. Para isso usamos o extends
	que faz justamente essa extensão. A classe Player possui métodos
	para pegar os movimentos de linha e coluna (getMovesX e getMovesY)
	que precisarão ser reescritos nessa classe já que a IA não faz
	a jogada por input de teclado. Tirando isso, todos os outros atributos
	e métodos se manterão, em que o atributo nome será preenchido de acordo com
	a dificuldade (Pai do morty, Morty, ...). Dessa forma a classe Rules funcionará
	para ambas as classes Player e AI. Reforçando a idéia: Ai É um Player, pelas
	características de herança.



	Atributos herdados:
		protected String name;
		protected int wins;     //TODO
		protected int losses;     //TODO
		protected int player_id;
		protected static int global_id = 1;
		protected boolean is_ai;

	Métodos herdados:
		public Player()
		public void setName()
		public int getWins()
		public int getLosses()
		public int getID()
		public String getName()

	Métodos sobrescritos (polimorfismo):
		public int[] getMove(Board board)
		public void setName()

	*/


public class Ai extends Player {
	private int LOSS = -1000;
	private int WIN = 1000;
	private int TIE = 0;

	public Ai() {
		this.is_ai = true;
		this.name = "Pai do Morty";
	}

	// Sobrescrita de método para se aproveitar do polimorfismo de player e Ai
	//@Overide
	public void setName() {
		//TODO
		// Scanner sc1 = new Scanner(System.in);
		// System.out.printf("Escolha com quem você quer jogar %d: ", player_id);
		// name = sc1.nextLine();
	}

	//@Overide
	public int[] getMove(Board board) {
		int[] out = new int[3];
		int[] best_move = new int[2];
		Board new_board = board;
		new_board.getFreeCells();
		// ArrayList<int[]> avaliableCells = board.getFreeCells();
		//
		// for(int[] move : avaliableCells)
		// 	System.out.printf("%d %d\n", move[0], move[1]);

		out = minmax(new_board, 0, true, LOSS, WIN);
		best_move[0] = out[0];
		best_move[1] = out[1];
		//
		// best_move[0] = 2;
		// best_move[1] = 2;
		return best_move;
	}

	private int currScore(Board board) {
		Player winner = board.checkWinner();

		if( winner != null )
			if( winner.getIsAi() == true)
				return WIN;
			else
				return LOSS;
		else
			return TIE;
	}

	private int[] minmax(Board board, int depth, boolean is_ai, int alpha, int beta) {
		int best_score = (is_ai == true) ? LOSS : WIN;
		int[] best_move = {-1, -1};
		int[] output = new int[3];

		int score_base = currScore(board);
		if(board.getFreeSpace() == 0 || score_base != 0) {
			output[0] = best_move[0];
			output[1] = best_move[1];
			output[2] = score_base;
			return output;
		}

		ArrayList<int[]> avaliableCells = board.getFreeCells();

		for(int[] move : avaliableCells) {

			int[] aux_move = move;
			Player px = (is_ai) ? this : board.getHumanObj();
			board.setMove(px, move[0], move[1]);

			if(is_ai) {
				int score = minmax(board, depth+1, !is_ai, alpha, beta)[2];

				if(best_score < score) {
					best_score = score - depth * 10;
					best_move = move;
				}
				alpha = Math.max(alpha, best_score);
				board.resetMove(move[0], move[1]);
				if(beta <= alpha)
					break;

			} else {
				int score = minmax(board, depth+1, !is_ai, alpha, beta)[2];

				if(best_score > score) {
					best_score = score + depth * 10;
					best_move = move;
				}
				beta = Math.min(beta, best_score);
				board.resetMove(move[0], move[1]);
				if(beta <= alpha)
					break;
			}
		}
		output[0] = best_move[0];
		output[1] = best_move[1];
		output[2] = score_base;
		return output;
	}
}
