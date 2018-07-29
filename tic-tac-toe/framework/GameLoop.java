import java.util.Scanner;

class GameLoop {
    private static Scanner sc = new Scanner(System.in);

    public static boolean askIfAi() {
        System.out.printf("Jogar contra computador? [s/N] ");
        char ans = sc.next().toLowerCase().charAt(0);
        System.out.print("\33[1A\33[2K");

        if (ans == 's')
            return true;
        else
            return false;
    }

    public static boolean askIfContinue() {
        System.out.printf("Continue? [s/N] ");
        char ans = sc.next().toLowerCase().charAt(0);
        System.out.print("\33[1A\33[2K");

        if (ans == 's')
            return true;
        else
            return false;
    }

    public static boolean askIfSamePlayers() {
        System.out.printf("São os mesmos jogadores? [s/N] ");
        char ans = sc.next().toLowerCase().charAt(0);
        System.out.print("\33[1A\33[2K");

        if (ans == 's')
            return true;
        else {
            ScoreBoard.resetScore();
            return false;
        }
    }

    public static Player[] setPlayers() {
        Player px, py;
        if (askIfAi()) {
            px = new Player();
            px.setName();
            px.setLabel();

            Ai ai = new Ai(px);
            py = ai;
        } else {
            px = new Player();
            px.setName();
            px.setLabel();

            py = new Player();
            py.setName();
            py.setLabel(px);
        }

        Player[] players = {px, py};
        return players;
    }

    public static void runGame(Rules game) {
        Player px = game.getFirstPlayer();
        Player py = game.getSecondPlayer();

        while (game.getGameStatus()) {
            game.printBoard();

            // Jogador 1 faz a sua jogada.
            while (!game.makeMove(px));

            game.eraseBoard();
            game.printBoard();
            // Verifica se jogador 1 ganhou.
            if (!game.validate(px, py)) {
                break;
            }

            game.eraseBoard();
            game.printBoard();

            // Jogador 2 faz sua jogada.
            while (!game.makeMove(py));

            game.eraseBoard();
            game.printBoard();
            // Verifica se jogador 2 ganhou.
            if (!game.validate(px, py)) {
                break;
            }

            game.eraseBoard();
        }
    }

    public static void main(String[] args) {
        Player[] ps = setPlayers();
        boolean status = true;

        do {
            Rules game = new Rules(ps[0], ps[1]);

            runGame(game);

            status = askIfContinue();
            game.eraseBoard(9);

            if(status && !askIfSamePlayers())
                ps = setPlayers();
        } while (status);
    }

}
