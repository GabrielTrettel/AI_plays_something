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
		ArrayList<int[]> avaliableCells = board.getFreeCells();
		int[] best_move = {-1, -1};
		Board new_board = board;
		int best_score = -1000;

		for(int[] move : avaliableCells) {
			new_board.setMove(this, move[0], move[1]);

			int move_score = minmax(new_board, 0, false);

			new_board.resetMove(move[0], move[1]);

			if(move_score > best_score) {
				best_move = move;
				best_score = move_score;
			}
		}

		return best_move;
	}

	private int currScore(Board board) {
		Player winner = board.checkWinner();

		if( winner != null )
			if( winner.getIsAi() == true)
				return 10;
			else
				return -10;
		else
			return 0;
	}

	private int minmax(Board board, int depth, boolean is_max) {
		int score = currScore(board);

		if(score == 10 || score == -10)
			return score - depth;
		if(board.getFreeSpace() == 0)
			return 0 - depth;

		if(is_max) {
			int best = -1000;

			for(int x=0; x<3; ++x) {
				for(int y=0; y<3; ++y) {
					if(board.getCell(x, y) == 0) {
						board.setMove(this, x, y);
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
					if(board.getCell(x, y) == 0) {
						board.setMove(board.getHumanObj(), x, y);       // Isso ta horrível, mds...
						best = Math.min(minmax(board, depth+1, !is_max), best);
						board.resetMove(x, y);
					}
				}
			}
			return best;
		}
	}
}
